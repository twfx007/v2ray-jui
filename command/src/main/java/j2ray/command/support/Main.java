package j2ray.command.support;

import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        CommandMap.init(args);
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
