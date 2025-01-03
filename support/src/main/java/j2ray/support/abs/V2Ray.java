package j2ray.support.abs;

import j2ray.support.config.LogObject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class V2Ray {
    private LogObject logObject=new LogObject();
    private final Map<String,InBound> inBoundMap=new HashMap<>();
    private final Map<String,OutBound> outBoundMap=new HashMap<>();
    private String binaryFile;
    private String configFile;
    private String jconfigFile;
    private Process process;

    public void init(String[] args) {
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
    }
    public void startV2ray() throws J2RayException {
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
