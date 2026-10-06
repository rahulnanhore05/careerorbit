package io.rahulnanhore.service;

import io.rahulnanhore.domain.ApplicationStatus;
import io.rahulnanhore.event.ApplicationStatusChangedEvent;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String username;

    private static final Map<ApplicationStatus, String> STATUS_LABELS = Map.of(
            ApplicationStatus.PENDING, "Pending Review",
            ApplicationStatus.REVIEWING, "Under Review",
            ApplicationStatus.SHORTLISTED, "Shortlisted",
            ApplicationStatus.INTERVIEW_SCHEDULED, "Interview Scheduled",
            ApplicationStatus.REJECTED, "Not Selected",
            ApplicationStatus.HIRED, "Hired!",
            ApplicationStatus.WITHDRAWN, "Withdrawn"
    );
    private static final Map<ApplicationStatus, String> STATUS_COLORS = Map.of(
            ApplicationStatus.PENDING, "#f59e0b",
            ApplicationStatus.REVIEWING, "#3b82f6",
            ApplicationStatus.SHORTLISTED, "#8b5cf6",
            ApplicationStatus.INTERVIEW_SCHEDULED, "#06b6d4",
            ApplicationStatus.REJECTED, "#ef4444",
            ApplicationStatus.HIRED, "#22c55e",
            ApplicationStatus.WITHDRAWN, "#6b7280"
    );

    public void sendStatusChangedEmail(ApplicationStatusChangedEvent event) {
        try {
            String username = event.getCandidateEmail();
            String subject = "Application Update: " + event.getJobTitle() + " at " + event.getCompanyName();
            String statusLabel = STATUS_LABELS.get(event.getNewStatus());
            String statusColor = STATUS_COLORS.get(event.getNewStatus());
            sendMail(username, subject, buildStatusChangedHtml(event, statusLabel, statusColor));
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMail(String candidateEmail, String subject, String html) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        helper.setFrom(username);
        helper.setTo(candidateEmail);
        helper.setSubject(subject);
        helper.setText(html,true);
        javaMailSender.send(mimeMessage);
    }

    private String buildStatusChangedHtml(
            ApplicationStatusChangedEvent event,
            String statusLabel,
            String statusColor) {

        String noteSection = (event.getNote() != null && !event.getNote().isBlank()) ?
                """
                    <div style="background:#f8fafc;border-left:4px solid %s;
                         padding:12px 16px;margin:16px 0;border-radius:4px;">
                        <p style="margin:0;color:#374151;font-size:14px;">
                            <strong>Note from employer:</strong> %s
                        </p>
                    </div>
                """.formatted(
                    statusColor,
                    escapeHtml(event.getNote())
            ) : "";

        return """
            <!DOCTYPE html>
            <html>
            <body style="font-family:Arial,sans-serif;background:#f3f4f6;margin:0;padding:20px;">

                <div style="max-width:600px;margin:0 auto;background:#fff;
                            border-radius:8px;overflow:hidden;
                            box-shadow:0 1px 3px rgba(0,0,0,0.1);">

                    <div style="background:%s;padding:24px;text-align:center;">
                        <h1 style="color:#fff;margin:0;font-size:22px;">
                            Application Status Update
                        </h1>
                    </div>

                    <div style="padding:32px;">

                        <p style="color:#374151;font-size:16px;">
                            Hi <strong>%s</strong>,
                        </p>

                        <p style="color:#6b7280;">
                            Your application for <strong>%s</strong>
                            at <strong>%s</strong> has been updated.
                        </p>

                        <div style="background:#f9fafb;border:1px solid #e5e7eb;
                                    border-radius:8px;padding:20px;margin:20px 0;
                                    text-align:center;">

                            <p style="margin:0 0 8px;color:#9ca3af;font-size:13px;
                                      text-transform:uppercase;letter-spacing:1px;">
                                New Status
                            </p>

                            <span style="display:inline-block;background:%s;color:#fff;
                                         padding:8px 20px;border-radius:20px;
                                         font-weight:bold;font-size:16px;">
                                %s
                            </span>
                        </div>

                        %s

                        <p style="color:#9ca3af;font-size:13px;margin-top:24px;">
                            You can log in to the Job Portal to view your full
                            application timeline.
                        </p>

                    </div>

                    <div style="background:#f9fafb;padding:16px;text-align:center;
                                border-top:1px solid #e5e7eb;">
                        <p style="margin:0;color:#9ca3af;font-size:12px;">
                            CareerOrbit &mdash; You are receiving this because
                            you applied for a job.
                        </p>
                    </div>

                </div>
            </body>
            </html>
            """.formatted(
                statusColor,
                escapeHtml(event.getCandidateName()),
                escapeHtml(event.getJobTitle()),
                escapeHtml(event.getCompanyName()),
                statusColor,
                escapeHtml(statusLabel),
                noteSection
        );
    }

    private String escapeHtml(String text) {
        if (text == null) return "";
        return text
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;");
    }
}
