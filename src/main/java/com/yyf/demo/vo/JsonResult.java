package com.yyf.demo.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author wangchen
 * @create 2019/12/02/19:08
 *
 * 基于此对象封装控制层对象的响应结果，在此对象中应该包含返回到客户端的数据
 * 以及一个状态码和状态信息
 */
@Getter
@Setter
@NoArgsConstructor
public class JsonResult implements Serializable {

    private static final long serialVersionUID = -7795815851642138272L;
    /*状态码*/
    private int state = 1;//1表示成功 0表示失败
    /*状态信息*/
    private String message = "OK";
    /*正确数据*/
    private Object data;

    /*查询时使用封装查询结果*/
    public JsonResult(Object data) {
        this.data = data;
    }

    public JsonResult(String message) {
        this.message = message;
    }
    /*出现异常时调用*/
    public JsonResult(Throwable t) {
        this.state = 0;
        this.message = t.getMessage();
    }
}
