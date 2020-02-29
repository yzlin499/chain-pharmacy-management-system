package top.yzlin.chainpharmacymanagementsystem.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import top.yzlin.chainpharmacymanagementsystem.dao.NoticeDAO;
import top.yzlin.chainpharmacymanagementsystem.entity.Notice;
import top.yzlin.chainpharmacymanagementsystem.entity.User;
import top.yzlin.chainpharmacymanagementsystem.entity.pass.PassNotice;
import top.yzlin.chainpharmacymanagementsystem.httpstatus.ForbiddenException;
import top.yzlin.tools.JpaTools;
import top.yzlin.tools.JsonTools;

import java.util.Date;

@RestController
public class NoticeController {
    private NoticeDAO noticeDAO;

    @Autowired
    public void setNoticeDAO(NoticeDAO noticeDAO) {
        this.noticeDAO = noticeDAO;
    }

    @GetMapping("/api/notices")
    public ObjectNode findAllNotice(@RequestParam(value = "sort", required = false) String sort,
                                    @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                    @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        return JsonTools.customResultData("notices", noticeDAO.findAll(
                JpaTools.createPageable(page, size, sort)).map(PassNotice::new));
    }

    @PostMapping("/api/notices")
    public ResponseEntity<PassNotice> createNotice(@RequestBody PassNotice notice) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            User user = (User) principal;
            Notice newNotice = new Notice();
            newNotice.setTitle(notice.getTitle());
            newNotice.setContent(notice.getContent());
            newNotice.setAuthor(user);
            newNotice.setDate(new Date());
            return new ResponseEntity<>(new PassNotice(noticeDAO.save(newNotice)), HttpStatus.CREATED);
        }
        throw new ForbiddenException();
    }


}
