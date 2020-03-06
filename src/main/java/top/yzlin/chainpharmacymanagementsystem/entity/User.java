package top.yzlin.chainpharmacymanagementsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import top.yzlin.chainpharmacymanagementsystem.layuiannotations.LayuiFormField;
import top.yzlin.chainpharmacymanagementsystem.layuiannotations.LayuiTableField;

import javax.persistence.*;
import java.util.List;

@Entity
public class User implements UserDetails {
    @LayuiTableField(enable = false)
    public static final String ADMIN = "ADMIN";
    @Id
    @GeneratedValue
    @LayuiTableField(enable = false)
    @Getter
    @Setter
    private Long id;
    @LayuiTableField("账号")
    @Getter
    @Setter
    private String username;
    @LayuiTableField("密码")
    @LayuiFormField(type = LayuiFormField.TYPE_PASSWORD)
    private String password;
    @LayuiTableField("姓名")
    @Getter
    @Setter
    private String name;
    @LayuiTableField("手机")
    @Getter
    @Setter
    private String phone;
    @LayuiTableField(enable = false)
    @Getter
    @Setter
    private String image;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "store_id")
    @JsonIgnoreProperties(ignoreUnknown = true, value = {"employees"})
    @LayuiTableField(enable = false)
    @Getter
    @Setter
    private Store store;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @LayuiTableField(enable = false)
    @Getter
    @Setter
    private List<Role> authorities;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @LayuiTableField(enable = false)
    @Getter
    @Setter
    private List<LogData> userLogger;

    @LayuiTableField(enable = false)
    @Getter
    @Setter
    private boolean accountNonExpired = true;
    @LayuiTableField(enable = false)
    @Getter
    @Setter
    private boolean accountNonLocked = true;
    @Getter
    @Setter
    @LayuiTableField(enable = false)
    private boolean credentialsNonExpired = true;
    @Getter
    @Setter
    @LayuiTableField(enable = false)
    private boolean enabled = true;

    @Override
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
