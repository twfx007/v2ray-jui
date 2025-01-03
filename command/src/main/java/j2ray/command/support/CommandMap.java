package j2ray.command.support;

import j2ray.command.impls.J2RayEnv;
import j2ray.command.impls.J2RayExit;
import j2ray.support.error.J2RayException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandMap {
    private static final Map<String,ICommand> map=new HashMap<>();
    private static final List<ICommand> list=new ArrayList<>();
    static void init(String[] args){
        registerKey(new J2RayEnv());
        registerKey(new J2RayExit());
    }


    private static void registerKey(ICommand cmd){
        map.put(cmd.commandKey(),cmd);
        list.add(cmd);
    }

    static void execute(String inputText){
        String[] cmds=inputText.split(" ");


        List<String> cmdList=new ArrayList<>();
        for(String cm:cmds){
            cm=cm.trim();
            if(cm.isEmpty()){
                continue;
            }
            cmdList.add(cm);
        }

        String cmdKey=cmdList.get(0);
        String[] args=new String[cmdList.size()-1];
        for(int i=1;i<cmdList.size();i++){
            args[i-1]=cmdList.get(i);
        }

        ICommand command=map.get(cmdKey);
        if(command==null){
            J2RayOutput.println(I18nKey.NOT_SUPPORT_COMMAND,cmdKey);
            return;
        }
        try {
            command.execute(args);
        } catch (J2RayException e) {
            J2RayOutput.println("Error Code={1},Msg={2}",e.code,e.msg);
        } catch (Exception e){
            J2RayOutput.exceptionStack(e);
        }
    }
}
