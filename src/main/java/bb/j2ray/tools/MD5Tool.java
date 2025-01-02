package bb.j2ray.tools;


import org.apache.commons.codec.digest.DigestUtils;

public class MD5Tool {
    public static String md5Hex(String str){
        return DigestUtils.md5Hex(str);
    }

    public static String md5Hex(byte[] str){
        return DigestUtils.md5Hex(str);
    }

    public static byte[] md5(String str){
        return DigestUtils.md5(str);
    }
    public static byte[] md5(byte[] str){
        return DigestUtils.md5(str);
    }


}
