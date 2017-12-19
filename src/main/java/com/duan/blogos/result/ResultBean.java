package com.duan.blogos.result;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created on 2017/12/13.
 * 返回结果统一定义，该类包装的结果可向页面传递
 *
 * @author DuanJiaNing
 */

@Getter
@Setter
public class ResultBean<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 结果状态为成功
     */
    public static final int SUCCESS = 0;

    /**
     * 结果状态为失败
     */
    public static final int FAIL = 1;

    private int code = SUCCESS;
    private String msg = "success";
    private T data;

    /**
     * 返回成功的构造函数
     *
     * @param data
     */
    public ResultBean(T data) {
        this.data = data;
    }

    /**
     * 返回错误（获取数据错误）的构造函数
     *
     * @param e
     */
    public ResultBean(Throwable e) {
        this.msg = e.toString();
        this.code = FAIL;
    }


}
