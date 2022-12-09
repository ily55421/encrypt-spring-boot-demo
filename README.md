一个简单的加密测试demo



主要的需求点如下：

尽量少改动，不影响之前的业务逻辑；
考虑到时间紧迫性，可采用对称性加密方式，服务需要对接安卓、IOS、H5三端，另外考虑到H5端存储密钥安全性相对来说会低一些，故分针对H5和安卓、IOS分配两套密钥；
要兼容低版本的接口，后面新开发的接口可不用兼容；
接口有GET和POST两种接口，需要都要进行加解密；

需求解析：

服务端、客户端和H5统一拦截加解密，网上有成熟方案，也可以按其他服务中实现的加解密流程来搞；
使用AES放松加密，考虑到H5端存储密钥安全性相对来说会低一些，故分针对H5和安卓、IOS分配两套密钥；
本次涉及客户端和服务端的整体改造，经讨论，新接口统一加 /secret/ 前缀来区分


目前主要是利用ControllerAdvice来对请求和响应体进行拦截，
主要定义SecretRequestAdvice对请求进行加密和SecretResponseAdvice对响应进行加密
(实际情况会稍微复杂一点，项目中又GET类型请求，自定义了一个Filter进行不同的请求解密处理)。

Jackson之LocalDateTime转换，无需改实体类这篇文章讲到了这个问题，并提出了一种解决方案，
不过用在我们目前这个需求里面，就是有损改装了啊，不太可取，遂去Jackson官网上查找一下相关文档，
当然Jackson也提供了ObjectMapper的序列化配置

进而通过实例化的AbstractJackson2HttpMessageConverter对象找到执行序列化的核心方法
可以看出SpringMVC在进行响应序列化的时候是从容器中获取的ObjectMapper实例对象，并会根据不同的默认配置条件进行序列化，那处理方法就简单了，从Spring容器拿数据进行序列化

```JSON
{
  "code": 200,
  "data": [
    {
      "id": 1,
      "name": "boyka",
      "userType": {
        "code": "COMMON",
        "type": "普通用户"
      },
      "registerTime": "2022-12-09 23",
      "registerTime1": "2022-12-09 23:13:25"
    }
  ],
  "msg": "用户列表查询成功",
  "salt": 0,
  "signature": null
}

```

http://localhost:8080/secret/user/list

```JSON
{
  "data": "fxHYvnIE54eAXDbErdrDryEsIYNvsOOkyEKYB1iBcreItEvkUiTgW+rYpK7bRWgi6L/fxLIBMVQyseVWb7jOmip0Jy1dFyCRPWsJsXSpF+jG7VylgWF4uTbgorN+/QIGjmo2DDYkvMPsigTOoniMupYFNOdkeYI8LxzMVOsXy3ZPFuhedR55UxclxJ3V0eHLqokoRk/eHhn6ICRwLjJHISrryFNc4c+tDkwy2tJYXtcwg1VdDkhXuwgMS51A+DBkrP9EfgxCNnULAd2byFHnaQJeVjKj3s1LFdBjbu3K2UWSMvkd6FNq0g+89fYodHUB",
  "code": 200,
  "signature": "f29427837090b7aa828c2d3e7e2d286c",
  "msg": "",
  "timestamp": 1670598759,
  "salt": 572582
}
```