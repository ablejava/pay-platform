/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.alipay.life.servlet.auth;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alipay.life.constants.AlipayServiceEnvConstants;

/**
 * 用户信息共享（网页授权获取用户信息）
 * 
 * @author taixu.zqq
 * @version $Id: LoginAuthServlet.java, v 0.1 2014年7月25日 下午5:13:03 taixu.zqq Exp $
 */
public class LoginAuthServlet extends HttpServlet {

    /**  */
    private static final long serialVersionUID = -6807693807426739985L;

    /**
     * 业务处理
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        //1. 解析请求参数
        String url = "https://openauth.alipay.com/oauth2/publicAppAuthorize.html?app_id="+AlipayServiceEnvConstants.APP_ID
                +"&scope=auth_user"
                +"&redirect_uri="+ URLEncoder.encode("www.ablejava.com/loginAuthServlet", "utf-8");
        response.sendRedirect(url);

    }

}
