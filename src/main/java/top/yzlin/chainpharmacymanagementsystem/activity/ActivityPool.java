package top.yzlin.chainpharmacymanagementsystem.activity;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class ActivityPool {
    private static final Map<String, BaseActivity> ACTIVITY_MAP = new HashMap<>();

    static {
        ACTIVITY_MAP.put("NothingActivity", NothingActivity.INSTANCE);
        ACTIVITY_MAP.put("FullReductionActivity", new FullReductionActivity());
    }

    public static BaseActivity getActivity(String type) {
        return Optional.ofNullable(ACTIVITY_MAP.get(type)).orElse(NothingActivity.INSTANCE);
    }
}
