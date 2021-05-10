package com.fox.activity;

/**
 * <p>
 * Activity
 * </p>
 *
 * @author 番茄ICE
 * @since 2021/5/8 package: com.fox.activity
 */
public interface Activity {

    /**
     * 在实例创建完成后被立即调用。在这一步，实例已完成以下的配置：数据观测 (data observer)，property 和方法的运算，watch/event 事件回调。然而，挂载阶段还没开始，$el property
     * 目前尚不可用。
     */
    void onCreate();


    /**
     * 实例被挂载后调用，这时 el 被新创建的 vm.$el 替换了。如果根实例挂载到了一个文档内的元素上，当 mounted 被调用时 vm.$el 也在文档内。注意 mounted
     * 不会保证所有的子组件也都一起被挂载。如果你希望等到整个视图都渲染完毕，可以在 mounted 内部使用 vm.$nextTick：
     */
    void onStart();


    /**
     * 数据更新时调用，发生在虚拟 DOM 打补丁之前。这里适合在更新之前访问现有的 DOM，比如手动移除已添加的事件监听器。
     */
    void onBeforeUpdate();


    /**
     * 数据更新时调用，发生在虚拟 DOM 打补丁之前。这里适合在更新之前访问现有的 DOM，比如手动移除已添加的事件监听器。 由于数据更改导致的虚拟 DOM 重新渲染和打补丁，在这之后会调用该钩子。 当这个钩子被调用时，组件 DOM
     * 已经更新，所以你现在可以执行依赖于 DOM 的操作。然而在大多数情况下，你应该避免在此期间更改状态。如果要相应状态改变，通常最好使用计算属性或 watcher 取而代之。 注意 updated
     * 不会保证所有的子组件也都一起被重绘。如果你希望等到整个视图都重绘完毕，可以在 updated 里使用 vm.$nextTick：
     */
    void onUpdated();


    /**
     * 停止（再定义）
     */
    void onStop();


    /**
     * 销毁（再定义）
     */
    void onDestroy();

}
