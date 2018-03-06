package com.bitauto.bdc.modules.resource.monitor.entity;


import java.io.Serializable;
import java.util.Date;


/**
 * 资源队列常量
 * 
 * @author liuming
 * @email liuming1@yiche.com
 * @date 2017-10-23 10:22:32
 */
public class QueueEntity implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    //
    private Integer id;
    
    // 队列id
    private Integer queueid;
    
    // 队列名称
    private String queuename;
    
    // 父队列id
    private Integer parentqueueid;
    
    // 队列路径
    private String queuepath;
    
    // 插入时间
    private Date inserttime;
    
    // 更新时间
    private Date updatetime;
    
    // 更新描述
    private String desc;
    
    //是否业节点
    private boolean leaf;
    
    /**
     * 设置：
     */
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
     * 设置：队列id
     */
    public void setQueueid(Integer queueid)
    {
        this.queueid = queueid;
    }
    
    /**
     * 获取：队列id
     */
    public Integer getQueueid()
    {
        return queueid;
    }
    
    /**
     * 设置：队列名称
     */
    public void setQueuename(String queuename)
    {
        this.queuename = queuename;
    }
    
    /**
     * 获取：队列名称
     */
    public String getQueuename()
    {
        return queuename;
    }
    
    /**
     * 设置：父队列id
     */
    public void setParentqueueid(Integer parentqueueid)
    {
        this.parentqueueid = parentqueueid;
    }
    
    /**
     * 获取：父队列id
     */
    public Integer getParentqueueid()
    {
        return parentqueueid;
    }
    
    /**
     * 设置：队列路径
     */
    public void setQueuepath(String queuepath)
    {
        this.queuepath = queuepath;
    }
    
    /**
     * 获取：队列路径
     */
    public String getQueuepath()
    {
        return queuepath;
    }
    
    /**
     * 设置：插入时间
     */
    public void setInserttime(Date inserttime)
    {
        this.inserttime = inserttime;
    }
    
    /**
     * 获取：插入时间
     */
    public Date getInserttime()
    {
        return inserttime;
    }
    
    /**
     * 设置：更新时间
     */
    public void setUpdatetime(Date updatetime)
    {
        this.updatetime = updatetime;
    }
    
    /**
     * 获取：更新时间
     */
    public Date getUpdatetime()
    {
        return updatetime;
    }
    
    /**
     * 设置：更新描述
     */
    public void setDesc(String desc)
    {
        this.desc = desc;
    }
    
    /**
     * 获取：更新描述
     */
    public String getDesc()
    {
        return desc;
    }

    /**
     * @return Returns the isLeaf.
     */
    public boolean isLeaf()
    {
        return leaf;
    }

    /**
     * @param isLeaf The isLeaf to set.
     */
    public void setLeaf(boolean leaf)
    {
        this.leaf = leaf;
    }
}
