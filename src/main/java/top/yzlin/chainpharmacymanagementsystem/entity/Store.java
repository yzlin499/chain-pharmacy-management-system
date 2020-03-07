package top.yzlin.chainpharmacymanagementsystem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import top.yzlin.chainpharmacymanagementsystem.layuiannotations.LayuiFormField;
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

    @LayuiTableField(value = "名字", edit = LayuiTableField.EDIT_TYPE_TEXT)
    private String name;

    @LayuiTableField(value = "地址", edit = LayuiTableField.EDIT_TYPE_TEXT)
    private String address;

    @LayuiTableField("创建日期")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @LayuiFormField(enable = false)
    @CreationTimestamp
    private Date createData;


    @LayuiTableField(value = "备注信息", edit = LayuiTableField.EDIT_TYPE_TEXT)
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
