package com.bitauto.bdc.modules.oozieDashboard.util;


import com.alibaba.fastjson.JSONObject;
import com.bitauto.bdc.modules.oozieDashboard.model.*;

import java.util.Date;
import java.util.List;

/**
 * Created by michealzhang on 2017/8/17.
 */
public class JobPraseUtil {
    /**
     * curl "http://172.20.0.67:11000/oozie/v1/job/0021399-170719105023061-oozie-root-W"
     * @param json
     * @return
     */
    public static JobInfo parseJobDetailJson(String json){
//        json = "{\n" +
//                "  \"appName\": \"t2pdm_t01_huimaicheapp_device_gen_dt\",\n" +
//                "  \"externalId\": null,\n" +
//                "  \"conf\": \"<configuration>\\r\\n  <property>\\r\\n    <name>end_date</name>\\r\\n    <value>2027-08-01T09:00+0800</value>\\r\\n  </property>\\r\\n  <property>\\r\\n    <name>wf_application_path</name>\\r\\n    <value>hdfs://cube1/bitauto/bdc/apps/dwd/t2pdm/t2pdm_t01_huimaicheapp_device_gen_dt</value>\\r\\n  </property>\\r\\n  <property>\\r\\n    <name>oozie.use.system.libpath</name>\\r\\n    <value>True</value>\\r\\n  </property>\\r\\n  <property>\\r\\n    <name>dryrun</name>\\r\\n    <value>False</value>\\r\\n  </property>\\r\\n  <property>\\r\\n    <name>security_enabled</name>\\r\\n    <value>False</value>\\r\\n  </property>\\r\\n  <property>\\r\\n    <name>pt</name>\\r\\n    <value>2017-08-15</value>\\r\\n  </property>\\r\\n  <property>\\r\\n    <name>oozie.coord.action.nominal_time</name>\\r\\n    <value>1502845200000</value>\\r\\n  </property>\\r\\n  <property>\\r\\n    <name>user.name</name>\\r\\n    <value>hejianglong</value>\\r\\n  </property>\\r\\n  <property>\\r\\n    <name>oozie.coord.application.path</name>\\r\\n    <value>hdfs://cube1/user/hue/oozie/deployments/_hejianglong_-oozie-64886-1502363825.0</value>\\r\\n  </property>\\r\\n  <property>\\r\\n    <name>mapreduce.job.user.name</name>\\r\\n    <value>hejianglong</value>\\r\\n  </property>\\r\\n  <property>\\r\\n    <name>oozie.wf.application.path</name>\\r\\n    <value>hdfs://cube1/bitauto/bdc/apps/dwd/t2pdm/t2pdm_t01_huimaicheapp_device_gen_dt</value>\\r\\n  </property>\\r\\n  <property>\\r\\n    <name>jobTracker</name>\\r\\n    <value>yarnRM</value>\\r\\n  </property>\\r\\n  <property>\\r\\n    <name>hue-id-c</name>\\r\\n    <value>64886</value>\\r\\n  </property>\\r\\n  <property>\\r\\n    <name>nameNode</name>\\r\\n    <value>hdfs://cube1</value>\\r\\n  </property>\\r\\n  <property>\\r\\n    <name>start_date</name>\\r\\n    <value>2017-08-01T09:00+0800</value>\\r\\n  </property>\\r\\n  <property>\\r\\n    <name>ods_depend</name>\\r\\n    <value>hdfs://cube1/user/hejianglong/</value>\\r\\n  </property>\\r\\n</configuration>\",\n" +
//                "  \"run\": 0,\n" +
//                "  \"acl\": null,\n" +
//                "  \"appPath\": \"hdfs://cube1/bitauto/bdc/apps/dwd/t2pdm/t2pdm_t01_huimaicheapp_device_gen_dt\",\n" +
//                "  \"parentId\": \"0014288-170719105022944-oozie-root-C@16\",\n" +
//                "  \"lastModTime\": \"Wed, 16 Aug 2017 01:09:37 GMT\",\n" +
//                "  \"consoleUrl\": \"http://IDC011R01:11000/oozie?job=0021548-170719105023061-oozie-root-W\",\n" +
//                "  \"createdTime\": \"Wed, 16 Aug 2017 01:00:01 GMT\",\n" +
//                "  \"startTime\": \"Wed, 16 Aug 2017 01:00:01 GMT\",\n" +
//                "  \"toString\": \"Workflow id[0021548-170719105023061-oozie-root-W] status[SUCCEEDED]\",\n" +
//                "  \"id\": \"0021548-170719105023061-oozie-root-W\",\n" +
//                "  \"endTime\": \"Wed, 16 Aug 2017 01:09:37 GMT\",\n" +
//                "  \"user\": \"hejianglong\",\n" +
//                "  \"actions\": [\n" +
//                "    {\n" +
//                "      \"cred\": null,\n" +
//                "      \"userRetryMax\": 0,\n" +
//                "      \"trackerUri\": \"-\",\n" +
//                "      \"data\": null,\n" +
//                "      \"errorMessage\": null,\n" +
//                "      \"userRetryCount\": 0,\n" +
//                "      \"externalChildIDs\": null,\n" +
//                "      \"externalId\": \"-\",\n" +
//                "      \"errorCode\": null,\n" +
//                "      \"conf\": \"\",\n" +
//                "      \"type\": \":START:\",\n" +
//                "      \"transition\": \"hive2-b887\",\n" +
//                "      \"retries\": 0,\n" +
//                "      \"consoleUrl\": \"-\",\n" +
//                "      \"stats\": null,\n" +
//                "      \"userRetryInterval\": 10,\n" +
//                "      \"name\": \":start:\",\n" +
//                "      \"startTime\": \"Wed, 16 Aug 2017 01:00:01 GMT\",\n" +
//                "      \"toString\": \"Action name[:start:] status[OK]\",\n" +
//                "      \"id\": \"0021548-170719105023061-oozie-root-W@:start:\",\n" +
//                "      \"endTime\": \"Wed, 16 Aug 2017 01:00:01 GMT\",\n" +
//                "      \"externalStatus\": \"OK\",\n" +
//                "      \"status\": \"OK\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "      \"cred\": \"hive2\",\n" +
//                "      \"userRetryMax\": 0,\n" +
//                "      \"trackerUri\": \"yarnRM\",\n" +
//                "      \"data\": null,\n" +
//                "      \"errorMessage\": null,\n" +
//                "      \"userRetryCount\": 0,\n" +
//                "      \"externalChildIDs\": \"job_1499939130669_96379,job_1499939130669_96402,job_1499939130669_96441\",\n" +
//                "      \"externalId\": \"job_1499939130669_96354\",\n" +
//                "      \"errorCode\": null,\n" +
//                "      \"conf\": \"<hive2 xmlns=\\\"uri:oozie:hive2-action:0.1\\\">\\r\\n  <job-tracker>yarnRM</job-tracker>\\r\\n  <name-node>hdfs://cube1</name-node>\\r\\n  <jdbc-url>jdbc:hive2://IDC011R01:10000/default</jdbc-url>\\r\\n  <script>/bitauto/bdc/apps/dwd/t2pdm/t2pdm_t01_huimaicheapp_device_gen_dt/t2pdm_t01_huimaicheapp_device_gen_dt_0200.hql</script>\\r\\n  <param>ETL_DT=2017-08-15</param>\\r\\n  <configuration />\\r\\n</hive2>\",\n" +
//                "      \"type\": \"hive2\",\n" +
//                "      \"transition\": \"fs-f6ce\",\n" +
//                "      \"retries\": 0,\n" +
//                "      \"consoleUrl\": \"http://IDC003R01:8088/proxy/application_1499939130669_96354/\",\n" +
//                "      \"stats\": null,\n" +
//                "      \"userRetryInterval\": 10,\n" +
//                "      \"name\": \"hive2-b887\",\n" +
//                "      \"startTime\": \"Wed, 16 Aug 2017 01:00:04 GMT\",\n" +
//                "      \"toString\": \"Action name[hive2-b887] status[OK]\",\n" +
//                "      \"id\": \"0021548-170719105023061-oozie-root-W@hive2-b887\",\n" +
//                "      \"endTime\": \"Wed, 16 Aug 2017 01:09:37 GMT\",\n" +
//                "      \"externalStatus\": \"SUCCEEDED\",\n" +
//                "      \"status\": \"OK\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "      \"cred\": null,\n" +
//                "      \"userRetryMax\": 0,\n" +
//                "      \"trackerUri\": \"-\",\n" +
//                "      \"data\": null,\n" +
//                "      \"errorMessage\": null,\n" +
//                "      \"userRetryCount\": 0,\n" +
//                "      \"externalChildIDs\": null,\n" +
//                "      \"externalId\": \"-\",\n" +
//                "      \"errorCode\": null,\n" +
//                "      \"conf\": \"\",\n" +
//                "      \"type\": \":END:\",\n" +
//                "      \"transition\": null,\n" +
//                "      \"retries\": 0,\n" +
//                "      \"consoleUrl\": \"-\",\n" +
//                "      \"stats\": null,\n" +
//                "      \"userRetryInterval\": 10,\n" +
//                "      \"name\": \"End\",\n" +
//                "      \"startTime\": \"Wed, 16 Aug 2017 01:09:37 GMT\",\n" +
//                "      \"toString\": \"Action name[End] status[OK]\",\n" +
//                "      \"id\": \"0021548-170719105023061-oozie-root-W@End\",\n" +
//                "      \"endTime\": \"Wed, 16 Aug 2017 01:09:37 GMT\",\n" +
//                "      \"externalStatus\": \"OK\",\n" +
//                "      \"status\": \"OK\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "      \"cred\": null,\n" +
//                "      \"userRetryMax\": 0,\n" +
//                "      \"trackerUri\": \"-\",\n" +
//                "      \"data\": null,\n" +
//                "      \"errorMessage\": null,\n" +
//                "      \"userRetryCount\": 0,\n" +
//                "      \"externalChildIDs\": null,\n" +
//                "      \"externalId\": \"-\",\n" +
//                "      \"errorCode\": null,\n" +
//                "      \"conf\": \"<fs xmlns=\\\"uri:oozie:workflow:0.5\\\">\\r\\n  <touchz path=\\\"hdfs://cube1/bitauto/sign/t2pdm/t2pdm_t01_huimaicheapp_device_gen_dt/2017-08-15/_SUCCESS\\\" />\\r\\n  <name-node>hdfs://cube1</name-node>\\r\\n  <configuration />\\r\\n</fs>\",\n" +
//                "      \"type\": \"fs\",\n" +
//                "      \"transition\": \"End\",\n" +
//                "      \"retries\": 0,\n" +
//                "      \"consoleUrl\": \"-\",\n" +
//                "      \"stats\": null,\n" +
//                "      \"userRetryInterval\": 10,\n" +
//                "      \"name\": \"fs-f6ce\",\n" +
//                "      \"startTime\": \"Wed, 16 Aug 2017 01:09:37 GMT\",\n" +
//                "      \"toString\": \"Action name[fs-f6ce] status[OK]\",\n" +
//                "      \"id\": \"0021548-170719105023061-oozie-root-W@fs-f6ce\",\n" +
//                "      \"endTime\": \"Wed, 16 Aug 2017 01:09:37 GMT\",\n" +
//                "      \"externalStatus\": \"OK\",\n" +
//                "      \"status\": \"OK\"\n" +
//                "    }\n" +
//                "  ],\n" +
//                "  \"status\": \"SUCCEEDED\",\n" +
//                "  \"group\": null\n" +
//                "}";

        JSONObject jsonObject = JSONObject.parseObject(json);
        String user = String.valueOf(jsonObject.get("user"));
        String id = String.valueOf(jsonObject.get("id"));
        String name = String.valueOf(jsonObject.get("appName"));

        JobInfo jobInfo = new JobInfo();
        jobInfo.setAuthName(user);
        jobInfo.setJobId(id);
        jobInfo.setJobName(name);

        return jobInfo;
    }

