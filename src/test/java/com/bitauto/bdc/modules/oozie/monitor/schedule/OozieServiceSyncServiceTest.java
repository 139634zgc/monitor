package com.bitauto.bdc.modules.oozie.monitor.schedule;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bitauto.bdc.RenrenApplication;
import com.bitauto.bdc.modules.oozie.monitor.entity.CoorDatasetEntity;
import org.json.XML;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by michealzhang on 2017/11/15.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RenrenApplication.class)
@ActiveProfiles("test")
public class OozieServiceSyncServiceTest {

    @Test
    public void getcoordinators() throws Exception {
        oozieServiceSyncService.getcoordinators();
    }

    @Autowired
    private  OozieServiceSyncService oozieServiceSyncService;

    @Test
    public void getWorkflows() throws Exception {
        oozieServiceSyncService.getWorkflows();
    }

    @Test
    public void getDataSet() throws Exception{
        String json = "{\"coordinator-app\":{\"xmlns\":\"uri:oozie:coordinator:0.2\",\"controls\":{\"execution\":\"FIFO\",\"timeout\":-1},\"timezone\":\"Asia/Shanghai\",\"name\":\"yiche_easypass_aggr_leads_app_channel_da\",\"start\":\"${start_date}\",\"action\":{\"workflow\":{\"configuration\":{\"property\":[{\"name\":\"dependence\",\"value\":\"${coord:dataIn('dependence')}\"},{\"name\":\"pt\",\"value\":\"${coord:formatTime(coord:dateOffset(coord:nominalTime(), -1, &#39;DAY&#39;),&#39;yyyy-MM-dd&#39;)}\"},{\"name\":\"oozie.use.system.libpath\",\"value\":true},{\"name\":\"start_date\",\"value\":\"${start_date}\"},{\"name\":\"end_date\",\"value\":\"${end_date}\"}]},\"app-path\":\"${wf_application_path}\"}},\"end\":\"${end_date}\",\"datasets\":{\"dataset\":{\"uri-template\":\"${nameNode}/bitauto/sign/yiche_easypass/attr_leads_all_day/${YEAR}-${MONTH}-${DAY}/_SUCCESS\",\"timezone\":\"Asia/Shanghai\",\"name\":\"dependence\",\"done-flag\":\"\",\"initial-instance\":\"${start_date}\",\"frequency\":\"${coord:days(1)}\"}},\"input-events\":{\"data-in\":{\"instance\":\"${coord:current(-1)}\",\"name\":\"dependence\",\"dataset\":\"dependence\"}},\"frequency\":\"0 6 * * *\"}}";
        String json1 = "{\"coordinator-app\":{\"xmlns\":\"uri:oozie:coordinator:0.2\",\"controls\":{\"execution\":\"FIFO\",\"timeout\":-1},\"timezone\":\"Asia/Shanghai\",\"name\":\"yiche_pcwap_fact_traffic_day\",\"start\":\"${start_date}\",\"action\":{\"workflow\":{\"configuration\":{\"property\":[{\"name\":\"ods_depend\",\"value\":\"${coord:dataIn('ods_depend')}\"},{\"name\":\"dim_depend\",\"value\":\"${coord:dataIn('dim_depend')}\"},{\"name\":\"pt\",\"value\":\"${coord:formatTime(coord:dateOffset(coord:nominalTime(), -1, &#39;DAY&#39;),&#39;yyyy-MM-dd&#39;)}\"},{\"name\":\"oozie.use.system.libpath\",\"value\":true},{\"name\":\"start_date\",\"value\":\"${start_date}\"},{\"name\":\"end_date\",\"value\":\"${end_date}\"}]},\"app-path\":\"${wf_application_path}\"}},\"end\":\"${end_date}\",\"datasets\":{\"dataset\":[{\"uri-template\":\"${nameNode}/bitauto/sign/bitauto_ods/ods_detail_lzo/${YEAR}-${MONTH}-${DAY}/_SUCCESS\",\"timezone\":\"Asia/Shanghai\",\"name\":\"ods_depend\",\"done-flag\":\"\",\"initial-instance\":\"${start_date}\",\"frequency\":\"${coord:days(1)}\"},{\"uri-template\":\"${nameNode}/bitauto/sign/yiche_pcwap/dim_channel_day/${YEAR}-${MONTH}-${DAY}/_SUCCESS\",\"timezone\":\"Asia/Shanghai\",\"name\":\"dim_depend\",\"done-flag\":\"\",\"initial-instance\":\"${start_date}\",\"frequency\":\"${coord:days(1)}\"}]},\"input-events\":{\"data-in\":[{\"instance\":\"${coord:current(0)}\",\"name\":\"ods_depend\",\"dataset\":\"ods_depend\"},{\"instance\":\"${coord:current(-1)}\",\"name\":\"dim_depend\",\"dataset\":\"dim_depend\"}]},\"frequency\":\"0 3 * * *\"}}";

        JSONObject jsonObject = JSONObject.parseObject(json);
        JSONObject datasets = jsonObject.getJSONObject("coordinator-app").getJSONObject("datasets");
        Object dataset = datasets.get("dataset");

        if(dataset instanceof JSONObject){
            String uri_template = ((JSONObject) dataset).getString("uri-template");
            System.out.println(uri_template);
        }else if(dataset instanceof JSONArray){
            JSONArray array = JSONObject.parseArray(JSON.toJSONString(dataset));

            for (int i = 0; i <array.size() ; i++) {
                JSONObject jsonObject1 = array.getJSONObject(i);

                if(jsonObject1.containsKey("uri-template")){ //如果包含fs key 则获取
                    String uri_template = jsonObject1.getString("uri-template");
                    System.out.println(uri_template);

                }

            }
        }



        if(jsonObject.containsKey("datasets")){ //如果包含fs key 则获取
            JSONObject fsObject = jsonObject.getJSONObject("fs");
            JSONArray array = jsonObject.getJSONArray("dataset");
            for (int i = 0; i < array.size(); i++) {
                String uri_template = JSONObject.parseObject(array.get(i).toString()).get("uri-template").toString();
                System.out.println(uri_template);
            }
        }
    }

