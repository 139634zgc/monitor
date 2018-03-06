package com.bitauto.bdc.modules.oozieServer.util;

import com.bitauto.bdc.modules.oozieServer.entity.Mail;
import com.bitauto.bdc.modules.oozieServer.entity.SendMessageVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SendWXUtil {

    //public  static  String remoteUrl = "http://192.168.15.46:9820/receiveandsendmail/sendmail";
    public  static   String remoteUrl = "http://yu.yiche.com/receiveandsendmail/sendmail";


    public static  void send(String groupid,String weixinReceiver,List<Mail> mails) throws Exception {

        SendMessageVo vo = new SendMessageVo();
        List<String> to=new ArrayList<String>();
//        to.add("liyang4@yiche.com");
//        vo.setPrimaryTo(to);to
        vo.setGroupId(groupid);
        vo.setWeixinReceiver(weixinReceiver);

        Map<String,Object> maptest=new HashMap<String,Object>();
        maptest.put("mails", mails);
        vo.setData(maptest);
        ObjectMapper mapper = new ObjectMapper();
        String json=mapper.writeValueAsString(vo);
        System.out.println("json: "+json);
        SendMessageVo entity=mapper.readValue(json, SendMessageVo.class);
        System.out.println(entity);
        String result = httpPostWithJSON(remoteUrl, json);
        System.out.println(result);
    }


    public static String httpPostWithJSON(String url, String json)
            throws Exception {

        HttpPost httpPost = new HttpPost(url);
        HttpClient client = new DefaultHttpClient();
        String respContent = null;

        // json方式
        StringEntity entity = new StringEntity(json.toString(), "utf-8");// 解决中文乱码问题
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
      //  System.out.println("POST的json数据格式如下: \n" + json.toString());

        HttpResponse resp = client.execute(httpPost);
        if (resp.getStatusLine().getStatusCode() == 200) {
            HttpEntity he = resp.getEntity();
            respContent = EntityUtils.toString(he, "UTF-8");
           // System.out.println("发送数据成功====================");
        }
        return respContent;
    }
}
