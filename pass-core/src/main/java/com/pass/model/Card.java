package com.pass.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Document
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Card {

    @Id
    private String id;
    @JsonIgnore
    private long deckId;
    @JsonIgnore
    private String userId;

    @NotBlank(message = "Title can not be empty.")
    private String title;

    @Size(max = 256, message = "Content can not have more than 128 words")
    private String content;

    @JsonIgnore
    @NotBlank(message = "Author can not be empty.")
    private String author;

    @NotBlank(message = "Source url can not be empty.")
    private String articleWebUrl;

    private String articleSourceLogo;

    private String articleSourceName;
    @NotNull
    private Media media;
    @NotBlank(message = "Template can not be empty.")
    private String template;

    private String status;
    private Survey survey;

    @JsonIgnore
    @CreatedDate
    private Date createdTime;

    @LastModifiedDate
    @JsonIgnore
    private Date modifiedTime;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonIgnore
    private int weight;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getDeckId() {
        return deckId;
    }

    public void setDeckId(long deckId) {
        this.deckId = deckId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getArticleWebUrl() {
        return articleWebUrl;
    }

    public void setArticleWebUrl(String articleWebUrl) {
        this.articleWebUrl = articleWebUrl;
    }

    public String getArticleSourceLogo() {
        return articleSourceLogo;
    }

    public void setArticleSourceLogo(String articleSourceLogo) {
        this.articleSourceLogo = articleSourceLogo;
    }

    public String getArticleSourceName() {
        return articleSourceName;
    }

    public void setArticleSourceName(String articleSourceName) {
        this.articleSourceName = articleSourceName;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}