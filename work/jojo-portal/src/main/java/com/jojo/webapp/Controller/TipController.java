/**
 *
 * JOJO
 * Copyright (c) 2014,Inc.All Rights Reserved.
 */
package com.jojo.webapp.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jojo.webapp.form.BaseForm;

/**
 *
 * @author JOJO
 */
@Controller
public class TipController extends BaseController {

    //, method = RequestMethod.GET
    @RequestMapping(value = "/tip/auth")
    public String auth(@ModelAttribute("form") BaseForm form) {

        return "view/tip/auth";

    }

    @RequestMapping(value = "/tip/exception")
    public String exception(@ModelAttribute("form") BaseForm form) {

        return "view/tip/exception";

    }

}
