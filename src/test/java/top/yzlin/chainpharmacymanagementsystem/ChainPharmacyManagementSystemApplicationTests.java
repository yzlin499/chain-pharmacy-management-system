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

import java.util.Arrays;
import java.util.Collections;

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
//        User user=new User();
//        user.setName("初始店长");
//        user.setUsername("newShopManager");
//        user.setImage("//tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg");
//        user.setPassword("q1w2e3r4");
//        user.setAuthorities(Collections.singletonList(roleDAO.getOne(5)));
//        userDAO.save(user);

//        user=new User();
//        user.setName("二号仓库管理员");
//        user.setUsername("user2");
//        user.setImage("//tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg");
//        user.setPassword("q1w2e3r4");
//        user.setAuthorities(Collections.singletonList(roleDAO.getOne(3)));
//        User one = userDAO.findById(14L).get();
//        one.setAuthorities(Arrays.asList(roleDAO.findById(3).get()));
//        userDAO.save(one);
        User one = userDAO.findById(2L).get();
        one.getAuthorities().add(roleDAO.findById(1).get());
        userDAO.save(one);
//        Store
//
//        storeDAO.save();
    }

}
