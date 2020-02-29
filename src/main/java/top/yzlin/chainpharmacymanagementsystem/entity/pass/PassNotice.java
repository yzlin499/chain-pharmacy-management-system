package top.yzlin.chainpharmacymanagementsystem.entity.pass;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import top.yzlin.chainpharmacymanagementsystem.entity.Notice;
import top.yzlin.chainpharmacymanagementsystem.layuiannotations.LayuiTableField;
import top.yzlin.chainpharmacymanagementsystem.layuiannotations.LayuiTableHeader;

import javax.persistence.*;
import java.util.Date;

@Data
@LayuiTableHeader(isCanDelete = false)
public class PassNotice {
    @LayuiTableField(enable = false)
    private Integer id;
    @LayuiTableField(value = "发布人", width = "12%")
    private String authorName;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")

    @LayuiTableField(value = "发布时间", width = "12%")
    private Date date;
    @LayuiTableField(value = "公告标题", width = "76%")
    private String title;
    @LayuiTableField(enable = false)
    private String content;

    public PassNotice() {

    }


    public PassNotice(Notice notice) {
        id = notice.getId();
        authorName = notice.getAuthor().getName();
        date = notice.getDate();
        title = notice.getTitle();
    }
}
