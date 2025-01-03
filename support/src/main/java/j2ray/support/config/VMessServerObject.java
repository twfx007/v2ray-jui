package j2ray.support.config;

import java.util.ArrayList;
import java.util.List;

public class VMessServerObject {
    public String address;
    public Integer port;
    public List<VMessUserObject> users;
    public void addUserObject(VMessUserObject userObject){
        if(users==null){
            users=new ArrayList<>();
        }
        users.add(userObject);
    }
}
