package top.yzlin.chainpharmacymanagementsystem.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import top.yzlin.chainpharmacymanagementsystem.layuiannotations.LayuiTableField;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Goods {
    @Id
    @GeneratedValue
    @LayuiTableField("货物ID")
    @NotNull(message = "条形码不能为空")
    private Integer id;

    @ManyToOne
    private Medicine medicine;
    private Integer storeId;
    private Double price;
    private Integer count;

}
