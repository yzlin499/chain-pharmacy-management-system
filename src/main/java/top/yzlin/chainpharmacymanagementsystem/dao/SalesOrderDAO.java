package top.yzlin.chainpharmacymanagementsystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.yzlin.chainpharmacymanagementsystem.entity.SalesOrder;

@Repository
public interface SalesOrderDAO extends JpaRepository<SalesOrder, Long> {

}
