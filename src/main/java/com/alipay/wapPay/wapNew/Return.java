package com.alipay.wapPay.wapNew;

import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.life.constants.AlipayServiceEnvConstants;
import com.alipay.life.util.RequestUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Return {

    public void returnPay(HttpServletRequest request, HttpServletResponse response) {
        try {
            //获取支付宝GET过来反馈信息
            Map<String, String> params = RequestUtil.getRequestStringArrParams(request);

            //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
            //商户订单号

            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //支付宝交易号

            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
            //计算得出通知验证结果
            //boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
            boolean verify_result = AlipaySignature.rsaCheckV1(params, AlipayServiceEnvConstants.ALIPAY_PUBLIC_KEY, AlipayServiceEnvConstants.CHARSET, "RSA2");

            if(verify_result){//验证成功
                //////////////////////////////////////////////////////////////////////////////////////////
                //请在这里加上商户的业务逻辑程序代码
                //该页面可做页面美工编辑
                response.getWriter().write("验证成功<br />");
                //——请根据您的业务逻辑来编写程序（以上代码仅作参考）——

                //////////////////////////////////////////////////////////////////////////////////////////
            }else{
                //该页面可做页面美工编辑
                response.getWriter().write("验证失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
