package com.jojo.service;

import com.jojo.dal.common.postgre.domain.AttachDO;



/**
 *
 * <summary>
 *
 * </summary>
 *
 * @author jojo
 *
 */
public interface CommonService
{
    /**
    *
    * <summary>
    * [根据附件id得到附件内容]<br>
    * <br>
    * </summary>
    *
    * @author jojo
    *
    * @param attchId
    * @return
    */
   public AttachDO download(String attchId);


}
