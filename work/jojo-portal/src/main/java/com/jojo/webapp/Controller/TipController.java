/**
 *
 * JOJO
 * Copyright (c) 2014,Inc.All Rights Reserved.
 */
package com.jojo.webapp.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jojo.webapp.form.BaseForm;

/**
 *
 * @author JOJO
 */
@Controller
public class TipController extends BaseController {

    @RequestMapping(value = "/tip/auth", method = RequestMethod.GET)
    public String auth(@ModelAttribute("form") BaseForm form) {

        return "WEB-INF/tip/auth";

    }

    @RequestMapping(value = "/tip/exception", method = RequestMethod.GET)
    public String exception(@ModelAttribute("form") BaseForm form) {

        return "WEB-INF/tip/exception";

    }

}
