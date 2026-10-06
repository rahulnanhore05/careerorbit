package io.rahulnanhore.service.impl;

import io.rahulnanhore.domain.SkillCategory;
import io.rahulnanhore.dto.request.JobSkillRequest;
import io.rahulnanhore.dto.response.JobSkillResponse;
import io.rahulnanhore.mapper.JobSkillMapper;
import io.rahulnanhore.model.JobSkill;
import io.rahulnanhore.repository.JobSkillsRepository;
import io.rahulnanhore.service.JobSkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class JobSkillServiceImpl implements JobSkillService {

    private final JobSkillsRepository jobSkillsRepository;

    @Override
    public JobSkillResponse createSkill(JobSkillRequest request) {

        JobSkill jobSkill = JobSkill.builder()
                .name(request.getName())
                .category(request.getCategory())
                .slug(generateSlug(request.getName()))
                .build();
        return JobSkillMapper.toJobSkillResponse(jobSkillsRepository.save(jobSkill));
    }

    @Override
    public List<JobSkillResponse> getAllSKills() {
        List<JobSkill> jobSkills = jobSkillsRepository.findAll();
        return jobSkills.stream().map(JobSkillMapper::toJobSkillResponse).toList();
    }

    @Override
    public JobSkillResponse getSkillById(Long id) throws Exception {
        JobSkill jobSkill = jobSkillsRepository.findById(id).orElseThrow(() -> new Exception("Skill not found"));
        return JobSkillMapper.toJobSkillResponse(jobSkill);
    }

    @Override
    public List<JobSkillResponse> getSkillsByCategory(SkillCategory category) {
        List<JobSkill> jobSkillsByCategory = jobSkillsRepository.findAllByCategory(category);
        return jobSkillsByCategory.stream().map(JobSkillMapper::toJobSkillResponse).toList();
    }

    @Override
    public JobSkillResponse updateSkill(Long id, JobSkillRequest request) throws Exception {
        JobSkill jobSkill = jobSkillsRepository.findById(id).orElseThrow(() -> new Exception("Skill not found"));

        if (StringUtils.hasText(request.getName()) && jobSkillsRepository.existsByName(request.getName())) throw new Exception("Skill name already exists");
        if (StringUtils.hasText(request.getName())) jobSkill.setName(request.getName());
        if (request.getCategory() != null) jobSkill.setCategory(request.getCategory());
        return JobSkillMapper.toJobSkillResponse(jobSkillsRepository.save(jobSkill));
    }

    @Override
    public void deleteSkill(Long id) throws Exception {
        JobSkill jobSkill = jobSkillsRepository.findById(id).orElseThrow(() -> new Exception("Skill not found"));
        jobSkillsRepository.deleteById(jobSkill.getId());
    }

    @Override
    public JobSkill getSkillEntityById(Long id) throws Exception {
        return jobSkillsRepository.findById(id).orElseThrow(() -> new Exception("Skill not found"));
    }

    @Override
    public Set<JobSkill> getSkillsByIds(Set<Long> ids) throws Exception {
        return new HashSet<>(jobSkillsRepository.findAllById(ids));
    }

    private String generateSlug(String name) {
        String slug = name.toLowerCase()
                .replaceAll("[^a-z0-9\\s-]", "")
                .replaceAll("[\\s-]+", "-");

        if(!jobSkillsRepository.existsBySlug(slug)) return slug;

        int counter = 1;
        while (jobSkillsRepository.existsBySlug(slug + "-" + counter)) {
            counter++;
        }

        return slug + "-" + counter;
    }
}
