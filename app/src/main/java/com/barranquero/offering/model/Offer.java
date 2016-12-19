package com.barranquero.offering.model;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Model class which describes an offer
 *
 * @author José Antonio Barranquero Fernández ; 14/12/2016
 * @version 1.0
 */
public class Offer {
    private String name, shop;
    private String date;
    private @Type int type;
    private @Importance int importance;

    @Retention(RetentionPolicy.SOURCE)
    // When the annotation is going to be removed, in this case in execution time
    @IntDef({TYPE_HOME, TYPE_ELECTRO, TYPE_SPORT})
    public @interface Type {}
    public static final int TYPE_HOME = 0;
    public static final int TYPE_ELECTRO = 1;
    public static final int TYPE_SPORT = 2;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({IMP_ALOT, IMP_REGULAR, IMP_NOT})
    public @interface Importance {}
    public static final int IMP_ALOT = 0;
    public static final int IMP_REGULAR = 1;
    public static final int IMP_NOT = 2;

    public Offer(String name, String shop, String date, @Type int type, @Importance int importance) {
        this.name = name;
        this.shop = shop;
        this.date = date;
        this.type = type;
        this.importance = importance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getType() {
        return type;
    }

    public void setType(@Type int type) {
        this.type = type;
    }

    public int getImportance() {
        return importance;
    }

    public void setImportance(@Importance int importance) {
        this.importance = importance;
    }
}
