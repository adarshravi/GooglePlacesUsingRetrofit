package app.pankaj.googleplacesusingretrofit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



public class PlacePicture {

    @SerializedName("height")
    @Expose
    int height;
    @SerializedName("width")
    @Expose
    int width;
    @SerializedName("photo_reference")
    @Expose
    String photo_reference;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getPhoto_reference() {
        return photo_reference;
    }

    public void setPhoto_reference(String photo_reference) {
        this.photo_reference = photo_reference;
    }
}
