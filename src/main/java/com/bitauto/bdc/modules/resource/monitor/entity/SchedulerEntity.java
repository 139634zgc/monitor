package com.bitauto.bdc.modules.resource.monitor.entity;


import java.io.Serializable;
import java.util.Date;


/**
 * 资源调度情况
 * 
 * @author liuming
 * @email liuming1@yiche.com
 * @date 2017-10-23 10:22:33
 */
public class SchedulerEntity implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    // 主键
    private Integer id;
    
    // 最大可以运行的app数目
    private Integer maxapps;
    
    // 最小内存
    private Integer minresMem;
    
    // 最小cpu核数
    private Integer minresCpu;
    
    // 最大内存
    private Integer maxresMem;
    
    // 最大cpu核数
    private Integer maxresCpu;
    
    // 已经被使用的内存
    private Integer usedresMem;
    
    // 已经被使用的cpu核数
    private Integer usedresCpu;
    
    // 固定占用的内存
    private Integer steadyfairresMem;
    
    // 固定占用的cpu核数
    private Integer steadyfairresCpu;
    
    // 共享内存
    private Integer fairresMem;
    
    // 共享cpu核数
    private Integer fairresCpu;
    
    // 集群总内存量
    private Integer clusterresMem;
    
    // 集群总cpu核数
    private Integer clusterresCpu;
    
    // 正在等待分配的容器数
    private Integer pendingcountainers;
    
    // 已经分配的容器数
    private Integer allocatedcontainers;
    
    // 固定占用的容器数
    private Integer reservedcontainers;
    
    // 队列名称
    private String queuename;
    
    // 调度策略
    private String schedulingpolicy;
    
    // 是否可以外借资源
    private boolean preemptable;
    
    // 被阻塞的应用数目
    private int numPendingApps;
    
    // 当前正在运行的任务数目
    private int numActiveApps;
    
    // 数据插入时间
    private Date inserttime;
    
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
     * 设置：最大可以运行的app数目
     */
    public void setMaxapps(Integer maxapps)
    {
        this.maxapps = maxapps;
    }
    
    /**
     * 获取：最大可以运行的app数目
     */
    public Integer getMaxapps()
    {
        return maxapps;
    }
    
    /**
     * 设置：最小内存
     */
    public void setMinresMem(Integer minresMem)
    {
        this.minresMem = minresMem;
    }
    
    /**
     * 获取：最小内存
     */
    public Integer getMinresMem()
    {
        return minresMem;
    }
    
    /**
     * 设置：最小cpu核数
     */
    public void setMinresCpu(Integer minresCpu)
    {
        this.minresCpu = minresCpu;
    }
    
    /**
     * 获取：最小cpu核数
     */
    public Integer getMinresCpu()
    {
        return minresCpu;
    }
    
    /**
     * 设置：最大内存
     */
    public void setMaxresMem(Integer maxresMem)
    {
        this.maxresMem = maxresMem;
    }
    
    /**
     * 获取：最大内存
     */
    public Integer getMaxresMem()
    {
        return maxresMem;
    }
    
    /**
     * 设置：最大cpu核数
     */
    public void setMaxresCpu(Integer maxresCpu)
    {
        this.maxresCpu = maxresCpu;
    }
    
    /**
     * 获取：最大cpu核数
     */
    public Integer getMaxresCpu()
    {
        return maxresCpu;
    }
    
    /**
     * 设置：已经被使用的内存
     */
    public void setUsedresMem(Integer usedresMem)
    {
        this.usedresMem = usedresMem;
    }
    
    /**
     * 获取：已经被使用的内存
     */
    public Integer getUsedresMem()
    {
        return usedresMem;
    }
    
    /**
     * 设置：已经被使用的cpu核数
     */
    public void setUsedresCpu(Integer usedresCpu)
    {
        this.usedresCpu = usedresCpu;
    }
    
    /**
     * 获取：已经被使用的cpu核数
     */
    public Integer getUsedresCpu()
    {
        return usedresCpu;
    }
    
    /**
     * 设置：固定占用的内存
     */
    public void setSteadyfairresMem(Integer steadyfairresMem)
    {
        this.steadyfairresMem = steadyfairresMem;
    }
    
    /**
     * 获取：固定占用的内存
     */
    public Integer getSteadyfairresMem()
    {
        return steadyfairresMem;
    }
    
    /**
     * 设置：固定占用的cpu核数
     */
    public void setSteadyfairresCpu(Integer steadyfairresCpu)
    {
        this.steadyfairresCpu = steadyfairresCpu;
    }
    
    /**
     * 获取：固定占用的cpu核数
     */
    public Integer getSteadyfairresCpu()
    {
        return steadyfairresCpu;
    }
    
    /**
     * 设置：共享内存
     */
    public void setFairresMem(Integer fairresMem)
    {
        this.fairresMem = fairresMem;
    }
    
    /**
     * 获取：共享内存
     */
    public Integer getFairresMem()
    {
        return fairresMem;
    }
    
    /**
     * 设置：共享cpu核数
     */
    public void setFairresCpu(Integer fairresCpu)
    {
        this.fairresCpu = fairresCpu;
    }
    
    /**
     * 获取：共享cpu核数
     */
    public Integer getFairresCpu()
    {
        return fairresCpu;
    }
    
    /**
     * 设置：集群总内存量
     */
    public void setClusterresMem(Integer clusterresMem)
    {
        this.clusterresMem = clusterresMem;
    }
    
    /**
     * 获取：集群总内存量
     */
    public Integer getClusterresMem()
    {
        return clusterresMem;
    }
    
    /**
     * 设置：集群总cpu核数
     */
    public void setClusterresCpu(Integer clusterresCpu)
    {
        this.clusterresCpu = clusterresCpu;
    }
    
    /**
     * 获取：集群总cpu核数
     */
    public Integer getClusterresCpu()
    {
        return clusterresCpu;
    }
    
    /**
     * 设置：正在等待分配的容器数
     */
    public void setPendingcountainers(Integer pendingcountainers)
    {
        this.pendingcountainers = pendingcountainers;
    }
    
    /**
     * 获取：正在等待分配的容器数
     */
    public Integer getPendingcountainers()
    {
        return pendingcountainers;
    }
    
    /**
     * 设置：已经分配的容器数
     */
    public void setAllocatedcontainers(Integer allocatedcontainers)
    {
        this.allocatedcontainers = allocatedcontainers;
    }
    
    /**
     * 获取：已经分配的容器数
     */
    public Integer getAllocatedcontainers()
    {
        return allocatedcontainers;
    }
    
    /**
     * 设置：固定占用的容器数
     */
    public void setReservedcontainers(Integer reservedcontainers)
    {
        this.reservedcontainers = reservedcontainers;
    }
    
    /**
     * 获取：固定占用的容器数
     */
    public Integer getReservedcontainers()
    {
        return reservedcontainers;
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
     * 设置：调度策略
     */
    public void setSchedulingpolicy(String schedulingpolicy)
    {
        this.schedulingpolicy = schedulingpolicy;
    }
    
    /**
     * 获取：调度策略
     */
    public String getSchedulingpolicy()
    {
        return schedulingpolicy;
    }
    
    /**
     * @return Returns the preemptable.
     */
    public boolean isPreemptable()
    {
        return preemptable;
    }
    
    /**
     * @param preemptable
     *            The preemptable to set.
     */
    public void setPreemptable(boolean preemptable)
    {
        this.preemptable = preemptable;
    }
    
    /**
     * @return Returns the numPendingApps.
     */
    public int getNumPendingApps()
    {
        return numPendingApps;
    }
    
    /**
     * @param numPendingApps
     *            The numPendingApps to set.
     */
    public void setNumPendingApps(int numPendingApps)
    {
        this.numPendingApps = numPendingApps;
    }
    
    /**
     * @return Returns the numActiveApps.
     */
    public int getNumActiveApps()
    {
        return numActiveApps;
    }
    
    /**
     * @param numActiveApps
     *            The numActiveApps to set.
     */
    public void setNumActiveApps(int numActiveApps)
    {
        this.numActiveApps = numActiveApps;
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
    
    /**
     * 对象打印
     */
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("SchedulerEntity [id=");
        builder.append(id);
        builder.append(", maxapps=");
        builder.append(maxapps);
        builder.append(", minresMem=");
        builder.append(minresMem);
        builder.append(", minresCpu=");
        builder.append(minresCpu);
        builder.append(", maxresMem=");
        builder.append(maxresMem);
        builder.append(", maxresCpu=");
        builder.append(maxresCpu);
        builder.append(", usedresMem=");
        builder.append(usedresMem);
        builder.append(", usedresCpu=");
        builder.append(usedresCpu);
        builder.append(", steadyfairresMem=");
        builder.append(steadyfairresMem);
        builder.append(", steadyfairresCpu=");
        builder.append(steadyfairresCpu);
        builder.append(", fairresMem=");
        builder.append(fairresMem);
        builder.append(", fairresCpu=");
        builder.append(fairresCpu);
        builder.append(", clusterresMem=");
        builder.append(clusterresMem);
        builder.append(", clusterresCpu=");
        builder.append(clusterresCpu);
        builder.append(", pendingcountainers=");
        builder.append(pendingcountainers);
        builder.append(", allocatedcontainers=");
        builder.append(allocatedcontainers);
        builder.append(", reservedcontainers=");
        builder.append(reservedcontainers);
        builder.append(", queuename=");
        builder.append(queuename);
        builder.append(", schedulingpolicy=");
        builder.append(schedulingpolicy);
        builder.append(", preemptable=");
        builder.append(preemptable);
        builder.append(", numPendingApps=");
        builder.append(numPendingApps);
        builder.append(", numActiveApps=");
        builder.append(numActiveApps);
        builder.append(", inserttime=");
        builder.append(inserttime);
        builder.append("]");
        return builder.toString();
    }
}
