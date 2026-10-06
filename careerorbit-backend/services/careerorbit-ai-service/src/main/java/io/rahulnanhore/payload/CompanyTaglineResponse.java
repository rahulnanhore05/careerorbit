package io.rahulnanhore.payload;

import java.util.List;

import lombok.Data;

@Data
public class CompanyTaglineResponse {
    private List<String> taglines;
}
