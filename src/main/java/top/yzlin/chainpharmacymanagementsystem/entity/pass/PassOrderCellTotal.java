package top.yzlin.chainpharmacymanagementsystem.entity.pass;

import lombok.Data;
import top.yzlin.chainpharmacymanagementsystem.layuiannotations.LayuiTableField;
import top.yzlin.chainpharmacymanagementsystem.layuiannotations.LayuiTableHeader;

@Data
@LayuiTableHeader(isCanDelete = false)
public class PassOrderCellTotal {
    @LayuiTableField("药品条形码")
    private Long id;
    @LayuiTableField("药品名字")
    private String name;
    @LayuiTableField("售出数量")
    private Integer count;
    @LayuiTableField("售出价格")
    private Double price;
    @LayuiTableField(value = "售出合计", totalRow = true)
    private Double total;
}
