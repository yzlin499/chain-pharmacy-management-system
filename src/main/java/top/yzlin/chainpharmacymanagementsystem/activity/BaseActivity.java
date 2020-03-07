package top.yzlin.chainpharmacymanagementsystem.activity;

import top.yzlin.chainpharmacymanagementsystem.entity.Activity;
import top.yzlin.chainpharmacymanagementsystem.entity.Customer;
import top.yzlin.chainpharmacymanagementsystem.entity.Goods;
import top.yzlin.chainpharmacymanagementsystem.entity.SalesOrder;

/**
 * @author yzlin
 */
public interface BaseActivity {

    /**
     * 检查参数时候正确
     *
     * @param param 参数字符串
     * @return 结果
     */
    boolean paramCheck(String param);

    /**
     * 获取中文名
     *
     * @return 名字
     */
    String getName();

    /**
     * 单个获取结算
     *
     * @param activity activity
     * @param param    param
     * @param customer customer
     * @param goods    goods
     * @param count    count
     * @return
     */
    Double settleAccountsSingle(Activity activity, String[] param, Customer customer, Goods goods, Integer count);

    Double settleAccountsAll(Activity activity, String[] param, Customer customer, SalesOrder salesOrder);

}
