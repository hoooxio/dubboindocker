package cn.wolfcode.dubbo.controller;

import cn.wolfcode.dubbo.domain.UserInfo;
import cn.wolfcode.dubbo.service.IUserinfoService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hox <h@hooox.io>
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Reference
    public IUserinfoService userinfoService;

    @GetMapping("/register")
    public UserInfo register(String username, String password) {
        return userinfoService.register(username, password);
    }

}