    /**
     * curl "http://172.20.0.67:11000/oozie/v1/job/0021399-170719105023061-oozie-root-W"
     * @param json
     * @return
     */
    public static JobExecInfo parseJobExecJson(String json){
//        json = "{\n" +
//                "  \"appName\": \"t2pdm_t01_huimaicheapp_device_gen_dt\",\n" +
//                "  \"externalId\": null,\n" +
//                "  \"conf\": \"<configuration>\\r\\n  <property>\\r\\n    <name>end_date</name>\\r\\n    <value>2027-08-01T09:00+0800</value>\\r\\n  </property>\\r\\n  <property>\\r\\n    <name>wf_application_path</name>\\r\\n    <value>hdfs://cube1/bitauto/bdc/apps/dwd/t2pdm/t2pdm_t01_huimaicheapp_device_gen_dt</value>\\r\\n  </property>\\r\\n  <property>\\r\\n    <name>oozie.use.system.libpath</name>\\r\\n    <value>True</value>\\r\\n  </property>\\r\\n  <property>\\r\\n    <name>dryrun</name>\\r\\n    <value>False</value>\\r\\n  </property>\\r\\n  <property>\\r\\n    <name>security_enabled</name>\\r\\n    <value>False</value>\\r\\n  </property>\\r\\n  <property>\\r\\n    <name>pt</name>\\r\\n    <value>2017-08-15</value>\\r\\n  </property>\\r\\n  <property>\\r\\n    <name>oozie.coord.action.nominal_time</name>\\r\\n    <value>1502845200000</value>\\r\\n  </property>\\r\\n  <property>\\r\\n    <name>user.name</name>\\r\\n    <value>hejianglong</value>\\r\\n  </property>\\r\\n  <property>\\r\\n    <name>oozie.coord.application.path</name>\\r\\n    <value>hdfs://cube1/user/hue/oozie/deployments/_hejianglong_-oozie-64886-1502363825.0</value>\\r\\n  </property>\\r\\n  <property>\\r\\n    <name>mapreduce.job.user.name</name>\\r\\n    <value>hejianglong</value>\\r\\n  </property>\\r\\n  <property>\\r\\n    <name>oozie.wf.application.path</name>\\r\\n    <value>hdfs://cube1/bitauto/bdc/apps/dwd/t2pdm/t2pdm_t01_huimaicheapp_device_gen_dt</value>\\r\\n  </property>\\r\\n  <property>\\r\\n    <name>jobTracker</name>\\r\\n    <value>yarnRM</value>\\r\\n  </property>\\r\\n  <property>\\r\\n    <name>hue-id-c</name>\\r\\n    <value>64886</value>\\r\\n  </property>\\r\\n  <property>\\r\\n    <name>nameNode</name>\\r\\n    <value>hdfs://cube1</value>\\r\\n  </property>\\r\\n  <property>\\r\\n    <name>start_date</name>\\r\\n    <value>2017-08-01T09:00+0800</value>\\r\\n  </property>\\r\\n  <property>\\r\\n    <name>ods_depend</name>\\r\\n    <value>hdfs://cube1/user/hejianglong/</value>\\r\\n  </property>\\r\\n</configuration>\",\n" +
//                "  \"run\": 0,\n" +
//                "  \"acl\": null,\n" +
//                "  \"appPath\": \"hdfs://cube1/bitauto/bdc/apps/dwd/t2pdm/t2pdm_t01_huimaicheapp_device_gen_dt\",\n" +
//                "  \"parentId\": \"0014288-170719105022944-oozie-root-C@16\",\n" +
//                "  \"lastModTime\": \"Wed, 16 Aug 2017 01:09:37 GMT\",\n" +
//                "  \"consoleUrl\": \"http://IDC011R01:11000/oozie?job=0021548-170719105023061-oozie-root-W\",\n" +
//                "  \"createdTime\": \"Wed, 16 Aug 2017 01:00:01 GMT\",\n" +
//                "  \"startTime\": \"Wed, 16 Aug 2017 01:00:01 GMT\",\n" +
//                "  \"toString\": \"Workflow id[0021548-170719105023061-oozie-root-W] status[SUCCEEDED]\",\n" +
//                "  \"id\": \"0021548-170719105023061-oozie-root-W\",\n" +
//                "  \"endTime\": \"Wed, 16 Aug 2017 01:09:37 GMT\",\n" +
//                "  \"user\": \"hejianglong\",\n" +
//                "  \"actions\": [\n" +
//                "    {\n" +
//                "      \"cred\": null,\n" +
//                "      \"userRetryMax\": 0,\n" +
//                "      \"trackerUri\": \"-\",\n" +
//                "      \"data\": null,\n" +
//                "      \"errorMessage\": null,\n" +
//                "      \"userRetryCount\": 0,\n" +
//                "      \"externalChildIDs\": null,\n" +
//                "      \"externalId\": \"-\",\n" +
//                "      \"errorCode\": null,\n" +
//                "      \"conf\": \"\",\n" +
//                "      \"type\": \":START:\",\n" +
//                "      \"transition\": \"hive2-b887\",\n" +
//                "      \"retries\": 0,\n" +
//                "      \"consoleUrl\": \"-\",\n" +
//                "      \"stats\": null,\n" +
//                "      \"userRetryInterval\": 10,\n" +
//                "      \"name\": \":start:\",\n" +
//                "      \"startTime\": \"Wed, 16 Aug 2017 01:00:01 GMT\",\n" +
//                "      \"toString\": \"Action name[:start:] status[OK]\",\n" +
//                "      \"id\": \"0021548-170719105023061-oozie-root-W@:start:\",\n" +
//                "      \"endTime\": \"Wed, 16 Aug 2017 01:00:01 GMT\",\n" +
//                "      \"externalStatus\": \"OK\",\n" +
//                "      \"status\": \"OK\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "      \"cred\": \"hive2\",\n" +
//                "      \"userRetryMax\": 0,\n" +
//                "      \"trackerUri\": \"yarnRM\",\n" +
//                "      \"data\": null,\n" +
//                "      \"errorMessage\": null,\n" +
//                "      \"userRetryCount\": 0,\n" +
//                "      \"externalChildIDs\": \"job_1499939130669_96379,job_1499939130669_96402,job_1499939130669_96441\",\n" +
//                "      \"externalId\": \"job_1499939130669_96354\",\n" +
//                "      \"errorCode\": null,\n" +
//                "      \"conf\": \"<hive2 xmlns=\\\"uri:oozie:hive2-action:0.1\\\">\\r\\n  <job-tracker>yarnRM</job-tracker>\\r\\n  <name-node>hdfs://cube1</name-node>\\r\\n  <jdbc-url>jdbc:hive2://IDC011R01:10000/default</jdbc-url>\\r\\n  <script>/bitauto/bdc/apps/dwd/t2pdm/t2pdm_t01_huimaicheapp_device_gen_dt/t2pdm_t01_huimaicheapp_device_gen_dt_0200.hql</script>\\r\\n  <param>ETL_DT=2017-08-15</param>\\r\\n  <configuration />\\r\\n</hive2>\",\n" +
//                "      \"type\": \"hive2\",\n" +
//                "      \"transition\": \"fs-f6ce\",\n" +
//                "      \"retries\": 0,\n" +
//                "      \"consoleUrl\": \"http://IDC003R01:8088/proxy/application_1499939130669_96354/\",\n" +
//                "      \"stats\": null,\n" +
//                "      \"userRetryInterval\": 10,\n" +
//                "      \"name\": \"hive2-b887\",\n" +
//                "      \"startTime\": \"Wed, 16 Aug 2017 01:00:04 GMT\",\n" +
//                "      \"toString\": \"Action name[hive2-b887] status[OK]\",\n" +
//                "      \"id\": \"0021548-170719105023061-oozie-root-W@hive2-b887\",\n" +
//                "      \"endTime\": \"Wed, 16 Aug 2017 01:09:37 GMT\",\n" +
//                "      \"externalStatus\": \"SUCCEEDED\",\n" +
//                "      \"status\": \"OK\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "      \"cred\": null,\n" +
//                "      \"userRetryMax\": 0,\n" +
//                "      \"trackerUri\": \"-\",\n" +
//                "      \"data\": null,\n" +
//                "      \"errorMessage\": null,\n" +
//                "      \"userRetryCount\": 0,\n" +
//                "      \"externalChildIDs\": null,\n" +
//                "      \"externalId\": \"-\",\n" +
//                "      \"errorCode\": null,\n" +
//                "      \"conf\": \"\",\n" +
//                "      \"type\": \":END:\",\n" +
//                "      \"transition\": null,\n" +
//                "      \"retries\": 0,\n" +
//                "      \"consoleUrl\": \"-\",\n" +
//                "      \"stats\": null,\n" +
//                "      \"userRetryInterval\": 10,\n" +
//                "      \"name\": \"End\",\n" +
//                "      \"startTime\": \"Wed, 16 Aug 2017 01:09:37 GMT\",\n" +
//                "      \"toString\": \"Action name[End] status[OK]\",\n" +
//                "      \"id\": \"0021548-170719105023061-oozie-root-W@End\",\n" +
//                "      \"endTime\": \"Wed, 16 Aug 2017 01:09:37 GMT\",\n" +
//                "      \"externalStatus\": \"OK\",\n" +
//                "      \"status\": \"OK\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "      \"cred\": null,\n" +
//                "      \"userRetryMax\": 0,\n" +
//                "      \"trackerUri\": \"-\",\n" +
//                "      \"data\": null,\n" +
//                "      \"errorMessage\": null,\n" +
//                "      \"userRetryCount\": 0,\n" +
//                "      \"externalChildIDs\": null,\n" +
//                "      \"externalId\": \"-\",\n" +
//                "      \"errorCode\": null,\n" +
//                "      \"conf\": \"<fs xmlns=\\\"uri:oozie:workflow:0.5\\\">\\r\\n  <touchz path=\\\"hdfs://cube1/bitauto/sign/t2pdm/t2pdm_t01_huimaicheapp_device_gen_dt/2017-08-15/_SUCCESS\\\" />\\r\\n  <name-node>hdfs://cube1</name-node>\\r\\n  <configuration />\\r\\n</fs>\",\n" +
//                "      \"type\": \"fs\",\n" +
//                "      \"transition\": \"End\",\n" +
//                "      \"retries\": 0,\n" +
//                "      \"consoleUrl\": \"-\",\n" +
//                "      \"stats\": null,\n" +
//                "      \"userRetryInterval\": 10,\n" +
//                "      \"name\": \"fs-f6ce\",\n" +
//                "      \"startTime\": \"Wed, 16 Aug 2017 01:09:37 GMT\",\n" +
//                "      \"toString\": \"Action name[fs-f6ce] status[OK]\",\n" +
//                "      \"id\": \"0021548-170719105023061-oozie-root-W@fs-f6ce\",\n" +
//                "      \"endTime\": \"Wed, 16 Aug 2017 01:09:37 GMT\",\n" +
//                "      \"externalStatus\": \"OK\",\n" +
//                "      \"status\": \"OK\"\n" +
//                "    }\n" +
//                "  ],\n" +
//                "  \"status\": \"SUCCEEDED\",\n" +
//                "  \"group\": null\n" +
//                "}";

        JSONObject jsonObject = JSONObject.parseObject(json);
        String name = String.valueOf(jsonObject.get("appName"));
        String user = String.valueOf(jsonObject.get("user"));
        String id = String.valueOf(jsonObject.get("id"));
        String startTime = TimeUtils.praseGMT(String.valueOf(jsonObject.get("startTime")));
        String endTime = TimeUtils.praseGMT(String.valueOf(jsonObject.get("endTime")));
        String status = String.valueOf(jsonObject.get("status"));


        JobExecInfo jobExecInfo = new JobExecInfo();
        Date start= null;
        Date end = null;
        try{
            start = TimeUtils.parse(startTime,TimeUtils.yyyy_MM_dd_HH_mm_ss);
            end = TimeUtils.parse(endTime,TimeUtils.yyyy_MM_dd_HH_mm_ss);

        }catch (Exception e){
            e.printStackTrace();
        }

        jobExecInfo.setStartTime(start);
        jobExecInfo.setEndTime(end);
        jobExecInfo.setJobId(id);
        jobExecInfo.setJobName(name);
        jobExecInfo.setExecTime(TimeUtils.getBetweenSecs(start,end)+"");
        jobExecInfo.setStatus(status);

        return jobExecInfo;

    }

