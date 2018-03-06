package com.bitauto.bdc.modules.config.dao;

import com.bitauto.bdc.modules.config.entity.ConfigEntity;
import com.bitauto.bdc.modules.sys.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-12-03 10:09:06
 */
@Mapper
public interface ConfigDao extends BaseDao<ConfigEntity> {

    ConfigEntity queryBykey(String key);

    void updateBykey(ConfigEntity entity);

}
