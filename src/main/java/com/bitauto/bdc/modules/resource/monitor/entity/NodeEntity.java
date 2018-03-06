package com.bitauto.bdc.modules.resource.monitor.entity;


import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;


/**
 * 计算节点使用情况
 * 
 * @author liuming
 * @email liuming1@yiche.com
 * @date 2017-10-23 10:22:33
 */
public class NodeEntity implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    //
    private Integer id;
    
    // 所在机架位置
    private String rack;
    
    // 状态
    private String state;
    
    // 节点id
    private String nodeid;
    
    // 主机名
    private String nodehostname;
    
    // http地址
    private String nodehttpaddress;
    
    // 健康报告
    private String healthreport;
    
    // 最后一次健康时间
    private String lasthealthupdate;
    
    // 已使用内存
    private String usedmemorymb;
    
    // 剩余内存
    private String availmemorymb;
    
    // 已使用cpu核数
    private String usedvirtualcores;
    
    // 剩余cpu核数
    private String availablevirtualcores;
    
    // 正在运行容器数目
    private Integer numcontainers;
    
    // 数据插入时间
    private Date inserttime;
    
    /**
     * 设置：
     */
    @JSONField(name = "xxx")
    public void setId(Integer id)
    {
        this.id = id;
    }
    
    /**
     * 获取：
     */
    public Integer getId()
    {
        return id;
    }
    
    /**
     * 设置：所在机架位置
     */
    public void setRack(String rack)
    {
        this.rack = rack;
    }
    
    /**
     * 获取：所在机架位置
     */
    public String getRack()
    {
        return rack;
    }
    
    /**
     * 设置：状态
     */
    public void setState(String state)
    {
        this.state = state;
    }
    
    /**
     * 获取：状态
     */
    public String getState()
    {
        return state;
    }
    
    /**
     * 设置：节点id
     */
    @JSONField(name = "id")
    public void setNodeid(String nodeid)
    {
        this.nodeid = nodeid;
    }
    
    /**
     * 获取：节点id
     */
    public String getNodeid()
    {
        return nodeid;
    }
    
    /**
     * 设置：主机名
     */
    public void setNodehostname(String nodehostname)
    {
        this.nodehostname = nodehostname;
    }
    
    /**
     * 获取：主机名
     */
    public String getNodehostname()
    {
        return nodehostname;
    }
    
    /**
     * 设置：http地址
     */
    public void setNodehttpaddress(String nodehttpaddress)
    {
        this.nodehttpaddress = nodehttpaddress;
    }
    
    /**
     * 获取：http地址
     */
    public String getNodehttpaddress()
    {
        return nodehttpaddress;
    }
    
    /**
     * 设置：健康报告
     */
    public void setHealthreport(String healthreport)
    {
        this.healthreport = healthreport;
    }
    
    /**
     * 获取：健康报告
     */
    public String getHealthreport()
    {
        return healthreport;
    }
    
    /**
     * 设置：最后一次健康时间
     */
    public void setLasthealthupdate(String lasthealthupdate)
    {
        this.lasthealthupdate = lasthealthupdate;
    }
    
    /**
     * 获取：最后一次健康时间
     */
    public String getLasthealthupdate()
    {
        return lasthealthupdate;
    }
    
    /**
     * 设置：已使用内存
     */
    public void setUsedmemorymb(String usedmemorymb)
    {
        this.usedmemorymb = usedmemorymb;
    }
    
    /**
     * 获取：已使用内存
     */
    public String getUsedmemorymb()
    {
        return usedmemorymb;
    }
    
    /**
     * 设置：剩余内存
     */
    public void setAvailmemorymb(String availmemorymb)
    {
        this.availmemorymb = availmemorymb;
    }
    
    /**
     * 获取：剩余内存
     */
    public String getAvailmemorymb()
    {
        return availmemorymb;
    }
    
    /**
     * 设置：已使用cpu核数
     */
    public void setUsedvirtualcores(String usedvirtualcores)
    {
        this.usedvirtualcores = usedvirtualcores;
    }
    
    /**
     * 获取：已使用cpu核数
     */
    public String getUsedvirtualcores()
    {
        return usedvirtualcores;
    }
    
    /**
     * 设置：剩余cpu核数
     */
    public void setAvailablevirtualcores(String availablevirtualcores)
    {
        this.availablevirtualcores = availablevirtualcores;
    }
    
    /**
     * 获取：剩余cpu核数
     */
    public String getAvailablevirtualcores()
    {
        return availablevirtualcores;
    }
    
    /**
     * 设置：正在运行容器数目
     */
    public void setNumcontainers(Integer numcontainers)
    {
        this.numcontainers = numcontainers;
    }
    
    /**
     * 获取：正在运行容器数目
     */
    public Integer getNumcontainers()
    {
        return numcontainers;
    }
    
    /**
     * 设置：数据插入时间
     */
    public void setInserttime(Date inserttime)
    {
        this.inserttime = inserttime;
    }
    
    /**
     * 获取：数据插入时间
     */
    public Date getInserttime()
    {
        return inserttime;
    }
    
    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("NodeEntity [id=");
        builder.append(id);
        builder.append(", rack=");
        builder.append(rack);
        builder.append(", state=");
        builder.append(state);
        builder.append(", nodeid=");
        builder.append(nodeid);
        builder.append(", nodehostname=");
        builder.append(nodehostname);
        builder.append(", nodehttpaddress=");
        builder.append(nodehttpaddress);
        builder.append(", healthreport=");
        builder.append(healthreport);
        builder.append(", lasthealthupdate=");
        builder.append(lasthealthupdate);
        builder.append(", usedmemorymb=");
        builder.append(usedmemorymb);
        builder.append(", availmemorymb=");
        builder.append(availmemorymb);
        builder.append(", usedvirtualcores=");
        builder.append(usedvirtualcores);
        builder.append(", availablevirtualcores=");
        builder.append(availablevirtualcores);
        builder.append(", numcontainers=");
        builder.append(numcontainers);
        builder.append(", inserttime=");
        builder.append(inserttime);
        builder.append("]");
        return builder.toString();
    }
}
