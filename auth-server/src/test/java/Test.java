import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.owasp.encoder.Encode;

public class Test {

	public static void main(String[] args) throws UnsupportedEncodingException {
		System.out.println(URLEncoder.encode("mongodb@j1d1sec.c0m","UTF-8"));
		System.out.println(getQRBarcodeURL("root", "host", "111"));
	}
	
	public static String getQRBarcodeURL(String user, String host, String secret) {
        String format = "https://www.google.com/chart?chs=200x200&chld=M%%7C0&cht=qr&chl=otpauth://totp/%s@%s%%3Fsecret%%3D%s";
        return String.format(format, user, host, secret);
    }
}
