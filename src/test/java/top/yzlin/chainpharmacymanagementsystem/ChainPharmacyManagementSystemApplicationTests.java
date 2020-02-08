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
    MedicineDAO medicineDAO;

    @Test
    void contextLoads() {
    }

}
