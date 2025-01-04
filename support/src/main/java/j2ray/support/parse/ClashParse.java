package j2ray.support.parse;

import j2ray.support.abs.J2RayException;
import j2ray.support.config.*;
import org.yaml.snakeyaml.Yaml;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
public class ClashParse implements IParseConfig{
    @Override
    public V2RayConfig parse(Object param) throws J2RayException {
        String yamlContent= (String) param;
        Yaml yaml=new Yaml();
        Map<String,Object> map=yaml.load(yamlContent);
        V2RayConfig config=new V2RayConfig();
        config.log.access=null;
        config.log.error=null;
        config.log.loglevel="info";
        InboundObject inboundObject=new InboundObject();
        inboundObject.listen="127.0.0.1";
        inboundObject.port=1081;
        inboundObject.protocol= V2RayProtocol.Socks.protocol;
        inboundObject.settings=new SocksInBoundSettingObject();
        inboundObject.sniffing=new SniffingObject();
        inboundObject.sniffing.enabled=true;
        inboundObject.sniffing.addDestOverride("http","tls");

        config.addInboundObject(inboundObject);
        inboundObject=new InboundObject();
        inboundObject.listen="127.0.0.1";
        inboundObject.port=1080;
        inboundObject.protocol=V2RayProtocol.HTTP.protocol;
        inboundObject.settings=new HttpInBoundSettingObject();
        inboundObject.sniffing=new SniffingObject();
        inboundObject.sniffing.enabled=true;
        inboundObject.sniffing.addDestOverride("http","tls");
        config.addInboundObject(inboundObject);
        List<LinkedHashMap<String,Object>> proxyList= (List<LinkedHashMap<String, Object>>) map.get("proxies");
        int oi=0;
        for(LinkedHashMap<String,Object> proxy:proxyList){
            String type=proxy.get("type").toString();
            String name=proxy.get("name").toString();
            OutboundObject outboundObject=new OutboundObject();
            String server=proxy.get("server").toString();
            Integer port= (Integer) proxy.get("port");
            Boolean udp= (Boolean) proxy.get("udp");
            String cipher=proxy.get("cipher").toString();
            if(type.equals("ss")){
                type=V2RayProtocol.Shadowsocks.protocol;
                String password=proxy.get("password").toString();
                ShadowSocksOutBoundSettingObject settings=new ShadowSocksOutBoundSettingObject();
                ShadowSocksServerObject serverObject=new ShadowSocksServerObject();
                serverObject.method=cipher;
                serverObject.password=password;
                serverObject.address=server;
                serverObject.port=port;
                serverObject.ota=true;
                settings.addServerObject(serverObject);
                outboundObject.settings=settings;
            }else if(type.equals("vmess")){
                type=V2RayProtocol.VMess.protocol;
                String uuid=proxy.get("uuid").toString();
                Integer alterId= (Integer) proxy.get("alterId");
                VMessOutBoundSettingObject settings=new VMessOutBoundSettingObject();
                VMessServerObject serverObject=new VMessServerObject();
                serverObject.address=server;
                serverObject.port=port;
                VMessUserObject userObject=new VMessUserObject();
                userObject.id=uuid;
                userObject.alterId=alterId;
                userObject.security="auto";
                serverObject.addUserObject(userObject);
                settings.addServerObject(serverObject);
                outboundObject.settings=settings;
            }
            if(name.contains("台湾")
                    ||name.contains("日本")
                    ||name.contains("新加坡")){
                outboundObject.tag="wall_gpt_"+oi;
            }else{
                outboundObject.tag="wall_"+oi;
            }


            outboundObject.protocol=type;
            config.addOutboundObject(outboundObject);
            oi++;
        }

        OutboundObject outboundObject=new OutboundObject();
        outboundObject.protocol=V2RayProtocol.Freedom.protocol;
        outboundObject.tag="direct";
        config.addOutboundObject(outboundObject);
        outboundObject=new OutboundObject();
        outboundObject.protocol=V2RayProtocol.BlackHole.protocol;
        outboundObject.tag="blocked";
        config.addOutboundObject(outboundObject);


        RouteObject routeObject=new RouteObject();


        BalancerObject balancerObject=new BalancerObject();
        balancerObject.tag="outer";
        balancerObject.addSelector("wall_");
        BalancerStrategyObject balancerStrategyObject=new BalancerStrategyObject();
        balancerStrategyObject.type= BalancerStrategy.leastping.strategy;
        balancerObject.strategy=balancerStrategyObject;
        routeObject.addBalancerObject(balancerObject);


        //chatgpt.com
        balancerObject=new BalancerObject();
        balancerObject.tag="gpt";
        balancerObject.addSelector("wall_gpt_");
        balancerStrategyObject=new BalancerStrategyObject();
        balancerStrategyObject.type=BalancerStrategy.random.strategy;
        balancerObject.strategy=balancerStrategyObject;
        routeObject.addBalancerObject(balancerObject);



        routeObject.domainStrategy= DomainStrategy.IPOnDemand.strategy;
        config.routing=routeObject;
        RuleObject rule;



        rule=new RuleObject();
        rule.balancerTag="gpt";
        rule.addDomain("chatgpt.com","openai.com");
        routeObject.addRuleObject(rule);


        rule=new RuleObject();
        rule.outboundTag="direct";
        rule.addDomain("geosite:cn");
        routeObject.addRuleObject(rule);

        rule=new RuleObject();
        rule.outboundTag="direct";
        rule.addIP("geoip:cn","geoip:private");
        routeObject.addRuleObject(rule);


        rule=new RuleObject();
        rule.balancerTag="outer";
        rule.addDomain("");
        routeObject.addRuleObject(rule);

        rule=new RuleObject();
        rule.balancerTag="outer";
        rule.addIP("geoip:!cn");
        routeObject.addRuleObject(rule);

        ObservatoryObject observatoryObject=new ObservatoryObject();
        observatoryObject.addSelector("wall_");
        observatoryObject.probeInterval="1m";
        config.observatory=observatoryObject;
        return config;
    }
}
