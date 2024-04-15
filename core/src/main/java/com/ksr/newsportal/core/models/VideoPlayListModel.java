package com.ksr.newsportal.core.models;

import com.ksr.newsportal.core.dto.YoutubeVideoResponse;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;

@Model(adaptables = Resource.class)
public class VideoPlayListModel {

    @ValueMapValue
    private String[] youtubeLinks;

    private List<YoutubeVideoResponse> videoList;

    @PostConstruct
    public void init() {
        if (youtubeLinks != null) {
            try (CloseableHttpClient httpClient = HttpClients.createDefault();) {
                for (String youTubeLink : youtubeLinks) {
                    HttpGet request = new HttpGet("https://www.youtube.com/oembed?url=" + youTubeLink);

                    try (CloseableHttpResponse response = httpClient.execute(request)) {
                        HttpEntity entity = response.getEntity();
                        if (entity != null) {

                            String result = EntityUtils.toString(entity);
                            try(JsonReader jsonReader = Json.createReader(new StringReader(result))){
                                JsonObject youTubeResponse = jsonReader.readObject();
                                YoutubeVideoResponse youtubeVideoResponseDto = new YoutubeVideoResponse();
                                youtubeVideoResponseDto.setHtml(youTubeResponse.getString("html"));
                                youtubeVideoResponseDto.setHtml(youTubeResponse.getString("title"));
                                youtubeVideoResponseDto.setHtml(youTubeResponse.getString("thumbnailUrl"));

                                videoList.add(youtubeVideoResponseDto);
                            }
                        }
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public List<YoutubeVideoResponse> getVideoList() {
        return videoList;
    }
}
