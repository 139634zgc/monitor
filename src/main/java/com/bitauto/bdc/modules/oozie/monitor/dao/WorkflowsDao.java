package com.bitauto.bdc.modules.oozie.monitor.dao;

import com.bitauto.bdc.modules.oozie.monitor.entity.WorkflowsEntity;
import com.bitauto.bdc.modules.sys.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-11-02 16:25:54
 */
@Mapper
public interface WorkflowsDao extends BaseDao<WorkflowsEntity> {

    List<WorkflowsEntity> getDoneFlagWfById(String wfId);

    List<WorkflowsEntity> getDoneFlagWf();

    WorkflowsEntity queryWorkflowsEntity(String wfId);

    List<WorkflowsEntity> getWfByDoneFlag(String doneFlage);

    List<WorkflowsEntity> getDoneFlagWfByName(String wfName);


}
