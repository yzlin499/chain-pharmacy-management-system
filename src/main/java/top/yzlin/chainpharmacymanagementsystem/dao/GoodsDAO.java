package top.yzlin.chainpharmacymanagementsystem.dao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import top.yzlin.chainpharmacymanagementsystem.entity.Goods;

import javax.validation.constraints.NotNull;

@Repository
public interface GoodsDAO extends JpaRepository<Goods, Long> {
    Page<Goods> findAllByStoreId(Integer storeId, Pageable p);

    @Query("select m from Goods m where m.storeId =:storeId " +
            "and m.count>0 " +
            "and (m.medicine.id like CONCAT('%',:k,'%') " +
            "or m.medicine.name like CONCAT('%',:k,'%'))")
    Page<Goods> commonSearchByStoreId(@Param("storeId") Integer storeId, @Param("k") String k, Pageable p);

    Goods findByStoreIdAndMedicineId(Integer storeId, Long medicineId);

}
