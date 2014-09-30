package com.jojo.test.biz;

import com.alibaba.fastjson.JSON;
import com.jojo.biz.model.JsTreeNode;


/**
 * <summary>
 * []<br>
 * <br>
 * </summary>
 *
 * @author jojo
 *
 */
public class JsTreeNodeJSONTest
{
    public static void main(String[] args)
    {
        System.out.println( JSON.toJSONString(new JsTreeNode()) );
    }
}
