package top.yzlin.chainpharmacymanagementsystem.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import top.yzlin.chainpharmacymanagementsystem.dao.CustomerDAO;
import top.yzlin.chainpharmacymanagementsystem.dao.GoodsDAO;
import top.yzlin.chainpharmacymanagementsystem.dao.SalesOrderDAO;
import top.yzlin.chainpharmacymanagementsystem.entity.*;
import top.yzlin.chainpharmacymanagementsystem.entity.pass.PassGoods;
import top.yzlin.chainpharmacymanagementsystem.entity.pass.PassOrderCell;
import top.yzlin.chainpharmacymanagementsystem.entity.pass.PassOrderRequest;
import top.yzlin.chainpharmacymanagementsystem.entity.pass.PassSalesOrder;
import top.yzlin.chainpharmacymanagementsystem.httpstatus.BadRequestException;
import top.yzlin.chainpharmacymanagementsystem.httpstatus.ForbiddenException;
import top.yzlin.tools.JpaTools;
import top.yzlin.tools.JsonTools;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class SalesController {
    private SalesOrderDAO salesOrderDAO;
    private CustomerDAO customerDAO;
    private GoodsDAO goodsDAO;

    @Autowired
    public void setSalesOrderDAO(SalesOrderDAO salesOrderDAO) {
        this.salesOrderDAO = salesOrderDAO;
    }

    @Autowired
    public void setCustomerDAO(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @Autowired
    public void setGoodsDAO(GoodsDAO goodsDAO) {
        this.goodsDAO = goodsDAO;
    }

    @PostMapping("/api/salesOrders")
    public ResponseEntity<SalesOrder> createOrder(@RequestBody PassOrderRequest passOrderRequest) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            User user = (User) principal;
            Date date = new Date();
            Customer byPhone = null;
            if (!"".equals(Optional.ofNullable(passOrderRequest.getCustomerPhone()).orElse(""))) {
                byPhone = customerDAO.findByPhone(passOrderRequest.getCustomerPhone());
            }
            SalesOrder salesOrder = new SalesOrder();
            salesOrder.setCustomer(byPhone);
            salesOrder.setOperateUser(user);
            salesOrder.setTotal(0D);
            salesOrder.setDate(date);
            Customer finalByPhone = byPhone;
            salesOrder.setSalesOrderCellList(passOrderRequest.getOrderMap().entrySet().stream()
                    .map(e -> goodsDAO.findById(Long.parseLong(e.getKey())).map(goods -> {
                        if (goods.getCount() < e.getValue()) {
                            SalesOrderCell orderCell = new SalesOrderCell();
                            orderCell.setCustomer(finalByPhone);
                            orderCell.setOperateUser(user);
                            orderCell.setStore(user.getStore());
                            orderCell.setDate(date);
                            orderCell.setCount(e.getValue());
                            orderCell.setMedicine(goods.getMedicine());
                            salesOrder.setTotal(orderCell.getCount() * goods.getPrice() + salesOrder.getTotal());
                            goods.setCount(goods.getCount() - e.getValue());
                            goodsDAO.save(goods);
                            return orderCell;
                        } else {
                            throw new BadRequestException("库存不足");
                        }
                    }).orElseThrow(BadRequestException::new))
                    .collect(Collectors.toSet()));
            salesOrder.setStore(user.getStore());
            return new ResponseEntity<>(salesOrderDAO.save(salesOrder), HttpStatus.CREATED);
        }
        throw new ForbiddenException();
    }

    @GetMapping("/api/salesOrders")
    public ObjectNode findGoods(@RequestParam(value = "sort", required = false) String sort,
                                @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            User user = (User) principal;
            return JsonTools.customResultData("salesOrders", salesOrderDAO
                    .findAllByStoreId(user.getStore().getId(), JpaTools.createPageable(page, size, sort)).map(PassSalesOrder::new));
        }
        throw new ForbiddenException();
    }

    @GetMapping("/api/salesOrders/{orderId}/salesOrderCellList")
    public ObjectNode findById(@PathVariable("orderId") Long orderId) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            User user = (User) principal;
            SalesOrder salesOrder = salesOrderDAO.findById(orderId).orElseThrow(BadRequestException::new);
            if (Objects.equals(salesOrder.getStore().getId(), user.getStore().getId())) {
                Set<SalesOrderCell> salesOrderCellList = salesOrder.getSalesOrderCellList();
                return JsonTools.customResultData("salesOrderCellList", salesOrderCellList.stream().map(PassOrderCell::new).collect(Collectors.toList()));
            }
        }
        throw new ForbiddenException();
    }

    @GetMapping("/api/salesOrders/search/common")
    public ObjectNode searchCommon(@RequestParam(value = "k", required = false, defaultValue = "") String k,
                                   @RequestParam(value = "sort", required = false) String sort,
                                   @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                   @RequestParam(value = "size", required = false, defaultValue = "10") int size) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            User user = (User) principal;
            return JsonTools.customResultData("salesOrders",
                    salesOrderDAO.commonSearch(k, user.getStore().getId(), JpaTools.createPageable(page, size, sort)).map(PassSalesOrder::new));
        }
        throw new ForbiddenException();
    }

    @GetMapping("/api/salesOrders/search/date/{date}")
    public ObjectNode findByDate(@PathVariable("date") String date,
                                 @RequestParam(value = "sort", required = false) String sort,
                                 @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                 @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            User user = (User) principal;
            return JsonTools.customResultData("salesOrders",
                    salesOrderDAO.findByDate(date, user.getStore().getId(), JpaTools.createPageable(page, size, sort)).map(PassSalesOrder::new));
        }
        throw new ForbiddenException();
    }


}
