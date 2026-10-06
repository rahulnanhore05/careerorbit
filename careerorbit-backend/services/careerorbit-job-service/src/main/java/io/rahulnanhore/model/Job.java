package io.rahulnanhore.model;

import io.rahulnanhore.domain.ExperienceLevel;
import io.rahulnanhore.domain.JobStatus;
import io.rahulnanhore.domain.JobType;
import io.rahulnanhore.domain.WorkMode;
import io.rahulnanhore.model.embeddable.JobLocation;
import io.rahulnanhore.model.embeddable.SalaryRange;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    private String responsibilities;

    private String requirements;

    private String benefits;

    @Column(nullable = false)
    private Long companyId;

    @Column(nullable = false)
    private Long employerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private JobCategory category;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "job_skill_required", joinColumns = @JoinColumn(name = "job_id"), inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private Set<JobSkill> skills;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "job_tags_required", joinColumns = @JoinColumn(name = "job_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<JobTag> tags;

    @Embedded
    private JobLocation location;

    @Embedded
    private SalaryRange salaryRange;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private JobType jobType;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private WorkMode workMode;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ExperienceLevel experienceLevel;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private JobStatus status;

    private Integer openings = 1;

    private LocalDateTime applicationDeadline;

    private LocalDateTime expiredAt;

    private Boolean active = true;

    private LocalDateTime publishedAt;

    private LocalDateTime closedAt;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
