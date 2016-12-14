package com.barranquero.offering;

import android.content.Context;

import com.barranquero.offering.model.Offer;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton which stores the offers information
 */
public class Repository {
    private static Repository myRepository;
    private Context myContext;
    private List<Offer> list;

    private Repository(Context context) {
        myContext = context;
        list = new ArrayList<>();
        addOffer(new Offer(myContext.getString(R.string.bike), context.getString(R.string.decathlon), "01/01/1970", Offer.TYPE_SPORT, Offer.IMP_NOT));
        addOffer(new Offer(myContext.getString(R.string.tracksuit), context.getString(R.string.adidas), "01/01/1970", Offer.TYPE_SPORT, Offer.IMP_REGULAR));
        addOffer(new Offer(myContext.getString(R.string.mirror), context.getString(R.string.media_markt), "01/01/1970", Offer.TYPE_HOME, Offer.IMP_NOT));
        addOffer(new Offer(myContext.getString(R.string.phone), context.getString(R.string.amazon), "01/01/1970", Offer.TYPE_ELECTRO, Offer.IMP_NOT));
        addOffer(new Offer(myContext.getString(R.string.computer), context.getString(R.string.pc_box), "01/01/1970", Offer.TYPE_ELECTRO, Offer.IMP_ALOT));
        addOffer(new Offer(myContext.getString(R.string.laptop), context.getString(R.string.pc_components), "01/01/1970", Offer.TYPE_ELECTRO, Offer.IMP_REGULAR));
        addOffer(new Offer(myContext.getString(R.string.sofa), context.getString(R.string.ikea), "01/01/1970", Offer.TYPE_HOME, Offer.IMP_ALOT));
        addOffer(new Offer(myContext.getString(R.string.dishes), context.getString(R.string.ikea), "01/01/1970", Offer.TYPE_HOME, Offer.IMP_REGULAR));

    }

    public static Repository getInstance(Context context) {
        if (myRepository == null) {
            myRepository = new Repository(context);
        }
        return myRepository;
    }

    public boolean addOffer(Offer offer) {
        return list.add(offer);
    }

    public List<Offer> getList() {
        return list;
    }
}
