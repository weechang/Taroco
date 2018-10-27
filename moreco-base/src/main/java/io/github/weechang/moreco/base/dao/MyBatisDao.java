package io.github.weechang.moreco.base.dao;

import io.github.weechang.moreco.base.domain.BaseEntry;

import java.io.Serializable;

/**
 * MyBatis Dao
 *
 * @author zhangwei
 * date 2018/10/27
 * time 10:06
 */
public interface MyBatisDao <T extends BaseEntry, ID extends Serializable> extends BaseDao<T, ID> {
}