    @Test
    public void getDoneFlag() throws Exception{
        String json = "{\"workflow-app\":{\"xmlns\":\"uri:oozie:workflow:0.5\",\"name\":\"app_dealer_signnum_brand\",\"start\":{\"to\":\"hive2-82ce\"},\"action\":[{\"cred\":\"hive2\",\"name\":\"hive2-82ce\",\"hive2\":{\"xmlns\":\"uri:oozie:hive2-action:0.1\",\"jdbc-url\":\"jdbc:hive2://IDC011R01:10000/default\",\"param\":\"date=${date}\",\"job-tracker\":\"${jobTracker}\",\"name-node\":\"${nameNode}\",\"script\":\"/user/renshuaitao/app_dealer_signnum_brand_day/dealer_signnum_brand_day.sql\"},\"ok\":{\"to\":\"fs-28df\"},\"error\":{\"to\":\"Kill\"}},{\"name\":\"fs-28df\",\"ok\":{\"to\":\"End\"},\"error\":{\"to\":\"Kill\"},\"fs\":{\"touchz\":{\"path\":\"${nameNode}/bitauto/sign/yiche_easypass/app_dealer_signnum_brand_day/${date}/_SUCCESS\"}}}],\"end\":{\"name\":\"End\"},\"kill\":{\"name\":\"Kill\",\"message\":\"Action failed, error message[${wf:errorMessage(wf:lastErrorNode())}]\"}}}";
        JSONObject jsonObject = JSONObject.parseObject(json);
        JSONArray jsonArray = jsonObject.getJSONObject("workflow-app").getJSONArray("action");

        for (int i = 0; i <jsonArray.size() ; i++) {
            JSONObject jsonObject1 = jsonArray.getJSONObject(i);

            if(jsonObject1.containsKey("fs")){ //如果包含fs key 则获取
                JSONObject fsObject = jsonObject1.getJSONObject("fs");
                String path = fsObject.getJSONObject("touchz").getString("path");
                System.out.println(path);
            }

        }


    }

