package com.bitauto.bdc.modules.yarnApplication.monitor.schedule;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bitauto.bdc.common.utils.HttpClientUtils;
import com.bitauto.bdc.common.utils.PropertyUtil;
import com.bitauto.bdc.modules.config.service.ConfigService;
import com.bitauto.bdc.modules.yarnApplication.monitor.entity.YarnApplicationEntity;
import com.bitauto.bdc.modules.yarnApplication.monitor.service.YarnApplicationService;
import com.mysql.jdbc.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by michealzhang on 2017/10/31.
 */
@Service
public class YarnApplicationSyncService {

    protected Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    private YarnApplicationService yarnApplicationService;

    @Autowired
    private ConfigService configService;

    @Value("${RESTAPI.YARN_BASE_URL}")
    private String YARN_BASE_URL="http://172.20.0.13:8088/ws/v1/cluster/apps";

    private static final String KEY="yarnApplicationSchedule-lasttime";

    /**
     * 获取 yarnApplication 并将数据插入数据库
     * curl "http://192.168.87.235:8088/ws/v1/cluster/apps?startedTimeBegin=1510713510135&startedTimeEnd=1510714510135"
     */
    public void getYarnApplication(){
        String url = YARN_BASE_URL;

        String startedTimeBegin =configService.queryBykey(KEY).getValue();
        String startedTimeEnd = String.valueOf(new Date().getTime());
        if(StringUtils.isNullOrEmpty(startedTimeBegin)){
            url = url+"?startedTimeEnd="+startedTimeEnd;
        }else{
            url = url+"?startedTimeBegin="+startedTimeBegin+"&startedTimeEnd="+startedTimeEnd;
        }

        String res = null;
        try {
            res = HttpClientUtils.get(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<YarnApplicationEntity> list  = this.buildYarnApplicationEntityList(res);
        logger.debug("getYarnApplication result size : "+list.size() + " startedTimeEnd "+startedTimeEnd);
        if(list.size() >0 ){
            yarnApplicationService.saveBatch(list);
            configService.updateBykey(KEY,startedTimeEnd);
        }

    }

    /**
     * 跟新yarn 任务 ，将状态为 running 和 accept 的任务更新
     */
    public void updateYarnApplication(){
        List<YarnApplicationEntity> runningList = yarnApplicationService.getRunnigTask("RUNNING");
        List<YarnApplicationEntity> acceptList = yarnApplicationService.getRunnigTask("ACCEPTED");
        runningList.addAll(acceptList);
        updateApplication(runningList);

    }

    private void updateApplication(List<YarnApplicationEntity> list){
        for (YarnApplicationEntity yarnApplicationEntity:list) {
            String url = YARN_BASE_URL+"/";
            url+=yarnApplicationEntity.getId();
            String res = null;
            try {
                res = HttpClientUtils.get(url);
                yarnApplicationService.update(buildYarnApplicationEntity(res));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }




    }

    public YarnApplicationEntity buildYarnApplicationEntity(String json){
        JSONObject jsonObject = JSONObject.parseObject(json);
        JSONObject app = jsonObject.getJSONObject("app");

        YarnApplicationEntity yarnApplicationEntity = null;
        if(null != app){
            yarnApplicationEntity = JSONObject.parseObject(app.toJSONString(),YarnApplicationEntity.class);
        }
        return  yarnApplicationEntity;
    }


    /**
     * 解析json 返回YarnApplicationEntity
     * @param json
     * @return
     */
    public List<YarnApplicationEntity> buildYarnApplicationEntityList(String json){
//    json="{\n" +
//            "  \"apps\": {\n" +
//            "    \"app\": [\n" +
//            "      {\n" +
//            "        \"id\": \"application_1505885262153_340464\",\n" +
//            "        \"user\": \"kongdq\",\n" +
//            "        \"name\": \"oozie:launcher:T=hive2:W=yiche_pcwap_aggr_traffic_page_channel_da:A=hive2-400a:ID=0102654-171018182151964-oozie-root-W\",\n" +
//            "        \"queue\": \"root.bdc.dw\",\n" +
//            "        \"state\": \"FINISHED\",\n" +
//            "        \"finalStatus\": \"SUCCEEDED\",\n" +
//            "        \"progress\": 100,\n" +
//            "        \"trackingUI\": \"History\",\n" +
//            "        \"trackingUrl\": \"http://IDC003R01:8088/proxy/application_1505885262153_340464/\",\n" +
//            "        \"diagnostics\": \"\",\n" +
//            "        \"clusterId\": 1505885262153,\n" +
//            "        \"applicationType\": \"MAPREDUCE\",\n" +
//            "        \"applicationTags\": \"\",\n" +
//            "        \"startedTime\": 1509364556997,\n" +
//            "        \"finishedTime\": 1509367038920,\n" +
//            "        \"elapsedTime\": 2481923,\n" +
//            "        \"amContainerLogs\": \"http://IDC017R01:8042/node/containerlogs/container_e17_1505885262153_340464_01_000001/kongdq\",\n" +
//            "        \"amHostHttpAddress\": \"IDC017R01:8042\",\n" +
//            "        \"allocatedMB\": -1,\n" +
//            "        \"allocatedVCores\": -1,\n" +
//            "        \"runningContainers\": -1,\n" +
//            "        \"memorySeconds\": 3919625,\n" +
//            "        \"vcoreSeconds\": 1275,\n" +
//            "        \"preemptedResourceMB\": 0,\n" +
//            "        \"preemptedResourceVCores\": 0,\n" +
//            "        \"numNonAMContainerPreempted\": 0,\n" +
//            "        \"numAMContainerPreempted\": 0,\n" +
//            "        \"logAggregationStatus\": \"SUCCEEDED\"\n" +
//            "      },\n" +
//            "      {\n" +
//            "        \"id\": \"application_1505885262153_340520\",\n" +
//            "        \"user\": \"kongdq\",\n" +
//            "        \"name\": \"oozie:launcher:T=hive2:W=yiche_pcwap_attr_traffic_page_day:A=hive2-aa20:ID=0102667-171018182512539-oozie-root-W\",\n" +
//            "        \"queue\": \"root.bdc.dw\",\n" +
//            "        \"state\": \"FINISHED\",\n" +
//            "        \"finalStatus\": \"SUCCEEDED\",\n" +
//            "        \"progress\": 100,\n" +
//            "        \"trackingUI\": \"History\",\n" +
//            "        \"trackingUrl\": \"http://IDC003R01:8088/proxy/application_1505885262153_340520/\",\n" +
//            "        \"diagnostics\": \"\",\n" +
//            "        \"clusterId\": 1505885262153,\n" +
//            "        \"applicationType\": \"MAPREDUCE\",\n" +
//            "        \"applicationTags\": \"\",\n" +
//            "        \"startedTime\": 1509364808120,\n" +
//            "        \"finishedTime\": 1509367496282,\n" +
//            "        \"elapsedTime\": 2688162,\n" +
//            "        \"amContainerLogs\": \"http://IDC020R01:8042/node/containerlogs/container_e17_1505885262153_340520_01_000001/kongdq\",\n" +
//            "        \"amHostHttpAddress\": \"IDC020R01:8042\",\n" +
//            "        \"allocatedMB\": -1,\n" +
//            "        \"allocatedVCores\": -1,\n" +
//            "        \"runningContainers\": -1,\n" +
//            "        \"memorySeconds\": 6132824,\n" +
//            "        \"vcoreSeconds\": 1995,\n" +
//            "        \"preemptedResourceMB\": 0,\n" +
//            "        \"preemptedResourceVCores\": 0,\n" +
//            "        \"numNonAMContainerPreempted\": 0,\n" +
//            "        \"numAMContainerPreempted\": 0,\n" +
//            "        \"logAggregationStatus\": \"SUCCEEDED\"\n" +
//            "      },\n" +
//            "      {\n" +
//            "        \"id\": \"application_1505885262153_340585\",\n" +
//            "        \"user\": \"kongdq\",\n" +
//            "        \"name\": \"oozie:launcher:T=hive2:W=yiche_pcwap_aggr_traffic_page_channel_da:A=hive2-400a:ID=0102679-171018182512539-oozie-root-W\",\n" +
//            "        \"queue\": \"root.bdc.dw\",\n" +
//            "        \"state\": \"FINISHED\",\n" +
//            "        \"finalStatus\": \"SUCCEEDED\",\n" +
//            "        \"progress\": 100,\n" +
//            "        \"trackingUI\": \"History\",\n" +
//            "        \"trackingUrl\": \"http://IDC003R01:8088/proxy/application_1505885262153_340585/\",\n" +
//            "        \"diagnostics\": \"\",\n" +
//            "        \"clusterId\": 1505885262153,\n" +
//            "        \"applicationType\": \"MAPREDUCE\",\n" +
//            "        \"applicationTags\": \"\",\n" +
//            "        \"startedTime\": 1509365018967,\n" +
//            "        \"finishedTime\": 1509367279762,\n" +
//            "        \"elapsedTime\": 2260795,\n" +
//            "        \"amContainerLogs\": \"http://IDC013R01:8042/node/containerlogs/container_e17_1505885262153_340585_01_000001/kongdq\",\n" +
//            "        \"amHostHttpAddress\": \"IDC013R01:8042\",\n" +
//            "        \"allocatedMB\": -1,\n" +
//            "        \"allocatedVCores\": -1,\n" +
//            "        \"runningContainers\": -1,\n" +
//            "        \"memorySeconds\": 4044548,\n" +
//            "        \"vcoreSeconds\": 1316,\n" +
//            "        \"preemptedResourceMB\": 0,\n" +
//            "        \"preemptedResourceVCores\": 0,\n" +
//            "        \"numNonAMContainerPreempted\": 0,\n" +
//            "        \"numAMContainerPreempted\": 0,\n" +
//            "        \"logAggregationStatus\": \"SUCCEEDED\"\n" +
//            "      },\n" +
//            "      {\n" +
//            "        \"id\": \"application_1505885262153_340630\",\n" +
//            "        \"user\": \"kongdq\",\n" +
//            "        \"name\": \"oozie:launcher:T=hive2:W=yiche_pcwap_aggr_traffic_page_channel_da:A=hive2-400a:ID=0102688-171018182512539-oozie-root-W\",\n" +
//            "        \"queue\": \"root.bdc.dw\",\n" +
//            "        \"state\": \"FINISHED\",\n" +
//            "        \"finalStatus\": \"SUCCEEDED\",\n" +
//            "        \"progress\": 100,\n" +
//            "        \"trackingUI\": \"History\",\n" +
//            "        \"trackingUrl\": \"http://IDC003R01:8088/proxy/application_1505885262153_340630/\",\n" +
//            "        \"diagnostics\": \"\",\n" +
//            "        \"clusterId\": 1505885262153,\n" +
//            "        \"applicationType\": \"MAPREDUCE\",\n" +
//            "        \"applicationTags\": \"\",\n" +
//            "        \"startedTime\": 1509365158752,\n" +
//            "        \"finishedTime\": 1509367440395,\n" +
//            "        \"elapsedTime\": 2281643,\n" +
//            "        \"amContainerLogs\": \"http://IDC004R02:8042/node/containerlogs/container_e17_1505885262153_340630_01_000001/kongdq\",\n" +
//            "        \"amHostHttpAddress\": \"IDC004R02:8042\",\n" +
//            "        \"allocatedMB\": -1,\n" +
//            "        \"allocatedVCores\": -1,\n" +
//            "        \"runningContainers\": -1,\n" +
//            "        \"memorySeconds\": 4156289,\n" +
//            "        \"vcoreSeconds\": 1352,\n" +
//            "        \"preemptedResourceMB\": 0,\n" +
//            "        \"preemptedResourceVCores\": 0,\n" +
//            "        \"numNonAMContainerPreempted\": 0,\n" +
//            "        \"numAMContainerPreempted\": 0,\n" +
//            "        \"logAggregationStatus\": \"SUCCEEDED\"\n" +
//            "      },\n" +
//            "      {\n" +
//            "        \"id\": \"application_1505885262153_340641\",\n" +
//            "        \"user\": \"kongdq\",\n" +
//            "        \"name\": \"oozie:launcher:T=hive2:W=yiche_pcwap_app_leads_channel_day:A=hive2-ca69:ID=0102690-171018182151964-oozie-root-W\",\n" +
//            "        \"queue\": \"root.bdc.dw\",\n" +
//            "        \"state\": \"FINISHED\",\n" +
//            "        \"finalStatus\": \"SUCCEEDED\",\n" +
//            "        \"progress\": 100,\n" +
//            "        \"trackingUI\": \"History\",\n" +
//            "        \"trackingUrl\": \"http://IDC003R01:8088/proxy/application_1505885262153_340641/\",\n" +
//            "        \"diagnostics\": \"\",\n" +
//            "        \"clusterId\": 1505885262153,\n" +
//            "        \"applicationType\": \"MAPREDUCE\",\n" +
//            "        \"applicationTags\": \"\",\n" +
//            "        \"startedTime\": 1509365184713,\n" +
//            "        \"finishedTime\": 1509367242833,\n" +
//            "        \"elapsedTime\": 2058120,\n" +
//            "        \"amContainerLogs\": \"http://IDC005R02:8042/node/containerlogs/container_e17_1505885262153_340641_01_000001/kongdq\",\n" +
//            "        \"amHostHttpAddress\": \"IDC005R02:8042\",\n" +
//            "        \"allocatedMB\": -1,\n" +
//            "        \"allocatedVCores\": -1,\n" +
//            "        \"runningContainers\": -1,\n" +
//            "        \"memorySeconds\": 1206260,\n" +
//            "        \"vcoreSeconds\": 391,\n" +
//            "        \"preemptedResourceMB\": 0,\n" +
//            "        \"preemptedResourceVCores\": 0,\n" +
//            "        \"numNonAMContainerPreempted\": 0,\n" +
//            "        \"numAMContainerPreempted\": 0,\n" +
//            "        \"logAggregationStatus\": \"SUCCEEDED\"\n" +
//            "      }\n" +
//            "    ]\n" +
//            "  }\n" +
//            "}";
        JSONObject jsonObject = JSONObject.parseObject(json);
        JSONObject apps = jsonObject.getJSONObject("apps");
        JSONArray appList = apps.getJSONArray("app");

        List<YarnApplicationEntity> list = new ArrayList<>();
        list = JSONObject.parseArray(appList.toJSONString(),YarnApplicationEntity.class);

        return  list;
    }



}
