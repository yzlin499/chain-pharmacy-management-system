package top.yzlin.chainpharmacymanagementsystem.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class LogData {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "HIBERNATE_SEQUENCES")
    @TableGenerator(name = "HIBERNATE_SEQUENCES", pkColumnValue = "log_key", allocationSize = 1)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;
    private String content;
}
