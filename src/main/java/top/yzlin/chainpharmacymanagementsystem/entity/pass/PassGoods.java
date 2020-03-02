package top.yzlin.chainpharmacymanagementsystem.entity.pass;

import lombok.Data;
import top.yzlin.chainpharmacymanagementsystem.entity.Goods;
import top.yzlin.chainpharmacymanagementsystem.layuiannotations.LayuiFormField;
import top.yzlin.chainpharmacymanagementsystem.layuiannotations.LayuiTableField;
import top.yzlin.chainpharmacymanagementsystem.layuiannotations.LayuiTableHeader;


@Data
@LayuiTableHeader(type = LayuiTableHeader.TYPE_CHECKBOX, tableName = "goods")
public class PassGoods {
    @LayuiTableField(enable = false)
    private Long id;
    @LayuiTableField("药品条形码")
    private Long medicineId;
    @LayuiTableField("药品名称")
    @LayuiFormField(enable = false)
    private String medicineName;
    @LayuiTableField("参考价格")
    @LayuiFormField(enable = false)
    private Double originalPrice;
    @LayuiTableField(enable = false)
    private Integer storeId;
    @LayuiTableField(value = "实际价格", edit = LayuiTableField.EDIT_TYPE_TEXT)
    private Double price;
    @LayuiTableField(value = "商店库存", edit = LayuiTableField.EDIT_TYPE_TEXT)
    private Integer count;

    public PassGoods() {
    }

    public PassGoods(Goods goods) {
        this.id = goods.getId();
        this.medicineId = goods.getMedicine().getId();
        this.medicineName = goods.getMedicine().getName();
        this.originalPrice = goods.getMedicine().getPrice();
        this.storeId = goods.getStoreId();
        this.price = goods.getPrice();
        this.count = goods.getCount();
    }
}
