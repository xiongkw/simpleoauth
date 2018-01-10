package com.github.simpleoauth.core.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.github.simpleoauth.core.dao.IOauthAccessTokenDao;
import com.github.simpleoauth.core.entity.OauthAccessToken;
import com.github.simpleoauth.core.entity.rsp.param.ErrorResponse;
import com.github.simpleoauth.core.service.enums.ErrorEnum;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenValidationService {

    @Autowired
    private IOauthAccessTokenDao oauthAccessTokenDao;

    @PostMapping("/api/authorize/validateToken")
    public Object validateAccessToken(@RequestParam(required=true)String access_token,
            HttpServletResponse resp) {
        if(access_token == null) {
            resp.setStatus(HttpStatus.SC_BAD_REQUEST);
            ErrorResponse response =  new ErrorResponse();
            response.setError(ErrorEnum.invalid_request.name());
            response.setError_description("param [access_token] cannot be null");
            return response;
        }
        OauthAccessToken accessToken = oauthAccessTokenDao.selectByAccessTokenCode(access_token);
        String msg = validateToken(accessToken);
        if(msg != null) {
            resp.setStatus(HttpStatus.SC_BAD_REQUEST);
            ErrorResponse response =  new ErrorResponse();
            response.setError(ErrorEnum.invalid_grant.name());
            response.setError_description(msg);
            return response;
        }
        Map<String, Object> res = new HashMap<String, Object>(3);
        res.put("userId", accessToken.getUser_id());
        res.put("tenantId", accessToken.getTenant_id());
        res.put("tenantCode", accessToken.getTenant_code());
        return res;
    }

    private String validateToken(OauthAccessToken accessToken) {
        if(accessToken == null) {
            return "Cannot find token";
        }
        Date createTime = accessToken.getCreate_time();
        Integer expireSeconds = accessToken.getExpires_in();
        // 当前时间距离创建时间大于过期时间，说明token已经过期
        if(System.currentTimeMillis() - createTime.getTime() > expireSeconds * 1000  ) {
            return "Token[" + accessToken.getAccess_token() + "] has expired";
        }
        return null;
    }
}
