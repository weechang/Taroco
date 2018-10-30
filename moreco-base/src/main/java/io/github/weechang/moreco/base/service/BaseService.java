package io.github.weechang.moreco.base.service;

import io.github.weechang.moreco.base.domain.BaseDomain;
import io.github.weechang.moreco.base.model.PageModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;

/**
 * 基础service类
 *
 * @author zhangwei
 * date 2018/10/27
 * time 0:32
 */
public interface BaseService<T extends BaseDomain> {

    /**
     * 保存记录
     *
     * @param t 记录
     * @return 记录
     */
    T save(T t);

    /**
     * 批量保存记录
     *
     * @param ts 记录集合
     * @return 记录集合
     */
    Iterable<T> save(Iterable<T> ts);

    /**
     * 根据id 查询一条记录
     *
     * @param id id
     * @return 记录
     */
    T findOne(Serializable id);

    /**
     * 根据id判断是否存在
     *
     * @param id id
     * @return 是否存在
     */
    boolean exists(Serializable id);

    /**
     * 查询所有记录
     *
     * @return 所有记录
     */
    Iterable<T> findAll();

    /**
     * 根据ids查询所有记录
     *
     * @param ids ids
     * @return ids包含的记录
     */
    Iterable<T> findAll(Iterable<Serializable> ids);

    /**
     * 排序查询所有对象
     *
     * @param sort 排序
     * @return 所有对象
     */
    Iterable<T> findAll(Sort sort);

    /**
     * 分页查询记录
     *
     * @param pageable 分页参数
     * @return 分页记录
     */
    PageModel<T> findAll(Pageable pageable);

    /**
     * 查询共有多少条数据
     *
     * @return 共有多少条数据
     */
    long count();

    /**
     * 根据id删除
     *
     * @param id id
     */
    void delete(Serializable id);

    /**
     * 删除对象
     *
     * @param t 要删除的对象
     */
    void delete(T t);

    /**
     * 批量删除对象
     *
     * @param ts 要批量删除的对象
     */
    void delete(Iterable<? extends T> ts);

    /**
     * 删除所有记录
     */
    void deleteAll();

    /**
     * 根据id删除
     *
     * @param id       id
     * @param physical 物理删除
     */
    void delete(Serializable id, boolean physical);

    /**
     * 删除对象
     *
     * @param t        要删除的对象
     * @param physical 物理删除
     */
    void delete(T t, boolean physical);

    /**
     * 批量删除对象
     *
     * @param ts       要批量删除的对象
     * @param physical 物理删除
     */
    void delete(Iterable<? extends T> ts, boolean physical);

    /**
     * 删除所有记录
     *
     * @param physical 物理删除
     */
    void deleteAll(boolean physical);
}
