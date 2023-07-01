package com.circle.drawable.mode.command;

/**
 * 开电脑的命令
 *
 * @author zhy
 */
public class DoorOnCommond implements Command {
    private Door door;

    public DoorOnCommond(Door door) {
        this.door = door;
    }

    @Override
    public void execute() {
        door.open();
    }

}