package com.org.oycm.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;

/**
 * @author ouyangcm
 * create 2024/6/5 17:04
 */
@Component
public class DynamicControllerConfig {

    private static final Log log = LogFactory.getLog(DynamicControllerConfig.class);
    @Autowired
    RequestMappingHandlerMapping requestMappingHandlerMapping;

    RequestMappingInfo mappingInfo = null;

    @PostConstruct
    public void init() throws NoSuchMethodException {

        RequestMappingInfo.BuilderConfiguration config = new RequestMappingInfo.BuilderConfiguration();
        config.setTrailingSlashMatch(requestMappingHandlerMapping.useTrailingSlashMatch());
        config.setContentNegotiationManager(requestMappingHandlerMapping.getContentNegotiationManager());
        config.setPatternParser(requestMappingHandlerMapping.getPatternParser());

        Method method = this.getClass().getMethod("handleDynamicRequest");

        mappingInfo = RequestMappingInfo.paths("/dynamic").methods(RequestMethod.GET).options(config).build();

        requestMappingHandlerMapping.registerMapping(mappingInfo, this, method);

        // 两次执行，请求被调用的时候会出现问题
        //mappingInfo = RequestMappingInfo.paths("/dynamic").methods(RequestMethod.GET).options(config).build();

        //requestMappingHandlerMapping.registerMapping(mappingInfo, this, method);
        log.info("dynamic controller init complete");

    }

    @ResponseBody
    public String handleDynamicRequest() {
        return "Dynamic Response";
    }
}
