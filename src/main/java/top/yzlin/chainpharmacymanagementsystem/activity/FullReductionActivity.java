package top.yzlin.chainpharmacymanagementsystem.activity;

import top.yzlin.chainpharmacymanagementsystem.entity.Activity;
import top.yzlin.chainpharmacymanagementsystem.entity.Customer;
import top.yzlin.chainpharmacymanagementsystem.entity.Goods;
import top.yzlin.chainpharmacymanagementsystem.entity.SalesOrder;

import java.util.regex.Pattern;

public class FullReductionActivity implements BaseActivity {
    private static final Pattern PARAM_CHECK = Pattern.compile("(\\d+-\\d+;)*(\\d+-\\d+)");

    @Override
    public boolean paramCheck(String param) {
        return PARAM_CHECK.matcher(param).matches();
    }

    @Override
    public String getName() {
        return "满减活动";
    }

    @Override
    public Double settleAccountsSingle(Activity activity, String[] param, Customer customer, Goods goods, Integer count) {
        return null;
    }

    @Override
    public Double settleAccountsAll(Activity activity, String[] param, Customer customer, SalesOrder salesOrder) {
        return null;
    }
}
