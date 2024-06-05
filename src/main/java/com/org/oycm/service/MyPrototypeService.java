package com.org.oycm.service;

import com.org.oycm.config.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * @author ouyangcm
 * create 2024/6/5 16:12
 */
@Service("myPrototypeService")
@Scope("prototype")
public class MyPrototypeService {

    private static final Log log = LogFactory.getLog(MyPrototypeService.class);
    private String jobId;
    private String jobName;

    public MyPrototypeService() {
        this.jobId = "prototype";
        this.jobName = "myPrototypeService";
        log.info("MyPrototypeService construct exec");
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }


    public String string() {
        return "prototype{" +
                "jobId='" + jobId + '\'' +
                ", jobName='" + jobName + '\'' +
                '}';
    }
}
