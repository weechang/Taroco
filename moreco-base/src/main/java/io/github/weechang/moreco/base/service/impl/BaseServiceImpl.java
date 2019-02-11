package io.github.weechang.moreco.base.service.impl;

import io.github.weechang.moreco.base.dao.BaseDao;
import io.github.weechang.moreco.base.model.domain.BaseDomain;
import io.github.weechang.moreco.base.model.dto.PageModel;
import io.github.weechang.moreco.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.Optional;


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
    public Iterable<T> saveAll(Iterable<T> ts) {
        return baseDao.saveAll(ts);
    }

    @Override
    public T findById(Object id) {
        T t = null;
        Optional<T> optional = baseDao.findById(id);
        if (optional.isPresent()){
            t = optional.get();
        }
        return t;
    }

    @Override
    public Iterable<T> findAll() {
        return baseDao.findAll();
    }

    @Override
    public Iterable<T> findAllById(Iterable<Serializable> ids) {
        return baseDao.findAllById(ids);
    }

    @Override
    public PageModel<T> findAll(Pageable pageable) {
        return new PageModel<T>(baseDao.findAll(pageable));
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
    public void delete(Serializable id, boolean physical) {
        if (physical) {
            baseDao.delete(id);
        } else {
            baseDao.logicDelete(id);
        }
    }

    @Override
    public void delete(T t, boolean physical) {
        if (physical) {
            baseDao.delete(t);
        } else {
            baseDao.logicDelete(t);
        }
    }

    @Override
    public void delete(Iterable<? extends T> ts, boolean physical) {
        if (physical) {
            baseDao.delete(ts);
        } else {
            baseDao.logicDelete(ts);
        }
    }

    @Override
    public void doConvertDataMap(T... ts) {

    }

}
