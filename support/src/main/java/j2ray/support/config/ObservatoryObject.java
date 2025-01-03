package j2ray.support.config;

import java.util.ArrayList;
import java.util.List;

public class ObservatoryObject {
    public List<String> subjectSelector;
    public String probeURL;
    public String probeInterval;
    public void addSelector(String... selector){
        if(subjectSelector==null){
            subjectSelector=new ArrayList<>();
        }
        for(String s:selector){
            if(subjectSelector.contains(s)){
                continue;
            }
            subjectSelector.add(s);
        }
    }
}
