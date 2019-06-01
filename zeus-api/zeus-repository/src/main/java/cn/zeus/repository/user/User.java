package cn.zeus.repository.user;

import com.framework.annotation.SoftDelete;
import com.framework.jpa.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.PreRemove;

/**
 * @author zhiqiang.huang
 */

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class User extends BaseEntity {
    private String userId;

}
