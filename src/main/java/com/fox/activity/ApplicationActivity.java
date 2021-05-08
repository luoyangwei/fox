package com.fox.activity;

/**
 * <p>
 * 应用Activity
 * </p>
 *
 * @author 番茄ICE
 * @since 2021/5/8 package: com.fox.activity
 */
public abstract class ApplicationActivity implements Activity {

    private String contextViewId;


    public ApplicationActivity() {
    }


    public ApplicationActivity(String contextViewId) {
        this.contextViewId = contextViewId;
    }


    /**
     * 设置视图
     */
    abstract protected void setContextView();


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
