package bb.j2ray.boot;

import java.io.File;

public class V2RayBoot {
    private static Process process;
    public static void start(){
        String binaryFile=System.getProperty("user.dir")+ File.separator+"v2ray";
        ProcessBuilder processBuilder=new ProcessBuilder();
        processBuilder.command(
                binaryFile,
                "run"
        );
        try{
            process=processBuilder.start();
        }catch (Exception e){

        }
    }
}
