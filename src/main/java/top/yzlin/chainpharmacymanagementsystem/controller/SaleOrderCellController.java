package top.yzlin.chainpharmacymanagementsystem.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import top.yzlin.chainpharmacymanagementsystem.dao.*;
import top.yzlin.chainpharmacymanagementsystem.entity.*;
import top.yzlin.chainpharmacymanagementsystem.entity.pass.PassOrderCellTotal;
import top.yzlin.chainpharmacymanagementsystem.httpstatus.ForbiddenException;
import top.yzlin.tools.JsonTools;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
public class SaleOrderCellController {
    private SalesOrderCellDAO salesOrderCellDAO;
    private GoodsDAO goodsDAO;

    @Autowired
    public void setSalesOrderCellDAO(SalesOrderCellDAO salesOrderCellDAO) {
        this.salesOrderCellDAO = salesOrderCellDAO;
    }

    @Autowired
    public void setGoodsDAO(GoodsDAO goodsDAO) {
        this.goodsDAO = goodsDAO;
    }

    @GetMapping("/api/saleOrderCells/{date}")
    public ObjectNode findSaleOrderCellByDate(@PathVariable("date") String date) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            User user = (User) principal;
            Map<Long, PassOrderCellTotal> countMap = new HashMap<>();
            salesOrderCellDAO.findAllByStoreIdAndDate(user.getStore().getId(), date).forEach(i -> {
                Long id = i.getMedicine().getId();
                PassOrderCellTotal total;
                if (countMap.containsKey(id)) {
                    total = countMap.get(id);
                } else {
                    total = new PassOrderCellTotal();
                    total.setCount(0);
                    countMap.put(id, total);
                }
                total.setCount(total.getCount() + i.getCount());
            });
            return JsonTools.customResultData("storeGoodsCell", countMap.entrySet().stream().map(e -> {
                PassOrderCellTotal total = e.getValue();
                Goods goods = goodsDAO.findByStoreIdAndMedicineId(user.getStore().getId(), e.getKey());
                total.setId(goods.getMedicine().getId());
                total.setName(goods.getMedicine().getName());
                total.setPrice(goods.getPrice());
                total.setTotal(total.getPrice() * total.getCount());
                return total;
            }).collect(Collectors.toList()));
        }
        throw new ForbiddenException();
    }


}
