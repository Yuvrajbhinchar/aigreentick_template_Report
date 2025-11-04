package com.aigreentick.services.template.report.dto;

import com.aigreentick.services.template.enums.TemplateCategory;
import com.aigreentick.services.template.enums.TemplateStatus;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class TemplateReportFilterDto {
    private Long organisationId;
    private Long userId;
    private TemplateStatus status;
    private String language;
    private TemplateCategory category;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime startDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime EndDate;

}
