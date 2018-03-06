package com.bitauto.bdc.modules.hdfs.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bitauto.bdc.common.utils.DateUtils;
import com.bitauto.bdc.modules.hdfs.dao.HdfsMonitorItemDao;
import com.bitauto.bdc.modules.hdfs.entity.HdfsMonitorItemEntity;
import com.bitauto.bdc.modules.hdfs.service.HdfsMonitorItemService;
import com.bitauto.bdc.modules.oozieDashboard.util.HttpClientUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by weiyongxu on 2017/10/23.
 */
@Service("hdfsMonitorItemService")
public class HdfsMonitorItemServiceImpl implements HdfsMonitorItemService{

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private HdfsMonitorItemDao hdfsMonitorItemDao;

    @Value("${gateway.hdfsjmx.host}")
    private String gatewayHdfsJmxHost;

    @Override
    public List<HdfsMonitorItemEntity> queryList(Map<String, Object> map) {
        List<HdfsMonitorItemEntity> list = hdfsMonitorItemDao.queryList(map);
        /*Long formatUnit = 1024*1024L;
        for(int i = 0; i < list.size(); i++) {
            HdfsMonitorItemEntity hdfsMonitorItemEntity = list.get(i);
            hdfsMonitorItemEntity.setUsedDisk(hdfsMonitorItemEntity.getUsedDisk()/formatUnit);
            hdfsMonitorItemEntity.setTotalDisk(hdfsMonitorItemEntity.getTotalDisk()/formatUnit);
            hdfsMonitorItemEntity.setIncreaseDisk(hdfsMonitorItemEntity.getIncreaseDisk()/1024);
            list.set(i, hdfsMonitorItemEntity);
        }*/
        return list;
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return hdfsMonitorItemDao.queryTotal(map);
    }

    private HdfsMonitorItemEntity fillHdfsMonitorItem(HdfsMonitorItemEntity hdfsMonitorItemEntity) throws Exception{
        JSONObject jsonResult;
        try {
            String url = "http://" + gatewayHdfsJmxHost + "/jmx?qry=Hadoop:service=NameNode,name=FSNamesystem";
            String r = HttpClientUtils.get(url);
            JSONObject jsonObject = JSONObject.parseObject(r);
            JSONArray array = jsonObject.getJSONArray("beans");
            jsonResult = array.getJSONObject(0);
        } catch (Exception e) {
            logger.error("HDFS JMX FSNamesystem Api Error", e);
            throw e;
        }
        hdfsMonitorItemEntity.setFileCount(jsonResult.getLong("TotalFiles"));
        hdfsMonitorItemEntity.setBlockCount(jsonResult.getLong("BlocksTotal"));
        hdfsMonitorItemEntity.setTotalDisk(jsonResult.getLong("CapacityTotalGB"));
        hdfsMonitorItemEntity.setUsedDisk(jsonResult.getLong("CapacityUsedGB"));
        return hdfsMonitorItemEntity;
    }

    private HdfsMonitorItemEntity fillNamenodeMemory(HdfsMonitorItemEntity hdfsMonitorItemEntity) throws Exception{
        JSONObject jsonResult;
        try {
            String url = "http://" + gatewayHdfsJmxHost + "/jmx?qry=java.lang:type=Memory";
            String r = HttpClientUtils.get(url);
            JSONObject jsonObject = JSONObject.parseObject(r);
            JSONArray array = jsonObject.getJSONArray("beans");
            jsonResult = array.getJSONObject(0);
        }catch (Exception e) {
            logger.error("HDFS JMX Namenode Memory Api Error", e);
            throw e;
        }
        JSONObject jsonHeapMemory = jsonResult.getJSONObject("HeapMemoryUsage");
        long usedMemory = jsonHeapMemory.getLong("used")/(1024*1024);
        long totalMemory = jsonHeapMemory.getLong("committed")/(1024*1024);
        hdfsMonitorItemEntity.setNamenodeUsedMomory(usedMemory);
        hdfsMonitorItemEntity.setNamenodeTotalMomory(totalMemory);
        return hdfsMonitorItemEntity;
    }

    @Override
    public HdfsMonitorItemEntity add() {
        HdfsMonitorItemEntity hdfsMonitorItemEntity = new HdfsMonitorItemEntity();
        try {
            fillHdfsMonitorItem(hdfsMonitorItemEntity);
            fillNamenodeMemory(hdfsMonitorItemEntity);
        } catch (Exception e) {
            return null;
        }

        Date date = new Date();
        hdfsMonitorItemEntity.setCreateTime(date);
        String today = DateUtils.format(date);
        hdfsMonitorItemEntity.setCreateDate(today);

        hdfsMonitorItemDao.updateNewestByDate(today);
        hdfsMonitorItemDao.save(hdfsMonitorItemEntity);
        addDiskDailyIncrease(date);

        return hdfsMonitorItemEntity;
    }

    public Integer addDiskDailyIncrease(Date date) {
        HdfsMonitorItemEntity dayHdfsMonitorItemEntity = hdfsMonitorItemDao.queryNewestDiskByDate(DateUtils.format(date));
        HdfsMonitorItemEntity lastDayHdfsMonitorItemEntity = hdfsMonitorItemDao.queryOldestDiskByDate(DateUtils.getLastDay(date));
        if(dayHdfsMonitorItemEntity == null || lastDayHdfsMonitorItemEntity == null) {
            logger.info("Get disk daily increase fail");
            return null;
        }
        HdfsMonitorItemEntity hdfsMonitorItemEntity = new HdfsMonitorItemEntity();
        hdfsMonitorItemEntity.setHdfsMonitorItemId(dayHdfsMonitorItemEntity.getHdfsMonitorItemId());
        long increaseDisk = dayHdfsMonitorItemEntity.getUsedDisk() - lastDayHdfsMonitorItemEntity.getUsedDisk();
        hdfsMonitorItemEntity.setIncreaseDisk(increaseDisk);
        return updateByPrimaryKeySelective(hdfsMonitorItemEntity);
    }

    @Override
    public int updateByPrimaryKeySelective(HdfsMonitorItemEntity hdfsMonitorItemEntity) {
        return hdfsMonitorItemDao.updateByPrimaryKeySelective(hdfsMonitorItemEntity);
    }
}
