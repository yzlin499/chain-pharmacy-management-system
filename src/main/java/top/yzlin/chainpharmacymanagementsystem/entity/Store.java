package top.yzlin.chainpharmacymanagementsystem.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Store {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String des;
    @OneToMany
    private Set<User> employees;

}
