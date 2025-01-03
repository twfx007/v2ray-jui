package j2ray.command.impls;

import j2ray.command.support.ICommand;
import j2ray.support.error.J2RayException;

public class J2RayExit implements ICommand {
    @Override
    public void execute(String[] args) throws J2RayException {
        System.exit(0);
    }

    @Override
    public String commandKey() {
        return "exit";
    }
}
