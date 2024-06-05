package com.org.oycm.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * @author ouyangcm
 * create 2024/6/5 16:12
 */
@Service("myPrototypeService")
@Scope("prototype")
public class MyPrototypeService {

    private String jobId;
    private String jobName;

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
}
