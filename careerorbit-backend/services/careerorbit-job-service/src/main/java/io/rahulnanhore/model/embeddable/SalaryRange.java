package io.rahulnanhore.model.embeddable;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.math.BigDecimal;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SalaryRange {
    BigDecimal minSalary;
    BigDecimal maxSalary;
}
