package com.alipay.wapPay.wapOld;

import com.alipay.life.constants.AlipayServiceResponseCodeConstants;
import com.alipay.life.util.AlipayNotify;
import com.alipay.life.util.RequestUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Map;

@Controller
@RequestMapping("/alipayNotify")
public class WapOldNotify {

    @GetMapping("/notify")
    public void notify(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String[]> paraMap = request.getParameterMap();

        String outTradeNo = request.getParameter("out_trade_no"); // 商户订单号
        String tradeNo = request.getParameter("trade_no"); // 支付宝交易单号
        String tradeStatus = request.getParameter("trade_status");
        String gmtPayment = request.getParameter("gmt_payment");
        String subject = request.getParameter("subject");

        try {
            Map<String, String> requestParams = RequestUtil.getRequestParams(request);
            // todo 保存请求数据 to log
            if (AlipayNotify.verify(requestParams)) {
                if (AlipayServiceResponseCodeConstants.ALIPAY_WAP_OLD_NOTIFY_TRADE_SUCCESS_CODE.equals(tradeStatus)) {
                    String totalFee = request.getParameter("total_fee");
                    BigDecimal bd = new BigDecimal(totalFee);
                    // todo 保存订单信息
                } else if (AlipayServiceResponseCodeConstants.ALIPAY_WAP_OLD_NOTIFY_TRADE_FINISHED_CODE.equals(tradeStatus)) {

                    // todo 保存订单支付失败
                }
                response.getWriter().write("success");
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
