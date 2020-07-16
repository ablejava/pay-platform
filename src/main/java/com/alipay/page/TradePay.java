package com.alipay.page;

import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.life.factory.AlipayAPIClientFactory;
import com.alipay.page.config.AlipayConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TradePay {

    public void tradePay(HttpServletRequest request, HttpServletResponse response){
        //获得初始化的AlipayClient
        AlipayClient alipayClient = AlipayAPIClientFactory.getAlipayClient();
        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        try {
            //商户订单号，商户网站订单系统中唯一订单号，必填
            String out_trade_no = new String(request.getParameter("WIDout_trade_no").getBytes("ISO-8859-1"),"UTF-8");
            //付款金额，必填
            String total_amount = new String(request.getParameter("WIDtotal_amount").getBytes("ISO-8859-1"),"UTF-8");
            //订单名称，必填
            String subject = new String(request.getParameter("WIDsubject").getBytes("ISO-8859-1"),"UTF-8");
            //商品描述，可空
            String body = new String(request.getParameter("WIDbody").getBytes("ISO-8859-1"),"UTF-8");

            alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                    + "\"total_amount\":\""+ total_amount +"\","
                    + "\"subject\":\""+ subject +"\","
                    + "\"body\":\""+ body +"\","
                    + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

            //若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
            //alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
            //		+ "\"total_amount\":\""+ total_amount +"\","
            //		+ "\"subject\":\""+ subject +"\","
            //		+ "\"body\":\""+ body +"\","
            //		+ "\"timeout_express\":\"10m\","
            //		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
            //请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节

            //请求
            String result = alipayClient.pageExecute(alipayRequest).getBody();

            //输出

            response.getWriter().write(result);

        } catch (Exception e){

        }
    }
}
