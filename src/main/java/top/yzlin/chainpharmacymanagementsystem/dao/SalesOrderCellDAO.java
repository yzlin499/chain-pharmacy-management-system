package top.yzlin.chainpharmacymanagementsystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import top.yzlin.chainpharmacymanagementsystem.entity.SalesOrderCell;

import java.util.Date;
import java.util.List;

@Repository
public interface SalesOrderCellDAO extends JpaRepository<SalesOrderCell, Long> {
    @Query("select s from SalesOrderCell s where s.store.id=:storeId and s.date between :startDate and :endDate")
    List<SalesOrderCell> findAllByStoreIdAndDate(@Param("storeId") Integer storeId,
                                                 @Param("startDate") Date startDate,
                                                 @Param("endDate") Date endDate);
}
