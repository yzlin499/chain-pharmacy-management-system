package top.yzlin.chainpharmacymanagementsystem;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.yzlin.chainpharmacymanagementsystem.dao.MedicineDAO;
import top.yzlin.chainpharmacymanagementsystem.dao.RoleDAO;
import top.yzlin.chainpharmacymanagementsystem.dao.UserDAO;
import top.yzlin.chainpharmacymanagementsystem.entity.Medicine;
import top.yzlin.chainpharmacymanagementsystem.entity.Role;
import top.yzlin.chainpharmacymanagementsystem.entity.User;

import java.util.Arrays;
import java.util.Collections;

@SpringBootTest
class ChainPharmacyManagementSystemApplicationTests {

    @Autowired
    UserDAO userDAO;

    @Autowired
    RoleDAO roleDAO;

    @Autowired
    MedicineDAO medicineDAO;

    @Test
    void contextLoads() {
        Role role = new Role();
        role.setAuthority("ROLE_ADMIN");
        roleDAO.save(role);
        User user = new User();
        user.setAuthorities(Arrays.asList(role));
        user.setPassword("q1w2e3r4");
        user.setUsername("admin");
        user.setImage("//tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg");
        user.setName("一号管理员");
        userDAO.save(user);
//        Medicine medicine=new Medicine();
//        medicine.setName("抗病毒口服液");
//        medicine.setDes("清热祛湿，凉血解毒，用于风热感冒，流感");
//        medicine.setId(6_923848_600727L);
//        medicine.setPrice(20);
//        medicine.setBrand("三九药业");
//        medicineDAO.save(medicine);

//        tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg
    }

}
