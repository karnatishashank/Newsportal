package com.ksr.newsportal.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class SecondaryHeaderModel {

    @ValueMapValue
    private String logo;

    @ValueMapValue
    private String[] languages;

    @ValueMapValue
    @Optional
    private Boolean displayLanguages;

    public String getLogo() {
        return logo;
    }

    public String[] getLanguages() {
        return languages;
    }

    public Boolean getDisplayLanguages() {
        return displayLanguages;
    }
}
