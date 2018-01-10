package com.cloud.ecloud.oauthweb.controller;

import com.cloud.ecloud.oauthweb.CodedException;
import com.cloud.ecloud.oauthweb.IConstants;
import com.cloud.ecloud.oauthweb.handler.IOauthHandler;
import com.cloud.ecloud.oauthweb.model.AppInfo;
import com.cloud.ecloud.oauthweb.model.CodeRequest;
import com.cloud.ecloud.oauthweb.model.UserInfo;
import com.cloud.ecloud.oauthweb.service.IAppService;
import com.cloud.ecloud.oauthweb.service.IUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by xiongkw on 2018/1/8.
 */
@Controller
public class AuthorizeController {

    @Autowired
    private IAppService appService;

    @Autowired
    private IUserService userService;

    @Autowired
    private List<IOauthHandler<CodeRequest,String>> codeHandlers;

    @RequestMapping(value = "/authorize", method = RequestMethod.GET)
    public String authorize(CodeRequest req, HttpSession session) {
        for (IOauthHandler<CodeRequest,String> handler : codeHandlers) {
            if (handler.support(req)) {
                break;
            }
            throw new CodedException(IConstants.CODE_INVALID_REQUEST, "Unsupported request [" + req + "]");
        }
        String app_key = req.getApp_key();
        if (StringUtils.isEmpty(app_key)) {
            throw new CodedException(IConstants.CODE_INVALID_REQUEST, "Invalid parameter app_key: [" + app_key + "]");
        }
        AppInfo appInfo = appService.getAppInfoByKey(app_key);
        if (appInfo == null) {
            throw new CodedException(IConstants.CODE_INVALID_REQUEST, "App [" + app_key + "] not exists!");
        }
        String redirect_url = req.getRedirect_url();
        if (!StringUtils.equals(redirect_url, appInfo.getRedirect_url())) {
            throw new CodedException(IConstants.CODE_INVALID_CLIENT, "Invalid parameter redirect_url: [" + redirect_url + "]");
        }
        UserInfo user = (UserInfo) session.getAttribute(IConstants.SESSION_KEY_USER_INFO);
        if (user != null) {
            return "authorize";
        }
        session.setAttribute(IConstants.SESSION_KEY_APP_INFO, appInfo);
        session.setAttribute(IConstants.SESSION_KEY_OAUTH_REQUEST, req);
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session) {
        CodeRequest req = (CodeRequest) session.getAttribute(IConstants.SESSION_KEY_OAUTH_REQUEST);
        if (req == null) {
            throw new CodedException(IConstants.CODE_INVALID_REQUEST, "Session expired!");
        }
        UserInfo userInfo = userService.authorize(username, password);
        if (userInfo == null) {
            //TODO 用户认证时的错误信息
            return "login";
        }
        session.setAttribute(IConstants.SESSION_KEY_USER_INFO, userInfo);
        return "authorize";
    }

    @RequestMapping(value = "/authorize/confirm", method = RequestMethod.POST)
    public String confirm(HttpSession session) {
        CodeRequest req = (CodeRequest) session.getAttribute(IConstants.SESSION_KEY_OAUTH_REQUEST);
        if (req == null) {
            throw new CodedException(IConstants.CODE_INVALID_REQUEST, "Session expired!");
        }
        for (IOauthHandler<CodeRequest, String> handler : codeHandlers) {
            if (handler.support(req)) {
                String redirectUrl =  handler.handle(req);
                return "redirect:" + redirectUrl;
            }
        }
        throw new CodedException(IConstants.CODE_INVALID_REQUEST, "Unsupported request [" + req + "]");
    }

}
