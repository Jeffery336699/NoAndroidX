package com.circle.drawable.mode.command;

/**
 * 关闭电灯的命令
 * @author zhy
 *
 */
public class LightOffCommand implements Command
{
    private Light light ;

    public LightOffCommand(Light light)
    {
        this.light = light;
    }

    @Override
    public void execute()
    {
        light.off();
    }

}
