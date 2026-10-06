package io.rahulnanhore.service.impl;

import io.rahulnanhore.dto.request.JobTagRequest;
import io.rahulnanhore.dto.response.JobTagResponse;
import io.rahulnanhore.mapper.JobTagMapper;
import io.rahulnanhore.model.JobTag;
import io.rahulnanhore.repository.JobTagRepository;
import io.rahulnanhore.service.JobTagService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class JobTagServiceImpl implements JobTagService {

    private final JobTagRepository jobTagRepository;

    @Override
    public JobTagResponse createJobTag(JobTagRequest jobTagRequest) {
        JobTag jobTag = JobTag.builder()
                .name(jobTagRequest.getName())
                .slug(generateSlug(jobTagRequest.getName()))
                .build();
        return JobTagMapper.toJobTagResponse(jobTagRepository.save(jobTag));
    }

    @Override
    public JobTagResponse getJobTagById(Long id) throws Exception {
        JobTag jobTag = jobTagRepository.findById(id).orElseThrow(() -> new Exception("Tag not found"));
        return JobTagMapper.toJobTagResponse(jobTag);
    }

    @Override
    public List<JobTagResponse> getAllJobTags() {
        List<JobTag> jobTags = jobTagRepository.findAll();
        return jobTags.stream()
                .map(JobTagMapper::toJobTagResponse)
                .toList();
    }

    @Override
    public JobTagResponse updateJobTag(Long id, JobTagRequest request) throws Exception {
        JobTag jobTag = jobTagRepository.findById(id).orElseThrow(() -> new Exception("Tag not found"));
        if(jobTagRepository.existsByName(request.getName())) throw new Exception("Tag name already exists");
        jobTag.setName(request.getName());
        jobTag.setSlug(generateSlug(request.getName()));
        return JobTagMapper.toJobTagResponse(jobTagRepository.save(jobTag));
    }

    @Override
    public void deleteJobTag(Long id) throws Exception {
        JobTag jobTag = jobTagRepository.findById(id).orElseThrow(() -> new Exception("Tag not found"));
        jobTagRepository.deleteById(jobTag.getId());
    }

    @Override
    public Set<JobTag> getTagsByIds(Set<Long> ids) throws Exception {
        return new HashSet<>(jobTagRepository.findAllById(ids));
    }

    @Override
    public JobTag getJobTagEntityById(Long id) throws Exception {
        return jobTagRepository.findById(id).orElseThrow(() -> new Exception("Tag not found"));
    }

    private String generateSlug(String name) {
        String slug = name.toLowerCase()
                .replaceAll("[^a-z0-9\\s-]", "")
                .replaceAll("[\\s-]+", "-");

        if(!jobTagRepository.existsBySlug(slug)) return slug;

        int counter = 1;
        while (jobTagRepository.existsBySlug(slug + "-" + counter)) {
            counter++;
        }

        return slug + "-" + counter;
    }
}