    /**
     * curl "http://172.20.0.67:11000/oozie/v1/jobs?len=2&offset=51&jobtype=wf"
     * @param workflows
     * @return
     */
    public static List<OozieWorkflow> parseworkflowsJson(String workflows){
//        workflows="{\n" +
//                "  \"total\": 17613,\n" +
//                "  \"offset\": 51,\n" +
//                "  \"len\": 2,\n" +
//                "  \"workflows\": [\n" +
//                "    {\n" +
//                "      \"appName\": \"t2pdm_t05_bjdq_pv_log\",\n" +
//                "      \"externalId\": null,\n" +
//                "      \"conf\": null,\n" +
//                "      \"run\": 0,\n" +
//                "      \"acl\": null,\n" +
//                "      \"appPath\": null,\n" +
//                "      \"parentId\": \"0016337-170719105022944-oozie-root-C@16\",\n" +
//                "      \"lastModTime\": \"Wed, 16 Aug 2017 01:07:17 GMT\",\n" +
//                "      \"consoleUrl\": \"http://IDC011R01:11000/oozie?job=0021547-170719105023061-oozie-root-W\",\n" +
//                "      \"createdTime\": \"Wed, 16 Aug 2017 01:00:01 GMT\",\n" +
//                "      \"startTime\": \"Wed, 16 Aug 2017 01:00:01 GMT\",\n" +
//                "      \"toString\": \"Workflow id[0021547-170719105023061-oozie-root-W] status[SUCCEEDED]\",\n" +
//                "      \"id\": \"0021547-170719105023061-oozie-root-W\",\n" +
//                "      \"endTime\": \"Wed, 16 Aug 2017 01:07:17 GMT\",\n" +
//                "      \"user\": \"hejianglong\",\n" +
//                "      \"actions\": [],\n" +
//                "      \"status\": \"SUCCEEDED\",\n" +
//                "      \"group\": null\n" +
//                "    },\n" +
//                "    {\n" +
//                "      \"appName\": \"t2pdm_t01_huimaicheapp_device_gen_dt\",\n" +
//                "      \"externalId\": null,\n" +
//                "      \"conf\": null,\n" +
//                "      \"run\": 0,\n" +
//                "      \"acl\": null,\n" +
//                "      \"appPath\": null,\n" +
//                "      \"parentId\": \"0014288-170719105022944-oozie-root-C@16\",\n" +
//                "      \"lastModTime\": \"Wed, 16 Aug 2017 01:09:37 GMT\",\n" +
//                "      \"consoleUrl\": \"http://IDC011R01:11000/oozie?job=0021548-170719105023061-oozie-root-W\",\n" +
//                "      \"createdTime\": \"Wed, 16 Aug 2017 01:00:01 GMT\",\n" +
//                "      \"startTime\": \"Wed, 16 Aug 2017 01:00:01 GMT\",\n" +
//                "      \"toString\": \"Workflow id[0021548-170719105023061-oozie-root-W] status[SUCCEEDED]\",\n" +
//                "      \"id\": \"0021548-170719105023061-oozie-root-W\",\n" +
//                "      \"endTime\": \"Wed, 16 Aug 2017 01:09:37 GMT\",\n" +
//                "      \"user\": \"hejianglong\",\n" +
//                "      \"actions\": [],\n" +
//                "      \"status\": \"SUCCEEDED\",\n" +
//                "      \"group\": null\n" +
//                "    }\n" +
//                "  ]\n" +
//                "}";
        JSONObject jsonObject = JSONObject.parseObject(workflows);
        String workflowsStr = jsonObject.getString("workflows");

        List<OozieWorkflow> list = JSONObject.parseArray(workflowsStr,OozieWorkflow.class);

        return list;
    }

