package top.yzlin.chainpharmacymanagementsystem;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import top.yzlin.chainpharmacymanagementsystem.dao.*;
import top.yzlin.chainpharmacymanagementsystem.entity.*;
import top.yzlin.chainpharmacymanagementsystem.entity.pass.PassGoods;
import top.yzlin.tools.JpaTools;

import java.text.SimpleDateFormat;

@SpringBootTest
class ChainPharmacyManagementSystemApplicationTests {

    @Autowired
    StoreDAO storeDAO;

    @Autowired
    UserDAO userDAO;

    @Autowired
    GoodsDAO goodsDAO;

    @Autowired
    RoleDAO roleDAO;

    @Autowired
    MedicineDAO medicineDAO;

    @Autowired
    SalesOrderDAO salesOrderDAO;

    @Test
    void contextLoads() {
        medicineDAO.findAll().forEach(i -> {
            Goods goods = new Goods();
            goods.setCount(10);
            goods.setPrice(60D);
            goods.setStoreId(16);
            goods.setMedicine(i);
            goodsDAO.save(goods);
        });
    }

    @Test
    void testBean() {
//        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
//        simpleDateFormat.parse("2020-03-02")
//        Page<SalesOrder> byDate = salesOrderDAO.findByDate("2020-03-02", 16, PageRequest.of(0, 10));
//        System.out.println(byDate.toList());
        System.out.println(salesOrderDAO
                .findAllByStoreId(16, JpaTools.createPageable(0, 10)).toList());
    }

}
