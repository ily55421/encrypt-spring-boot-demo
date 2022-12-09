一个简单的加密测试demo

请求示例
http://localhost:8080/user/list

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