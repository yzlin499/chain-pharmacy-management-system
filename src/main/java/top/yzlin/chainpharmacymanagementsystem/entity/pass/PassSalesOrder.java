package top.yzlin.chainpharmacymanagementsystem.entity.pass;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import top.yzlin.chainpharmacymanagementsystem.entity.SalesOrder;
import top.yzlin.chainpharmacymanagementsystem.layuiannotations.LayuiTableField;
import top.yzlin.chainpharmacymanagementsystem.layuiannotations.LayuiTableHeader;

import java.util.Date;

@Data
@LayuiTableHeader(isCanDelete = false)
public class PassSalesOrder {
    @LayuiTableField("订单号")
    private Long id;
    @LayuiTableField("订单日期")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;
    @LayuiTableField("操作人")
    private String operateUser;
    @LayuiTableField("订单合计")
    private Double total;

    public PassSalesOrder() {
    }

    public PassSalesOrder(SalesOrder salesOrder) {
        id = salesOrder.getId();
        date = salesOrder.getDate();
        total = salesOrder.getTotal();
        operateUser = salesOrder.getOperateUser().getName();
    }
}
