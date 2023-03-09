package com.tp.utils;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class BCryptUtil {
    /**
     * 密码加密
     */
    public static String encipher(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
