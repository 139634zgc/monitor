package com.bitauto.bdc.modules.oozieServer.service;

import com.bitauto.bdc.modules.oozieServer.entity.OozieCollectVO;

import java.util.Map;

public interface CoordJobsService {

    int queryTotal(Map<String, Object> map);
    public OozieCollectVO queryStatusTotal(Map<String, Object> map);
    public int queryWaitingcount(Map<String, Object> map);

}
