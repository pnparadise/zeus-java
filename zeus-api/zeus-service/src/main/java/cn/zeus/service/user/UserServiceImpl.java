package cn.zeus.service.user;

import cn.zeus.repository.user.User;
import cn.zeus.repository.user.UserRepository;
import cn.zeus.service.api.user.IUserService;
import cn.zeus.service.api.user.UserReq;
import cn.zeus.service.api.user.UserResp;
import lombok.val;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author zhiqiang.huang
 */
@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResp detail(String userId) {
        UserResp userResp = new UserResp();
        BeanUtils.copyProperties(userRepository.findByUserId(userId), userResp);
        return userResp;
    }

    @Override
    public UserResp register(UserReq userReq) {
        User user = new User();
        BeanUtils.copyProperties(userReq, user);
        val userResp = new UserResp();
        user = this.userRepository.save(user);
        BeanUtils.copyProperties(user, userResp);
        return userResp;
    }

    @Override
    public void remove(String userId) {
        this.userRepository.deleteById(userId);
    }


}
