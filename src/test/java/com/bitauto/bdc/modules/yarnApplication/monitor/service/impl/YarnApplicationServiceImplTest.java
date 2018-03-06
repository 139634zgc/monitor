package com.bitauto.bdc.modules.yarnApplication.monitor.service.impl;

import com.alibaba.fastjson.JSON;
import com.bitauto.bdc.RenrenApplication;
import com.bitauto.bdc.modules.oozie.monitor.entity.CoorDatasetEntity;
import com.bitauto.bdc.modules.oozie.monitor.entity.CoordinatorjobsEntity;
import com.bitauto.bdc.modules.oozie.monitor.entity.WorkflowsEntity;
import com.bitauto.bdc.modules.oozie.monitor.service.CoorDatasetService;
import com.bitauto.bdc.modules.oozie.monitor.service.CoordinatorjobsService;
import com.bitauto.bdc.modules.oozie.monitor.service.WorkflowsService;
import com.bitauto.bdc.modules.yarnApplication.monitor.entity.TaskDependentEntity;
import com.bitauto.bdc.modules.yarnApplication.monitor.service.YarnApplicationService;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by michealzhang on 2017/11/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RenrenApplication.class)
@ActiveProfiles("test")
public class YarnApplicationServiceImplTest {



    @Autowired
    private YarnApplicationService yarnApplicationService;

    @Autowired
    private CoorDatasetService coorDatasetService;

    @Autowired
    private WorkflowsService workflowsService;

    @Autowired
    private CoordinatorjobsService coordinatorjobsService;

    @Test
    public void getQueneList() throws Exception {
        Map<String, Object> map = new HashMap<>();
        yarnApplicationService.getQueneList(map);

    }

    @Test
    public void getUserTaskList() throws Exception {
    }

    @Test
    public void getTaskDependentTree() throws  Exception{
        List<TaskDependentEntity> list =  yarnApplicationService.getTaskDependentTree();

        System.out.println(JSON.toJSONString(list));

//        TaskDependentEntity entity = yarnApplicationService.buildTaskDependentEntity("0145152-171018182151964-oozie-root-W");
//        System.out.println(JSON.toJSON(entity));

    }

    @Test
    public void getTaskDependentTree2() throws  Exception{
        //依赖a对应的调度
//        List<CoorDatasetEntity> list = coorDatasetService.getCoorDatasetEntity("${nameNode}/bitauto/sign/yiche_easypass/app_epdatacenter_dealer_day");
        String coordId = "0167141-171018182151964-oozie-root-C";
        //coordId="0172695-171018182512539-oozie-root-C";

        CoorDatasetEntity coorDatasetEntity = coorDatasetService.queryObject("0172695-171018182512539-oozie-root-C");
        System.out.println("输入: "+coorDatasetEntity.getCoordinatorjobid()+"  "+coorDatasetEntity.getCoordjobname());

        //上层依赖

        TaskDependentEntity upTaaskDependentEntity = new TaskDependentEntity();
        upTaaskDependentEntity.setName(coorDatasetEntity.getCoordjobname());
        upTaaskDependentEntity.setValue(coorDatasetEntity.getCoordinatorjobid());

        List<TaskDependentEntity> list = new ArrayList<>();

        this.getUpCoord(coorDatasetEntity.getCoordinatorjobid());

        upTaaskDependentEntity.setChildren(list);


        System.out.println(JSON.toJSON(upTaaskDependentEntity));


        //下层依赖
        this.getDownCoord(coordId);

    }

    private List<CoordinatorjobsEntity> getDownCoord(String coorId){
        CoorDatasetEntity coorDatasetEntity = coorDatasetService.queryObject(coorId);

        WorkflowsEntity entity = workflowsService.queryWorkflowsEntity(coorDatasetEntity.getWfId());

        if(entity== null || StringUtils.isEmpty(entity.getDoneflag())){
            System.out.println("没有下游："+coorDatasetEntity.getCoordjobname());
            return null;
        }
        System.out.println("寻找下游 输入: "+coorDatasetEntity.getCoordinatorjobid()+"  "+coorDatasetEntity.getCoordjobname());

        Set<String> coordIdSet = new HashSet<>();

        List<CoorDatasetEntity> coorDatasetEntityList1 = coorDatasetService.getCoorDatasetEntity(entity.getDoneflag());
        for(CoorDatasetEntity coord:coorDatasetEntityList1){
            coordIdSet.add(coord.getCoordinatorjobid());
        }

        List<CoordinatorjobsEntity> downCoordList =  coordinatorjobsService.queryListByIds(coordIdSet);

        for (CoordinatorjobsEntity coordinatorjobsEntity: downCoordList) {
            getDownCoord(coordinatorjobsEntity.getCoordjobid());
            System.out.println("下游依赖: "+coordinatorjobsEntity.getCoordjobid()+"  "+coordinatorjobsEntity.getCoordjobname());
        }
        return downCoordList;
    }

    private void getUpCoord(String coorId){

        CoorDatasetEntity coorDatasetEntity = coorDatasetService.queryObject(coorId);

        if(coorDatasetEntity == null || StringUtils.isEmpty(coorDatasetEntity.getDataset())){
             System.out.println("没有上游：");
            return ;
        }
        System.out.println("寻找上游输入: "+coorDatasetEntity.getCoordinatorjobid()+"  "+coorDatasetEntity.getCoordjobname());


        //生成依赖a的wf-a
        List<WorkflowsEntity> wfList = workflowsService.getWfByDoneFlag(coorDatasetEntity.getDataset());

        //使用wf-a 的coord
        Set<String> coordIdSet = new HashSet<>();

        for (WorkflowsEntity entity:wfList) {
            if(StringUtils.isNotBlank(entity.getParentid())){
                coordIdSet.add(getParentId(entity.getParentid()));
            }
        }
        //上层依赖
        List<CoordinatorjobsEntity> upCoordList =  coordinatorjobsService.queryListByIds(coordIdSet);
        if(null == upCoordList){
            return ;
        }
        for (CoordinatorjobsEntity coordinatorjobsEntity:upCoordList) {
            System.out.println("上游依赖: "+coordinatorjobsEntity.getCoordjobid()+"  "+coordinatorjobsEntity.getCoordjobname());
            this.getUpCoord(coordinatorjobsEntity.getCoordjobid());
        }

    }


    private void convertCoordToTaskDepen(List<TaskDependentEntity> childList,List<CoordinatorjobsEntity> coordList ){

        if(null == coordList){
            return ;
        }
        for (CoordinatorjobsEntity coordinatorjobsEntity:coordList) {
            TaskDependentEntity entity = new TaskDependentEntity();
            entity.setName(coordinatorjobsEntity.getCoordjobname());
            entity.setValue(coordinatorjobsEntity.getCoordjobid());
            childList.add(entity);
            System.out.println("上游依赖: "+coordinatorjobsEntity.getCoordjobid()+"  "+coordinatorjobsEntity.getCoordjobname());
            this.getUpCoord(coordinatorjobsEntity.getCoordjobid());
        }
    }



    private String getParentId(String parentId){
        if(StringUtils.isBlank(parentId)){
            return null;
        }
        int len = parentId.indexOf("@");
        String coordId = parentId.substring(0,len);
        return coordId;
    }


    }