package com.bitauto.bdc.modules.oozieServer.controller;


import com.bitauto.bdc.common.utils.DateUtils;
import com.bitauto.bdc.common.utils.R;
import com.bitauto.bdc.modules.oozieServer.entity.OozieCollectVO;
import com.bitauto.bdc.modules.oozieServer.entity.WfJobs;
import com.bitauto.bdc.modules.oozieServer.service.CoordJobsService;
import com.bitauto.bdc.modules.oozieServer.service.WfJobsService;
import com.bitauto.bdc.modules.oozieServer.util.UserGroupUtil;
import com.bitauto.bdc.modules.yarnApplication.monitor.service.YarnApplicationService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.*;

@RestController
@RequestMapping("/oozieServer/oozieCollect")
public class OozieCollectController {

    public static Logger logger=  LoggerFactory.getLogger("OozieCollectController");

    @Autowired
    private CoordJobsService coordJobsService;

    @Autowired
    private WfJobsService wfJobsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){

        String username = String.valueOf(params.get("username"));
        String startTime = String.valueOf(params.get("startTime"));


        List<String> list = new ArrayList<String>();
        Map<String,Object>  map =new HashMap<String,Object>();


        if(StringUtils.isEmpty(startTime) || "null".equals(startTime)){
            startTime = DateUtils.format(new Date(),"yyyy-MM-dd");
        }


        map.put("startTime",startTime);

        if("数仓组".equals(username)){
            map.put("username", UserGroupUtil.getGroupUserName());
        }else  if(!StringUtils.isEmpty(username) && !"null".equals(username)){
            //map.put("username",username);
            list.add(username);
            map.put("username",list);
        }


        OozieCollectVO vo = new OozieCollectVO();

        vo = coordJobsService.queryStatusTotal(map);


        List<WfJobs> wfjobsList = wfJobsService.queryListcount(map);
        vo = getDateTimeCount(vo,wfjobsList,0);
        wfjobsList = wfJobsService.queryFinishcount(map);
        vo = getDateTimeCount(vo,wfjobsList,1);

        wfjobsList = wfJobsService.queryExecutionTimecount(map);

        vo = getyExecutionTimeCount(vo,wfjobsList);

        return R.ok().put("page", vo );
    }


    private  OozieCollectVO getDateTimeCount( OozieCollectVO vo, List<WfJobs> wfjobsList,int type){
        Map<String,Integer> vmap = new HashMap<String,Integer>();
        StringBuffer buffer = new StringBuffer();
        StringBuffer keyBuffer = new StringBuffer();

        if(wfjobsList != null){
            for(WfJobs wj : wfjobsList){
                vmap.put(wj.getxName().substring(11),wj.getSum());
            }
        }

        for (int i=0 ;i<=24;i++){
            String n =i+"";
            if(i<10){
                n="0"+i;
            }
            if(vmap.containsKey(n)){
                buffer.append(vmap.get(n));
            }else{
                buffer.append("0");
            }
            keyBuffer.append(n);
            if(i!=24){
                buffer.append(",");
                keyBuffer.append(",");
            }

        }

        if(0==type){
            vo.setChardata(buffer.toString());
            vo.setCharkey(keyBuffer.toString());
        }else {
            vo.setCharFinishdata(buffer.toString());
            vo.setCharFinishkey(keyBuffer.toString());
        }

        return  vo;
    }


    private  OozieCollectVO getCrossDate( OozieCollectVO vo, List<WfJobs> wfjobsList,int type){
        Map<String,Map<String,Integer>> map = new HashMap<String,Map<String,Integer>>();
        Map<String,Integer> vmap = new HashMap<String,Integer>();
        StringBuffer buffer = new StringBuffer();
        StringBuffer keyBuffer = new StringBuffer();
        StringBuffer legendBuffer = new StringBuffer();

        if(wfjobsList != null){
            for(WfJobs wj : wfjobsList){
                if(map.containsKey(wj.getUserName())){
                    vmap = map.get(wj.getUserName());
                }else {
                    vmap = new HashMap<String,Integer>();
                    vmap.put(wj.getxName().substring(11),wj.getSum());
                    map.put(wj.getUserName(),vmap);
                }

//                legendBuffer.append(wj.getUserName()).append(",");
            }
        }

        for (int i=0 ;i<=24;i++){
            String n =i+"";
            if(i<10){
                n="0"+i;
            }
            if(vmap.containsKey(n)){
                buffer.append(vmap.get(n));
            }else{
                buffer.append("0");
            }
            keyBuffer.append(n);
            if(i!=24){
                buffer.append(",");
                keyBuffer.append(",");
            }

        }

        if(0==type){
            vo.setChardata(buffer.toString());
            vo.setCharkey(keyBuffer.toString());
        }else if(1==type){
            vo.setCharFinishdata(buffer.toString());
            vo.setCharFinishkey(keyBuffer.toString());
        }else if(2==type){
            vo.setUsernameStarTimetkey(buffer.toString());
            vo.setUsernameStarTimetdata(keyBuffer.toString());
            vo.setLegendData(legendBuffer.length()>0?legendBuffer.toString().substring(0,legendBuffer.toString().length()-1):legendBuffer.toString());
        }

        return  vo;
    }



    private  OozieCollectVO getyExecutionTimeCount( OozieCollectVO vo, List<WfJobs> wfjobsList){
        Map<String,Integer> vmap = new LinkedHashMap<String,Integer>();
        StringBuffer buffer = new StringBuffer();
        StringBuffer keyBuffer = new StringBuffer();

        vmap.put("0-5分",0);
        vmap.put("6-10分",0);
        vmap.put("11-15分",0);
        vmap.put("16-20分",0);
        vmap.put("21-25分",0);
        vmap.put("26-30分",0);
        vmap.put("31-35分",0);
        vmap.put("36-40分",0);
        vmap.put("41-45分",0);
        vmap.put("46-50分",0);
        vmap.put("51-55分",0);
        vmap.put("55-60分",0);
        vmap.put("1小时以上",0);


        if(wfjobsList != null){
            for(WfJobs wj : wfjobsList){
               if(wj.getTimes() == null){
                   continue;
               }
                if(wj.getTimes() == null || (wj.getTimes()<=5 && wj.getTimes()>=0)){
                    vmap.put("0-5分", vmap.get("0-5分")+wj.getSum());
                }else if(wj.getTimes() >= 6 && wj.getTimes() <= 10){
                    vmap.put("6-10分", vmap.get("6-10分")+wj.getSum());
                }else if(wj.getTimes() >=11 && wj.getTimes() <= 15){
                    vmap.put("11-15分", vmap.get("11-15分")+wj.getSum());
                }else if(wj.getTimes() >= 16 && wj.getTimes() <= 20){
                    vmap.put("16-20分", vmap.get("16-20分")+wj.getSum());
                }else if(wj.getTimes() >= 21 && wj.getTimes() <= 25){
                    vmap.put("21-25分", vmap.get("21-25分")+wj.getSum());
                }else if(wj.getTimes() >= 26 && wj.getTimes() <= 30){
                    vmap.put("26-30分", vmap.get("26-30分")+wj.getSum());
                }else if(wj.getTimes() >= 31 && wj.getTimes() <= 35){
                    vmap.put("31-35分", vmap.get("31-35分")+wj.getSum());
                }else if(wj.getTimes() >= 36 && wj.getTimes() <= 40){
                    vmap.put("36-40分", vmap.get("36-40分")+wj.getSum());
                }else if(wj.getTimes() >= 41 && wj.getTimes() <= 45){
                    vmap.put("36-40分", vmap.get("36-40分")+wj.getSum());
                }else if(wj.getTimes() >= 46 && wj.getTimes() <= 50){
                    vmap.put("46-50分", vmap.get("46-50分")+wj.getSum());
                }else if(wj.getTimes() >= 51 && wj.getTimes() <= 55){
                    vmap.put("51-55分", vmap.get("51-55分")+wj.getSum());
                }else if(wj.getTimes() >= 56 && wj.getTimes() <= 60){
                    vmap.put("56-60分",wj.getSum()+wj.getSum()+wj.getSum());
                }else{
                    vmap.put("1小时以上",wj.getSum()+wj.getSum()+wj.getSum());
                }
            }
        }

        for (Map.Entry<String, Integer> m : vmap.entrySet()) {
            buffer.append(m.getValue()).append(",");
            keyBuffer.append(m.getKey()).append(",");
        }
        vo.setCharExecutionTimekey(keyBuffer.toString().substring(0,keyBuffer.toString().length()-1));
        vo.setCharExecutionTimedata(buffer.toString().substring(0,buffer.toString().length()-1));
        return  vo;
    }



}
