package com.aigreentick.services.template.report.dto;

import com.aigreentick.services.template.enums.TemplateCategory;
import com.aigreentick.services.template.enums.TemplateStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemplateReportDto {
    private String templateId;
    private String templateName;
    private TemplateCategory category;
    private String language;
    private TemplateStatus status;
    private String rejectionReason;
    private Long organisationId;
    private String organisationName;
    private Long userId;
    private String uploadedBy;
    private String createdAt;
    private String updatedAt;
}
