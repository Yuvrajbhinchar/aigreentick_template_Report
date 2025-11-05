package com.aigreentick.services.template.report.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserTemplateSummaryReportDto {
    private String templateName;
    private String status;
    private String language;
    private String category;
}