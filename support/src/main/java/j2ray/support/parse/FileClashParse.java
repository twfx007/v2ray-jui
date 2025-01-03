package j2ray.support.parse;

import j2ray.support.config.V2RayConfig;
import j2ray.support.tools.FileTool;

public class FileClashParse extends ClashParse{
    @Override
    public V2RayConfig parse(Object param) throws Exception {
        return super.parse(FileTool.readFileContent((String) param,"UTF-8"));

    }
}
