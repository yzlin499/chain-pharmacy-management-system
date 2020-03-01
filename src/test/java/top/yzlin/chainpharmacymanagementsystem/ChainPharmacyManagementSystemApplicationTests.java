package top.yzlin.chainpharmacymanagementsystem;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import top.yzlin.chainpharmacymanagementsystem.dao.*;
import top.yzlin.chainpharmacymanagementsystem.entity.*;
import top.yzlin.chainpharmacymanagementsystem.entity.pass.PassGoods;

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
        Page<Goods> goods = goodsDAO.commonSearchByStoreId(16, "123", PageRequest.of(0, 10));
        goods.toList().forEach(System.out::println);
    }

}
