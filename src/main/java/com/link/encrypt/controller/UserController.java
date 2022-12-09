package com.link.encrypt.controller;

import com.alibaba.fastjson.JSON;
import com.link.encrypt.common.base.ResponseBasic;
import com.link.encrypt.common.enums.UserType;
import com.link.encrypt.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.link.encrypt.common.constants.SecretConstant.PREFIX;

/**
 * @author lin 2022/12/9 22:57
 */
@Slf4j
@RestController
@RequestMapping(value = {"/user", PREFIX + "/user"})
public class UserController {
    @RequestMapping("/list")
    ResponseBasic<List<User>> listUser() {
        List<User> users = new ArrayList<>();
        User u = new User();
        u.setId(1);
        u.setName("boyka");
        u.setRegisterTime(LocalDateTime.now());
        u.setUserType(UserType.COMMON);
        users.add(u);
        ResponseBasic<List<User>> response = new ResponseBasic<>();
        response.setCode(200);
        response.setData(users);
        response.setMsg("用户列表查询成功");
        return response;
    }

    @RequestMapping("/save")
    ResponseBasic<Boolean> saveUser(@RequestBody User user) {
        // ... 新建用户
        log.info("save user ok: {}", JSON.toJSON(user));
        ResponseBasic<Boolean> response = new ResponseBasic<>();
        response.setCode(200);
        response.setData(Boolean.TRUE);
        response.setMsg("用户创建成功");
        return response;
    }
}

