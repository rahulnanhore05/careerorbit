package io.rahulnanhore.service;

import io.rahulnanhore.dto.request.JobRequest;
import io.rahulnanhore.dto.response.JobResponse;
import io.rahulnanhore.payload.JobSearchRequest;

import java.util.List;

public interface JobService {

    JobResponse createJob(Long employerId, JobRequest jobRequest) throws Exception;

    JobResponse getJobById(Long jobId) throws Exception;

    List<JobResponse> getJobs(JobSearchRequest jobSearchRequest);

    List<JobResponse> getJobsByCompany(Long companyId) throws Exception;

    JobResponse updateJob(Long jobId, Long employerId, JobRequest jobRequest) throws Exception;

    JobResponse publishJob(Long jobId, Long employerId) throws Exception;

    JobResponse closeJob(Long jobId, Long employerId) throws Exception;

    void deleteJob(Long jobId, Long employerId) throws Exception;

    List<JobResponse> getAllJobs();
}
