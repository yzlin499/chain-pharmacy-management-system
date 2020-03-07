package top.yzlin.chainpharmacymanagementsystem;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import top.yzlin.chainpharmacymanagementsystem.dao.RoleDAO;
import top.yzlin.chainpharmacymanagementsystem.dao.UserDAO;
import top.yzlin.chainpharmacymanagementsystem.entity.Role;
import top.yzlin.chainpharmacymanagementsystem.entity.User;

import java.util.Collections;

@SpringBootApplication
public class ChainPharmacyManagementSystemApplication implements InitializingBean {
    private UserDAO userDAO;
    private String defaultHeader;
    private RoleDAO roleDAO;

    @Value("default-header")
    public void setDefaultHeader(String defaultHeader) {
        this.defaultHeader = defaultHeader;
    }

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Autowired
    public void setRoleDAO(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    public static void main(String[] args) {
        SpringApplication.run(ChainPharmacyManagementSystemApplication.class, args);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (roleDAO.count() == 0) {
            Role role = new Role();
            role.setName("店长");
            role.setAuthority("ROLE_SHOP_MANAGER");
            roleDAO.save(role);
            role = new Role();
            role.setName("销售员");
            role.setAuthority("ROLE_SALESMAN");
            roleDAO.save(role);
            role = new Role();
            role.setName("仓库管理员");
            role.setAuthority("ROLE_WAREHOUSE_KEEPER");
            roleDAO.save(role);
            role = new Role();
            role.setName("管理员");
            role.setAuthority("ROLE_ADMIN");
            roleDAO.save(role);
        }
        if (userDAO.count() == 0) {
            User user = new User();
            user.setAuthorities(Collections.singletonList(roleDAO.findByAuthority("ROLE_ADMIN")));
            user.setName("初始管理员");
            user.setUsername("admin");
            user.setPassword("123456");
            user.setImage(defaultHeader);
            userDAO.save(user);
        }
    }
}
