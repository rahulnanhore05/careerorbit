package io.rahulnanhore.payload;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CareerFeedbackResponse {
    private int profileStrength;
    private List<String> shortlistingIssues;
    private List<Improvement> improvements;
    private List<JobTarget> jobTargets;
    private String overallSummary;;

    @Data
    public static class Improvement{
        private String area;
        private String issue;
        private String action;
        private String priority;
    }

    @Data
    public static class JobTarget{
        private String jobTarget;
        private String reason;
        private String skillMatch;
    }
}
