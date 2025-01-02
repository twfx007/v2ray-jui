package bb.j2ray.config;

import java.util.ArrayList;
import java.util.List;

public class RuleObject {
    public String domainMatcher;
    public String type="field";
    public List<String> domains;
    public List<String> ip;
    public String port;
    public String sourcePort;
    public String network;
    public List<String> source;
    public List<String> user;
    public List<String> inboundTag;
    public List<String> protocol;
    public String attrs;
    public String outboundTag;
    public String balancerTag;



    public void addIP(String... ips){
        if(ip==null){
            ip=new ArrayList<>();
        }
        for(String d:ips){
            if(ip.contains(d)){
                continue;
            }
            ip.add(d);
        }

    }

    public void addDomain(String... domain){
        if(domains==null){
            domains=new ArrayList<>();
        }
        for(String d:domain){
            if(domains.contains(d)){
                continue;
            }
            domains.add(d);
        }

    }
}
