package com.alipay.wapPay.wapOld;

import com.alipay.life.util.AlipaySignUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.util.Map;


@RequestMapping("/wapPay")
@org.springframework.stereotype.Controller
public class TradePay {

    /**
     * 文档地址
     * https://opendocs.alipay.com/open/62/104743
     * @param model
     * @return
     */
    @GetMapping("/wapOld/tradePay")
    public String tradePay(Map<String, Object> model) {
        Alipayment alipayment = new Alipayment();
        alipayment.setBody("");
        alipayment.setSubject("productName");
        alipayment.setSign_type("MD5");
        alipayment.setNotify_url("https://www.ablejava.com/notify.do"); // 异步通知url
        alipayment.setOut_trade_no("orderNo"); // 商品订单号
        alipayment.setReturn_url("https://www.ablejava.com/alipayReturn.jsp"); // 同步返回url
        alipayment.setSign("");
        alipayment.set_input_charset("utf-8");
        alipayment.setTotal_fee("100.0");
        alipayment.setService("alipay.wap.create.direct.pay.by.user"); // 接口名称，固定
        alipayment.setSeller_id("2088701769340865XXX"); // 签约的支付宝账号2088开头
        alipayment.setPayment_type("1"); // 1。固定值
        try {
            alipayment.setSign(AlipaySignUtil.sign(alipayment));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        model.put("alipayment", alipayment);
        return "/jsp/wapPay";
    }
}
