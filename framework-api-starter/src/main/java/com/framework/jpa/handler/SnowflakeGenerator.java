package com.framework.jpa.handler;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

/**
 * @author zhiqiang.huang
 */
public class SnowflakeGenerator implements IdentifierGenerator {
    public static final String TYPE = "com.framework.jpa.handler.SnowflakeGenerator";

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        return IdWorker.getIdStr();
    }
}
