package bb.j2ray.config;

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
}
