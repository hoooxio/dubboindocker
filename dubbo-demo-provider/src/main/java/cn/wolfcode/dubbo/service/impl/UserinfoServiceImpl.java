package cn.wolfcode.dubbo.service.impl;

import cn.wolfcode.dubbo.domain.UserInfo;
import cn.wolfcode.dubbo.exception.BusinessException;
import cn.wolfcode.dubbo.service.IUserinfoService;
import cn.wolfcode.dubbo.util.AssertUtil;
import com.alibaba.dubbo.config.annotation.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hox
 */
@Service
public class UserinfoServiceImpl implements IUserinfoService {

    /**
     * 自增 id 的 key
     */
    private static final String AUTOMATIC_INCREMENT_ID = "automatic_increment_id";

    /**
     * 保存对象的集合
     */
    private static final Map<Object, Object> OBJECT_MAP = new HashMap<>();

    static {
        // 初始化 id 值
        OBJECT_MAP.put(AUTOMATIC_INCREMENT_ID, 0);
    }

    @Override
    public UserInfo register(String username, String password) throws BusinessException {
        // 参数校验
        this.valid(username, password);

        // 获取自增 id
        Long incrementId = this.incrementId();

        // 构建对象
        UserInfo userInfo = this.build(username, password, incrementId);

        // 保存对象
        OBJECT_MAP.put(username, userInfo);
        // 返回对象
        return userInfo;
    }

    private Long incrementId() {
        // 自增 id
        Long incrementId = Long.parseLong(OBJECT_MAP.get(AUTOMATIC_INCREMENT_ID).toString()) + 1;
        // 重新设置回去
        OBJECT_MAP.put(AUTOMATIC_INCREMENT_ID, incrementId);
        return incrementId;
    }

    private UserInfo build(String username, String password, Long incrementId) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(incrementId);
        userInfo.setUsername(username);
        userInfo.setPassword(password);
        return userInfo;
    }

    private void valid(String username, String password) {
        AssertUtil.notEmpty(username, "用户名不能为空！");
        AssertUtil.notEmpty(password, "密码不能为空！");
        AssertUtil.isFalse(OBJECT_MAP.containsKey(username), "用户名已存在！");
    }
}
