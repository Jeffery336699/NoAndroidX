package com.circle.drawable.mode.command;

/**
 * 定义一个命令，可以干一系列的事情
 *
 * @author zhy
 */
public class QuickCommand implements Command {
    private Command[] commands;

    public QuickCommand(Command[] commands) {
        this.commands = commands;
    }

    @Override
    public void execute() {
        for (int i = 0; i < commands.length; i++) {
            commands[i].execute();
        }
    }

}