package com.born.bc.commons.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.pagehelper.PageInfo;

import java.io.Serializable;

/**
 * ResultJson
 * 统一restful接口返回
 * @author wangjian
 *
 */
public class ResultJson implements Serializable {

    private static final long serialVersionUID = -5425361471916352020L;

    /**
     * 状态
     */
    private Integer result;
    /**
     * 消息
     */
    private String msg;
    /**
     * 数据
     */
    private Object data;
    /**
     * 当前页码
     */
    private Integer pageIndex;
    /**
     * 每页显示条数
     */
    private Integer pageSize;
    /**
     * 总条数
     */
    private Long total;

    /**
     * 构造函数
     * @param result
     * @param msg
     * @param data
     * @param pageIndex
     * @param pageSize
     * @param total
     */
    public ResultJson(Integer result, String msg, Object data, Integer pageIndex, Integer pageSize, Long total){
        this.result = result;
        this.msg = msg;
        this.data = data;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.total = total;
    }


    /**
     * 操作成功返回
     * @param msg
     * @return
     */
    public static ResultJson buildSuccess(String msg){
        return new ResultJson(RequestStatusEnum.SUCCESS.getStatus(),msg,null,null,null,null);
    }

    /**
     * 操作成功返回
     * @param msg
     * @param data
     * @return
     */
    public static ResultJson buildSuccess(String msg, Object data){
       return new ResultJson(RequestStatusEnum.SUCCESS.getStatus(),msg,data,null,null,null);
    }

    /**
     * 操作成功返回
     * @param msg
     * @param pageInfo
     * @return
     */
    public static ResultJson buildSuccess(String msg, PageInfo<?> pageInfo){
        if(pageInfo == null || pageInfo.getList().isEmpty()){
            return new ResultJson(RequestStatusEnum.SUCCESS.getStatus(),msg,null,null,null,null);
        }

        return new ResultJson(RequestStatusEnum.SUCCESS.getStatus(),msg,pageInfo.getList(),pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal());
    }

    /**
     * 操作失败返回
     * @param msg
     * @return
     */
    public static ResultJson buildError(String msg){
        return new ResultJson(RequestStatusEnum.FAIL_FIELD.getStatus(),msg,null,null,null,null);
    }

    /**
     * 操作失败返回
     * @param msg
     * @param data
     * @return
     */
    public static ResultJson buildError(String msg, Object data){
        return new ResultJson(RequestStatusEnum.FAIL_FIELD.getStatus(),msg,data,null,null,null);
    }

    /**
     * 操作失败,参数异常返回
     * @param msg
     * @return
     */
    public static ResultJson buildParamError(String msg){
        return new ResultJson(RequestStatusEnum.FAIL_PARAM.getStatus(),msg,null,null,null,null);
    }



    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
