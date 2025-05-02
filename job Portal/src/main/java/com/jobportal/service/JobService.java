package com.jobportal.service;

import com.jobportal.dto.JobDTO;
import com.jobportal.exception.JobPortalException;

import java.util.List;

public interface JobService {
    public JobDTO postJob(JobDTO jobDTO) throws JobPortalException;

    public  List<JobDTO> getAllJobs();

    public JobDTO getJob(Long id) throws JobPortalException;
}
