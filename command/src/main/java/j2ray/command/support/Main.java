package j2ray.command.support;

import j2ray.support.abs.J2RayException;

import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        try {
            CommandMap.init(args);
        } catch (J2RayException e) {
            J2RayOutput.println("CommandMap.init error Code={1},Msg={2}",e.code,e.msg);
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
