package top.yzlin.chainpharmacymanagementsystem.entity.pass;

import lombok.Data;
import top.yzlin.chainpharmacymanagementsystem.entity.*;
import top.yzlin.chainpharmacymanagementsystem.layuiannotations.LayuiTableField;
import top.yzlin.chainpharmacymanagementsystem.layuiannotations.LayuiTableHeader;

@Data
@LayuiTableHeader(isCanDelete = false)
public class PassOrderCell {
    @LayuiTableField(value = "药品条形码", width = "170")
    private Long medicineId;
    @LayuiTableField(value = "药品名称", width = "230")
    private String medicineName;
    @LayuiTableField(value = "数量", width = "100")
    private Integer count;

    @LayuiTableField(enable = false)
    private String userName;

    public PassOrderCell() {
    }

    public PassOrderCell(SalesOrderCell salesOrderCell) {
        count = salesOrderCell.getCount();
        userName = salesOrderCell.getOperateUser().getName();
        medicineId = salesOrderCell.getMedicine().getId();
        medicineName = salesOrderCell.getMedicine().getName();
    }
}
