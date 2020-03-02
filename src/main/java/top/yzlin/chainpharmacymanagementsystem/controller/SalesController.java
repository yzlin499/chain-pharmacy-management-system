package top.yzlin.chainpharmacymanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.yzlin.chainpharmacymanagementsystem.dao.CustomerDAO;
import top.yzlin.chainpharmacymanagementsystem.dao.GoodsDAO;
import top.yzlin.chainpharmacymanagementsystem.dao.MedicineDAO;
import top.yzlin.chainpharmacymanagementsystem.dao.SalesOrderDAO;
import top.yzlin.chainpharmacymanagementsystem.entity.*;
import top.yzlin.chainpharmacymanagementsystem.entity.pass.PassSalesOrder;
import top.yzlin.chainpharmacymanagementsystem.httpstatus.BadRequestException;
import top.yzlin.chainpharmacymanagementsystem.httpstatus.ForbiddenException;

import java.util.Date;
import java.util.Optional;
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
    public ResponseEntity<SalesOrder> createOrder(@RequestBody PassSalesOrder passSalesOrder) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            User user = (User) principal;
            Date date = new Date();
            Customer byPhone = null;
            if (!"".equals(Optional.ofNullable(passSalesOrder.getCustomerPhone()).orElse(""))) {
                byPhone = customerDAO.findByPhone(passSalesOrder.getCustomerPhone());
            }
            SalesOrder salesOrder = new SalesOrder();
            salesOrder.setCustomer(byPhone);
            salesOrder.setOperateUser(user);
            salesOrder.setDate(date);
            Customer finalByPhone = byPhone;
            salesOrder.setSalesOrderCellList(passSalesOrder.getOrderMap().entrySet().stream()
                    .map(e -> goodsDAO.findById(Long.parseLong(e.getKey())).map(goods -> {
                        if (goods.getCount() < e.getValue()) {
                            SalesOrderCell orderCell = new SalesOrderCell();
                            orderCell.setCustomer(finalByPhone);
                            orderCell.setOperateUser(user);
                            orderCell.setStore(user.getStore());
                            orderCell.setDate(date);
                            orderCell.setCount(e.getValue());
                            orderCell.setMedicine(goods.getMedicine());
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
}
