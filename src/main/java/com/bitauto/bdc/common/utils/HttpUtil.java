/*
 * 文件名：HttpUtil.java
 * 版权：Copyright by www.yiche.com
 * 描述：
 * 修改人：liuming1
 * 修改时间：2017年8月3日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.bitauto.bdc.common.utils;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;


public class HttpUtil
{
    /**
     * 日志打印对象定义
     */
    private static final Log LOG = LogFactory.getLog(HttpUtil.class);
    
    /**
     * Description: http请求
     * 
     * @param url
     *            需要查询的url地址
     * @return
     * @see
     */
    public static String requestHttp(String url)
    {
        StringBuffer sb = new StringBuffer("");
        try
        {
            // 定义HttpClient
            @SuppressWarnings("deprecation")
            HttpClient client = new DefaultHttpClient();
            
            // 实例化HTTP方法
            HttpGet request = new HttpGet();
            
            request.setURI(new URI(url));
            HttpResponse response = client.execute(request);
            
            try (BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity()
                .getContent())))
            {
                String line = "";
                String NL = System.getProperty("line.separator");
                while ((line = in.readLine()) != null)
                {
                    sb.append(line + NL);
                }
            }
            catch (Exception e)
            {
                LOG.error("read content from server exception[{}]", e);
            }
        }
        catch (Exception e)
        {
            LOG.error("http client connection exception[{}]", e);
        }
        return sb.toString();
    }
}
