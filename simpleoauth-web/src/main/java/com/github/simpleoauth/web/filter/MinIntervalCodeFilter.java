package com.cloud.ecloud.oauthweb.filter;

import com.cloud.ecloud.oauthweb.CodedException;
import com.cloud.ecloud.oauthweb.IConstants;
import com.cloud.ecloud.oauthweb.model.OauthCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * Created by xiongkw on 2018/1/9.
 */
@Component
public class MinIntervalCodeFilter implements ICodeFilter {
    @Value("${oauth.code.minIntervalInSeconds:60}000")
    private long minInterval;

    @Override
    public void filter(OauthCode code) {
        Timestamp create_time = code.getCreate_time();
        if (System.currentTimeMillis() - create_time.getTime() < minInterval) {
            throw new CodedException(IConstants.CODE_INVALID_REQUEST, "Too often code request!");
        }
    }
}
