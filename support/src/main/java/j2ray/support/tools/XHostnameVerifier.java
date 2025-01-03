package j2ray.support.tools;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

public class XHostnameVerifier implements HostnameVerifier{
	@Override
	public boolean verify(String hostname, SSLSession session) {
		return true;
	}
}
