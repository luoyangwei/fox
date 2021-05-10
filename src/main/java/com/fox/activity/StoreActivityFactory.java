package com.fox.activity;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * Activity 工厂
 * </p>
 *
 * @author 番茄ICE
 * @since 2021/5/10 package: com.fox.activity
 */
public class StoreActivityFactory {


    private final List<Activity> activityCollection = Collections.synchronizedList(new ArrayList<>());


    public void registerActivity(Activity activity) {
        activityCollection.add(activity);
    }


    public List<Activity> getActivityCollection() {
        return this.activityCollection;
    }




}
