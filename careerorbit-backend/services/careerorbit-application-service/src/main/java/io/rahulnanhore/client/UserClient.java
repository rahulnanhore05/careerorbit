package io.rahulnanhore.client;

import io.rahulnanhore.dto.response.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CAREERORBIT-USER-SERVICE")
public interface UserClient {

    @GetMapping("/api/v1/users/{userId}")
    UserResponse getUserById(@PathVariable Long userId);
}
