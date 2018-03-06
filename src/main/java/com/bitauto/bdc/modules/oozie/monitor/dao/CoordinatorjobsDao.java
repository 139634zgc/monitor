package com.bitauto.bdc.modules.oozie.monitor.dao;

import com.bitauto.bdc.modules.oozie.monitor.entity.CoordinatorjobsEntity;
import com.bitauto.bdc.modules.sys.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-11-02 16:25:54
 */
@Mapper
public interface CoordinatorjobsDao extends BaseDao<CoordinatorjobsEntity> {

    List<CoordinatorjobsEntity> queryListByIds(@Param("set") Set<String> set);

    List<CoordinatorjobsEntity> queryListByCoorId(String ids);



}
