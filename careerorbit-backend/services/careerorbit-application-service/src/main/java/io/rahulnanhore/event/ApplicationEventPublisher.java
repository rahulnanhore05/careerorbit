package io.rahulnanhore.event;

import io.rahulnanhore.client.CompanyClient;
import io.rahulnanhore.client.JobClient;
import io.rahulnanhore.client.UserClient;
import io.rahulnanhore.domain.ApplicationStatus;
import io.rahulnanhore.dto.response.CompanyResponse;
import io.rahulnanhore.dto.response.JobResponse;
import io.rahulnanhore.dto.response.UserResponse;
import io.rahulnanhore.model.Application;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ApplicationEventPublisher {

    private static final String APPLICATION_STATUS_CHANGED_TOPIC = "application.status.changed";
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final UserClient userClient;
    private final JobClient jobClient;
    private final CompanyClient companyClient;

    public void publishStatusChanged(Application app,
                                     String note) {
        try {
            UserResponse candidate = userClient.getUserById(app.getCandidateId());
            JobResponse job = jobClient.getJobById(app.getJobId());
            CompanyResponse company = companyClient.getCompanyById(app.getCompanyId());

            ApplicationStatusChangedEvent event = ApplicationStatusChangedEvent.builder()
                    .applicationId(app.getId())
                    .candidateId(app.getCandidateId())
                    .candidateEmail(candidate.getEmail())
                    .candidateName(candidate.getFullName())
                    .newStatus(app.getStatus())
                    .note(note)
                    .jobTitle(job.getTitle())
                    .companyName(company.getName())
                    .changedAt(LocalDateTime.now())
                    .build();

            kafkaTemplate.send(
                    APPLICATION_STATUS_CHANGED_TOPIC,
                    String.valueOf(app.getId()),
                    event
            );
        } catch (Exception e) {
            throw new RuntimeException("Failed to publish application status changed event", e);
        }
    }
}
