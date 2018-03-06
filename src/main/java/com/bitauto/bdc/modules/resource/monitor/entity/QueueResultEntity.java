/*
 * 文件名：queueResultEntity.java
 * 版权：Copyright by www.yiche.com
 * 描述：
 * 修改人：liuming1
 * 修改时间：2017年12月16日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.bitauto.bdc.modules.resource.monitor.entity;

import java.util.List;

public class QueueResultEntity
{   
    private Integer containerSize = 0 ;
    
    private Integer maxRes = 0;
    
    private List<String> [] values;

    /**
     * @return Returns the maxRes.
     */
    public Integer getMaxRes()
    {
        return maxRes;
    }

    /**
     * @param maxRes The maxRes to set.
     */
    public void setMaxRes(Integer maxRes)
    {
        this.maxRes = maxRes;
    }

    /**
     * @return Returns the containerSize.
     */
    public Integer getContainerSize()
    {
        return containerSize;
    }

    /**
     * @param containerSize The containerSize to set.
     */
    public void setContainerSize(Integer containerSize)
    {
        this.containerSize = containerSize;
    }

    /**
     * @return Returns the values.
     */
    public List<String>[] getValues()
    {
        return values;
    }

    /**
     * @param values The values to set.
     */
    public void setValues(List<String>[] values)
    {
        this.values = values;
    }
    
    
}
