package top.yzlin.chainpharmacymanagementsystem;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.yzlin.chainpharmacymanagementsystem.dao.*;
import top.yzlin.chainpharmacymanagementsystem.entity.*;

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

    @Autowired
    ActivityDAO activityDAO;

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
        Activity activity = new Activity();
        activity.setTitle("标准满减");
        activity.setContent("标准满减");
        activity.setType("FullReductionActivity");
        activity.setParam("100-20;200-50;300-80");
        activityDAO.save(activity);
    }

}
