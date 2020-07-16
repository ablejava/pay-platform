<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>手机网页支付</title>
</head>
<body>
	<form id="alipayForm" name="alipayForm" action="https://mapi.alipay.com/getway.do" method="get">
		<input type="hidden" id="body" name="body" value="${aliPayment.body}"/>
		<input type="hidden" id="subject" name="subject" value="${aliPayment.subject}"/>
		<input type="hidden" id="sign_type" name="sign_type" value="${aliPayment.sign_type}"/>
		<input type="hidden" id="notify_url" name="notify_url" value="${aliPayment.notify_url}"/>
		<input type="hidden" id="out_trade_no" name="out_trade_no" value="${aliPayment.out_trade_no}"/>
		<input type="hidden" id="return_url" name="return_url" value="${aliPayment.return_url}"/>
		<input type="hidden" id="sign" name="sign" value="${aliPayment.sign}"/>
		<input type="hidden" id="_input_charset" name="_input_charset" value="${aliPayment._input_charset}"/>
		<input type="hidden" id="total_fee" name="total_fee" value="${aliPayment.total_fee}"/>
		<input type="hidden" id="service" name="service" value="${aliPayment.service}"/>
		<input type="hidden" id="seller_id" name="seller_id" value="${aliPayment.seller_id}"/>
		<input type="hidden" id="partner" name="partner" value="${aliPayment.partner}"/>
		<input type="hidden" id="payment_type" name="payment_type" value="${aliPayment.payment_type}"/>
	</form>

</body>
<script type="text/javascript">
	var alipayForm = document.getElementById('alipayForm');
	alipayForm.submit();
</script>
</html>