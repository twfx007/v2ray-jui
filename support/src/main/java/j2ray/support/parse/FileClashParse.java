package j2ray.support.parse;

import j2ray.support.abs.J2RayException;
import j2ray.support.config.V2RayConfig;
import j2ray.support.tools.FileTool;

public class FileClashParse extends ClashParse{
    @Override
    public V2RayConfig parse(Object param) throws J2RayException {
        try {
            return super.parse(FileTool.readFileContent((String) param,"UTF-8"));
        } catch (Exception e) {
            throw new J2RayException("read file error");
        }

    }
}
