package bb.j2ray.config;

import java.util.ArrayList;
import java.util.List;
public class HttpInBoundSettingObject extends InBoundSettingObject{
    public Integer timeout;
    public Boolean allowTransparent;
    public Integer userLevel;
    public List<AccountObject> accounts;
    public void addAccount(String user,String pass){
        if(accounts==null){
            accounts=new ArrayList<>();
        }
        accounts.add(new AccountObject(user,pass));
    }
}
