package top.yzlin.chainpharmacymanagementsystem;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.yzlin.chainpharmacymanagementsystem.dao.MedicineDAO;
import top.yzlin.chainpharmacymanagementsystem.dao.RoleDAO;
import top.yzlin.chainpharmacymanagementsystem.dao.StoreDAO;
import top.yzlin.chainpharmacymanagementsystem.dao.UserDAO;
import top.yzlin.chainpharmacymanagementsystem.entity.Medicine;
import top.yzlin.chainpharmacymanagementsystem.entity.Role;
import top.yzlin.chainpharmacymanagementsystem.entity.Store;
import top.yzlin.chainpharmacymanagementsystem.entity.User;

import java.util.*;

@SpringBootTest
class ChainPharmacyManagementSystemApplicationTests {

    @Autowired
    StoreDAO storeDAO;

    @Autowired
    UserDAO userDAO;

    @Autowired
    RoleDAO roleDAO;

    @Test
    void contextLoads() {
        Optional<User> user = userDAO.findById(2L);
        Optional<Store> store = storeDAO.findById(16);
        Store store1 = store.get();
        store1.getEmployees().add(user.get());
        storeDAO.save(store1);

    }

}
