package com.ksr.newsportal.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class BannerArticleModel {

    @ValueMapValue
    private String bannerImage;

    @ValueMapValue
    private String bannerText;

    @ValueMapValue(name = "jcr:created")
    private Date jcrCreated;

    private String createdDateStr;

    private String path;

    @PostConstruct
    public void init(){
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE,d MMMM YYYY");
        createdDateStr = formatter.format(jcrCreated);
    }

    public String getBannerImage() {
        return bannerImage;
    }

    public String getBannerText() {
        return bannerText;
    }

    public String getCreatedDateStr() {
        return createdDateStr;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
