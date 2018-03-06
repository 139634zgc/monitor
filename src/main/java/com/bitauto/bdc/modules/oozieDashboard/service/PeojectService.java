package com.bitauto.bdc.modules.oozieDashboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.bitauto.bdc.modules.oozieDashboard.dao.ProjectInfoDao;
import com.bitauto.bdc.modules.oozieDashboard.model.ProjectInfo;

import java.util.List;

/**
 * Created by michealzhang on 2017/8/16.
 */
@Service
public class PeojectService {

    @Autowired
    private ProjectInfoDao projectInfoMapper;

    public int addProject(ProjectInfo projectInfo){
       int res =  projectInfoMapper.insertSelective(projectInfo);
       return res;
    }

    public int updateProject(int id ,ProjectInfo projectInfo){
        projectInfo.setId(id);
        int res = projectInfoMapper.updateByPrimaryKeySelective(projectInfo);
        return res;
    }

    public ProjectInfo getProjectInfoById(int id){
        ProjectInfo projectInfo  = projectInfoMapper.selectByPrimaryKey(id);
        Assert.notNull(projectInfo,"project is null ,id :"+id);
        return projectInfo;
    }

    public List<ProjectInfo> getProjectList(){
        List<ProjectInfo> list = projectInfoMapper.selectList();
        if(list != null && list.size()>0){
            return list;
        }
        return null;
    }

}
