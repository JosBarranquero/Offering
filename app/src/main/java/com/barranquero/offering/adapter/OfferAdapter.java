package com.barranquero.offering.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.barranquero.offering.R;
import com.barranquero.offering.model.Offer;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter which will show the information stored in the Repository
 *
 * @author José Antonio Barranquero
 * @version 1.0
 * @see com.barranquero.offering.Repository
 */
public class OfferAdapter extends ArrayAdapter<Offer> {
    private Context myContext;
    private List<Offer> myList;
    private boolean[] myOptions;
    public static final int ORDER_ASC = 0;
    public static final int ORDER_DESC = 1;
    public static final int ORDER_TYPE = 2;

    public OfferAdapter(Context context, List<Offer> list, boolean[] options) {
        super(context, R.layout.item_offer, list);
        myContext = context;
        myOptions = options;
        myList = list;

    }

    public static List<Offer> filter(List<Offer> list, boolean[] options) {
        List<Offer> filteredList = new ArrayList<>();
        for (Offer myOffer :
                list) {
            if ((options[0] && myOffer.getType() == Offer.TYPE_HOME)
                    || (options[1] && myOffer.getType() == Offer.TYPE_ELECTRO)
                    || (options[2] && myOffer.getType() == Offer.TYPE_SPORT))
                filteredList.add(myOffer);
        }
        return filteredList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rootView = convertView;
        OfferHolder myHolder;

        if (rootView == null) {
            rootView = View.inflate(myContext, R.layout.item_offer, null);
            myHolder = new OfferHolder();

            myHolder.imgType = (ImageView) rootView.findViewById(R.id.imgOfferType);
            myHolder.txvName = (TextView) rootView.findViewById(R.id.txvOfferName);
            myHolder.txvDate = (TextView) rootView.findViewById(R.id.txvOfferDate);
            myHolder.txvShop = (TextView) rootView.findViewById(R.id.txvOfferShop);

            rootView.setTag(myHolder);

        } else {
            myHolder = (OfferHolder) rootView.getTag();
        }


        switch (myList.get(position).getType()) {
            case Offer.TYPE_HOME:
                myHolder.imgType.setImageResource(R.mipmap.ic_home);
                break;
            case Offer.TYPE_ELECTRO:
                myHolder.imgType.setImageResource(R.mipmap.ic_mobile);
                break;
            case Offer.TYPE_SPORT:
                myHolder.imgType.setImageResource(R.mipmap.ic_sports);
                break;
        }

        myHolder.txvName.setText(myList.get(position).getName());
        myHolder.txvDate.setText(myList.get(position).getDate());
        myHolder.txvShop.setText(myList.get(position).getShop());

        if (myOptions[3]) {
            switch (myList.get(position).getImportance()) {
                case Offer.IMP_ALOT:
                    rootView.setBackground(myContext.getDrawable(R.drawable.shape_veryimp));
                    break;
                case Offer.IMP_REGULAR:
                    rootView.setBackground(myContext.getDrawable(R.drawable.shape_imp));
                    break;
                case Offer.IMP_NOT:
                    rootView.setBackground(myContext.getDrawable(R.drawable.shape_notimp));
                    break;
            }
        }

        return rootView;
    }

    public void orderList(int order) {
        List<Offer> orderedList = new ArrayList<>();
        char[] alpha = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        int[] types = new int[]{Offer.TYPE_ELECTRO, Offer.TYPE_SPORT, Offer.TYPE_HOME};
        switch (order) {
            case ORDER_ASC:
                for (char myLetter :
                        alpha) {
                    for (Offer myOffer :
                            myList) {
                        if (myOffer.getName().toLowerCase().startsWith(Character.toString(myLetter)))
                            orderedList.add(myOffer);
                    }
                }
                myList = orderedList;
                notifyDataSetChanged();
                break;
            case ORDER_DESC:
                for (int i = alpha.length - 1; i >= 0; i--) {
                    for (Offer myOffer :
                            myList) {
                        if (myOffer.getName().toLowerCase().startsWith(Character.toString(alpha[i])))
                            orderedList.add(myOffer);
                    }
                }
                myList = orderedList;
                notifyDataSetChanged();
                break;
            case ORDER_TYPE:
                for (int i = 0; i < types.length; i++) {
                    for (Offer myOffer :
                            myList) {
                        if (myOffer.getType() == i)
                            orderedList.add(myOffer);
                    }
                }
                myList = orderedList;
                notifyDataSetChanged();
                break;
        }
    }

    class OfferHolder {
        ImageView imgType;
        TextView txvName, txvShop, txvDate;
    }
}
