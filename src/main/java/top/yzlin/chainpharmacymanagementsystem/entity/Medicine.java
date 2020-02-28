package top.yzlin.chainpharmacymanagementsystem.entity;

import lombok.Data;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.rest.core.annotation.RestResource;
import top.yzlin.chainpharmacymanagementsystem.layuiannotations.LayuiFormField;
import top.yzlin.chainpharmacymanagementsystem.layuiannotations.LayuiTableField;
import top.yzlin.chainpharmacymanagementsystem.layuiannotations.LayuiTableHeader;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Data
@LayuiTableHeader(type = LayuiTableHeader.TYPE_CHECKBOX)
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "medicine-uuid")
    @GenericGenerator(name = "medicine-uuid", strategy = "top.yzlin.chainpharmacymanagementsystem.idgenerator.MedicineIdentityGenerator")
    @LayuiTableField("条形码ID")
    @NotNull(message = "条形码不能为空")
    private Long id;

    @LayuiTableField(value = "药品名称", edit = LayuiTableField.EDIT_TYPE_TEXT)
    private String name;

    @LayuiTableField("制药厂")
    @LayuiFormField(placeholder = "厂家")
    private String brand;
    @LayuiTableField("描述")
    @LayuiFormField(type = LayuiFormField.TYPE_TEXTAREA)
    private String des;
    @LayuiTableField("参考价格")
    private Double price;

}
