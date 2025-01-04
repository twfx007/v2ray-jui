package j2ray.support.abs;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;
import j2ray.support.config.OutboundObject;
import j2ray.support.config.V2RayConfig;
import j2ray.support.config.VMessOutBoundSettingObject;
import j2ray.support.tools.FileTool;

import java.io.File;
import java.io.IOException;

public class V2Ray {
    private V2RayConfig config;
    private V2RayConfig jconfig;
    private String binaryFile;
    private String configFile;
    private String jconfigFile;
    private Process process;


    public void test(int relay){
        for(OutboundObject obo:jconfig.outbounds){
            if(obo.settings instanceof VMessOutBoundSettingObject){
                VMessOutBoundSettingObject vobo= (VMessOutBoundSettingObject) obo.settings;
                testOutBound(relay,obo,vobo);
            }
        }
    }
    private void testOutBound(int relay,OutboundObject obo,VMessOutBoundSettingObject setting){

    }

    public void setConfig(V2RayConfig v2RayConfig) throws J2RayException {
        this.config=v2RayConfig;
        String content= JSON.toJSONString(config, JSONWriter.Feature.PrettyFormat);
        jconfig=JSON.parseObject(content,V2RayConfig.class);
        String dir=System.getProperty("user.dir")+ File.separator+"config.json";
        try {
            FileTool.writeFileContent(dir,content,"UTF-8");
        } catch (Exception e) {
            throw new J2RayException("write config file error");
        }
        dir=System.getProperty("user.dir")+ File.separator+"jconfig.json";
        try {
            FileTool.writeFileContent(dir,content,"UTF-8");
        } catch (Exception e) {
            throw new J2RayException("write config file error");
        }
    }
    public void init(String[] args) throws J2RayException {
        String fileName;
        OSType osType=CommonTool.getOSType();
        if(osType==OSType.WINDOWS){
            fileName="v2ray.exe";
        }else{
            fileName="v2ray";
        }
        String userDir=System.getProperty("user.dir");
        binaryFile=userDir+ File.separator+fileName;
        configFile=userDir+File.separator+"config.json";
        jconfigFile=userDir+File.separator+"jconfig.json";
        String tmp=null;
        try{
            tmp= FileTool.readFileContent(configFile,"UTF-8");
            if(tmp!=null){
                config= JSON.parseObject(tmp,V2RayConfig.class);
            }
        }catch (Exception e){
            throw new J2RayException("read configFile error");
        }
        try{
            tmp= FileTool.readFileContent(jconfigFile,"UTF-8");
            if(tmp!=null){
                jconfig= JSON.parseObject(tmp,V2RayConfig.class);
            }
        }catch (Exception e){
            throw new J2RayException("read configFile error");
        }
    }
    public void startV2ray() throws J2RayException {
        if(config==null){
            throw new J2RayException("the v2ray config.json is not exist");
        }
        ProcessBuilder processBuilder=new ProcessBuilder();
        processBuilder.command(
                binaryFile,
                "run"
        );
        try {
            process=processBuilder.start();
        } catch (IOException e) {
            throw new J2RayException("start v2ray processor error");
        }
    }
    public void stopV2ray(){
        if(process!=null){
            process.destroy();
            process=null;
        }

    }
}
