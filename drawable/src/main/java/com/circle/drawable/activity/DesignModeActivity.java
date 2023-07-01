package com.circle.drawable.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.circle.drawable.R;
import com.circle.drawable.mode.command.Command;
import com.circle.drawable.mode.command.Computer;
import com.circle.drawable.mode.command.ComputerOffCommond;
import com.circle.drawable.mode.command.ComputerOnCommond;
import com.circle.drawable.mode.command.ControlPanel;
import com.circle.drawable.mode.command.Door;
import com.circle.drawable.mode.command.DoorOffCommond;
import com.circle.drawable.mode.command.DoorOnCommond;
import com.circle.drawable.mode.command.Light;
import com.circle.drawable.mode.command.LightOffCommand;
import com.circle.drawable.mode.command.LightOnCommond;
import com.circle.drawable.mode.command.QuickCommand;
import com.circle.drawable.mode.decorator.ArmEquip;
import com.circle.drawable.mode.decorator.BlueGemDecorator;
import com.circle.drawable.mode.decorator.IEquip;
import com.circle.drawable.mode.decorator.RedGemDecorator;
import com.circle.drawable.mode.decorator.ShoeEquip;
import com.circle.drawable.mode.decorator.YellowGemDecorator;
import com.circle.drawable.mode.factroy.abtract.RouJiaMo;
import com.circle.drawable.mode.factroy.method.RoujiaMoStore;
import com.circle.drawable.mode.factroy.method.XianRouJiaMoStore;
import com.circle.drawable.mode.factroy.singleton.Singleton04;
import com.circle.drawable.mode.observer_auth.Observer3;
import com.circle.drawable.mode.observer_auth.SubjectFor3d;
import com.circle.drawable.mode.observer_auth.SubjectForSSQ;
import com.circle.drawable.mode.observer_custom.ObjectFor3D;
import com.circle.drawable.mode.observer_custom.Observer;
import com.circle.drawable.mode.observer_custom.Observer1;
import com.circle.drawable.mode.observer_custom.Observer2;
import com.circle.drawable.mode.strategy.AttackJY;
import com.circle.drawable.mode.strategy.DefendTBS;
import com.circle.drawable.mode.strategy.DisplayA;
import com.circle.drawable.mode.strategy.Role;
import com.circle.drawable.mode.strategy.RoleA;
import com.circle.drawable.mode.strategy.RunJCTQ;

/**
 * 参考：鸿洋大神的博客
 * https://blog.csdn.net/lmj623565791/article/details/24179699?spm=1001.2014.3001.5502
 * https://blog.csdn.net/lmj623565791/article/details/24116745?spm=1001.2014.3001.5502
 * https://blog.csdn.net/lmj623565791/article/details/24460585?spm=1001.2014.3001.5502
 */
public class DesignModeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design_mode);
    }

    public void onStrategy(View view) {
        Role roleA = new RoleA("A");

        roleA.setAttackBehavior(new AttackJY())//
                .setDefendBehavior(new DefendTBS())//
                .setDisplayBehavior(new DisplayA())//
                .setRunBehavior(new RunJCTQ());
        System.out.println(roleA.name + ":");
        roleA.run();
        roleA.attack();
        roleA.defend();
        roleA.display();
    }

    public void onCustomObserver(View view) {
        //模拟一个3D的服务号
        ObjectFor3D subjectFor3d = new ObjectFor3D();
        //客户1
        Observer observer1 = new Observer1(subjectFor3d);
        Observer observer2 = new Observer2(subjectFor3d);

        subjectFor3d.setMsg("20140420的3D号码是：127" );
        subjectFor3d.setMsg("20140421的3D号码是：333" );
    }

    public void onAuthorityObserver(View view) {
        SubjectFor3d subjectFor3d = new SubjectFor3d() ;
        SubjectForSSQ subjectForSSQ = new SubjectForSSQ() ;

        Observer3 observer3 = new Observer3();
        observer3.registerSubject(subjectFor3d);
        observer3.registerSubject(subjectForSSQ);


        subjectFor3d.setMsg("hello 3d'nums : 110 ");
        subjectForSSQ.setMsg("ssq'nums : 12,13,31,5,4,3 15");
    }

    public void onDecorator(View view) {
        // 一个镶嵌2颗红宝石，1颗蓝宝石的靴子
        System.out.println(" 一个镶嵌2颗红宝石，1颗蓝宝石的靴子");
        IEquip equip = new RedGemDecorator(new RedGemDecorator(new BlueGemDecorator(new ShoeEquip())));
        System.out.println("攻击力  : " + equip.caculateAttack());
        System.out.println("描述 :" + equip.description());
        System.out.println("-------");
        // 一个镶嵌1颗红宝石，1颗蓝宝石的武器
        System.out.println(" 一个镶嵌1颗红宝石，1颗蓝宝石,1颗黄宝石的武器");
        equip = new RedGemDecorator(new BlueGemDecorator(new YellowGemDecorator(new ArmEquip())));
        System.out.println("攻击力  : " + equip.caculateAttack());
        System.out.println("描述 :" + equip.description());
        System.out.println("-------");
    }

    public void onFactory(View view) {
        RoujiaMoStore roujiaMoStore = new XianRouJiaMoStore();
        RouJiaMo suanRoujiaMo = roujiaMoStore.sellRouJiaMo("Suan");
        System.out.println(suanRoujiaMo.name);
    }
    // 单例模式
    public void onSingle(){
        Singleton04 instance = Singleton04.getInstance();
        Singleton04 instance2 = Singleton04.getInstance();
        System.out.println(instance == instance2);// true
    }


    public void onCommand(View view) {
        /**
         * 三个家电
         */
        Light light = new Light();
        Door door = new Door();
        Computer computer = new Computer();

        /**
         * 一个控制器，假设是我们的app主界面
         */
        ControlPanel controlPanel = new ControlPanel();
        // 为每个按钮设置功能
        controlPanel.setCommand(0, new LightOnCommond(light));
        controlPanel.setCommand(1, new LightOffCommand(light));
        controlPanel.setCommand(2, new ComputerOnCommond(computer));
        controlPanel.setCommand(3, new ComputerOffCommond(computer));
        controlPanel.setCommand(4, new DoorOnCommond(door));
        controlPanel.setCommand(5, new DoorOffCommond(door));

        // 模拟点击
        controlPanel.keyPressed(0);
        controlPanel.keyPressed(2);
        controlPanel.keyPressed(3);
        controlPanel.keyPressed(4);
        controlPanel.keyPressed(5);
        controlPanel.keyPressed(8);// 这个没有指定，但是不会出任何问题，我们的NoCommand的功劳
    }

    public void onCommandQuick(View view) {
        /**
         * 三个家电
         */
        Light light = new Light();
        Door door = new Door();
        Computer computer = new Computer();

        /**
         * 一个控制器，假设是我们的app主界面
         */
        ControlPanel controlPanel = new ControlPanel();
        // 定义一键搞定模式
        QuickCommand quickCommand = new QuickCommand(new Command[] { new DoorOffCommond(door),
                new LightOffCommand(light), new ComputerOnCommond(computer) });
        System.out.println("****点击一键搞定按钮****");
        controlPanel.setCommand(8, quickCommand);
        controlPanel.keyPressed(8);
    }
}