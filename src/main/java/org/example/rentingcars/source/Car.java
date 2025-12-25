package org.example.rentingcars.source;

public class Car {
    private Integer id_car;
    private String type;
    private String city;
    private String mark;
    private String model;
    private Double price;
    private String file_main_view;
    private String file_back_view;
    private String file_left_view;
    private String file_into_view;

    public Integer getId_car() {
        return id_car;
    }

    public void setId_car(Integer id_car) {
        this.id_car = id_car;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getFile_main_view() {
        return file_main_view;
    }

    public void setFile_main_view(String file_main_view) {
        this.file_main_view = file_main_view;
    }

    public String getFile_back_view() {
        return file_back_view;
    }

    public void setFile_back_view(String file_back_view) {
        this.file_back_view = file_back_view;
    }

    public String getFile_left_view() {
        return file_left_view;
    }

    public void setFile_left_view(String file_left_view) {
        this.file_left_view = file_left_view;
    }

    public String getFile_into_view() {
        return file_into_view;
    }

    public void setFile_into_view(String file_into_view) {
        this.file_into_view = file_into_view;
    }

    public String getImageUrl() {
        return "/uploads/" + file_main_view;
    }

    public String getImageUrl2() {
        return "/uploads/" + file_back_view;
    }

    public String getImageUrl3() {
        return "/uploads/" + file_left_view;
    }

    public String getImageUrl4() {
        return "/uploads/" + file_into_view;
    }
}

