package com.circle.drawable.mode.command;

/**
 * 开电脑的命令
 * @author zhy
 *
 */
public class ComputerOnCommond implements Command
{
    private Computer computer ;

    public ComputerOnCommond( Computer computer)
    {
        this.computer = computer;
    }

    @Override
    public void execute()
    {
        computer.on();
    }

}