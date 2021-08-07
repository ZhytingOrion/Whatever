package com.nic.calculate.help;


/*
 * 返回对象基类
 * */
public class BaseResponse<TValueObject> implements IResultJudgment {
    public BaseResponse(boolean flag) {
        this.success = flag;
        if (flag) {
            this.code = 0;
        }
    }

    public BaseResponse() {
        this.code = -1;
        this.success = false;
    }

    public BaseResponse(Integer code, String message) {
        this.success = false;
        this.code = code;
        this.message = message;
    }

    private Integer code;
    private Boolean success;
    private String message;
    private PageExtend page;
    private TValueObject data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
        if (this.code == null || this.code != 0) {
            this.success = false;
        }
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
        if (success) {
            this.code = 0;
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PageExtend getPage() {
        return page;
    }

    public void setPage(PageExtend page) {
        this.page = page;
    }

    public TValueObject getData() {
        return data;
    }

    public void setData(TValueObject data) {
        this.data = data;
    }
}
