package top.yzlin.chainpharmacymanagementsystem.entity.pass;

import lombok.Data;
import top.yzlin.chainpharmacymanagementsystem.entity.Customer;

import java.util.List;
import java.util.Map;

@Data
public class PassSalesOrder {
    private String customerPhone;
    private Map<String, Integer> orderMap;
}
