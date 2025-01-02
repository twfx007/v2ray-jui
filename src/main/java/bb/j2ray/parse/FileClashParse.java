package bb.j2ray.parse;

import bb.j2ray.config.*;
import bb.j2ray.tools.FileTool;
import org.yaml.snakeyaml.Yaml;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FileClashParse extends ClashParse{
    @Override
    public V2RayConfig parse(Object param) throws Exception {
        return super.parse(FileTool.readFileContent((String) param,"UTF-8"));

    }
}
