package j2ray.support.abs;

public class J2RayException extends Exception{
    public final int code;
    public final String msg;

    public J2RayException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public J2RayException(String msg) {
        super(msg);
        this.code=-1;
        this.msg = msg;
    }
}
