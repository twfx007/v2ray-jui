package bb.j2ray.config;

import java.util.ArrayList;
import java.util.List;

public class RouteObject {
    public String domainStrategy;
    public String domainMatcher;
    public List<RuleObject> rules;
    public List<BalancerObject> balancers;
    public void addRuleObject(RuleObject ruleObject){
        if(rules==null){
            rules=new ArrayList<>();
        }
        rules.add(ruleObject);
    }
    public void addBalancerObject(BalancerObject balancerObject){
        if(balancers ==null){
            balancers =new ArrayList<>();
        }
        balancers.add(balancerObject);

    }
}
