package cn.wolfcode.dubbo.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hox <h@hooox.io>
 */
@ControllerAdvice
public class BusinessExceptionHandler {

    public static final int DEFAULT_SERVER_EXCEPTION_CODE = 5001;

    @ExceptionHandler
    @ResponseBody
    public Map<String, Object> handler(Exception e) {
        return this.build(e);
    }

    private Map<String, Object> build(Exception e) {
        Map<String, Object> ret = new HashMap<>(2);
        ret.put("msg", e.getMessage());
        ret.put("code", DEFAULT_SERVER_EXCEPTION_CODE);
        return ret;
    }
}
