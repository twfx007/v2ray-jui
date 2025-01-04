package j2ray.support.config;

import j2ray.support.abs.J2RayException;

public interface IParseConfig {
    V2RayConfig parse(Object param) throws J2RayException;
}
