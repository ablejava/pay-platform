package com.alipay.life.controller;


import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeCreateModel;
import com.alipay.api.request.AlipayTradeCreateRequest;
import com.alipay.api.response.AlipayTradeCreateResponse;
import com.alipay.life.constants.AlipayServiceEnvConstants;
import com.alipay.life.constants.AlipayServiceResponseCodeConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Hashtable;
import java.util.Map;

@Controller
@RequestMapping("/cashier")
public class Cashier {

    @GetMapping("/tradeCreate")
    public Map<String, String> tradeCreate(HttpServletRequest request) {
        AlipayClient client = new DefaultAlipayClient(AlipayServiceEnvConstants.ALIPAY_GATEWAY, AlipayServiceEnvConstants.APP_ID,
                AlipayServiceEnvConstants.PRIVATE_KEY, "json", AlipayServiceEnvConstants.CHARSET,
                AlipayServiceEnvConstants.ALIPAY_PUBLIC_KEY, AlipayServiceEnvConstants.SIGN_TYPE);
        AlipayTradeCreateRequest tradeCreateRequestrequest = new AlipayTradeCreateRequest();
        AlipayTradeCreateModel model = new AlipayTradeCreateModel();
        model.setOutTradeNo("2131231412");
        model.setSellerId("2088102168512190");
        model.setTotalAmount("0.01");
        model.setBuyerLogonId("501624560335vj@sandbox.com");
        model.setSubject("iphone7");
        tradeCreateRequestrequest.setBizModel(model);
        tradeCreateRequestrequest.setNotifyUrl("www.ablejava.com/alipayApplet/notifyUrl");

        AlipayTradeCreateResponse response = new AlipayTradeCreateResponse();
        Map<String, String> result = new Hashtable<>();
        try {
            response = client.execute(tradeCreateRequestrequest);
            System.out.println(response.getBody());
            String code = response.getCode();
            if (AlipayServiceResponseCodeConstants.SUCCESS.equals(code)) {
                String outTradeNo = response.getOutTradeNo();
                String tradeNo = response.getTradeNo();
                result.put("outTradeNo", outTradeNo);
                result.put("tradeNo", code);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
