package com.aigreentick.services.template.report.service;

import com.aigreentick.services.template.enums.TemplateCategory;
import com.aigreentick.services.template.model.Template;
import com.aigreentick.services.template.report.dto.TemplateReportDto;
import com.aigreentick.services.template.report.dto.TemplateReportFilterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class TemplateReportService {

    private final MongoTemplate mongoTemplate;

    public List<TemplateReportDto> genrateTemplateReport(TemplateReportFilterDto filter){

        Query query = new Query();
        Criteria criteria = new Criteria();

        if(filter.getUserId() != null)
            criteria = criteria.and("userId").is(filter.getUserId());

        if (filter.getStatus() != null)
            criteria = criteria.and("status").is(filter.getStatus());

        if (filter.getCategory() != null)
            criteria = criteria.and("category").is(filter.getCategory());

        if (filter.getLanguage() != null && !filter.getLanguage().isEmpty())
            criteria = criteria.and("language").is(filter.getLanguage());

        if (filter.getStartDate() != null && filter.getEndDate() != null) {
            criteria = criteria.and("createdAt")
                    .gte(filter.getStartDate())
                    .lte(filter.getEndDate());
        }


        query.addCriteria(criteria);

        List<Template> templates = mongoTemplate.find(query, Template.class);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return templates.stream().map(template -> {
            TemplateReportDto dto = new TemplateReportDto();
            dto.setTemplateId(template.getId());
            dto.setTemplateName(template.getName());
            dto.setCategory(template.getCategory() != null ? TemplateCategory.valueOf(template.getCategory()) : null);
            dto.setLanguage(template.getLanguage());
            dto.setStatus(template.getStatus() != null ? template.getStatus() : null);
            dto.setRejectionReason(template.getRejectionReason());
            dto.setUserId(template.getUserId());
            dto.setOrganisationId(template.getOrganisationId());
            dto.setCreatedAt(template.getCreatedAt() != null ? template.getCreatedAt().format(formatter) : null);
            dto.setUpdatedAt(template.getUpdatedAt() != null ? template.getUpdatedAt().format(formatter) : null);
            return dto;
        }).collect(Collectors.toList());
    }



}
