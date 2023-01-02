package com.pingan.service.impl;

import com.pingan.entity.Suggestion;
import com.pingan.mapper.SuggestionMapper;
import com.pingan.service.SuggestionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 客户建议或反馈Service实现类
 */
@Service("suggestionService")
public class SuggestionServiceImpl implements SuggestionService {

    @Resource
    private SuggestionMapper suggestionMapper;

    @Override
    public List<Suggestion> list(Map<String, Object> map) {
        return suggestionMapper.list(map);
    }

    @Override
    public Long getCount(Map<String, Object> map) {
        return suggestionMapper.getCount(map);
    }

    @Override
    public Integer add(Suggestion suggestion) {
        return suggestionMapper.add(suggestion);
    }

    @Override
    public Integer update(Suggestion suggestion) {
        return suggestionMapper.update(suggestion);
    }

    @Override
    public Integer delete(Integer id) {
        return suggestionMapper.delete(id);
    }

    @Override
    public Suggestion findById(Integer id) {
        return suggestionMapper.findById(id);
    }

    @Override
    public Long getCountTodaySuggestion(Integer customerId) {
        return suggestionMapper.getCountTodaySuggestion(customerId);
    }
}
