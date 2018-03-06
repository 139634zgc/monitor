package com.bitauto.bdc.modules.oozieDashboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitauto.bdc.modules.oozieDashboard.dao.ProjectInfoDao;
import com.bitauto.bdc.modules.oozieDashboard.model.ProjectInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by michealzhang on 2017/8/18.
 */
@Service
public class ProjectInfoService {


    @Autowired
    private ProjectInfoDao projectInfoDao;

    public List<ProjectInfo> getProjectByName(Map<String, Object> params){
        List<ProjectInfo> list =   projectInfoDao.getProjectByName(params);
        return list;
    }

    public List<ProjectInfo> getProjectList(){
        return projectInfoDao.selectList();
    }

    public  ProjectInfo getProjectInfo(Integer projectId){
        return projectInfoDao.selectByPrimaryKey(projectId);
    }

    public int queryTotal(Map<String, Object> params){
        return projectInfoDao.queryTotal(params);
    }

    public int updateProject(int id,ProjectInfo projectInfo){
        projectInfo.setId(id);
        return projectInfoDao.updateByPrimaryKeySelective(projectInfo);
    }

    public int deleteBatch(Integer[] projectIds){
        return projectInfoDao.deleteBatch(projectIds);
    }

    public int insertSelective(ProjectInfo record){
        return projectInfoDao.insertSelective(record);
    }



}
