package com.daqinzhonggong.utils;

/**
 * CallBack接口定义了一种回调机制，允许在SpringContextHolder初始化后执行特定的任务。通过实现这个接口并重写executor()方法，开发者可以定义自己的回调任务。此外，getCallBackName()方法提供了一种默认的实现来获取回调任务的名称，但开发者可以根据需要重写它。
 *
 * @author free
 */
public interface CallBack {

    // 描述: 这是回调执行方法。当SpringContextHolder初始化完成后，该方法会被调用以执行回调任务。
    // 返回类型: void，表示该方法没有返回值。
    void executor();

    // 描述: 这是一个默认方法，用于获取当前回调任务的名称。
    // 返回类型: String，返回当前线程ID和当前类名的组合，作为回调任务的名称。
    // 实现: 默认实现是通过Thread.currentThread().getId()获取当前线程ID，this.getClass().getName()获取当前类的全限定名，然后将它们拼接起来作为回调任务的名称。
    default String getCallBackName() {
        return Thread.currentThread().getId() + ":" + this.getClass().getName();
    }

}

