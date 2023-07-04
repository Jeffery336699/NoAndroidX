package com.circle.drawable.mode.facede;

import com.circle.drawable.mode.command.Computer;
import com.circle.drawable.mode.command.Light;

/**
 * 外观模式:
 * 提供一个统一的接口，用来访问子系统中的一群接口，外观定义了一个高层的接口，让子系统更容易使用。
 * TODO: 说人话:其实就是为了方便客户的使用，把一群操作，封装成一个方法。
 */
public class HomeTheaterFacade {
    private Computer computer;
    private Player player;
    private Light light;
    private Projector projector;
    private PopcornPopper popper;

    public HomeTheaterFacade(Computer computer, Player player, Light light, Projector projector, PopcornPopper popper) {
        this.computer = computer;
        this.player = player;
        this.light = light;
        this.projector = projector;
        this.popper = popper;
    }

    public void watchMovie() {
        /**
         *  1、打开爆米花机
         2、制作爆米花
         3、将灯光调暗
         4、打开投影仪
         5、放下投影仪投影区
         6、打开电脑
         7、打开播放器
         8、将播放器音调设为环绕立体声
         */
        popper.on();
        popper.makePopcorn();
        light.on();
        projector.on();
        projector.open();
        computer.on();
        player.on();
        player.make3DListener();
    }

    public void stopMovie() {
        popper.off();
        popper.stopMakePopcorn();
        light.off();
        projector.close();
        projector.off();
        player.off();
        computer.off();
    }
}