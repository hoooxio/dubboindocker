package cn.wolfcode.dubbo.service;

import cn.wolfcode.dubbo.domain.UserInfo;
import cn.wolfcode.dubbo.exception.BusinessException;

/**
 * @author hox
 */
public interface IUserinfoService {

    /**
     * 注册接口
     */
    UserInfo register(String username, String password) throws BusinessException;
}
