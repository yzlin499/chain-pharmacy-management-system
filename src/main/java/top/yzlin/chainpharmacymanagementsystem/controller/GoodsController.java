package top.yzlin.chainpharmacymanagementsystem.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import top.yzlin.chainpharmacymanagementsystem.dao.GoodsDAO;
import top.yzlin.chainpharmacymanagementsystem.dao.MedicineDAO;
import top.yzlin.chainpharmacymanagementsystem.entity.Goods;
import top.yzlin.chainpharmacymanagementsystem.entity.User;
import top.yzlin.chainpharmacymanagementsystem.entity.pass.PassGoods;
import top.yzlin.chainpharmacymanagementsystem.httpstatus.BadRequestException;
import top.yzlin.chainpharmacymanagementsystem.httpstatus.ForbiddenException;
import top.yzlin.tools.JpaTools;
import top.yzlin.tools.JsonTools;

import java.util.Objects;

@RestController
public class GoodsController {
    private GoodsDAO goodsDAO;
    private MedicineDAO medicineDAO;

    @Autowired
    public void setMedicineDAO(MedicineDAO medicineDAO) {
        this.medicineDAO = medicineDAO;
    }

    @Autowired
    public void setGoodsDAO(GoodsDAO goodsDAO) {
        this.goodsDAO = goodsDAO;
    }


    @GetMapping("/api/storeGoods/{storeId}/goods")
    public ObjectNode findGoods(@PathVariable("storeId") Integer storeId,
                                @RequestParam(value = "sort", required = false) String sort,
                                @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        return JsonTools.customResultData("goods", goodsDAO.findAllByStoreId(storeId,
                JpaTools.createPageable(page, size, sort)).map(PassGoods::new));
    }

    @GetMapping("/api/storeGoods/{storeId}/goods/search/common")
    public ObjectNode commonSearch(@PathVariable("storeId") Integer storeId,
                                   @RequestParam(value = "k", required = false, defaultValue = "") String k,
                                   @RequestParam(value = "sort", required = false) String sort,
                                   @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                   @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        return JsonTools.customResultData("goods",
                goodsDAO.commonSearchByStoreId(storeId, k, JpaTools.createPageable(page, size, sort)).map(PassGoods::new));
    }

    @PostMapping("/api/storeGoods/{storeId}/goods")
    public ResponseEntity<PassGoods> createGoods(@PathVariable("storeId") Integer storeId,
                                                 @RequestBody PassGoods goods) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            User user = (User) principal;
            if (Objects.equals(user.getStore().getId(), storeId)) {
                Goods newGoods = new Goods();
                newGoods.setMedicine(medicineDAO.findById(goods.getMedicineId()).orElseThrow(BadRequestException::new));
                newGoods.setCount(goods.getCount());
                newGoods.setPrice(goods.getPrice());
                newGoods.setStoreId(user.getStore().getId());
                return new ResponseEntity<>(new PassGoods(goodsDAO.save(newGoods)), HttpStatus.CREATED);
            }
        }
        throw new ForbiddenException();
    }


    @PutMapping("/api/storeGoods/{storeId}/goods/{goodsId}")
    public PassGoods updateGoods(@PathVariable("storeId") Integer storeId,
                                 @PathVariable("goodsId") Long goodsId,
                                 @RequestBody PassGoods goods) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            User user = (User) principal;
            if (Objects.equals(user.getStore().getId(), storeId)
                    && Objects.equals(goods.getStoreId(), storeId)
                    && Objects.equals(goods.getId(), goodsId)) {
                Goods goodsById = goodsDAO.findById(goodsId).orElseThrow(ForbiddenException::new);
                goodsById.setPrice(goods.getPrice());
                goodsById.setCount(goods.getCount());
                return new PassGoods(goodsDAO.save(goodsById));
            }
        }
        throw new ForbiddenException();
    }

    @DeleteMapping("/api/storeGoods/{storeId}/goods/{goodsId}")
    public ResponseEntity deleteGoods(@PathVariable("storeId") Integer storeId,
                                      @PathVariable("goodsId") Long goodsId) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            User user = (User) principal;
            if (Objects.equals(user.getStore().getId(), storeId)) {
                goodsDAO.deleteById(goodsId);
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        }
        throw new ForbiddenException();
    }

}
