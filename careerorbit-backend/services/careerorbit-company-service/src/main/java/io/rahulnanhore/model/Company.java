package io.rahulnanhore.model;

import io.rahulnanhore.domain.*;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(unique = true)
    private String slug;

    @Column(unique = true)
    private String registrationNumber;

    private boolean verified = false;

    @Column(nullable = false, unique = true)
    private Long ownerId;

    private String tagline;

    private String description;

    private String logoUrl;

    private String coverImageUrl;

    private String websiteUrl;

    private Integer foundedYear;

    @Enumerated(EnumType.STRING)
    private CompanySize companySize;

    @Enumerated(EnumType.STRING)
    private CompanyType companyType;

    @Enumerated(EnumType.STRING)
    private IndustryType industryType;

    @Enumerated(EnumType.STRING)
    private CompanyStatus status;

    @ElementCollection
    private List<SocialLink> socialLinks = new ArrayList<>();

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
