package top.yzlin.chainpharmacymanagementsystem.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import top.yzlin.chainpharmacymanagementsystem.entity.SalesOrder;
import top.yzlin.chainpharmacymanagementsystem.entity.SalesOrderCell;

@Repository
public interface SalesOrderCellDAO extends JpaRepository<SalesOrderCell, Long> {
    @Query("select s from SalesOrderCell s where s.store.id=:storeId and s.date between :date and CONCAT(:date,' 23:59:59')")
    Page<SalesOrderCell> findAllByStoreIdAndDate(@Param("storeId") Integer storeId, @Param("date") String date, Pageable p);
}
