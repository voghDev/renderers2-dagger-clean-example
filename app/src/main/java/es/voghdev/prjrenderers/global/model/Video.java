package es.voghdev.prjrenderers.global.model;
/*
 * Copyright (C) 2015 Olmo Gallegos Hernández.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Video {
    private int id;
    private String title;
    private String description;
    private String imageUrl;
    private String url;
    private boolean favorite;
    private List<Customer> customersWhoLiked;

    protected Video(Builder builder){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isFavorite() {  return favorite; }

    public void setFavorite(boolean favorite) {  this.favorite = favorite;  }

    public List<Customer> getCustomersWhoLiked() {
        return customersWhoLiked;
    }

    public void setCustomersWhoLiked(List<Customer> customersWhoLiked) {
        this.customersWhoLiked = customersWhoLiked;
    }

    public void addCustomer(Customer customer) {
        if(this.customersWhoLiked == null)
            customersWhoLiked = new ArrayList<Customer>();

        this.customersWhoLiked.add(customer);
    }

    public static class Builder{
        private String title="";
        private String description="";
        private String imageUrl="";
        private String url="";
        private List<Customer> customers
                = new ArrayList<Customer>();

        public Builder(){

        }

        public Builder setTitle(String title){
            this.title = title;
            return this;
        }

        public Builder setDescription(String description){
            this.description = description;
            return this;
        }

        public Builder setImageUrl(String imageUrl){
            this.imageUrl = imageUrl;
            return this;
        }

        public Builder setUrl(String url){
            this.url = url;
            return this;
        }

        public Builder setCustomers(List<Customer> customers){
            this.customers = customers;
            return this;
        }

        public Video build(){
            Video video = new Video(this);

            video.setTitle(title);
            video.setDescription(description);
            video.setImageUrl(imageUrl);
            video.setUrl(url);
            video.setCustomersWhoLiked(customers);

            return video;
        }
    }
}
