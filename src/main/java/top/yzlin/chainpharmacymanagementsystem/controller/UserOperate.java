package top.yzlin.chainpharmacymanagementsystem.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yzlin.chainpharmacymanagementsystem.entity.Role;
import top.yzlin.chainpharmacymanagementsystem.entity.User;
import top.yzlin.chainpharmacymanagementsystem.httpstatus.ForbiddenException;

import java.io.IOException;

@RestController
public class UserOperate {

    private ObjectNode permissionsConfig;

    @Value("${permissions-config}")
    public void setPermissionsConfig(Resource resource) throws IOException {
        permissionsConfig = new ObjectMapper().readValue(resource.getFile(), ObjectNode.class);
    }

    @GetMapping("/api/user")
    public ObjectNode getUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ObjectMapper().createObjectNode()
                .put("isLogin", principal instanceof User)
                .putPOJO("data", principal);
    }

    @GetMapping("/api/permissions")
    public JsonNode getPermissions() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            User user = (User) principal;
            return permissionsConfig.get(user.getAuthorities().get(0).getAuthority());
        }
        throw new ForbiddenException();
    }
}
