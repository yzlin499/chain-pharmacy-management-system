package top.yzlin.chainpharmacymanagementsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import top.yzlin.chainpharmacymanagementsystem.activity.ActivityPool;
import top.yzlin.chainpharmacymanagementsystem.activity.BaseActivity;
import top.yzlin.chainpharmacymanagementsystem.layuiannotations.LayuiTableField;

import javax.persistence.*;
import java.util.Optional;

@Data
@Entity
public class Activity {
    @Id
    @GeneratedValue
    @LayuiTableField(enable = false)
    private Integer id;

    @LayuiTableField(enable = false)
    private String type;

    @LayuiTableField("活动标题")
    private String title;

    @LayuiTableField("活动描述")
    private String content;

    @LayuiTableField(enable = false)
    private String param;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "store_id")
    @JsonIgnoreProperties(ignoreUnknown = true, value = {"employees"})
    @LayuiTableField(enable = false)
    private Store store;

    @Transient
    @JsonIgnore
    @LayuiTableField(enable = false)
    private BaseActivity activity;

    @Transient
    @LayuiTableField(enable = false)
    private String[] args;

    @Transient
    @LayuiTableField("活动类型")
    private String typeValue;

    private BaseActivity initActivity() {
        activity = ActivityPool.getActivity(type);
        args = param.split(";");
        return activity;
    }

    public Double settleAccountsSingle(Customer customer, Goods goods, Integer count) {
        return Optional.ofNullable(activity).orElseGet(this::initActivity)
                .settleAccountsSingle(this, args, customer, goods, count);
    }

    public Double settleAccountsAll(Customer customer, SalesOrder salesOrder) {
        return Optional.ofNullable(activity).orElseGet(this::initActivity)
                .settleAccountsAll(this, args, customer, salesOrder);
    }

    public String[] getArgs() {
        return Optional.ofNullable(args).orElseGet(() -> args = param.split(";"));
    }

    public String getTypeValue() {
        return Optional.ofNullable(typeValue).orElseGet(() -> typeValue = ActivityPool.getActivity(type).getName());
    }
}