    @Test
    public void xmlToJson() throws Exception{
        String res = "<coordinator-app name=\"yiche_pcwap_fact_traffic_day\"\n" +
                "  frequency=\"0 3 * * *\"\n" +
                "  start=\"${start_date}\" end=\"${end_date}\" timezone=\"Asia/Shanghai\"\n" +
                "  xmlns=\"uri:oozie:coordinator:0.2\"\n" +
                "  >\n" +
                "  <controls>\n" +
                "    <timeout>-1</timeout>\n" +
                "    <execution>FIFO</execution>\n" +
                "  </controls>\n" +
                "  <datasets>\n" +
                "    <dataset name=\"ods_depend\" frequency=\"${coord:days(1)}\"\n" +
                "             initial-instance=\"${start_date}\" timezone=\"Asia/Shanghai\">\n" +
                "      <uri-template>${nameNode}/bitauto/sign/bitauto_ods/ods_detail_lzo/${YEAR}-${MONTH}-${DAY}/_SUCCESS</uri-template>\n" +
                "      <done-flag></done-flag>\n" +
                "    </dataset>\n" +
                "    <dataset name=\"dim_depend\" frequency=\"${coord:days(1)}\"\n" +
                "             initial-instance=\"${start_date}\" timezone=\"Asia/Shanghai\">\n" +
                "      <uri-template>${nameNode}/bitauto/sign/yiche_pcwap/dim_channel_day/${YEAR}-${MONTH}-${DAY}/_SUCCESS</uri-template>\n" +
                "      <done-flag></done-flag>\n" +
                "    </dataset>\n" +
                "  </datasets>\n" +
                "  <input-events>\n" +
                "    <data-in name=\"ods_depend\" dataset=\"ods_depend\">\n" +
                "      <instance>${coord:current(0)}</instance>\n" +
                "    </data-in>\n" +
                "    <data-in name=\"dim_depend\" dataset=\"dim_depend\">\n" +
                "      <instance>${coord:current(-1)}</instance>\n" +
                "    </data-in>\n" +
                "  </input-events>\n" +
                "  <action>\n" +
                "    <workflow>\n" +
                "      <app-path>${wf_application_path}</app-path>\n" +
                "      <configuration>\n" +
                "          <property>\n" +
                "            <name>ods_depend</name>\n" +
                "            <value>${coord:dataIn('ods_depend')}</value>\n" +
                "          </property>\n" +
                "          <property>\n" +
                "            <name>dim_depend</name>\n" +
                "            <value>${coord:dataIn('dim_depend')}</value>\n" +
                "          </property>\n" +
                "        <property>\n" +
                "            <name>pt</name>\n" +
                "            <value> ${coord:formatTime(coord:dateOffset(coord:nominalTime(), -1, &#39;DAY&#39;),&#39;yyyy-MM-dd&#39;)}</value>\n" +
                "        </property>\n" +
                "        <property>\n" +
                "            <name>oozie.use.system.libpath</name>\n" +
                "            <value>True</value>\n" +
                "        </property>\n" +
                "        <property>\n" +
                "            <name>start_date</name>\n" +
                "            <value>${start_date}</value>\n" +
                "        </property>\n" +
                "        <property>\n" +
                "            <name>end_date</name>\n" +
                "            <value>${end_date}</value>\n" +
                "        </property>\n" +
                "      </configuration>\n" +
                "   </workflow>\n" +
                "  </action>\n" +
                "</coordinator-app>";
        String json = XML.toJSONObject(res).toString();

        System.out.println(json);

    }



    @Test
    public void spiltDoneFlag(){
        String str = "${nameNode}/bitauto/sign/yiche_easypass/attr_leads_all_day/${YEAR}-${MONTH}-${DAY}/_SUCCESS";
        String res = str.substring(0,str.indexOf("/$"));
        System.out.println(res);
    }



}