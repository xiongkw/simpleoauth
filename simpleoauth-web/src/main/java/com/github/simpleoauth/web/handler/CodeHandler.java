package com.cloud.ecloud.oauthweb.handler;

import com.cloud.ecloud.oauthweb.filter.ICodeFilter;
import com.cloud.ecloud.oauthweb.IConstants;
import com.cloud.ecloud.oauthweb.model.OauthCode;
import com.cloud.ecloud.oauthweb.model.CodeRequest;
import com.cloud.ecloud.oauthweb.service.IOAuthService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by xiongkw on 2018/1/9.
 */
@Component
public class CodeHandler implements IOauthHandler<CodeRequest, String> {

    @Value("${oauth.code.length:64}")
    private int codeStringLength;

    @Value("${oauth.code.expiresInSeconds:7776000}")
    private int codeExpiresIn;

    @Autowired
    private IOAuthService oAuthService;

    @Autowired(required = false)
    private List<ICodeFilter> codeFilterList;

    @Override
    public boolean support(CodeRequest request) {
        return IConstants.ResponseType.code.name().equalsIgnoreCase(request.getResponse_type());
    }

    @Override
    public String handle(CodeRequest request) {
        OauthCode latest = oAuthService.getLatestCodeByAppKey(request.getApp_key());
        filterCode(latest);
        String code = RandomStringUtils.randomAscii(codeStringLength);
        OauthCode c = OauthCode.fromCodeRequest(request);
        c.setCode(code);
        c.setExpires_in(codeExpiresIn);
        oAuthService.saveCode(c);
        StringBuilder sb = new StringBuilder(request.getRedirect_url()).append("?code=").append(code);
        String state = request.getState();
        if (state != null) {
            sb.append("&state=").append(state);
        }
        return sb.toString();
    }

    private void filterCode(OauthCode lastCode) {
        if (codeFilterList == null || lastCode == null) {
            return;
        }
        for (ICodeFilter filter : codeFilterList) {
            filter.filter(lastCode);
        }
    }


}
