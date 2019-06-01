package cn.zeus.service.api.user;

/**
 * @author zhiqiang.huang
 */
public interface IUserService {
    /**
     * 查看用户信息
     * @param userId 用户id
     * @return 用户详情
     */
    UserResp detail(String userId);

    /**
     * 注册用户信息
     * @param userReq 用户信息
     * @return 用户详情
     */
    UserResp register(UserReq userReq);

    /**
     * 删除用户
     * @param userId 用户id
     * @return 用户详情
     */
    void remove(String userId);
}
