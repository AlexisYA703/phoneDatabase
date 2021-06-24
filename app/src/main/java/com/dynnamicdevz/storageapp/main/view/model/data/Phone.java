package com.dynnamicdevz.storageapp.main.view.model.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Phone implements Parcelable {

    protected Phone(Parcel in) {
        phoneID = in.readInt();
        phoneModel = in.readString();
        price = in.readDouble();
    }

    public static final Creator<Phone> CREATOR = new Creator<Phone>() {
        @Override
        public Phone createFromParcel(Parcel in) {
            return new Phone(in);
        }

        @Override
        public Phone[] newArray(int size) {
            return new Phone[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(phoneID);
        dest.writeString(phoneModel);
        dest.writeDouble(price);
    }

    public enum Manufacturer {
        iPhone,
        Samsung,
        Motorola,
        Google
    }

    private int phoneID;
    private String phoneModel;
    private Manufacturer manufacturer;
    private double price;

    public Phone(String phoneModel, double price) {
        this.phoneModel = phoneModel;
        this.price = price;
    }

    public Phone(int phoneID, String phoneModel, Manufacturer manufacturer, double price) {
        this.phoneID = phoneID;
        this.phoneModel = phoneModel;
        this.manufacturer = manufacturer;
        this.price = price;
    }

    public Phone(String phoneModel, Manufacturer manufacturer, double price) {
        this.phoneModel = phoneModel;
        this.manufacturer = manufacturer;
        this.price = price;
    }

    public int getPhoneID() {
        return phoneID;
    }

    public String getPhoneModel() {
        return phoneModel;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public double getPrice() {
        return price;
    }
}
