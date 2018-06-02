package cn.wolfcode.dubbo.exception;

/**
 * @author hox <h@hooox.io>
 */
public class BusinessException extends RuntimeException {

    public BusinessException() {
    }

    public BusinessException(String message) {
        super(message);
    }

    /**
     * 避免异常栈拷贝，性能优化
     * @return
     */
    @Override
    public synchronized Throwable fillInStackTrace() {
        return null;
    }
}
