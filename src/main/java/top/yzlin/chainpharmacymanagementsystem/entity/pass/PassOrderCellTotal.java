package top.yzlin.chainpharmacymanagementsystem.entity.pass;

import lombok.Data;

@Data
public class PassOrderCellTotal {
    private Long id;
    private String name;
    private Integer count;
    private Double price;
    private Double total;
}
