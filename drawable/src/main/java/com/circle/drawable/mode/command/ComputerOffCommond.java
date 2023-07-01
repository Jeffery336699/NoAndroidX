package com.circle.drawable.mode.command;

/**
 * 关电脑的命令
 *
 * @author zhy
 */
public class ComputerOffCommond implements Command {
    private Computer computer;

    public ComputerOffCommond(Computer computer) {
        this.computer = computer;
    }

    @Override
    public void execute() {
        computer.off();
    }
}
