package com.ksr.newsportal.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class PrimaryHeaderModel {

    @ValueMapValue
    private String trendingText;

    @ValueMapValue
    private String trendingDesc;

    @ValueMapValue
    private String todayDateStr;

    @PostConstruct
    public void init(){
        SimpleDateFormat format = new SimpleDateFormat("EEEE,d MMMM YYYY");
        todayDateStr = format.format(new Date());

    }

    public String getTrendingText() {
        return trendingText;
    }

    public String getTrendingDesc() {
        return trendingDesc;
    }

    public String getTodayDateStr() {
        return todayDateStr;
    }
}
