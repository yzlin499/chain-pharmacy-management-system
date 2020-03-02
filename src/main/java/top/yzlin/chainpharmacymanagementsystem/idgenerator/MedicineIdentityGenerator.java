package top.yzlin.chainpharmacymanagementsystem.idgenerator;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentityGenerator;
import top.yzlin.chainpharmacymanagementsystem.entity.Medicine;

import java.io.Serializable;

public class MedicineIdentityGenerator extends IdentityGenerator {
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        if (object instanceof Medicine) {
            return ((Medicine) object).getId();
        } else {
            return super.generate(session, object);
        }
    }
}
