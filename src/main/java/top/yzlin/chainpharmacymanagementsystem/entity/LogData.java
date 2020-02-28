package top.yzlin.chainpharmacymanagementsystem.entity;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
public class LogData {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "HIBERNATE_SEQUENCES")
    @TableGenerator(name = "HIBERNATE_SEQUENCES", pkColumnValue = "log_key", allocationSize = 1)
    private Long id;
    private Date date;
    private String content;
}
