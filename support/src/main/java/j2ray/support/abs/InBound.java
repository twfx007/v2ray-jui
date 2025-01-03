package j2ray.support.abs;

import j2ray.support.config.V2RayProtocol;

public class InBound {
    private int port;
    private String listen="127.0.0.1";
    private String name;
    private V2RayProtocol protocol=V2RayProtocol.HTTP;
    private boolean udp=true;
}
