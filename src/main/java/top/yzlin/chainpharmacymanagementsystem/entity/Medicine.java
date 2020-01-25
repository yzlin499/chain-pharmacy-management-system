package top.yzlin.chainpharmacymanagementsystem.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "medicine-uuid")
    @GenericGenerator(name = "medicine-uuid", strategy = "top.yzlin.chainpharmacymanagementsystem.idgenerator.MedicineIdentityGenerator")
    private Long id;
    private String name;
    private String brand;
    private String des;
    private Integer price;

}
