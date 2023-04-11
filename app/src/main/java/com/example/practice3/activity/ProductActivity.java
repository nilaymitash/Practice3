package com.example.practice3.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.practice3.R;
import com.example.practice3.model.Product;
import com.example.practice3.util.Utility;

public class ProductActivity extends AppCompatActivity {

    private TextView mLogoutLink;
    private Button mBackButton;
    private TextView mProductDescriptionLabel;
    private TextView mProductTitleLabel;
    private TextView mProductPriceLabel;
    private ImageView mProductImage;
    private RatingBar mProductRatingBar;
    private TextView mNumOfRatings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        mLogoutLink = findViewById(R.id.logout_link);
        mBackButton = findViewById(R.id.back_to_search_btn);
        mProductTitleLabel = findViewById(R.id.product_title_label);
        mProductPriceLabel = findViewById(R.id.product_price_label);
        mProductDescriptionLabel = findViewById(R.id.product_description);
        mProductImage = findViewById(R.id.product_img_holder);
        mProductRatingBar = findViewById(R.id.product_rating_bar);
        mNumOfRatings = findViewById(R.id.num_of_ratings);

        Intent intent = getIntent();
        Product selectedProduct = (Product) intent.getSerializableExtra("selectedProduct");
        //TODO: grab search filter/sort criteria from extra

        mProductTitleLabel.setText(String.valueOf(selectedProduct.getTitle()));
        mProductImage.setImageResource(selectedProduct.getImgSrc());
        mProductPriceLabel.setText(Utility.formatCurrency(selectedProduct.getPrice()));
        mProductDescriptionLabel.setText(String.valueOf(selectedProduct.getDescription()));
        float avgRating = Utility.getAverageRating(selectedProduct.getFeedback());
        mProductRatingBar.setRating(avgRating);
        mNumOfRatings.setText("(" + String.valueOf(selectedProduct.getFeedback().size()) + ")");

        mBackButton.setOnClickListener(new ProductPageListener());
        mLogoutLink.setOnClickListener(new ProductPageListener());
    }

    private class ProductPageListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.logout_link: logout(); break;
                case R.id.back_to_search_btn: backToSearch(); break;
                default: break;
            }
        }

        private void backToSearch() {
            Intent intent = new Intent(ProductActivity.this, SearchProductActivity.class);
            //TODO: add search filter/sort criteria as extra
            // so that search page can preserve its state from before navigation.
            intent.putExtra("RETURN_FROM_PRODUCT_PAGE", true);
            startActivity(intent);
        }

        private void logout() {
            Intent intent = new Intent(ProductActivity.this, MainActivity.class);
            intent.putExtra(getResources().getString(R.string.signing_out_extra), true);
            startActivity(intent);
        }
    }
}