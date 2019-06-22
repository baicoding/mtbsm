package org.msgtu.sys.controller;

import org.msgtu.sys.entry.User;
import org.msgtu.sys.service.intf.TokenManagerService;
import org.msgtu.sys.service.intf.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Wonshine on 2017-07-20.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService service;

    @Autowired
    private TokenManagerService tmService;

    @GetMapping("/login/{login}")
    public ResponseEntity<String> login(@PathVariable("login") String login, @RequestParam("password") String password) {
        User user = service.findByUserLogin(login);
        if (user != null) {
            String realpassword = password;
            if (!StringUtils.isEmpty(realpassword) && realpassword.equals(user.getUserPsw())) {
                String token=tmService.createToken(user, "");
                logger.info(String.format("%s login",token));
                return new ResponseEntity(token, HttpStatus.OK);
            }else{
                return new ResponseEntity("用户名或者密码错误",HttpStatus.FORBIDDEN);
            }
        }
        return new ResponseEntity("用户名或者密码错误",HttpStatus.FORBIDDEN);
    }

    @GetMapping("/token/{token}")
    public ResponseEntity<User> findByToken(@PathVariable("token") String token) {
        User user = tmService.getUserInfo(token);
        User o = new User();//摘除掉用户的敏感信息（如密码）再返回给前端
        o.setUserId(user.getUserId());
        o.setUserLogin(user.getUserLogin());
        o.setUserName(user.getUserName());
        if (user != null) {
            return new ResponseEntity(o, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);//根据token为能获取用户信息，返回401
        }
    }

    @DeleteMapping("/logout/{token}")
    public ResponseEntity<Object> logout(@PathVariable("token") String token) {
        logger.info(String.format("%s loginout",token));
        tmService.deleteToken(token);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
