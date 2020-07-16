package com.alipay.life.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.util.Objects;

public class MD5Util {

    /**
     *
     * @param text 需要签名的字符串
     * @param key 密钥
     * @param input_charset 编码格式
     * @return
     */
    public static String sign(String text, String key, String input_charset) throws UnsupportedEncodingException {
        text = text+key;
        return DigestUtils.md5Hex(getContentBytes(text, input_charset));
    }

    private static  byte[] getContentBytes(String context, String charset) throws UnsupportedEncodingException {
        if (Objects.isNull(charset)) {
            return context.getBytes();
        }
        return context.getBytes(charset);
    }
}
