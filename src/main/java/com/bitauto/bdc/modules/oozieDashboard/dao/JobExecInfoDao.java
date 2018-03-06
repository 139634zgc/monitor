package com.bitauto.bdc.modules.oozieDashboard.dao;

import org.apache.ibatis.annotations.Mapper;

import com.bitauto.bdc.modules.oozieDashboard.model.JobExecInfo;

import java.util.List;

@Mapper
public interface JobExecInfoDao {
    int deleteByPrimaryKey(Integer id);

    int insert(JobExecInfo record);

    int insertSelective(JobExecInfo record);

    JobExecInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JobExecInfo record);

    int updateByPrimaryKey(JobExecInfo record);

    List<JobExecInfo>  selectByJobId(String jobId);
}