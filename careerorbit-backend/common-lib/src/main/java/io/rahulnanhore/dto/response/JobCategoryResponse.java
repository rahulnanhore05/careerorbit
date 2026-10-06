package io.rahulnanhore.dto.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobCategoryResponse {
    private Long id;
    private String name;
    private String slug;
    private String description;
    private String iconUrl;
    private Long parentId;
    private String parent;
    private List<JobCategoryResponse> subCategories;
    private Boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
