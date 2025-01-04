package j2ray.command.impls;

import j2ray.command.support.CommandMap;
import j2ray.command.support.ICommand;
import j2ray.support.abs.J2RayException;

public class TestOutBound implements ICommand {
    @Override
    public void execute(String[] args) throws J2RayException {
        int relay=300;
        if(args.length>0){
            relay = Integer.parseInt(args[0]);
        }
        CommandMap.ray.test(relay);
    }

    @Override
    public String commandKey() {
        return "test";
    }
}
