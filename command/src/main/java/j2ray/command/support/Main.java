package j2ray.command.support;

import j2ray.support.abs.J2RayException;

import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        CommandMap.init(args);
        try {
            CommandMap.ray.startV2ray();
        } catch (J2RayException e) {
            J2RayOutput.println("start v2ray error code={1},msg={2}",e.code,e.msg);
        }
        Scanner scanner=new Scanner(System.in);
        while (true){
            System.out.print("j2ray> ");
            String ctxt=scanner.nextLine();
            if(ctxt==null||ctxt.isEmpty()){
                continue;
            }
            CommandMap.execute(ctxt);
        }
    }
}
