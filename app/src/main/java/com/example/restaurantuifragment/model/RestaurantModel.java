package com.example.restaurantuifragment.model;

public class RestaurantModel {
    public String restaurantName;
    public Integer restaurantStar;
    public Boolean restaurantRecommended;
    public Integer restaurantCommentCount;
    public Integer restaurantImage;

    public RestaurantModel(String restaurantName, Integer restaurantStar, Boolean restaurantRecommended, Integer restaurantCommentCount, Integer restaurantImage) {
        this.restaurantName = restaurantName;
        this.restaurantStar = restaurantStar;
        this.restaurantRecommended = restaurantRecommended;
        this.restaurantCommentCount = restaurantCommentCount;
        this.restaurantImage = restaurantImage;
    }
}
