package j2ray.command.impls;

import j2ray.command.support.ICommand;
import j2ray.command.support.J2RayOutput;
import j2ray.support.abs.J2RayException;

public class J2RayEnv implements ICommand {
    @Override
    public void execute(String[] args) throws J2RayException {
        if(args.length==0){
            return;
        }
        String envName=args[0];
        J2RayOutput.println(System.getProperty(envName));
    }

    @Override
    public String commandKey() {
        return "env";
    }
}
