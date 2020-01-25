package top.yzlin.chainpharmacymanagementsystem.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Data
public class User implements UserDetails {
    public static final String ADMIN = "ADMIN";

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String username;
    @JSONField(serialize = false)
    private String password;
    private String image;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Role> authorities;
    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;
}
