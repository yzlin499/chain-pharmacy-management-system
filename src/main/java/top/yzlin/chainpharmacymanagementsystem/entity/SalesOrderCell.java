package top.yzlin.chainpharmacymanagementsystem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class SalesOrderCell {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "HIBERNATE_SEQUENCES")
    @TableGenerator(name = "HIBERNATE_SEQUENCES", pkColumnValue = "sales_log_key", allocationSize = 1)
    private Long id;

    private Integer count;


    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;

    @ManyToOne
    private User operateUser;

    @ManyToOne
    private Store store;

    @ManyToOne
    private Medicine medicine;

    @ManyToOne
    private Customer customer;

}
