package com.utp.util;

import java.util.Random;
import java.security.SecureRandom;

public class CodeGenerator {

    static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    static final String NUMBER = "0123456789";
    static final Random RANDOM = new SecureRandom();

    public static String generateCodepay() {
        StringBuilder code = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            code.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }
        return code.toString();
    }

    public static String generateCode() {
        StringBuilder code = new StringBuilder(6);
        for (int i = 0; i < 5; i++) {
            code.append(NUMBER.charAt(RANDOM.nextInt(NUMBER.length())));
        }
        return code.toString();
    }

}
