package com.bitauto.bdc.modules.oozieServer.dao;

import com.bitauto.bdc.modules.oozieServer.entity.CoordJobs;

import com.bitauto.bdc.modules.oozieServer.entity.OozieCollectVO;
import com.bitauto.bdc.modules.oozieServer.entity.WfJobs;
import com.bitauto.bdc.modules.sys.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface CoordJobsDao extends BaseDao<CoordJobs> {

    public OozieCollectVO queryStatusTotal(Map<String, Object> map);

    public int queryWaitingcount(Map<String, Object> map);
}
