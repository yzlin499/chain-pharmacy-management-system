package top.yzlin.chainpharmacymanagementsystem.entity;

import lombok.Data;
import top.yzlin.chainpharmacymanagementsystem.layuiannotations.LayuiTableField;

import javax.persistence.*;

@Data
@Entity
public class Goods {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Medicine medicine;

    @LayuiTableField("商店ID")
    private Integer storeId;
    private Double price;
    private Integer count;

}
