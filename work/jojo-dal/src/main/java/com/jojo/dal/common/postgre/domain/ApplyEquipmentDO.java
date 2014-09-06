/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.dal.common.postgre.domain;

import org.springframework.web.multipart.MultipartFile;

import com.jojo.util.pojo.BasePOJO;

/**
 * <summary>
 * [物料申请DO]<br>
 * <br>
 * </summary>
 *
 * @author jojo
 *
 */
public class ApplyEquipmentDO extends BasePOJO
{

    /**   */
    private static final long serialVersionUID = 4368474325703503787L;

    /**
     * 检查上传文件的类名称
     */
    private String[] checkerClassNames[];

    /**
     * 待上传的文件，目前限制为只能一个
     */
    private MultipartFile[] multipartFiles[];

    public String[][] getCheckerClassNames()
    {
        return checkerClassNames;
    }

    public void setCheckerClassNames(String[][] checkerClassNames)
    {
        this.checkerClassNames = checkerClassNames;
    }

    public MultipartFile[][] getMultipartFiles()
    {
        return multipartFiles;
    }

    public void setMultipartFiles(MultipartFile[][] multipartFiles)
    {
        this.multipartFiles = multipartFiles;
    }


}
