package com.bitauto.bdc.modules.oozieDashboard.dao;

import org.apache.ibatis.annotations.Mapper;

import com.bitauto.bdc.modules.oozieDashboard.model.ProjectInfo;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProjectInfoDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ProjectInfo record);

    int insertSelective(ProjectInfo record);

    ProjectInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProjectInfo record);

    int updateByPrimaryKey(ProjectInfo record);

    List<ProjectInfo> selectList();

    List<ProjectInfo> getProjectByName(Map<String, Object> params);

    int queryTotal(Map<String, Object> map);

    int deleteBatch(Object[] id);


}