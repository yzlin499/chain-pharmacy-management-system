package top.yzlin.chainpharmacymanagementsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.userdetails.UserDetails;
import top.yzlin.chainpharmacymanagementsystem.layuiannotations.LayuiTableField;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Role> authorities;
    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;
}
