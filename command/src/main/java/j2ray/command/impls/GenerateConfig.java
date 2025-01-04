package j2ray.command.impls;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;
import j2ray.command.support.CommandMap;
import j2ray.command.support.ICommand;
import j2ray.support.abs.J2RayException;
import j2ray.support.config.IParseConfig;
import j2ray.support.config.V2RayConfig;
import j2ray.support.parse.FileClashParse;
import j2ray.support.parse.URLClashParse;

public class GenerateConfig implements ICommand {
    @Override
    public void execute(String[] args) throws J2RayException {
        IParseConfig pc;
        if(args[0].startsWith("https://")||args[0].startsWith("http://")){
            pc=new URLClashParse();
        }else{
            pc=new FileClashParse();
        }
        V2RayConfig vc=pc.parse(args[0]);


        CommandMap.ray.setConfig(vc);

    }

    @Override
    public String commandKey() {
        return "config";
    }
}
