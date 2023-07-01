package com.circle.drawable.mode.command;

/**
 * 关闭电灯的命令
 *
 * @author zhy
 */
public class LightOnCommond implements Command {
    private Light light;

    public LightOnCommond(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }

}