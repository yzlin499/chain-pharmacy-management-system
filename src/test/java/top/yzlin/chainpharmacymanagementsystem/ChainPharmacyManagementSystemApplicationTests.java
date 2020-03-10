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

    }

    @Test
    void testBean() {

    }

}
