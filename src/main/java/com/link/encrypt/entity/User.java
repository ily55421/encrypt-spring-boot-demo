package com.link.encrypt.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.link.encrypt.common.enums.UserType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author lin 2022/12/9 22:45
 */
@Data
public class User {
    private Integer id;
    private String name;
    private UserType userType = UserType.COMMON;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH")
    private LocalDateTime registerTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date registerTime1 = new Date();

    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(UserType.COMMON, SerializerFeature.WriteEnumUsingToString));
    }
}
