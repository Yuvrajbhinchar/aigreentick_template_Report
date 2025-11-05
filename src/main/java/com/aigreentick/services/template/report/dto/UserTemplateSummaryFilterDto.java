package com.aigreentick.services.template.report.dto;

import com.aigreentick.services.template.enums.TemplateStatus;
import lombok.Data;

@Data
public class UserTemplateSummaryFilterDto {
    private Long userId;
    private TemplateStatus status;
    private String language;
    private String category;
}
