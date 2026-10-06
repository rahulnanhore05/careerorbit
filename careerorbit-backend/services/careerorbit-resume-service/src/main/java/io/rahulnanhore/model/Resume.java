package io.rahulnanhore.model;

import io.rahulnanhore.domain.ResumeTemplate;
import io.rahulnanhore.domain.ResumeVisibility;
import io.rahulnanhore.model.embeddable.PersonalInfo;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "resumes")
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long candidateId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private ResumeTemplate resumeTemplate = ResumeTemplate.PROFESSIONAL;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private ResumeVisibility visibility = ResumeVisibility.PUBLIC;

    @Column(nullable = false)
    @Builder.Default
    private Boolean isDefault = false;

    @Embedded
    private PersonalInfo personalInfo;

    private String summary;

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Education> educations;

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkExperience> experiences;

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Project> projects;

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ResumeSkill> skills;

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Language> languages;

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Certificate> certificates;

    @Builder.Default
    private Integer completionScore = 0;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
