package com.bitauto.bdc.modules.oozie.monitor.schedule;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bitauto.bdc.common.utils.HttpClientUtils;
import com.bitauto.bdc.common.utils.PropertyUtil;
import com.bitauto.bdc.modules.config.service.ConfigService;
import com.bitauto.bdc.modules.oozie.monitor.entity.CoorDatasetEntity;
import com.bitauto.bdc.modules.oozie.monitor.entity.CoordinatorjobsEntity;
import com.bitauto.bdc.modules.oozie.monitor.entity.WorkflowsEntity;
import com.bitauto.bdc.modules.oozie.monitor.service.CoorDatasetService;
import com.bitauto.bdc.modules.oozie.monitor.service.CoordinatorjobsService;
import com.bitauto.bdc.modules.oozie.monitor.service.WorkflowsService;
import com.bitauto.bdc.modules.oozieDashboard.model.OozieCoordAction;
import com.bitauto.bdc.modules.resource.monitor.service.impl.NodeServiceImpl;
import com.bitauto.bdc.modules.yarnApplication.monitor.entity.YarnApplicationEntity;
import com.bitauto.bdc.modules.yarnApplication.monitor.service.YarnApplicationService;
import net.sf.json.JSON;
import net.sf.json.xml.XMLSerializer;
import org.json.JSONException;
import org.json.XML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by michealzhang on 2017/10/31.
 */
@Service
public class OozieServiceSyncService {

    @Value("${RESTAPI.OOZIE_BASE_URL}")
    private String OOZIE_BASE_URL="http://172.20.0.67:11000";

    private static final  String WF_KEY="workflows_offset";
    private static final  String COOR_KEY="coordinator_offset";

    private Logger LOG = LoggerFactory.getLogger(OozieServiceSyncService.class);



    @Autowired
    private WorkflowsService workflowsService;

    @Autowired
    private CoordinatorjobsService coordinatorjobsService;

    @Autowired
    private CoorDatasetService coorDatasetService;

    @Autowired
    private ConfigService configService;

