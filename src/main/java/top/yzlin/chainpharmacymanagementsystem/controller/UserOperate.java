package top.yzlin.chainpharmacymanagementsystem.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import top.yzlin.chainpharmacymanagementsystem.dao.RoleDAO;
import top.yzlin.chainpharmacymanagementsystem.dao.UserDAO;
import top.yzlin.chainpharmacymanagementsystem.entity.Goods;
import top.yzlin.chainpharmacymanagementsystem.entity.LogData;
import top.yzlin.chainpharmacymanagementsystem.entity.Role;
import top.yzlin.chainpharmacymanagementsystem.entity.User;
import top.yzlin.chainpharmacymanagementsystem.entity.pass.PassGoods;
import top.yzlin.chainpharmacymanagementsystem.entity.pass.PassUser;
import top.yzlin.chainpharmacymanagementsystem.httpstatus.BadRequestException;
import top.yzlin.chainpharmacymanagementsystem.httpstatus.ForbiddenException;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

@RestController
public class UserOperate {
    private UserDAO userDAO;
    private ObjectNode permissionsConfig;
    private RoleDAO roleDAO;
    private String defaultHeader;

    @Autowired
    public void setRoleDAO(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Value("${permissions-config}")
    public void setPermissionsConfig(Resource resource) throws IOException {
        permissionsConfig = new ObjectMapper().readValue(resource.getFile(), ObjectNode.class);
    }

    @Value("${default-header}")
    public void setDefaultHeader(String defaultHeader) throws IOException {
        this.defaultHeader = defaultHeader;
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

    
    @PutMapping("/api/user/{userId}")
    public PassUser updateUser(@PathVariable("userId") Long userId,
                               @RequestBody PassUser passUser) {
        User user = userDAO.findById(userId).orElseThrow(BadRequestException::new);
        user.setName(passUser.getName());
        user.setImage(passUser.getImage());
        user.setPhone(passUser.getPhone());
        user.getAuthorities().set(0, roleDAO.findByAuthority(passUser.getAuthority()));
        return new PassUser(userDAO.save(user));
    }

    @PostMapping("/api/users")
    public ResponseEntity<User> createUser(@RequestBody User createUser) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            User operateUser = (User) principal;
            createUser.setStore(operateUser.getStore());
            createUser.setAuthorities(Collections.singletonList(roleDAO.findByAuthority("ROLE_SALESMAN")));
            createUser.setImage(defaultHeader);
            createUser.setUserLogger(Collections.singletonList(new LogData(createUser.getName() + "账号初始化")));
            return new ResponseEntity<>(userDAO.save(createUser), HttpStatus.CREATED);
        }
        throw new ForbiddenException();
    }

    @DeleteMapping("/api/user/{userId}")
    public ResponseEntity setUserDisEnable(@PathVariable("userId") Long userId) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            User user = (User) principal;
            User user1 = userDAO.findById(userId).orElseThrow(BadRequestException::new);
            if (Objects.equals(user.getStore().getId(), user1.getStore().getId())) {
                user1.setEnabled(false);
                userDAO.save(user1);
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        }
        throw new ForbiddenException();
    }
}
