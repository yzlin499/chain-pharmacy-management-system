package top.yzlin.chainpharmacymanagementsystem.entity.pass;

import lombok.Data;

import java.util.Map;

@Data
public class PassOrderRequest {
    private String customerPhone;
    private Map<String, Integer> orderMap;
}
