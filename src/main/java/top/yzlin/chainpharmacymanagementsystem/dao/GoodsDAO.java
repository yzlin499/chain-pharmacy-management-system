package top.yzlin.chainpharmacymanagementsystem.dao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.yzlin.chainpharmacymanagementsystem.entity.Goods;

@Repository
public interface GoodsDAO extends JpaRepository<Goods, Long> {
    Page<Goods> findAllByStoreId(Integer storeId, Pageable p);
}
