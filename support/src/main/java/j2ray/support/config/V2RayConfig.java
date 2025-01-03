package j2ray.support.config;

import java.util.ArrayList;
import java.util.List;

public class V2RayConfig {
    public final LogObject log=new LogObject();
    public List<InboundObject> inbounds;
    public List<OutboundObject> outbounds;
    public RouteObject routing;
    public ObservatoryObject observatory;

    public void addOutboundObject(OutboundObject outboundObject){
        if(outbounds==null){
            outbounds=new ArrayList<>();
        }
        outbounds.add(outboundObject);
    }
    public void addInboundObject(InboundObject inboundObject){
        if(inbounds==null){
            inbounds=new ArrayList<>();
        }
        inbounds.add(inboundObject);
    }
}
