package com.duan.blogos.result;

import com.duan.blogos.exception.BaseRuntimeException;
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

    private int code = SUCCESS;
    private String msg = "success";
    private T data;

    /**
     * 返回成功的构造函数
     */
    public ResultBean(T data) {
        this.data = data;
    }

    /**
     * 返回错误（获取数据错误）的构造函数
     */
    public ResultBean(BaseRuntimeException e) {
        this.msg = e.getMessage();
        this.code = e.getCode();
    }


}
