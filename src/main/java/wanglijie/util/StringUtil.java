package wanglijie.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by $Jason.Zhang on 1/12/16.
 */
public class StringUtil {
    public static  String decode(String s) throws UnsupportedEncodingException {

        return URLDecoder.decode(URLEncoder.encode(s, "iso8859-1"), "utf-8");
    }
}
