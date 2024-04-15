package com.ksr.newsportal.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Model(adaptables ={ Resource.class, SlingHttpServletRequest.class},defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class HomeBannerModel {

    @ValueMapValue
    private String[] articleLinks;


    @SlingObject
    ResourceResolver resolver;

    List<BannerArticleModel> bannerList = new ArrayList<>();


    @PostConstruct
    public void init(){
        if(articleLinks !=null){
            for (String articleLink : articleLinks ){
                Resource articleResource = resolver.getResource(articleLink+"/jcr:content/root/container/banner_article");
                if(articleResource !=null){
                    BannerArticleModel bannerArticle =  articleResource.adaptTo(BannerArticleModel.class);
                    if (bannerArticle !=null){
                        bannerArticle.setPath(articleLink);

                        if (bannerList.size()<5){
                            bannerList.add(bannerArticle);
                        }
                    }
                }
            }
        }
    }

    public List<BannerArticleModel> getBannerList() {
        return bannerList;
    }
}
