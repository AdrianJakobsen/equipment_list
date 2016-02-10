package com.example.adrian.equipmentlist;

import com.google.gson.Gson;

public class Equipment {
    private int e_id;           //Primærnøkkelfelt, autogenereres i databasen.
    private String type;        //Type/gruppe utstyr, f.eks. "Nettbrett". Settes til en av verdiene fra: http://kark.hin.no:8088/d3330log_backend/utstyrstyper.txt
    private String brand;       //F.eks. "Samsung"
    private String model;       //F.eks. "Galaxy Tab S 10.5"
    private String description; //En kort beskrivelse av utstyret (ved behov)
    private String it_no;       //Klistrelappene som vi setter på alt utstyr, f.eks. "IT-4111".
    private String aquired;     //Når ble utstyret kjøpt, dvs. en dato på format "dd.mm.åååå"
    private byte[] image;       //Bilde (nedskalert) av utstyret.
    private String image_url = "http://kark.hin.no/~wfa/d3330/images/IT9999.jpg";

    public Equipment() {
        this.type = "";
        this.brand = "";
        this.model = "";
        this.description = "";
        this.it_no = "";
        this.aquired = "";
        this.image = null;
        //this.image_url="http://kark.hin.no/~wfa/d3330/images/IT9999.jpg";
    }

    public Equipment(int e_id, String type, String brand, String model, String description, String it_no, String aquired, byte[] image, String image_url) {
        this.e_id = e_id;
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.description = description;
        this.it_no = it_no;
        this.aquired = aquired;
        this.image = image;
        if (!image_url.trim().equalsIgnoreCase(""))
            this.image_url = image_url;
    }

    public Equipment(String type, String brand, String model, String description, String it_no, String aquired, byte[] image, String image_url) {
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.description = description;
        this.it_no = it_no;
        this.aquired = aquired;
        this.image = image;
        if (!image_url.trim().equalsIgnoreCase(""))
            this.image_url = image_url;
    }

    public int getE_id() {
        return e_id;
    }

    public void setE_id(int e_id) {
        this.e_id = e_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIt_no() {
        return it_no;
    }

    public void setIt_no(String it_no) {
        this.it_no = it_no;
    }

    public String getAquired() {
        return aquired;
    }

    public void setAquired(String aquired) {
        this.aquired = aquired;
    }

    public String toJSONString() {
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "e_id=" + e_id +
                ", type='" + type + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", description='" + description + '\'' +
                ", it_no='" + it_no + '\'' +
                ", aquired='" + aquired + '\'' +
                ", image_url='" + image_url + '\'' +
                '}';
    }

}