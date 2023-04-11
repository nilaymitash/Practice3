package com.example.practice3.util;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practice3.R;
import com.example.practice3.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {

    private List<Product> products;
    public Map<String, Product> selectedProducts = new HashMap();

    public ProductListAdapter(List<Product> products) {
        this.products = products;
    }

    public List<Product> getSelectedProducts() {
        return new ArrayList<>(this.selectedProducts.values());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list_item,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = products.get(position);

        holder.mProductImage.setImageResource(product.getImgSrc());
        holder.mTextViewName.setText(String.valueOf(product.getTitle()));
        holder.mTextViewPrice.setText(Utility.formatCurrency(product.getPrice()));
        holder.mTextViewDescription.setText(Utility.truncateString(product.getDescription(), 160));
        holder.mProductRating.setText(String.valueOf(product.getRating()));
        holder.mProductRatingBar.setRating(product.getRating());
        holder.mNumOfRatings.setText("(" + product.getNumOfRatings() + ")");
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public CheckBox mSelectItemCheckBox;
        public TextView mTextViewName;
        public TextView mTextViewPrice;
        public TextView mTextViewDescription;
        public ImageView mProductImage;
        public RatingBar mProductRatingBar;
        public TextView mProductRating;
        public TextView mNumOfRatings;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextViewName = itemView.findViewById(R.id.product_title);
            mTextViewPrice = itemView.findViewById(R.id.product_price);
            mTextViewDescription = itemView.findViewById(R.id.product_description);
            mProductImage = itemView.findViewById(R.id.product_img_holder);
            mProductRatingBar = itemView.findViewById(R.id.product_rating_bar);
            mProductRating = itemView.findViewById(R.id.product_rating);
            mNumOfRatings = itemView.findViewById(R.id.num_of_ratings);
            mSelectItemCheckBox = itemView.findViewById(R.id.select_item);

            mSelectItemCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    int position = getLayoutPosition();
                    Product product = products.get(position);
                    if(b) {
                        //if selected, add to the list
                        selectedProducts.put(product.getTitle(), product);
                    } else {
                        //if unselected, remove from the list
                        selectedProducts.remove(product.getTitle());
                    }
                }
            });
        }
    }
}
