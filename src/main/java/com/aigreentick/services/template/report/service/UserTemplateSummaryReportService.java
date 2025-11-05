package com.aigreentick.services.template.report.service;

import com.aigreentick.services.template.model.Template;
import com.aigreentick.services.template.report.dto.UserTemplateSummaryFilterDto;
import com.aigreentick.services.template.report.dto.UserTemplateSummaryReportDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserTemplateSummaryReportService {

    private final MongoTemplate mongoTemplate;

    public List<UserTemplateSummaryReportDto> generateSummary(UserTemplateSummaryFilterDto filter){

        if(filter.getUserId() == null){
            throw new IllegalArgumentException("userId is required");
        }

        Query query = new Query();
        Criteria criteria = Criteria.where("userId").is(filter.getUserId());

        if (filter.getStatus() != null)
            criteria = criteria.and("status").is(filter.getStatus());

        if (filter.getLanguage() != null && !filter.getLanguage().isBlank())
            criteria = criteria.and("language").is(filter.getLanguage());

        if (filter.getCategory() != null && !filter.getCategory().isBlank())
            criteria = criteria.and("category").is(filter.getCategory());

        query.addCriteria(criteria);

        List<Template> templates = mongoTemplate.find(query, Template.class);

        return templates.stream().map(t ->
                UserTemplateSummaryReportDto.builder()
                        .templateName(t.getName())
                        .status(t.getStatus() != null ? t.getStatus().name() : "")
                        .language(t.getLanguage())
                        .category(t.getCategory())
                        .build()
        ).collect(Collectors.toList());
    }

}
