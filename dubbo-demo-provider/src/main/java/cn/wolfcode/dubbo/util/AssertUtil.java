package cn.wolfcode.dubbo.util;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;

/**
 * @author hox <h@hooox.io>
 */
public class AssertUtil {

    /**
     * 断言为 true，若为 false 则抛出异常
     * @param success
     * @param msg
     */
    public static void isTrue(boolean success, String msg) {
        failed(!success, msg);
    }

    /**
     * 断言为 false，若为 true 则抛出异常
     * @param success
     * @param msg
     */
    public static void isFalse(boolean success, String msg) {
        failed(success, msg);
    }

    /**
     * 断言不为空，若为空则抛出异常
     * @param object
     * @param msg
     */
    public static void notEmpty(Object object, String msg) {
        failed(object == null, msg);

        if (object instanceof Collection) {
            Collection collection = (Collection) object;
            failed(CollectionUtils.isEmpty(collection), msg);
        } else if (object instanceof String) {
            failed(StringUtils.isEmpty(object.toString()), msg);
        }
    }

    private static void failed(boolean failed, String msg) {
        if (failed) {
            throw new RuntimeException(msg);
        }
    }
}
