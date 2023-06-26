package com.circle.drawable.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.transition.ChangeBounds
import android.transition.Scene
import android.transition.TransitionManager
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.circle.drawable.R
import com.circle.drawable.transition.TextSizeTransition
import kotlinx.android.synthetic.main.fragment_first.*

class FirstFragment : Fragment() {

    private var scene1: Scene? = null
    private var scene2: Scene? = null
    private var isScene1: Boolean = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_first.setOnClickListener {
            changeScene()
        }

        //变换前后的两个场景
        scene1 = Scene.getSceneForLayout(fl_scene_root, R.layout.scene_1, context)
        scene2 = Scene.getSceneForLayout(fl_scene_root, R.layout.scene_2, context)

        //默认先展示场景1
        TransitionManager.go(scene1)
        isScene1 = true
    }

    private fun changeScene() {
        val toScene = if (isScene1) scene2 else scene1
        isScene1 = !isScene1
        val changeBounds = ChangeBounds()
        val sizeTransition = TextSizeTransition()
        TransitionManager.go(toScene, changeBounds)
        TransitionManager.go(toScene, sizeTransition)

    }

}