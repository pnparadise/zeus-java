package cn.zeus.controller.user;

import cn.zeus.service.api.user.IUserService;
import cn.zeus.service.api.user.UserReq;
import cn.zeus.service.api.user.UserResp;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhiqiang.huang
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/{userId}")
    public UserResp detail(@PathVariable String userId) {
        return userService.detail(userId);
    }

    @PutMapping("/{userId}")
    public UserResp register(@PathVariable String userId, @ModelAttribute UserReq userReq) {
        return userService.register(userReq);
    }

    @DeleteMapping("/{userId}")
    public String remove(@PathVariable String userId) {
        userService.remove(userId);
        return "success";
    }
}
