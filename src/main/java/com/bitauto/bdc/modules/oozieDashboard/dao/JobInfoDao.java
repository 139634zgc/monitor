package com.bitauto.bdc.modules.oozieDashboard.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.bitauto.bdc.modules.oozieDashboard.model.JobInfo;
import com.bitauto.bdc.modules.oozieDashboard.model.vo.JobDetailVO;

import java.util.List;

@Mapper
public interface JobInfoDao {
    int deleteByPrimaryKey(Integer id);

    int insert(JobInfo record);

    int insertSelective(JobInfo record);

    JobInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JobInfo record);

    int updateByPrimaryKey(JobInfo record);

    List<JobInfo> getJobListByProjectId(Integer id);

    JobInfo getJobInfoByProjectIdAndJobId(@Param("projectId") Integer projectId, @Param("jobId") String jobId);

    int removeJob(@Param("projectId") Integer projectId, @Param("jobId") String jobId);

    List<JobDetailVO> getJobDetail(Integer projectId);

    JobInfo selectByJobId(String jobId);
}