package com.cjwsc.idcm.base;


/**
 *  作者：yzr
 *  电话：18826585609
 *  邮箱：956942189@qq.com
 *  版本号：1.0
 *  类描述： 抽取View 接口中公共方法 处理相同回调更新UI
 *  备注消息：
 *  修改时间：2016/11/15 上午9:56
 **/
public interface CommonView {
    /**
     *  提示成功信息
     * @param msg
     */
    void showSuccessWithStatus(String msg);

    /**
     *  提示错误信息
     * @param msg
     */
    void showErrorWithStatus(String msg);

    /**
     *  提示消息
     * @param msg
     */
    void showsInfoWithStatus(String msg);
    /**
     *  进度框
     * @param msg
     */
    void showWithProgress(String msg);

    /**
     *
     */
    void dismiss();
}
