package com.nanjolono.blog.domain;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

public class Blog {

    private String title;

    private String author;

    private String context;

    private String createDatetime;

    private String updateDatetime;

    public void apply(Function<String,String> d){
        Objects.requireNonNull(d);
        this.author = d.apply(this.author);
    }

    private Blog(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(String createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(String updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public static class Builder{

        private String title;

        private String author;

        private String context;

        private String createDatetime;

        private String updateDatetime;

        public Builder ( String  title)   {
            this.title = title;
        }

        public Builder author(String author){
            this.author = author;
            return this;
        }

        public Builder context(String context){
            this.context = context;
            return this;
        }

        public Builder createDatetime(String createDatetime){
            this.createDatetime = createDatetime;
            return this;
        }

        public Builder updateDatetime(String updateDatetime){
            this.updateDatetime = updateDatetime;
            return this;
        }

        public Blog build(){
            return new Blog(this);
        }


    }

    private Blog (Builder builder)   {

        title = builder.title;

        author = builder.author;

        context = builder.context;

        createDatetime = builder.createDatetime;

        updateDatetime = builder.updateDatetime;

    }

}
