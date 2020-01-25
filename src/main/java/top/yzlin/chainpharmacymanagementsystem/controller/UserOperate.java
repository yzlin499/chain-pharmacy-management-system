package top.yzlin.chainpharmacymanagementsystem.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yzlin.chainpharmacymanagementsystem.entity.User;

@RestController
@RequestMapping("/user")
public class UserOperate {

    @GetMapping
    public JSONObject getUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new JSONObject().fluentPut("isLogin", principal instanceof User)
                .fluentPut("data", principal);
    }
}
