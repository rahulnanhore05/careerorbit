package io.rahulnanhore.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkExperienceBulletsResponse {
    private List<String> bullets;
}
