package top.yzlin.chainpharmacymanagementsystem.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import top.yzlin.chainpharmacymanagementsystem.entity.Goods;
import top.yzlin.chainpharmacymanagementsystem.entity.SalesOrder;

@Repository
public interface SalesOrderDAO extends JpaRepository<SalesOrder, Long> {

    Page<SalesOrder> findAllByStoreId(Integer storeId, Pageable p);


    @Query("select s from SalesOrder s where s.store.id=:storeId and (s.id like CONCAT('%',:k,'%') or s.operateUser.name like CONCAT('%',:k,'%'))")
    Page<SalesOrder> commonSearch(@Param("k") String k, @Param("storeId") Integer storeId, Pageable p);

    @Query("select s from SalesOrder s where s.store.id=:storeId and s.date between :date and CONCAT(:date,' 23:59:59')")
    Page<SalesOrder> findByDate(@Param("date") String date, @Param("storeId") Integer storeId, Pageable p);
}
