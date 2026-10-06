package io.rahulnanhore.controller;

import io.rahulnanhore.domain.SkillCategory;
import io.rahulnanhore.dto.request.JobSkillRequest;
import io.rahulnanhore.dto.response.JobSkillResponse;
import io.rahulnanhore.service.JobSkillService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/jobs/skills")
public class JobSkillController {

    private final JobSkillService jobSkillService;

    @PostMapping
    public ResponseEntity<JobSkillResponse> createSkill(@Valid @RequestBody JobSkillRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(jobSkillService.createSkill(request));
    }

    @GetMapping
    public ResponseEntity<List<JobSkillResponse>> getAllSkills() {
        return ResponseEntity.ok(jobSkillService.getAllSKills());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobSkillResponse> getSkillById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(jobSkillService.getSkillById(id));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<JobSkillResponse>> getSkillsByCategory(@PathVariable SkillCategory category) {
        return ResponseEntity.ok(jobSkillService.getSkillsByCategory(category));
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobSkillResponse> updateSkill(@PathVariable Long id, @RequestBody JobSkillRequest request) throws Exception {
        return ResponseEntity.ok(jobSkillService.updateSkill(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSkill(@PathVariable Long id) throws Exception {
        jobSkillService.deleteSkill(id);
        return ResponseEntity.ok().build();
    }
}
