package io.github.weechang.moreco.security.base.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 密码工具类
 *
 * @author zhangwei
 * date 2018/10/30
 * time 21:24
 */
public class PasswordUtil {

    /**
     * 加密算法
     */
    public final static String hashAlgorithmName = "SHA-256";
    /**
     * 循环次数
     */
    public final static int hashIterations = 16;

    public static String passwordEncryptor(String password, String salt) throws NoSuchAlgorithmException {
        byte[] passwordByte = password == null ? new byte[0] : password.getBytes();
        byte[] saltByte = salt == null ? null : salt.getBytes();
        MessageDigest digest = MessageDigest.getInstance(hashAlgorithmName);
        if (saltByte != null) {
            digest.reset();
            digest.update(saltByte);
        }
        byte[] hashedByte = digest.digest(passwordByte);
        if (password != null) {
            for (int i = 0; i < hashIterations - 1; i++) {
                hashedByte = digest.digest(hashedByte);
            }
        }
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < hashedByte.length; i++) {
            int val = ((int) hashedByte[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

}
