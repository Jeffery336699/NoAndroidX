package com.circle.drawable.mode.command;

/**
 * 开电脑的命令
 *
 * @author zhy
 */
public class DoorOffCommond implements Command {
    private Door door;

    public DoorOffCommond(Door door) {
        this.door = door;
    }

    @Override
    public void execute() {
        door.close();
    }

}