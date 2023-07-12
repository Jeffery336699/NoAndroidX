package com.circle.drawable;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.circle.drawable.activity.AIDLStudyActivity;
import com.circle.drawable.activity.ArcMenuActivity;
import com.circle.drawable.activity.BadgeActivity;
import com.circle.drawable.activity.BounceScrollActivity;
import com.circle.drawable.activity.Byte2StrActivity;
import com.circle.drawable.activity.ChatRobotActivity;
import com.circle.drawable.activity.CommonAdapterActivity;
import com.circle.drawable.activity.CompletionServiceActivity;
import com.circle.drawable.activity.ConfigChangesTestActivity;
import com.circle.drawable.activity.CustomCircleActivity;
import com.circle.drawable.activity.CustomDialogActivity;
import com.circle.drawable.activity.CustomDrawableActivity;
import com.circle.drawable.activity.CustomFlowLayoutActivity;
import com.circle.drawable.activity.CustomImageViewActivity;
import com.circle.drawable.activity.CustomProgressActivity;
import com.circle.drawable.activity.CustomTextViewActivity;
import com.circle.drawable.activity.CustomViewGroupActivity;
import com.circle.drawable.activity.CustomViewPagerTranslateActivity;
import com.circle.drawable.activity.CustomVolumnActivity;
import com.circle.drawable.activity.DesignModeActivity;
import com.circle.drawable.activity.DialogFragmentActivity;
import com.circle.drawable.activity.DrawableActivity;
import com.circle.drawable.activity.FixProblemsActivity;
import com.circle.drawable.activity.FragmentRetainDataActivity;
import com.circle.drawable.activity.GestureLockActivity;
import com.circle.drawable.activity.HorizontalScrollActivity;
import com.circle.drawable.activity.ImageLoaderActivity;
import com.circle.drawable.activity.ImageScaleActivity;
import com.circle.drawable.activity.InflateActivity;
import com.circle.drawable.activity.JavaConcurrentActivity;
import com.circle.drawable.activity.MultiDownloadActivity;
import com.circle.drawable.activity.NOAidlStudyActivity;
import com.circle.drawable.activity.PinYinDemoActivity;
import com.circle.drawable.activity.PortraitListViewActivity;
import com.circle.drawable.activity.PortraitScrollActivity;
import com.circle.drawable.activity.PropertyAnim2Activity;
import com.circle.drawable.activity.PropertyAnimActivity;
import com.circle.drawable.activity.QQListviewActivity;
import com.circle.drawable.activity.RecycleGalleryActivity;
import com.circle.drawable.activity.SavedInstanceStateUsingActivity;
import com.circle.drawable.activity.ScheduledExecutor2TimerMainActivity;
import com.circle.drawable.activity.ThemeActivity;
import com.circle.drawable.activity.TransitionManagerActivity;
import com.circle.drawable.activity.TransitionManagerXmlActivity;
import com.circle.drawable.activity.VerticalScrollActivity;
import com.circle.drawable.activity.ViewGroupTouchEventActivity;
import com.circle.drawable.activity.ViewTouchEventActivity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MenuListActivity extends ListActivity {

    private final Map<String, Class<? extends Activity>> map = new LinkedHashMap<String, Class<? extends Activity>>() {
        {
            put("Drawable定制化", DrawableActivity.class);
            put("Drawable自定义属性", CustomDrawableActivity.class);
            put("TransitionManager动画API使用", TransitionManagerActivity.class);
            put("TransitionManager【xml】转场动画使用", TransitionManagerXmlActivity.class);
            put("QQListView删除", QQListviewActivity.class);
            put("Theme自定义Activity间的切换动画", ThemeActivity.class);
            put("利用Activity创建Dialog", CustomDialogActivity.class);
            put("汉语转拼音工具类", PinYinDemoActivity.class);
            put("图片点击缩放效果", ImageScaleActivity.class);
            put("字节数组转字符串", Byte2StrActivity.class);
            put("仿垂直滚动ViewPager", VerticalScrollActivity.class);
            put("设计模式合集", DesignModeActivity.class);
            put("自定义View-TextView", CustomTextViewActivity.class);
            put("自定义View-ImageView", CustomImageViewActivity.class);
            put("自定义View-Progress", CustomProgressActivity.class);
            put("自定义View-音量", CustomVolumnActivity.class);
            put("自定义View-圆形圆角图片", CustomCircleActivity.class);
            put("自定义View-滑动Tab+Barge装饰消息通知", BadgeActivity.class);
            put("Java并发系列", JavaConcurrentActivity.class);
            put("多线程下载", MultiDownloadActivity.class);
            put("SchedulerService优于Timer三点概述", ScheduledExecutor2TimerMainActivity.class);
            put("批量执行任务，且携带返回结果的案例", CompletionServiceActivity.class);
            put("自定义View-回弹滚动ScrollerView", BounceScrollActivity.class);
            put("自定义View-手势锁控件", GestureLockActivity.class);
            put("自定义View-卫星菜单", ArcMenuActivity.class);
            put("DialogFragment 创建对话框", DialogFragmentActivity.class);
            put("数据状态保留(少量数据)-SavedInstanceState", SavedInstanceStateUsingActivity.class);
            put("数据状态保留(大的数据 eg.Bitmap)-FragmentRetainData", FragmentRetainDataActivity.class);
            put("数据状态保留-配置configChanges", ConfigChangesTestActivity.class);
            put("数据状态保留-最终方案(Fragment保留Task)", FixProblemsActivity.class);
            put("自定义View-ViewPager切换效果", CustomViewPagerTranslateActivity.class);
            put("属性动画学习-1", PropertyAnimActivity.class);
            put("属性动画学习-2", PropertyAnim2Activity.class);
            put("自定义View-水平滚动画廊效果", HorizontalScrollActivity.class);
            put("自定义View-recycleView优化画廊", RecycleGalleryActivity.class);
            put("LayoutInflate.inflate(resId, root, attachToRoot)深度解析", InflateActivity.class);
            put("自定义View-ViewGroup容器探究", CustomViewGroupActivity.class);
            put("自定义View-FlowLayout流式布局(标签)", CustomFlowLayoutActivity.class);
            put("借助异步消息机制打造图片加载框架", ImageLoaderActivity.class);
            put("aidl Binder框架浅析", AIDLStudyActivity.class);
            put("手撸aidl Binder框架浅析", NOAidlStudyActivity.class);
            put("图灵机器人-聊天", ChatRobotActivity.class);
            put("打造万能的ListView GridView 适配器", CommonAdapterActivity.class);
            put("自定义View-ScrollView纵向滑动", PortraitScrollActivity.class);
            put("自定义View-ListView纵向滑动", PortraitListViewActivity.class);
            put("自定义View-View事件分发", ViewTouchEventActivity.class);
            put("自定义View-ViewGroup事件分发", ViewGroupTouchEventActivity.class);
        }
    };

    private List<String> titles = new ArrayList<>(map.keySet());
    private List<Class<? extends Activity>> targetClazz = new ArrayList<>(map.values());


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<String> items = fillList();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        setListAdapter(adapter);

    }

    private List<String> fillList() {
        return titles;
    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        startActivity(new Intent(this, targetClazz.get(position)));
    }

}
