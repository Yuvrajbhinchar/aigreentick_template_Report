package com.aigreentick.services.template.report.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarouselUsageDto {
    private String templateId;
    private String templateName;
    private int numberOfCards;
    private int numberOfButtons;
}
