package io.github.weechang.moreco.base.service.impl;

import io.github.weechang.moreco.base.dao.BaseDao;
import io.github.weechang.moreco.base.domain.BaseDomain;
import io.github.weechang.moreco.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;


/**
 * 基础实现
 *
 * @author zhangwei
 * date 2018/10/27
 * time 0:32
 */
public abstract class BaseServiceImpl<D extends BaseDao, T extends BaseDomain> implements BaseService<T> {

    @Autowired
    protected D baseDao;

    /**
     * 默认逻辑删除
     */
    private final boolean physical = false;

    @Override
    public T save(T t) {
        return (T) baseDao.save(t);
    }

    @Override
    public Iterable<T> save(Iterable<T> ts) {
        return baseDao.save(ts);
    }

    @Override
    public T findOne(Serializable id) {
        return (T) baseDao.findOne(id);
    }

    @Override
    public boolean exists(Serializable id) {
        return baseDao.exists(id);
    }

    @Override
    public Iterable<T> findAll() {
        return baseDao.findAll();
    }

    @Override
    public Iterable<T> findAll(Iterable<Serializable> ids) {
        return baseDao.findAll(ids);
    }

    @Override
    public Iterable<T> findAll(Sort sort) {
        return baseDao.findAll(sort);
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return baseDao.findAll(pageable);
    }

    @Override
    public long count() {
        return baseDao.count();
    }

    @Override
    public void delete(Serializable id) {
        this.delete(id, physical);
    }

    @Override
    public void delete(T t) {
        this.delete(t, physical);
    }

    @Override
    public void delete(Iterable<? extends T> ts) {
        this.delete(ts, physical);
    }

    @Override
    public void deleteAll() {
        this.deleteAll(physical);
    }

    @Override
    public void delete(Serializable id, boolean physical) {
        if (physical){
            baseDao.delete(id);
        } else {
            baseDao.logicDelete(id);
        }
    }

    @Override
    public void delete(T t, boolean physical) {
        if (physical){
            baseDao.delete(t);
        } else {
            baseDao.logicDelete(t);
        }
    }

    @Override
    public void delete(Iterable<? extends T> ts, boolean physical) {
        if (physical){
            baseDao.delete(ts);
        } else {
            baseDao.logicDelete(ts);
        }
    }

    @Override
    public void deleteAll(boolean physical) {
        if (physical){
            baseDao.deleteAll();
        } else {
//            baseDao.logicDeleteAll();
        }
    }


}
