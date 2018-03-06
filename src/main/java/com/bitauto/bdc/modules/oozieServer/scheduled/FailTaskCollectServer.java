package com.bitauto.bdc.modules.oozieServer.scheduled;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bitauto.bdc.common.utils.DateUtils;
import com.bitauto.bdc.common.utils.HttpClientUtils;
import com.bitauto.bdc.modules.oozieServer.entity.Mail;
import com.bitauto.bdc.modules.oozieServer.entity.WorkflowsVo;
import com.bitauto.bdc.modules.oozieServer.util.SendWXUtil;
import com.bitauto.bdc.modules.oozieServer.util.UserGroupUtil;
import com.bitauto.bdc.modules.oozieServer.util.WorkflowsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class FailTaskCollectServer {

    private final  Logger logger = LoggerFactory.getLogger(getClass());
    private  static boolean  isNext = true;

    private  static  List<WorkflowsVo> failTaskList = new ArrayList<>();
    private  static  int step = 1000;
    //public static String  oozieUrl = "http://192.168.15.46:11000";
    private String oozieUrl="http://172.20.0.67:11000";


    public void sendFailTaxtToWX() {
   // public static void main(String [] args ){
        logger.info("sendFailTaxtToWX定时任务启动");
        int offset = 0;
        int  len = 0;
        String result="";
        FailTaskCollectServer ftask = new FailTaskCollectServer();

        while(isNext){
            offset = len + 1;
            len = len + step;
            result = ftask.getRequest(offset,len);
            if("-1".equals(result)){
                isNext = false;
            }else{
                ftask.buildWorkflowsList(result);
            }
        }

        if("-1".equals(result)){//发送调用 调口失败的消息
            List<Mail> mails=new ArrayList<Mail>();
            Mail m=new Mail();
            m.setDate("日期："+DateUtils.format(new Date(),"yyyy-MM-dd")+"\n");
            m.setIndex("调用OOZIE接口失败!");
            mails.add(m);
            try {
                //3379tt59:测试
                SendWXUtil.send("74nxnq7f",UserGroupUtil.getGroupNumber(),mails);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {//发送失任务的消息
            if(failTaskList!=null && failTaskList.size()>0){
                List<String> list = new ArrayList<String>();
                for(WorkflowsVo wfv : failTaskList){
                    list.add(wfv.getUser()+"："+wfv.getAppname());
                }
                List<Mail> mails=new ArrayList<Mail>();
                Mail m=new Mail();
                m.setDate("日期："+DateUtils.format(new Date(),"yyyy-MM-dd")+"\n");
                m.setIndex("失败任务明细:\n");
                m.setList(list);
                mails.add(m);
                try {
                    //d302w7tt:测试
                    SendWXUtil.send("l3t1sfrf", UserGroupUtil.getGroupNumber(),mails);
                } catch (Exception e) {
                    e.printStackTrace();
                    e.printStackTrace();
                }
            }
        }
        //重新初始化
        isNext = true;
        offset = 0;
        len = 0;

        logger.info("sendFailTaxtToWX定时任务完成");
    }


    /**
     * 请求oozie的接口
     * @param offset
     * @param len
     * @return
     */
    public String getRequest( int offset,int len){
        String url=oozieUrl+"/oozie/v1/jobs?len="+len+"&offset="+offset+
                "&jobtype=wf&filter="+java.net.URLEncoder.encode("status=FAILED;status=KILLED");
        try {
            return HttpClientUtils.get(url);
        } catch (Exception e) {
            e.printStackTrace();
            return  "-1";
        }
    }


    /**
     * 构建失败Workflows信息
     * @param workflowsRes
     * @return
     */
    private List<WorkflowsVo> buildWorkflowsList(String workflowsRes){
        JSONObject jsonObject = JSONObject.parseObject(workflowsRes);
        JSONArray array = jsonObject.getJSONArray("workflows");
        Calendar nowTime = Calendar.getInstance();
        List<String>  userList =  UserGroupUtil.getGroupUserName();
        List<String>  workflowsNameList = WorkflowsUtil.getNotworkflowsName();

        for (int i = 0; i < array.size(); i++) {
            WorkflowsVo entity =  JSONObject.parseObject(array.get(i).toString(),WorkflowsVo.class);
             //if (DateUtils.parseGMTTime(entity.getLastModTime()).equals(DateUtils.format(new Date()))) {
             if (DateUtils.parseGMTTime(entity.getLastModTime()).equals("2018-02-24")) {
                 //用户的过滤
                if(userList.contains(entity.getUser()) && !workflowsNameList.contains(entity.getAppname())){
                     failTaskList.add(entity);
                 }
             }else{
                  isNext=false;
             }
        }
        return failTaskList;
    }

}
