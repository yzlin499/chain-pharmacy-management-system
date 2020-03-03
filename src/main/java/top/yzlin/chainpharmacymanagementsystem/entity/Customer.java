package top.yzlin.chainpharmacymanagementsystem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import top.yzlin.chainpharmacymanagementsystem.layuiannotations.LayuiFormField;
import top.yzlin.chainpharmacymanagementsystem.layuiannotations.LayuiTableField;
import top.yzlin.chainpharmacymanagementsystem.layuiannotations.LayuiTableHeader;

import javax.persistence.*;
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
    @LayuiFormField(enable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    @LayuiTableField(value = "积分", edit = LayuiTableField.EDIT_TYPE_TEXT)
    @LayuiFormField(enable = false)
    private Integer integral = 0;
}
