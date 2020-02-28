package top.yzlin.chainpharmacymanagementsystem.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue
    private Integer id;
    private String authority;
    private String name;

}
