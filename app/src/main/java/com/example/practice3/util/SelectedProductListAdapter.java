package com.example.practice3.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.practice3.model.Product;

import com.example.practice3.R;

import java.util.ArrayList;

public class SelectedProductListAdapter extends ArrayAdapter<Product> {

    public SelectedProductListAdapter(@NonNull Context context, @NonNull ArrayList<Product> products) {
        super(context, R.layout.selected_product_list_item, products);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.selected_product_list_item, parent, false);
        }

        Product product = getItem(position);
        ImageView mProductImage = convertView.findViewById(R.id.product_img_holder);
        TextView mTextViewName = convertView.findViewById(R.id.product_title);
        TextView mTextViewPrice = convertView.findViewById(R.id.product_price);

        mProductImage.setImageResource(product.getImgSrc());
        mTextViewName.setText(String.valueOf(product.getTitle()));
        mTextViewPrice.setText(Utility.formatCurrency(product.getPrice()));

        return convertView;
    }
}
