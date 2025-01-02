package bb.j2ray.tools;

public class HttpException extends Exception{
    public int code;
    public String msg;

    public HttpException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public HttpException(int code, String msg, Throwable throwable) {
        super(msg,throwable);
        this.code = code;
        this.msg = msg;
    }
}
