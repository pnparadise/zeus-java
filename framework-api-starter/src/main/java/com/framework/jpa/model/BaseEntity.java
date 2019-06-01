package com.framework.jpa.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.framework.annotation.SoftDelete;
import com.framework.jpa.handler.SnowflakeGenerator;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author zhiqiang.huang
 */
@Data
@MappedSuperclass
@SoftDelete
public abstract class BaseEntity implements Serializable {
    @Id
    @GenericGenerator(name = "snowflake", strategy = SnowflakeGenerator.TYPE)
    @GeneratedValue(generator = "snowflake")
    protected String id;

    @Column(insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @JSONField(serialize = false)
    protected Date lastModifyTime;

    @Column(insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @JSONField(serialize = false)
    protected Date createTime;


    @Column(insertable = false)
    @JSONField(serialize = false)
    protected Boolean deleted;


}
