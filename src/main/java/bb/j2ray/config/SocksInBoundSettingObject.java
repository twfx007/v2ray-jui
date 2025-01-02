package bb.j2ray.config;
import java.util.ArrayList;
import java.util.List;
public class SocksInBoundSettingObject extends InBoundSettingObject{
    public static final String AUTH_TYPE_NO="noauth";
    public static final String AUTH_TYPE_PASSWORD="password";
    public Boolean udp;
    public String ip;
    public Integer userLevel;
    public List<AccountObject> accounts;
    public void addAccount(String user,String pass){
        if(accounts==null){
            accounts=new ArrayList<>();
        }
        accounts.add(new AccountObject(user,pass));
    }
}
