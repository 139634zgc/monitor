package com.bitauto.bdc.modules.oozieServer.service.impl;

import com.bitauto.bdc.common.annotation.DataSource;
import com.bitauto.bdc.dynamicdatasource.DataSourceNames;
import com.bitauto.bdc.modules.oozieServer.dao.CoordJobsDao;
import com.bitauto.bdc.modules.oozieServer.entity.OozieCollectVO;
import com.bitauto.bdc.modules.oozieServer.service.CoordJobsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("coordJobsServiceImpl")
public class CoordJobsServiceImpl implements CoordJobsService {

    @Autowired
    private CoordJobsDao coordJobsDao;

    @Override
    @DataSource(name = DataSourceNames.OOZIE)
    public int queryTotal(Map<String, Object> map) {
        return coordJobsDao.queryTotal(map);
    }

    @Override
    @DataSource(name = DataSourceNames.OOZIE)
    public OozieCollectVO queryStatusTotal(Map<String, Object> map) {
        return coordJobsDao.queryStatusTotal(map);
    }

    @Override
    @DataSource(name = DataSourceNames.OOZIE)
    public int queryWaitingcount(Map<String, Object> map){
        return coordJobsDao.queryWaitingcount(map);
    }
}
