package com.ksr.newsportal.core.services.impl;

import com.ksr.newsportal.core.models.BannerArticleModel;
import com.ksr.newsportal.core.services.TrendingArticleService;
import org.osgi.service.component.annotations.Component;

import java.util.List;

@Component(service = TrendingArticleService.class, immediate = true)
public class TrendingArticleServiceImpl implements TrendingArticleService {
    @Override
    public List<BannerArticleModel> getTrendingArticles() {
        return null;
    }
}
