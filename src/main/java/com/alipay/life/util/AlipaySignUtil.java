package com.alipay.life.util;

import com.alipay.wapPay.wapOld.Alipayment;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

public class AlipaySignUtil {

    public static String sign(Alipayment alipayment) throws UnsupportedEncodingException {
        Map<String, String[]> paraMap = new HashMap<>();
        paraMap.put("body", toArray(alipayment.getBody()));
        paraMap.put("subject", toArray(alipayment.getSubject()));
        paraMap.put("sign_type", toArray(alipayment.getSign_type()));
        paraMap.put("notify_url", toArray(alipayment.getNotify_url()));
        paraMap.put("out_trade_no", toArray(alipayment.getOut_trade_no()));
        paraMap.put("return_url", toArray(alipayment.getReturn_url()));
        paraMap.put("sign", toArray(alipayment.getSign()));
        paraMap.put("_input_charset", toArray(alipayment.get_input_charset()));
        paraMap.put("total_fee", toArray(alipayment.getTotal_fee()));
        paraMap.put("service", toArray(alipayment.getService()));
        paraMap.put("seller_id", toArray(alipayment.getSeller_id()));
        paraMap.put("partner", toArray(alipayment.getPartner()));
        paraMap.put("payment_type", toArray(alipayment.getPayment_type()));

        String singKey ="自己的私钥";
        String charset = "utf-8";

        Map<String, String> aliMap = filterAlipayMap(paraMap);
        String linkString = createLinkString(aliMap);

        String sign = MD5Util.sign(linkString, singKey, charset);

        return sign;

    }

    private static String[] toArray(String str) {
        String[] arr = new String[1];
        arr[0] = str == null ? "" : str;
        return arr;
    }


    private static Map<String, String> filterAlipayMap(Map<String, String[]> paraMap) {
        Map<String, String> resultMap = new HashMap<>();
        for (String key : paraMap.keySet()) {
            String[] value = paraMap.get(key);
            if (value == null || "".equals(value[0]) || key.equals("sign") || key.equalsIgnoreCase("sign_type")) {
                continue;
            }
            try {
                resultMap.put(key, URLEncoder.encode(value[0].replace("\\+", "%2B"), "utf-8"));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return resultMap;
    }


    private static String createLinkString(Map<String, String> params) {
        List<String> keys = new ArrayList<>(params.keySet());
        Collections.sort(keys);
        String prestr = "";
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            if (i == keys.size() -1) { // 最后一个不包含&
                prestr = prestr +key +"="+value;
            } else {
                prestr = prestr +key +"="+value+"&";
            }
        }
        return prestr;
    }
}
