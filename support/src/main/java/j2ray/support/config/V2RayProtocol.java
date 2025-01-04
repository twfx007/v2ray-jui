package j2ray.support.config;

import java.util.HashMap;
import java.util.Map;

public enum V2RayProtocol {
    BlackHole("blackhole"),
    DNS("dns"),
    Dokodemo_door("dokodemo-door"),
    Freedom("freedom"),
    HTTP("http"),
    Socks("socks"),
    VMess("vmess"),
    VLESS("vless"),
    Shadowsocks("shadowsocks"),
    Trojan("trojan"),
    Loopback("loopback")
    ;
    public final String protocol;

    V2RayProtocol(String protocol) {
        this.protocol = protocol;
    }
    private static final Map<String,V2RayProtocol> map=new HashMap<>();
    static {
        for(V2RayProtocol vr:V2RayProtocol.values()){
            map.put(vr.protocol,vr);
        }
    }
    public static V2RayProtocol getV2RayProtocol(String n){
        return map.get(n);
    }
}
