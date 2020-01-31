package top.yzlin.chainpharmacymanagementsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;
import top.yzlin.chainpharmacymanagementsystem.layuiannotations.LayuiTableField;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class User implements UserDetails {
    @LayuiTableField(enable = false)
    public static final String ADMIN = "ADMIN";

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String username;

    @JsonIgnore
    private String password;
    private String image;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Role> authorities;
    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;
}
