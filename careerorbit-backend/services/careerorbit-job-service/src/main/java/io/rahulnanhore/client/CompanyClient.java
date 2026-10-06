package io.rahulnanhore.client;

import io.rahulnanhore.dto.response.CompanyResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "careerorbit-company-service")
public interface CompanyClient {

    @GetMapping("/api/v1/companies/{id}")
    CompanyResponse getCompanyById(@PathVariable Long id);

    @GetMapping("/api/v1/companies/my")
    CompanyResponse getMyCompany(@RequestHeader("X-User-Id") Long ownId);
}
