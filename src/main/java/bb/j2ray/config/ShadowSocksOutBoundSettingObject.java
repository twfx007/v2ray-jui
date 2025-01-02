package bb.j2ray.config;

import java.util.ArrayList;
import java.util.List;

public class ShadowSocksOutBoundSettingObject extends OutBoundSettingObject{
    public List<ShadowSocksServerObject> servers;

    public ShadowSocksOutBoundSettingObject() {
        auth=null;
    }
    public void addServerObject(ShadowSocksServerObject serverObject){
        if(servers==null){
            servers=new ArrayList<>();
        }
        servers.add(serverObject);

    }
}
