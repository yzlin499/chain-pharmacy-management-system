package top.yzlin.chainpharmacymanagementsystem.entity;

import lombok.Data;
import top.yzlin.chainpharmacymanagementsystem.layuiannotations.LayuiTableField;
import top.yzlin.chainpharmacymanagementsystem.layuiannotations.LayuiTableHeader;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * 顾客类
 *
 * @author yzlin
 */
@Entity
@Data
@LayuiTableHeader(type = LayuiTableHeader.TYPE_CHECKBOX)
public class Customer {
    @Id
    @GeneratedValue
    @LayuiTableField(enable = false)
    private Integer id;
    @LayuiTableField("姓名")
    private String name;
    @LayuiTableField("手机")
    private String phone;
    @LayuiTableField("创建时间")
    private Date createDate;
    @LayuiTableField(value = "积分", edit = LayuiTableField.EDIT_TYPE_TEXT)
    private Integer integral;
}
