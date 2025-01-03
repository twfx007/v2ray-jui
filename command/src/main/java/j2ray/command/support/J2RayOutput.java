package j2ray.command.support;

import java.io.PrintWriter;
import java.io.StringWriter;

public class J2RayOutput {
    public static void println(Object... msg){
        String ma=I18nText.getText(msg[0].toString());
        for(int i=1;i<msg.length;i++){
            ma=ma.replaceAll("\\{"+i+"}",msg[i].toString());
        }
        System.out.println(ma);
    }
    public static void exceptionStack(Throwable e) {
        if (e == null){
            return;
        }
        StringWriter stringWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(stringWriter));
        println(stringWriter.toString());
    }
}
