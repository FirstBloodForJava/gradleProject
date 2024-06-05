package com.org.oycm.controller;

import com.org.oycm.config.BeanUtils;
import com.org.oycm.service.MyPrototypeService;
import com.org.oycm.service.MySingletonService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ouyangcm
 * create 2024/6/5 16:11
 */
@RestController
public class MyController {

    private static final Log log = LogFactory.getLog(MyController.class);
    @Autowired
    MyPrototypeService myPrototypeService;

    @Autowired
    MySingletonService mySingletonService;

    @GetMapping("getPrototype")
    public String getPrototypeInfo(){
        log.info("prototype: " + myPrototypeService);
        log.info("prototype: " + BeanUtils.getBean("myPrototypeService"));
        return myPrototypeService.string();
    }

    @GetMapping("getSingleton")
    public String getSingletonInfo(){
        log.info("singleton: " + mySingletonService);
        log.info("singleton: " + BeanUtils.getBean("mySingletonService"));
        return mySingletonService.string();
    }

    @GetMapping("update/prototype/{name}")
    public String updatePrototype(@PathVariable("name") String name){
        myPrototypeService.setJobName(name);
        return myPrototypeService.string();
    }

    @GetMapping("update/singleton/{name}")
    public String updateSingleton(@PathVariable("name") String name){
        myPrototypeService.setJobName(name);
        return myPrototypeService.string();
    }

}
