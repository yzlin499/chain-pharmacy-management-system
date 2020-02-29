package top.yzlin.chainpharmacymanagementsystem.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import top.yzlin.chainpharmacymanagementsystem.layuiannotations.LayuiTableField;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Store {
    @Id
    @GeneratedValue
    @LayuiTableField(enable = false)
    private Integer id;

    @LayuiTableField("名字")
    private String name;

    @LayuiTableField("地址")
    private String address;

    @LayuiTableField("创建日期")
    private Date createData;


    @LayuiTableField("备注信息")
    private String des;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @LayuiTableField(enable = false)
    private List<User> employees;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @LayuiTableField(enable = false)
    private List<LogData> employeesChangeLogger;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @LayuiTableField(enable = false)
    private List<LogData> storeLogger;

}