    /**
     * 获取workflows
     * curl "http://172.20.0.67:11000/oozie/v1/jobs?len=2&offset=51&jobtype=wf”
     */
    public void getWorkflows(){

        String offset = configService.queryBykey(WF_KEY).getValue();
        String len = "100";
        String url=OOZIE_BASE_URL+"/oozie/v1/jobs?len="+len+"&offset="+offset+"&jobtype=wf";
        String res = null;
        try {
            res = HttpClientUtils.get(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<WorkflowsEntity> list = buildWorkflowsEntityBaseInfoList(res);
        if(list.size()>0){
            workflowsService.saveBatch(list);
            int newoffset =Integer.valueOf(offset);
            if(list.size()==Integer.valueOf(len).intValue()){
                newoffset = newoffset+Integer.parseInt(len);
            }else{
                newoffset = newoffset+list.size();
            }
            configService.updateBykey(WF_KEY,String.valueOf(newoffset));
        }
    }

    /**
     * 获取 coordinators
     * @return
     */
    public void getcoordinators(){
        String offset = configService.queryBykey(COOR_KEY).getValue();
        String len = "100";
        String url=OOZIE_BASE_URL+"/oozie/v1/jobs?len="+len+"&offset="+offset+"&jobtype=coord";
        String res = null;
        try {
            res = HttpClientUtils.get(url);
        } catch (Exception e) {
            LOG.error("getcoordinators error ,url "+url ,e);
        }
        List<CoordinatorjobsEntity> list = buildCoordinatorEntityBaseInfoList(res);
        if(list.size()>0){
            coordinatorjobsService.saveBatch(list);

            //获取实际拿到的数据集条数
            int lenth = list.size()< Integer.parseInt(len)?list.size():Integer.parseInt(len);
            int newoffset = Integer.parseInt(offset)+lenth;
            configService.updateBykey(COOR_KEY,String.valueOf(newoffset));
        }
    }



    /**
     * 获取workflow 执行信息,通过执行信息获取 对应到yarn中的子任务ID
     * curl "http://172.20.0.67:11000/oozie/v1/job/0110569-171018182512539-oozie-root-W"
     * @param
     * @return
     * {
    "appName": "dw_page_day",
    "externalId": null,
    "conf": "<configuration>\r\n  <property>\r\n    <name>end_date</name>\r\n    <value>2017-12-09T10:01+0800</value>\r\n  </property>\r\n  <property>\r\n    <name>wf_application_path</name>\r\n    <value>hdfs://cube1/user/cuiqian/persona_ycapp/dw_page_day</value>\r\n  </property>\r\n  <property>\r\n    <name>oozie.use.system.libpath</name>\r\n    <value>True</value>\r\n  </property>\r\n  <property>\r\n    <name>dryrun</name>\r\n    <value>False</value>\r\n  </property>\r\n  <property>\r\n    <name>security_enabled</name>\r\n    <value>False</value>\r\n  </property>\r\n  <property>\r\n    <name>pt</name>\r\n    <value>2017-09-09</value>\r\n  </property>\r\n  <property>\r\n    <name>oozie.coord.action.nominal_time</name>\r\n    <value>1504998900000</value>\r\n  </property>\r\n  <property>\r\n    <name>user.name</name>\r\n    <value>cuiqian</value>\r\n  </property>\r\n  <property>\r\n    <name>oozie.coord.application.path</name>\r\n    <value>hdfs://cube1/user/hue/oozie/deployments/_cuiqian_-oozie-123543-1509588175.11</value>\r\n  </property>\r\n  <property>\r\n    <name>mapreduce.job.user.name</name>\r\n    <value>cuiqian</value>\r\n  </property>\r\n  <property>\r\n    <name>oozie.wf.application.path</name>\r\n    <value>hdfs://cube1/user/cuiqian/persona_ycapp/dw_page_day</value>\r\n  </property>\r\n  <property>\r\n    <name>jobTracker</name>\r\n    <value>yarnRM</value>\r\n  </property>\r\n  <property>\r\n    <name>hue-id-c</name>\r\n    <value>123543</value>\r\n  </property>\r\n  <property>\r\n    <name>nameNode</name>\r\n    <value>hdfs://cube1</value>\r\n  </property>\r\n  <property>\r\n    <name>start_date</name>\r\n    <value>2017-09-01T01:10+0800</value>\r\n  </property>\r\n</configuration>",
    "run": 0,
    "acl": null,
    "appPath": "hdfs://cube1/user/cuiqian/persona_ycapp/dw_page_day",
    "parentId": "0109928-171018182512539-oozie-root-C@10",
    "lastModTime": "Thu, 02 Nov 2017 07:46:55 GMT",
    "consoleUrl": "http://IDC011R01:11000/oozie?job=0110569-171018182512539-oozie-root-W",
    "createdTime": "Thu, 02 Nov 2017 07:09:16 GMT",
    "startTime": "Thu, 02 Nov 2017 07:09:16 GMT",
    "toString": "Workflow id[0110569-171018182512539-oozie-root-W] status[SUCCEEDED]",
    "id": "0110569-171018182512539-oozie-root-W",
    "endTime": "Thu, 02 Nov 2017 07:46:55 GMT",
    "user": "cuiqian",
    "actions": [
    {
    "cred": null,
    "userRetryMax": 0,
    "trackerUri": "-",
    "data": null,
    "errorMessage": null,
    "userRetryCount": 0,
    "externalChildIDs": null,
    "externalId": "-",
    "errorCode": null,
    "conf": "",
    "type": ":START:",
    "transition": "hive2-393a",
    "retries": 0,
    "consoleUrl": "-",
    "stats": null,
    "userRetryInterval": 10,
    "name": ":start:",
    "startTime": "Thu, 02 Nov 2017 07:09:16 GMT",
    "toString": "Action name[:start:] status[OK]",
    "id": "0110569-171018182512539-oozie-root-W@:start:",
    "endTime": "Thu, 02 Nov 2017 07:09:16 GMT",
    "externalStatus": "OK",
    "status": "OK"
    },
    {
    "cred": "hive2",
    "userRetryMax": 0,
    "trackerUri": "yarnRM",
    "data": null,
    "errorMessage": null,
    "userRetryCount": 0,
    "externalChildIDs": "job_1505885262153_384750",
    "externalId": "job_1505885262153_384748",
    "errorCode": null,
    "conf": "<hive2 xmlns=\"uri:oozie:hive2-action:0.1\">\r\n  <job-tracker>yarnRM</job-tracker>\r\n  <name-node>hdfs://cube1</name-node>\r\n  <jdbc-url>jdbc:hive2://IDC011R01:10000/default</jdbc-url>\r\n  <script>/user/cuiqian/persona_ycapp/dw_page_day/dw_page_day</script>\r\n  <param>pt=2017-09-09</param>\r\n  <configuration>\r\n    <property xmlns=\"\">\r\n      <name>mapreduce.job.queuename</name>\r\n      <value>root.bdc.da</value>\r\n      <source>programatically</source>\r\n    </property>\r\n  </configuration>\r\n</hive2>",
    "type": "hive2",
    "transition": "fs-572e",
    "retries": 0,
    "consoleUrl": "http://IDC003R01:8088/proxy/application_1505885262153_384748/",
    "stats": null,
    "userRetryInterval": 10,
    "name": "hive2-393a",
    "startTime": "Thu, 02 Nov 2017 07:09:16 GMT",
    "toString": "Action name[hive2-393a] status[OK]",
    "id": "0110569-171018182512539-oozie-root-W@hive2-393a",
    "endTime": "Thu, 02 Nov 2017 07:46:55 GMT",
    "externalStatus": "SUCCEEDED",
    "status": "OK"
    },
    {
    "cred": null,
    "userRetryMax": 0,
    "trackerUri": "-",
    "data": null,
    "errorMessage": null,
    "userRetryCount": 0,
    "externalChildIDs": null,
    "externalId": "-",
    "errorCode": null,
    "conf": "",
    "type": ":END:",
    "transition": null,
    "retries": 0,
    "consoleUrl": "-",
    "stats": null,
    "userRetryInterval": 10,
    "name": "End",
    "startTime": "Thu, 02 Nov 2017 07:46:55 GMT",
    "toString": "Action name[End] status[OK]",
    "id": "0110569-171018182512539-oozie-root-W@End",
    "endTime": "Thu, 02 Nov 2017 07:46:55 GMT",
    "externalStatus": "OK",
    "status": "OK"
    },
    {
    "cred": null,
    "userRetryMax": 0,
    "trackerUri": "-",
    "data": null,
    "errorMessage": null,
    "userRetryCount": 0,
    "externalChildIDs": null,
    "externalId": "-",
    "errorCode": null,
    "conf": "<fs xmlns=\"uri:oozie:workflow:0.5\">\r\n  <touchz path=\"hdfs://cube1/bitauto/sign/bitauto_bdc_persona_yiche_app/persona_ycapp_dw_page_day/2017-09-09/_SUCCESS\" />\r\n  <name-node>hdfs://cube1</name-node>\r\n  <configuration>\r\n    <property xmlns=\"\">\r\n      <name>mapreduce.job.queuename</name>\r\n      <value>root.bdc.da</value>\r\n      <source>programatically</source>\r\n    </property>\r\n  </configuration>\r\n</fs>",
    "type": "fs",
    "transition": "End",
    "retries": 0,
    "consoleUrl": "-",
    "stats": null,
    "userRetryInterval": 10,
    "name": "fs-572e",
    "startTime": "Thu, 02 Nov 2017 07:46:55 GMT",
    "toString": "Action name[fs-572e] status[OK]",
    "id": "0110569-171018182512539-oozie-root-W@fs-572e",
    "endTime": "Thu, 02 Nov 2017 07:46:55 GMT",
    "externalStatus": "OK",
    "status": "OK"
    }
    ],
    "status": "SUCCEEDED",
    "group": null
    }
     *
     */
    private WorkflowsEntity getWorkflowInfo(WorkflowsEntity workflowsEntity){
        String url=OOZIE_BASE_URL+"/oozie/v1/job/"+workflowsEntity.getId();
        String res = null;
        try {
            res = HttpClientUtils.get(url);
        } catch (Exception e) {
            e.printStackTrace();
        }

        JSONObject jsonObject = JSONObject.parseObject(res);
        JSONArray array = jsonObject.getJSONArray("actions");

        StringBuilder externalChildIDs = new StringBuilder();
        StringBuilder externalId = new StringBuilder();

        for (int i = 0; i < array.size(); i++) {
            JSONObject jsonObject1 = JSONObject.parseObject(array.get(i).toString());
            if(jsonObject1.containsKey("externalChildIDs")){
                String str = jsonObject1.get("externalChildIDs").toString();
                if(!str.equals("-")){
                    externalChildIDs.append(str).append(",");
                }

            }

            JSONObject jsonObject2 =JSONObject.parseObject(array.get(i).toString());
            if(jsonObject2.containsKey("externalId")){
                String str1 = jsonObject2.get("externalId").toString();
                if(!str1.equals("-")){
                    externalChildIDs.append(str1).append(",");
                }
            }

        }

        workflowsEntity.setExternalchildids(externalChildIDs.toString());
        workflowsEntity.setExternalid(externalId.toString());

        return workflowsEntity;

    }


        /**
         * 获取workflow 定义，以此获取work 定义中产生的成功标识
         * curl "http://172.20.0.67:11000/oozie/v1/job/0110569-171018182512539-oozie-root-W?show=definition"
         * @return
         */
    private  WorkflowsEntity getWorkflowDoneFlag(WorkflowsEntity workflowsEntity){
        String url=OOZIE_BASE_URL+"/oozie/v1/job/"+workflowsEntity.getId()+"?show=definition";
        String res = null;
        try {
            res = HttpClientUtils.get(url);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String json = XML.toJSONObject(res).toString();
            JSONObject jsonObject = JSONObject.parseObject(json);


            Object action = jsonObject.getJSONObject("workflow-app").get("action");

            if(action instanceof  JSONObject){

                JSONObject jsonObject1 = (JSONObject)action;
                if(jsonObject1.containsKey("fs")){ //如果包含fs key 则获取
                    JSONObject fsObject = jsonObject1.getJSONObject("fs");
                    String path = fsObject.getJSONObject("touchz").getString("path");
                    workflowsEntity.setDoneflag(getRealPaht(path,workflowsEntity.getId()));
                }

            }else if(action instanceof  JSONArray){
                JSONArray jsonArray = JSONObject.parseArray(com.alibaba.fastjson.JSON.toJSONString(action));
                for (int i = 0; i <jsonArray.size() ; i++) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                    if(jsonObject1.containsKey("fs")){ //如果包含fs key 则获取
                        JSONObject fsObject = jsonObject1.getJSONObject("fs");
                        if(fsObject.containsKey("touchz")){
                            String path = fsObject.getJSONObject("touchz").getString("path");
                            workflowsEntity.setDoneflag(getRealPaht(path,workflowsEntity.getId()));
                        }
                    }

                }
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return workflowsEntity;
    }

    /**
     * 构建包含 成功标识 和子任务id 的 workflowEntity list
     * @param workflowsRes
     * @return
     */
    private List<WorkflowsEntity> buildWorkflowsEntityBaseInfoList(String workflowsRes){
        JSONObject jsonObject = JSONObject.parseObject(workflowsRes);
        JSONArray array = jsonObject.getJSONArray("workflows");

        List<WorkflowsEntity> list = new ArrayList<>();

        for (int i = 0; i < array.size(); i++) {
           WorkflowsEntity entity =  JSONObject.parseObject(array.get(i).toString(),WorkflowsEntity.class);

           entity = this.getWorkflowInfo(entity);     //获取yarn 中的applicatid
           entity = this.getWorkflowDoneFlag(entity); //获取创建的 成功标识

           list.add(entity);
        }

        return list;

    }


    private List<CoordinatorjobsEntity> buildCoordinatorEntityBaseInfoList(String coordinatorRes){
        JSONObject jsonObject = JSONObject.parseObject(coordinatorRes);
        JSONArray array = jsonObject.getJSONArray("coordinatorjobs");

        List<CoordinatorjobsEntity> list = new ArrayList<>();

        for (int i = 0; i < array.size(); i++) {
            CoordinatorjobsEntity entity =  JSONObject.parseObject(array.get(i).toString(),CoordinatorjobsEntity.class);
            setCoordWorkFlow(entity);
            setCoordinatorDateSet(entity);
            list.add(entity);
        }

        return list;

    }



    /**
     * curl "http://172.20.0.67:11000/oozie/v1/job/0017249-170719105023061-oozie-root-C"
     * 获取coordinate 所有执行过的action  根据action 获取 coord 对应的wofkflow_id
     * @return
     */
    private void setCoordWorkFlow(CoordinatorjobsEntity entity) {

        String url=OOZIE_BASE_URL+"/oozie/v1/job/"+entity.getCoordjobid();
        String coord = null;
        try {
            coord = HttpClientUtils.get(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String wfId = "";
        JSONObject jsonObject = JSONObject.parseObject(coord);
        String coordinator = jsonObject.getString("actions");
        List<OozieCoordAction> list = JSONObject.parseArray(coordinator,OozieCoordAction.class);
        if(null != list && list.size() > 0){
            wfId = list.get(0).getExternalId();
        }
        entity.setWfId(wfId);
    }

    /**
     *
     * @param entity
     * @return
     */
    private  void setCoordinatorDateSet(CoordinatorjobsEntity entity){

        String url=OOZIE_BASE_URL+"/oozie/v1/job/"+entity.getCoordjobid()+"?show=definition";
        String res = null;
        try {
            res = HttpClientUtils.get(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<CoorDatasetEntity> list =  new ArrayList<>();
        try {
            String json = XML.toJSONObject(res).toString();
            JSONObject jsonObject = JSONObject.parseObject(json);
            JSONObject coordinator = jsonObject.getJSONObject("coordinator-app");
            if(coordinator.containsKey("datasets")) {
                Object dataset = coordinator.getJSONObject("datasets").get("dataset");

                if (dataset instanceof JSONObject) {
                    String uri_template = ((JSONObject) dataset).getString("uri-template");

                    CoorDatasetEntity coorDatasetEntity = new CoorDatasetEntity();
                    coorDatasetEntity.setCoordinatorjobid(entity.getCoordjobid());
                    coorDatasetEntity.setDataset(getRealPaht(uri_template,entity.getCoordexternalid()));
                    coorDatasetEntity.setWfId(entity.getWfId());
                    coorDatasetEntity.setCoordjobname(entity.getCoordjobname());

                    list.add(coorDatasetEntity);

                } else if (dataset instanceof JSONArray) {
                    JSONArray array = JSONObject.parseArray(com.alibaba.fastjson.JSON.toJSONString(dataset));

                    for (int i = 0; i < array.size(); i++) {
                        JSONObject jsonObject1 = array.getJSONObject(i);

                        if (jsonObject1.containsKey("uri-template")) { //如果包含fs key 则获取
                            String uri_template = jsonObject1.getString("uri-template");

                            CoorDatasetEntity coorDatasetEntity = new CoorDatasetEntity();
                            coorDatasetEntity.setCoordinatorjobid(entity.getCoordjobid());
                            coorDatasetEntity.setDataset(getRealPaht(uri_template,entity.getCoordexternalid()));
                            coorDatasetEntity.setWfId(entity.getWfId());
                            coorDatasetEntity.setCoordjobname(entity.getCoordjobname());

                            list.add(coorDatasetEntity);

                        }

                    }
                }
                coorDatasetService.saveBatch(list);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取依赖路径 ，并去掉时间 表达式
     * @param source
     * @return
     */
    private String getRealPaht(String source,String id){
        String res = "";
        try {
             res =  source.substring(0,source.indexOf("/$"));
        }catch (Exception e){
            LOG.error(id+" "+source);
        }
        return  res;
    }




}
