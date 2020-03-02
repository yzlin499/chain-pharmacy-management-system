package top.yzlin.chainpharmacymanagementsystem.idgenerator;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentityGenerator;
import top.yzlin.chainpharmacymanagementsystem.entity.Medicine;
import top.yzlin.chainpharmacymanagementsystem.entity.SalesOrder;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

/**
 * 这个id在9224年的时候会发生溢出
 */
public class SalesOrderIdentityGenerator extends IdentityGenerator {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    private long idCount = 0;

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        idCount++;
        if (object instanceof SalesOrder) {
            SalesOrder salesOrder = (SalesOrder) object;
            return Long.parseLong(sdf.format(Optional.ofNullable(salesOrder.getDate()).orElse(new Date()) + "00000")) + (idCount % 100000);
        } else {
            return super.generate(session, object);
        }
    }
}
