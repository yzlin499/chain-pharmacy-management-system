package top.yzlin.chainpharmacymanagementsystem.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class LogData {

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @CreationTimestamp
    private Date date;

    public LogData() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "HIBERNATE_SEQUENCES")
    @TableGenerator(name = "HIBERNATE_SEQUENCES", pkColumnValue = "log_key", allocationSize = 1)
    private Long id;

    public LogData(String content) {
        this.content = content;
    }
    private String content;
}
