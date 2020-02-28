package top.yzlin.chainpharmacymanagementsystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yzlin.chainpharmacymanagementsystem.entity.User;

@RestController
public class UserOperate {

    @GetMapping("/api/user")
    public ObjectNode getUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ObjectMapper().createObjectNode()
                .put("isLogin", principal instanceof User)
                .putPOJO("data", principal);
    }
}
