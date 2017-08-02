package bj.my.blog.blog.start.util;


import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class DESUtil {
    private static final char[] HEX_DIGITS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};



    public static String urlEncode(String content, String charset) {
        try {
            return URLEncoder.encode(content, charset);
        } catch (UnsupportedEncodingException var3) {
            return content;
        }
    }

    public static String encode(String source, String key) {
        try {
            source = urlEncode(source, "UTF-8");
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
            IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
            return toHexString(cipher.doFinal(source.getBytes("UTF-8")));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static final int charToNibble(char c) {
        if (c >= '0' && c <= '9') {
            return c - 48;
        } else if (c >= 'a' && c <= 'f') {
            return 10 + (c - 97);
        } else {
            return c >= 'A' && c <= 'F' ? 10 + (c - 65) : -1;
        }
    }

    public static byte[] fromHexString(String text) {
        text = text.trim();
        if (text.length() % 2 != 0) {
            text = "0" + text;
        }

        int resLen = text.length() / 2;
        byte[] res = new byte[resLen];

        for (int i = 0; i < resLen; ++i) {
            int j = i << 1;
            int hiNibble = charToNibble(text.charAt(j));
            int loNibble = charToNibble(text.charAt(j + 1));
            if (loNibble == -1 || hiNibble == -1) {
                return null;
            }

            res[i] = (byte) (hiNibble << 4 | loNibble);
        }

        return res;
    }

    public static String decode(String source, String key) {
        try {
            byte[] src = fromHexString(source);
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
            IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
            cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
            byte[] retByte = cipher.doFinal(src);
            return urlDecode(new String(retByte), "UTF-8");
        } catch (Exception e) {
            return null;
        }
    }

    public static String toHexString(byte[] buf) {
        return toHexString(buf, (String) null, 2147483647);
    }

    public static String toHexString(byte[] buf, String sep, int lineLen) {
        if (buf == null) {
            return null;
        } else {
            if (lineLen <= 0) {
                lineLen = 2147483647;
            }

            StringBuffer res = new StringBuffer(buf.length * 2);

            for (int i = 0; i < buf.length; ++i) {
                int b = buf[i];
                res.append(HEX_DIGITS[b >> 4 & 15]);
                res.append(HEX_DIGITS[b & 15]);
                if (i > 0 && i % lineLen == 0) {
                    res.append('\n');
                } else if (sep != null && i < lineLen - 1) {
                    res.append(sep);
                }
            }

            return res.toString();
        }
    }

    public static String urlDecode(String content, String charset) {
        try {
            return content != null ? URLDecoder.decode(content, charset) : "";
        } catch (UnsupportedEncodingException var3) {
            return content;
        }
    }
}
