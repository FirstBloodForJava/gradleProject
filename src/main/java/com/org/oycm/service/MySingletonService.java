package com.org.oycm.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * @author ouyangcm
 * create 2024/6/5 16:17
 */
@Service("mySingletonService")
@Scope("singleton")
public class MySingletonService {

    private static final Log log = LogFactory.getLog(MySingletonService.class);
    private String jobId;
    private String jobName;

    public MySingletonService() {
        this.jobId = "singleton";
        this.jobName = "mySingletonService";
        log.info("MySingletonService construct exec");
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
        return "singleton{" +
                "jobId='" + jobId + '\'' +
                ", jobName='" + jobName + '\'' +
                '}';
    }
}
