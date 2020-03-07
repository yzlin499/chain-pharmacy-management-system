package top.yzlin.chainpharmacymanagementsystem.activity;

import top.yzlin.chainpharmacymanagementsystem.entity.Activity;
import top.yzlin.chainpharmacymanagementsystem.entity.Customer;
import top.yzlin.chainpharmacymanagementsystem.entity.Goods;
import top.yzlin.chainpharmacymanagementsystem.entity.SalesOrder;

public class NothingActivity implements BaseActivity {
    public static final NothingActivity INSTANCE = new NothingActivity();

    private NothingActivity() {

    }

    @Override
    public boolean paramCheck(String param) {
        return true;
    }

    @Override
    public String getName() {
        return "无用活动";
    }

    @Override
    public Double settleAccountsSingle(Activity activity, String[] param, Customer customer, Goods goods, Integer count) {
        return goods.getPrice() * count;
    }

    @Override
    public Double settleAccountsAll(Activity activity, String[] param, Customer customer, SalesOrder salesOrder) {
        return salesOrder.getTotal();
    }
}
