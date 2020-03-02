package top.yzlin.chainpharmacymanagementsystem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Data
public class SalesOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "medicine-uuid")
    @GenericGenerator(name = "medicine-uuid", strategy = "top.yzlin.chainpharmacymanagementsystem.idgenerator.SalesOrderIdentityGenerator")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;

    @ManyToOne
    @JsonIgnore
    private User operateUser;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Store store;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<SalesOrderCell> salesOrderCellList;
}
