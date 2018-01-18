package com.example.demosqlite;

/**
 * Created by sooryen on 1/17/18.
 */

public class Products {

    private int _id;
    private String _productName;

    //default empty constructor
    public  Products() {

    }

    //constructor
    public Products(String productName) {
        this._productName = productName;
    }

    //Getter and setter
    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_productName() {
        return _productName;
    }

    public void set_productName(String _productName) {
        this._productName = _productName;
    }
}
