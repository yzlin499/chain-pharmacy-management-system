package top.yzlin.chainpharmacymanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.yzlin.chainpharmacymanagementsystem.activity.ActivityPool;
import top.yzlin.chainpharmacymanagementsystem.dao.ActivityDAO;
import top.yzlin.chainpharmacymanagementsystem.dao.StoreDAO;
import top.yzlin.chainpharmacymanagementsystem.entity.Activity;
import top.yzlin.chainpharmacymanagementsystem.entity.User;
import top.yzlin.chainpharmacymanagementsystem.httpstatus.BadRequestException;
import top.yzlin.chainpharmacymanagementsystem.httpstatus.ForbiddenException;

@RestController
public class ActivityController {
    private StoreDAO storeDAO;
    private ActivityDAO activityDAO;

    @Autowired
    public void setStoreDAO(StoreDAO storeDAO) {
        this.storeDAO = storeDAO;
    }

    @Autowired
    public void setActivityDAO(ActivityDAO activityDAO) {
        this.activityDAO = activityDAO;
    }

    @PostMapping("/api/activity")
    public ResponseEntity<Activity> createActivities(@RequestBody Activity activity) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            User user = (User) principal;
            if (!ActivityPool.getActivity(activity.getType()).paramCheck(activity.getParam())) {
                throw new BadRequestException();
            }
            activity.setStore(storeDAO.findById(user.getStore().getId()).orElseThrow(BadRequestException::new));
            return new ResponseEntity<>(activityDAO.save(activity), HttpStatus.CREATED);
        }
        throw new ForbiddenException();
    }

}
