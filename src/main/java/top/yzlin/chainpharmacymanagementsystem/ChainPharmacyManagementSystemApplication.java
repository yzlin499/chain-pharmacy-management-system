package top.yzlin.chainpharmacymanagementsystem;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import top.yzlin.chainpharmacymanagementsystem.dao.GoodsDAO;
import top.yzlin.chainpharmacymanagementsystem.entity.Goods;

@SpringBootApplication
public class ChainPharmacyManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChainPharmacyManagementSystemApplication.class, args);
    }
}
