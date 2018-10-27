//package io.github.weechang.moreco.security.shiro.dao;
//
//import org.apache.shiro.session.Session;
//import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.io.Serializable;
//import java.util.Date;
//
///**
// * @author zhangwei
// * date 2018/9/28
// * time 11:12
// */
//@Component
//public class MysqlShiroSessionDAO extends EnterpriseCacheSessionDAO {
//
//    @Autowired(required = false)
////    private SysTokenDao tokenDao;
//
//    //创建session
//    @Override
//    protected Serializable doCreate(Session session) {
//        Serializable sessionId = super.doCreate(session);
//        String key = sessionId.toString();
//        setShiroSession(key, session);
//        return sessionId;
//    }
//
//    //获取session
//    @Override
//    protected Session doReadSession(Serializable sessionId) {
//        Session session = super.doReadSession(sessionId);
//        if(session == null){
//            final String key = sessionId.toString();
//            session = getShiroSession(key);
//        }
//        return session;
//    }
//
//    //更新session
//    @Override
//    protected void doUpdate(Session session) {
//        super.doUpdate(session);
//        final String key = session.getId().toString();
//        setShiroSession(key, session);
//    }
//
//    //删除session
//    @Override
//    protected void doDelete(Session session) {
//        super.doDelete(session);
//        final String key = session.getId().toString();
//        tokenDao.deleteById(key);
//    }
//
//    private Session getShiroSession(String key) {
//        SysTokenEntity tokenEntity = tokenDao.selectById(key);
//        Session result = null;
//        if (tokenEntity != null){
//            result = SessionSerializableUtils.deserializ(tokenEntity.getToken());
//        }
//        return result;
//    }
//
//    private void setShiroSession(String key, Session session){
//        SysTokenEntity shiro = new SysTokenEntity();
//        shiro.setSessionId(key);
//        shiro.setToken(SessionSerializableUtils.serializ(session));
//        shiro.setType(SysTokenEntity.TYPE_ADMIN);
//        Long nowTime = System.currentTimeMillis() + 60 * 60 * 1000;
//        shiro.setExpireTime(new Date(nowTime));
//
//        if (tokenDao.selectById(key) != null){
//            tokenDao.updateById(shiro);
//        } else {
//            tokenDao.insert(shiro);
//        }
//    }
//}
