package cn.zeus.repository.user;

import com.framework.jpa.respository.BaseRepository;

/**
 * @author zhiqiang.huang
 */
public interface UserRepository extends BaseRepository<User> {
    User findByUserId(String userId);
}
