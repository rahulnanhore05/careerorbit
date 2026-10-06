package io.rahulnanhore.service;

import io.rahulnanhore.domain.SkillCategory;
import io.rahulnanhore.dto.request.JobSkillRequest;
import io.rahulnanhore.dto.response.JobSkillResponse;
import io.rahulnanhore.model.JobSkill;

import java.util.List;
import java.util.Set;

public interface JobSkillService {

    JobSkillResponse createSkill(JobSkillRequest request);

    List<JobSkillResponse> getAllSKills();

    JobSkillResponse getSkillById(Long id) throws Exception;

    List<JobSkillResponse> getSkillsByCategory(SkillCategory category);

    JobSkillResponse updateSkill(Long id, JobSkillRequest request) throws Exception;

    void deleteSkill(Long id) throws Exception;

    JobSkill getSkillEntityById(Long id) throws Exception;

    Set<JobSkill> getSkillsByIds(Set<Long> ids) throws Exception;

}
