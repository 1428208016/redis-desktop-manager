package com.lingzhen.myproject.jiqun.config.shiro;

import com.alibaba.fastjson.annotation.JSONField;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.mgt.SimpleSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

public class RedisSimpleSession extends SimpleSession {

    private static final transient Logger log = LoggerFactory.getLogger(RedisSimpleSession.class);

    private Serializable id;
    private Date startTimestamp;
    private Date stopTimestamp;
    private Date lastAccessTime;
    private long timeout;
    private boolean expired;
    private String host;
    private Map<Object, Object> attributes;


    public RedisSimpleSession() {
        super();
    }

    public RedisSimpleSession(String host) {
        this();
        this.host = host;
    }

    @JSONField(serialize = false)
    @Override
    public boolean isValid() {
        return super.isValid();
    }

    @JSONField(serialize = false)
    @Override
    public Collection<Object> getAttributeKeys() throws InvalidSessionException {
        return super.getAttributeKeys();
    }

    @JSONField(serialize = false)
    @Override
    public Map<Object, Object> getAttributes() {
        return super.getAttributes();
    }

    //    @Override
//    protected Session newSessionInstance(SessionContext context) {
//        SimpleSession session = new MyRedisSession(context.getHost());
//// session.setId(IdGen.uuid());
//        session.setTimeout(SessionUtils.SESSION_TIME);
//        return session;
//    }
}