    /**
     * curl "http://172.20.0.67:11000/oozie/v1/jobs?len=2&offset=51&jobtype=coord"
     * 解析 coordinate
     * @param coord
     * @return
     */
    public static List<OozieCoordinator> parseCoordinatorjob(String coord){
//         coord = "{\n" +
//                "  \"total\": 576,\n" +
//                "  \"offset\": 51,\n" +
//                "  \"len\": 2,\n" +
//                "  \"coordinatorjobs\": [\n" +
//                "    {\n" +
//                "      \"mat_throttling\": 0,\n" +
//                "      \"executionPolicy\": \"FIFO\",\n" +
//                "      \"conf\": null,\n" +
//                "      \"acl\": null,\n" +
//                "      \"frequency\": \"30 2 * * *\",\n" +
//                "      \"total\": 0,\n" +
//                "      \"pauseTime\": null,\n" +
//                "      \"consoleUrl\": null,\n" +
//                "      \"lastAction\": \"Thu, 17 Aug 2017 18:30:00 GMT\",\n" +
//                "      \"coordJobName\": \"hive_audit\",\n" +
//                "      \"startTime\": \"Fri, 11 Aug 2017 08:03:00 GMT\",\n" +
//                "      \"coordExternalId\": null,\n" +
//                "      \"timeUnit\": \"CRON\",\n" +
//                "      \"group\": null,\n" +
//                "      \"coordJobId\": \"0017249-170719105023061-oozie-root-C\",\n" +
//                "      \"coordJobPath\": \"hdfs://cube1/user/hue/oozie/deployments/_liuming_-oozie-67291-1502446405.19\",\n" +
//                "      \"bundleId\": null,\n" +
//                "      \"timeZone\": \"Asia/Shanghai\",\n" +
//                "      \"concurrency\": 1,\n" +
//                "      \"timeOut\": 120,\n" +
//                "      \"nextMaterializedTime\": \"Thu, 17 Aug 2017 18:30:00 GMT\",\n" +
//                "      \"toString\": \"Coordinator application id[0017249-170719105023061-oozie-root-C] status[RUNNING]\",\n" +
//                "      \"endTime\": \"Tue, 18 Aug 2020 08:03:00 GMT\",\n" +
//                "      \"user\": \"liuming\",\n" +
//                "      \"actions\": [],\n" +
//                "      \"status\": \"RUNNING\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "      \"mat_throttling\": 0,\n" +
//                "      \"executionPolicy\": \"FIFO\",\n" +
//                "      \"conf\": null,\n" +
//                "      \"acl\": null,\n" +
//                "      \"frequency\": \"0 9 * * *\",\n" +
//                "      \"total\": 0,\n" +
//                "      \"pauseTime\": null,\n" +
//                "      \"consoleUrl\": null,\n" +
//                "      \"lastAction\": \"Tue, 15 Aug 2017 01:00:00 GMT\",\n" +
//                "      \"coordJobName\": \"t2pdm_t05_xs_leads_all\",\n" +
//                "      \"startTime\": \"Tue, 01 Aug 2017 01:00:00 GMT\",\n" +
//                "      \"coordExternalId\": null,\n" +
//                "      \"timeUnit\": \"CRON\",\n" +
//                "      \"group\": null,\n" +
//                "      \"coordJobId\": \"0017229-170719105022944-oozie-root-C\",\n" +
//                "      \"coordJobPath\": \"hdfs://cube1/user/hue/oozie/deployments/_hejianglong_-oozie-64449-1502443790.51\",\n" +
//                "      \"bundleId\": null,\n" +
//                "      \"timeZone\": \"Asia/Shanghai\",\n" +
//                "      \"concurrency\": 1,\n" +
//                "      \"timeOut\": 86400,\n" +
//                "      \"nextMaterializedTime\": \"Tue, 15 Aug 2017 01:00:00 GMT\",\n" +
//                "      \"toString\": \"Coordinator application id[0017229-170719105022944-oozie-root-C] status[RUNNING]\",\n" +
//                "      \"endTime\": \"Sun, 01 Aug 2027 01:00:00 GMT\",\n" +
//                "      \"user\": \"hejianglong\",\n" +
//                "      \"actions\": [],\n" +
//                "      \"status\": \"RUNNING\"\n" +
//                "    }\n" +
//                "  ]\n" +
//                "}";

        JSONObject jsonObject = JSONObject.parseObject(coord);
        String coordinator = jsonObject.getString("coordinatorjobs");
        List<OozieCoordinator> list = JSONObject.parseArray(coordinator,OozieCoordinator.class);

        return list;

    }


    /**
     * curl "http://172.20.0.67:11000/oozie/v1/job/0017249-170719105023061-oozie-root-C"
     * 获取coordinate 所有执行过的action
     * @param coord
     * @return
     */
    public static List<OozieCoordAction> parseCoordJson(String coord) {
        JSONObject jsonObject = JSONObject.parseObject(coord);
        String coordinator = jsonObject.getString("actions");
        List<OozieCoordAction> list = JSONObject.parseArray(coordinator,OozieCoordAction.class);

        return list;
    }




        public static void main(String[] args) {
        parseworkflowsJson("");
    }
}
