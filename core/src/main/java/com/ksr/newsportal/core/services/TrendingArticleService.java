package com.ksr.newsportal.core.services;

import com.ksr.newsportal.core.models.BannerArticleModel;

import java.util.List;

public interface TrendingArticleService {

    public List<BannerArticleModel> getTrendingArticles();
}
