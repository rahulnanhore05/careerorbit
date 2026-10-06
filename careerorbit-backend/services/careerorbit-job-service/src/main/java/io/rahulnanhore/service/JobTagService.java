package io.rahulnanhore.service;

import io.rahulnanhore.dto.request.JobTagRequest;
import io.rahulnanhore.dto.response.JobTagResponse;
import io.rahulnanhore.model.JobTag;

import java.util.List;
import java.util.Set;

public interface JobTagService {

    JobTagResponse createJobTag(JobTagRequest jobTagRequest);

    JobTagResponse getJobTagById(Long id) throws Exception;

    List<JobTagResponse> getAllJobTags();

    JobTagResponse updateJobTag(Long id, JobTagRequest jobTagRequest) throws Exception;

    void deleteJobTag(Long id) throws Exception;

    Set<JobTag> getTagsByIds(Set<Long> ids) throws Exception;

    JobTag getJobTagEntityById(Long id) throws Exception;
}
