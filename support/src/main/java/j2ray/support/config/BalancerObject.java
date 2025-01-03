package j2ray.support.config;

import java.util.ArrayList;
import java.util.List;

public class BalancerObject {
    public String tag;
    public List<String> selector;
    public BalancerStrategyObject strategy;
    public void addSelector(String... selectorList){
        if(selector==null){
            selector=new ArrayList<>();
        }
        for(String s:selectorList){
            if(selector.contains(s)){
                continue;
            }
            selector.add(s);
        }
    }
}
