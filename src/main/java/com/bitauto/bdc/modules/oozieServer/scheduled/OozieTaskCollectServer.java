package com.bitauto.bdc.modules.oozieServer.scheduled;

import com.bitauto.bdc.common.utils.DateUtils;
import com.bitauto.bdc.modules.oozieServer.entity.Mail;
import com.bitauto.bdc.modules.oozieServer.entity.OozieCollectVO;
import com.bitauto.bdc.modules.oozieServer.service.CoordJobsService;
import com.bitauto.bdc.modules.oozieServer.util.CoordUtil;
import com.bitauto.bdc.modules.oozieServer.util.SendWXUtil;
import com.bitauto.bdc.modules.oozieServer.util.UserGroupUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.*;

@Service
public class OozieTaskCollectServer {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CoordJobsService coordJobsService;


    public void sendTastToWX() {
        logger.info("sendTastToWX定时任务启动");

        Map<String,Object> map =new HashMap<String,Object>();
        map.put("startTime", DateUtils.format(new Date(),"yyyy-MM-dd"));
        map.put("username", UserGroupUtil.getGroupUserName());
        map.put("notcoord", CoordUtil.getNotInCoord());
       // System.out.println("startTime:"+( DateUtils.format(new Date(),"yyyy-MM-dd"))+"、username:"+UserGroupUtil.getGroupUserName());
        OozieCollectVO vo = coordJobsService.queryStatusTotal(map);
        int waitingcount = coordJobsService.queryWaitingcount(map);
        vo.setWaitingcount(waitingcount);

        String result = "100";
        if(vo.getSucceededcount() != vo.getJobcount() ){
            NumberFormat numberFormat = NumberFormat.getInstance();
            numberFormat.setMaximumFractionDigits(0);
            result = numberFormat.format((float) vo.getSucceededcount() / (float) vo.getJobcount() * 100);
        }
       // System.out.println("总任务量:"+vo.getJobcount()+"、成功:"+vo.getSucceededcount()+"、失败:"+vo.getErrorcount()+"、运行中:"+vo.getRuningcount()+"、待运行:"+vo.getWaitingcount()+"  result:"+result);
        List<Mail> mails=new ArrayList<Mail>();
        Mail m=new Mail();
        m.setIndex("日期："+DateUtils.format(new Date(),"yyyy-MM-dd")+"\n");
        m.setDate("当前整体进度："+result+"%\n");
        m.setSql("当前运行明细：总任务量 "+vo.getJobcount()+"、成功"+vo.getSucceededcount()+"、失败 "+vo.getErrorcount()+"、运行中 "+vo.getRuningcount()+"、待运行 "+vo.getWaitingcount()+"\n");
        mails.add(m);
        // System.out.println("result====================="+UserGroupUtil.getGroupNumber());
        try {
            SendWXUtil.send("24",UserGroupUtil.getGroupNumber(),mails);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
