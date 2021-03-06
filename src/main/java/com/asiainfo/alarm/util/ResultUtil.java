package com.asiainfo.alarm.util;

import com.asiainfo.alarm.enums.ResultEnum;
import com.asiainfo.alarm.model.Result;

/**
 * Created by RUOK on 2017/6/15.
 */
public class ResultUtil {
    public static Result success(Object object) {
        Result result = new Result();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(ResultEnum.SUCCESS.getMsg());
        result.setData(object);
        return result;
    }

    public static Result success() {
        return success(null);
    }

    public static Result error(ResultEnum resultEnum) {
        Result result = new Result();
        result.setCode(resultEnum.getCode());
        result.setMsg(resultEnum.getMsg());
        return result;
    }

    public static Result error(String msg) {
        Result result = new Result();
        result.setCode(ResultEnum.UNKNOWN_ERROR.getCode());
        result.setMsg(msg);
        return result;
    }
}
