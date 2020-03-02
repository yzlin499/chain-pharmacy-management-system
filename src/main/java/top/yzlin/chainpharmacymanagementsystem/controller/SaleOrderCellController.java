package top.yzlin.chainpharmacymanagementsystem.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import top.yzlin.chainpharmacymanagementsystem.dao.*;
import top.yzlin.chainpharmacymanagementsystem.entity.*;
import top.yzlin.chainpharmacymanagementsystem.entity.pass.PassOrderCellTotal;
import top.yzlin.chainpharmacymanagementsystem.httpstatus.ForbiddenException;
import top.yzlin.tools.JpaTools;
import top.yzlin.tools.JsonTools;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;


@RestController
public class SaleOrderCellController {
    private SalesOrderCellDAO salesOrderCellDAO;

    @Autowired
    public void setSalesOrderCellDAO(SalesOrderCellDAO salesOrderCellDAO) {
        this.salesOrderCellDAO = salesOrderCellDAO;
    }

    @GetMapping("/api/storeGoodsCell/{date}")
    public ObjectNode findGoods(@PathVariable("date") String date,
                                @RequestParam(value = "sort", required = false) String sort,
                                @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            User user = (User) principal;
            Map<Long, PassOrderCellTotal> countMap = new HashMap<>();
            Map<Long, Goods> countMap = new HashMap<>();
            salesOrderCellDAO.findAllByStoreIdAndDate(user.getStore().getId(), date, JpaTools.createPageable(page, size, sort))
                    .toList().forEach(i -> {
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
            countMap.entrySet().stream().map(e -> {

            })

            return JsonTools.customResultData("storeGoodsCell", allByStoreIdAndDate.map(PassOrderCellTotal::new));
        }
        throw new ForbiddenException();
    }
}
