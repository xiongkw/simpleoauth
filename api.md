# OAuth Server接口

# 1. oauthz

oauth后端服务

### 1.1 应用注册

用于注册第三方应用信息，若应用信息不存在则insert；若存在则update，返回key和secret

#### 1.1.1 请求

```
url: /api/app/register
method: POST
Content-Type: application/json
```

| 参数名 | 类型 | 可选 | 描述 |
| --- | --- | --- | --- |
| app\_code | String | 必选 | 应用对应的编码 |
| redirect\_url | String | 必选 | 回调地址 |
| app\_name | String | 可选 | 应用名称 |
| image\_url | String | 可选 | 应用图标url |

#### 1.1.2 响应

```
Content-Type: application/json
```

| key | 类型 | 描述 |
| --- | --- | --- |
| app\_key | String | 应用在Oauth Server端的标识 |
| app\_secret | String | 应用在Oauth Server端的密码 |

### 1.2 查询应用信息

用于查询应用注册信息

#### 1.2.1 请求

```
url: /api/app/info
method: GET
```

| 参数名 | 类型 | 可选 | 描述 |
| --- | --- | --- | --- |
| app\_code | String | 必选 | 创建的应用标识 |

#### 1.2.2 响应

```
Content-Type: application/json
```

| key | 类型 | 描述 |
| --- | --- | --- |
| app\_key | String | 应用在Oauth Server端的标识 |
| app\_secret | String | 应用在Oauth Server端的密码 |
| app\_code | String | 应用编码 |
| redirect\_url | String | 回调地址 |
| app\_name | String | 应用名称 |
| image\_url | String | 应用图标url |

### 1.3 重新生成app\_secret

重新生成app\_secret

#### 1.3.1 请求

```
url：/api/app/refreshSecret
method：POST
Content-Type: application/x-www-form-urlencoded
```

| 参数名 | 类型 | 可选 | 描述 |
| --- | --- | --- | --- |
| app\_key | String | 必选 | 应用在Oauth Server端的标识 |
| app\_secret | String | 必选 | 应用在Oauth Server端的密码 |

#### 1.3.2 响应

```
Content-Type: application/json
```

| key | 类型 | 描述 |
| --- | --- | --- |
| app\_key | String | 应用在Oauth Server端的标识 |
| app\_secret | String | 新的Oauth Server端应用密码 |

### 1.4 访问令牌验证

用于验证访问令牌并返回租户信息

#### 1.4.1 请求

```
url：/api/authorize/validateToken
method：POST
Content-Type: application/x-www-form-urlencoded
```

| 参数名 | 类型 | 可选 | 描述 |
| --- | --- | --- | --- |
| assess\_token | String | 必选 | 授权服务器颁发的访问令牌 |

#### 1.4.2 响应

```
Content-Type: application/json
```

| key | 类型 | 描述 |
| --- | --- | --- |
| userId | Long | access\_token对应的授权用户标识 |
| tenantId | Long | access\_token对应的授权租户标识 |
| tenantCode | String | 授权租户编码 |

## 2. oauthweb

oauth前端服务

### 2.1 授权许可

用于获取用户授权码

#### 2.1.1 请求

```
url：/authorize
method：GET
```

| 参数名 | 类型 | 可选 | 描述 |
| --- | --- | --- | --- |
| response\_type | String | 必选 | 固定取值为code |
| app\_key | String | 必选 | 应用在Oauth Server端的标识 |
| redirect\_url | String | 必选 | 生成授权码之后跳转的地址 |
| scope | String | 可选 | 申请的权限范围 |
| state | String | 可选 | 一个随机值，oauth server在跳转到redirect\_url时会原封不动的返回这个值，客户端须验证此数据的一致性 |

#### 2.1.2 响应

通过重定向方式传递参数code，例如：

```
http://example.com?code=SplxlOBeZQQYbYS6WxSbIA
```

### 2.2 获取访问令牌

客户端使用2.1中获取到的用户授权码进一步获取访问令牌

#### 2.2.1 请求

```
url：/token
method：POST
Content-Type: application/x-www-form-urlencoded
```

| 参数名 | 类型 | 可选 | 描述 |
| --- | --- | --- | --- |
| grant\_type | String | 必选 | 固定取值为authorization\_code |
| code | String | 必选 | 从授权服务器收到的授权码 |
| app\_key | String | 必选 | 应用在Oauth Server端的标识 |
| app\_secret | String | 必选 | 应用在Oauth Server端的密码 |

#### 2.2.2 响应

```
Content-Type: application/json
```

| key | 类型 | 描述 |
| --- | --- | --- |
| access\_token | String | 授权服务器颁发的访问令牌 |
| token\_type | String | 固定取值bearer，意思是在请求中包含访问令牌字符串来使用 |
| expires\_in | Integer | 以秒为单位的访问令牌生命周期,如值“3600”表示访问令牌将在从生成响应时的1小时后到期 |
| refresh\_token | String | 刷新令牌，用于获取新的访问令牌数据 |

### 2.3 刷新访问令牌

用于刷新访问令牌

#### 2.3.1 请求

```
url：/token
method：POST
Content-Type: application/x-www-form-urlencoded
```

| 参数名 | 类型 | 可选 | 描述 |
| --- | --- | --- | --- |
| grant\_type | String | 必选 | 固定取值为refresh\_token |
| refresh\_token | String | 必选 | 刷新令牌 |

#### 2.3.2 响应

```
Content-Type: application/json
```

| key | 类型 | 描述 |
| --- | --- | --- |
| assess\_token | String | 授权服务器颁发的访问令牌 |
| token\_type | String | 固定取值bearer，意思是在请求中包含访问令牌字符串来使用 |
| expires\_in | Integer | 以秒为单位的访问令牌生命周期,如值“3600”表示访问令牌将在从生成响应时的1小时后到期 |
| refresh\_token | String | 刷新令牌，用于获取新的访问令牌数据 |

## 3. 授权过程中的错误响应

授权服务器使用HTTP 400（错误请求）状态码响应，在响应中包含下列参数：

* error 必需的。值：ba

  * invalid\_request  
    请求缺少必需的参数、包含不支持的参数值、重复参数、包含多个凭据、采用超过一种客户端身份验证机制或其他不规范的格式。

  * invalid\_client  
    客户端身份验证失败（例如，未知的客户端，不包含客户端身份验证，或不支持的身份验证方法）。授权服务器可以返回HTTP 401（未授权）状态码来指出支持的HTTP身份验证方案。

  * invalid\_grant  
    提供的授权许可（如授权码、资源所有者凭据）或刷新令牌无效、过期、吊销、与在授权请求使用的重定向URI不匹配。

  * unauthorized\_client  
    进行身份验证的客户端没有被授权使用这种授权许可类型。

  * unsupported\_grant\_type  
    授权许可类型不被授权服务器支持。

* error\_description  
  可选的。提供额外信息的人类可读的ASCII\[USASCII\]文本，用于协助客户端开发人员理解所发生的错误。



