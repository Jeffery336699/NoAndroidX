package com.circle.drawable.transition;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.os.Build;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.util.Log;
import android.util.Property;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.TextView;

// TextSizeTransition.java
@TargetApi(Build.VERSION_CODES.KITKAT)
public class TextSizeTransition extends Transition {

    private static final String PROPNAME_TEXT_SIZE = "sodaglobaldidifood:transition:textsize";
    private static final String[] TRANSITION_PROPERTIES = {PROPNAME_TEXT_SIZE};

    private static final Property<TextView, Float> TEXT_SIZE_PROPERTY = Property.of(TextView.class,Float.class,"textSize");
    private static final String TAG = "TextSizeTransition";

    public TextSizeTransition() {
    }

    @Override
    public void captureEndValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    @Override
    public void captureStartValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    @Override
    public Animator createAnimator(ViewGroup sceneRoot, TransitionValues startValues,
                                   TransitionValues endValues) {
        if (startValues == null || endValues == null) {
            return null;
        }

        Float startSize = (Float) startValues.values.get(PROPNAME_TEXT_SIZE);
        Float endSize = (Float) endValues.values.get(PROPNAME_TEXT_SIZE);
        Log.i(TAG, "startSize: "+startSize);
        Log.i(TAG, "endSize: "+endSize);
        if (startSize == null || endSize == null ||
                startSize.floatValue() == endSize.floatValue()) {
            return null;
        }

        TextView view = (TextView) endValues.view;

        view.setTextSize(TypedValue.COMPLEX_UNIT_PX, startSize);
        return ObjectAnimator.ofFloat(view, TEXT_SIZE_PROPERTY, startSize, endSize);
    }

    @Override
    public String[] getTransitionProperties() {
        return TRANSITION_PROPERTIES;
    }

    private void captureValues(TransitionValues transitionValues) {
        if (transitionValues.view instanceof TextView) {
            TextView textView = (TextView) transitionValues.view;
            transitionValues.values.put(PROPNAME_TEXT_SIZE, textView.getTextSize());
        }
    }
}
