package com.github.simpleoauth.core.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.github.simpleoauth.core.entity.OauthAppInfo;
import com.github.simpleoauth.core.service.enums.ErrorEnum;
import com.github.simpleoauth.core.service.utils.CodeGenerator;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.simpleoauth.core.dao.IOauthAppInfoDao;
import com.github.simpleoauth.core.entity.req.param.RegisterParam;
import com.github.simpleoauth.core.entity.rsp.param.ErrorResponse;

@RestController
public class OauthAppInfoService {

    @Autowired
    private IOauthAppInfoDao oauthAppInfoDao;
    
    /**
     * 参数（appCode, appName）由调用方保证正确性
     * @param param
     * @param result
     * @return
     */
    @PostMapping(value="/api/app/register")
    public Object registerApplication(@RequestBody @Valid RegisterParam param,
            HttpServletResponse resp, BindingResult result) {
        if(result.hasErrors()) {
            ErrorResponse response =  new ErrorResponse();
            List<ObjectError> errors = result.getAllErrors();
            StringBuilder builder = new StringBuilder();
            for(ObjectError e : errors) {
                builder.append(e.getDefaultMessage());
            }
            response.setError(ErrorEnum.invalid_request.name());
            response.setError_description(builder.toString());
            resp.setStatus(HttpStatus.SC_BAD_REQUEST);
            return response;
        }

        OauthAppInfo info = oauthAppInfoDao.selectByPrimaryKey(param.getApp_code());
        if(info == null) {// 没有则新增
            info = new OauthAppInfo();
            info.setApp_code(param.getApp_code());
            info.setApp_key(CodeGenerator.generate8());
            info.setApp_secret(CodeGenerator.generate32());
            info.setRedirect_url(param.getRedirect_url());
            info.setApp_name(param.getApp_name());
            info.setImage_url(param.getImage_url());
            oauthAppInfoDao.insertSelective(info);
        } else {// 存在则修改
            info.setRedirect_url(param.getRedirect_url());
            info.setImage_url(param.getImage_url());
            info.setApp_name(param.getApp_name());
            oauthAppInfoDao.updateByPrimaryKey(info);
        }
        
        Map<String, String> resMap = new HashMap<String, String>(2);
        resMap.put("app_key", info.getApp_key());
        resMap.put("app_secret", info.getApp_secret());
        return resMap;
    }

    @GetMapping(value="/api/app/info")
    public Object getAppInfo(String app_code, HttpServletResponse resp) {
        if(app_code == null) {
            resp.setStatus(HttpStatus.SC_BAD_REQUEST);
            ErrorResponse response =  new ErrorResponse();
            response.setError(ErrorEnum.invalid_request.name());
            response.setError_description("param [app_code] cannot be null");
            return response;
        }
        OauthAppInfo info = oauthAppInfoDao.selectByPrimaryKey(app_code);
        if(info == null) {
            resp.setStatus(HttpStatus.SC_BAD_REQUEST);
            ErrorResponse response =  new ErrorResponse();
            response.setError(ErrorEnum.invalid_request.name());
            response.setError_description("cannot find app info by app_code:" + app_code);
            return response;
        }
        return info;
    }

    @PostMapping("/api/app/refreshSecret")
    public Object refreshAppSecret(@RequestParam(required=true)String app_key,
            @RequestParam(required=true) String app_secret, HttpServletResponse resp) {
        ErrorResponse response =  new ErrorResponse();
        if(app_key == null) {
            resp.setStatus(HttpStatus.SC_BAD_REQUEST);
            response.setError(ErrorEnum.invalid_request.name());
            response.setError_description("param [app_key] cannot be null");
            return response;
        }
        if(app_secret == null) {
            resp.setStatus(HttpStatus.SC_BAD_REQUEST);
            response.setError(ErrorEnum.invalid_request.name());
            response.setError_description("param [app_secret] cannot be null");
            return response;
        }

        OauthAppInfo appInfo = oauthAppInfoDao.selectByAppkey(app_key);
        if(appInfo == null || !app_secret.equals(appInfo.getApp_secret())) {
            resp.setStatus(HttpStatus.SC_BAD_REQUEST);
            response.setError(ErrorEnum.invalid_client.name());
            response.setError_description("validation error, please check the value of parameters");
            return response;
        }

        String newAppSecret = CodeGenerator.generate32();
        appInfo.setApp_secret(newAppSecret);
        oauthAppInfoDao.updateByPrimaryKey(appInfo);
        Map<String, String> resMap = new HashMap<String, String>(2);
        resMap.put("app_key", appInfo.getApp_key());
        resMap.put("app_secret", appInfo.getApp_secret());
        return resMap;
    }
}
