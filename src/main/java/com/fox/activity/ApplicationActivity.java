package com.fox.activity;

/**
 * <p>
 * 应用Activity
 * </p>
 *
 * @author 番茄ICE
 * @since 2021/5/8 package: com.fox.activity
 */
public abstract class ApplicationActivity extends StoreActivityFactory implements Activity {

    private String contextViewId;


    public ApplicationActivity() {
    }


    public ApplicationActivity(String contextViewId) {
        // 设置了视图
        this.contextViewId = contextViewId;
        // 加入当前Activity
        setContextView();
    }


    /**
     * 设置视图
     */
    abstract protected Activity setContextView();


    @Override
    public void onCreate() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onBeforeUpdate() {

    }

    @Override
    public void onUpdated() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

}
