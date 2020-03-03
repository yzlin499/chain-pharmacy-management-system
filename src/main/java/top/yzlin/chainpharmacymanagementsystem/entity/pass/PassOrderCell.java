package top.yzlin.chainpharmacymanagementsystem.entity.pass;

import lombok.Data;
import top.yzlin.chainpharmacymanagementsystem.entity.*;

@Data
public class PassOrderCell {
    private Integer count;
    private String userName;
    private Long medicineId;
    private String medicineName;

    public PassOrderCell() {
    }

    public PassOrderCell(SalesOrderCell salesOrderCell) {
        count = salesOrderCell.getCount();
        userName = salesOrderCell.getOperateUser().getName();
        medicineId = salesOrderCell.getMedicine().getId();
        medicineName = salesOrderCell.getMedicine().getName();
    }
}
