package com.github.simpleoauth.core.service.enums;

public enum ErrorEnum {
    /*请求缺少必需的参数、包含不支持的参数值（除了许可类型）、重复参数、包含多个凭据、采用超过一种客户端身份验证机制或其他不规范的格式。*/
    invalid_request,
    
    /*客户端身份验证失败*/
    invalid_client,
    
    /*提供的授权许可（如授权码、资源所有者凭据）或刷新令牌无效、过期、吊销*/
    invalid_grant,

    /*进行身份验证的客户端没有被授权使用这种授权许可类型。*/
    unauthorized_client,

    /*授权许可类型不被授权服务器支持。*/
    unsupported_grant_type,

    /*请求的范围无效、未知的、格式不正确或超出资源所有者许可的范围。*/
    invalid_scope,
    
}
