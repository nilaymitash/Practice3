package com.example.practice3.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.practice3.R;
import com.example.practice3.model.Feedback;
import com.example.practice3.model.Product;
import com.example.practice3.model.ProductListAdapter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SearchProductActivity extends AppCompatActivity {

    private ListView mProductListView;
    private TextView mLogoutLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_product);

        mLogoutLink = findViewById(R.id.logout_link);
        mProductListView = findViewById(R.id.product_listview);

        mLogoutLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchProductActivity.this, MainActivity.class);
                intent.putExtra(getResources().getString(R.string.signing_out_extra), true);
                startActivity(intent);
            }
        });

        ProductListAdapter adapter = new ProductListAdapter(SearchProductActivity.this, populateDemoProductList());
        mProductListView.setAdapter(adapter);
        mProductListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Product selectedProduct = (Product) adapterView.getItemAtPosition(position);
                Intent intent = new Intent(SearchProductActivity.this, ProductActivity.class);
                intent.putExtra("selectedProduct", selectedProduct);
                //TODO: add search filter/sort criteria as extra
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        boolean backFromProductPage = getIntent().getBooleanExtra("RETURN_FROM_PRODUCT_PAGE", false);

        if (backFromProductPage) {
            Toast.makeText(this, "Welcome back to the product page!", Toast.LENGTH_SHORT).show();
        }
    }

    private ArrayList<Product> populateDemoProductList() {
        ArrayList<Product> productList = new ArrayList<>();

        Product p1 = new Product();
        p1.setTitle(getResources().getString(R.string.wings_name));
        p1.setPrice(new BigDecimal("4.69"));
        p1.setDescription(getResources().getString(R.string.wings_description));
        p1.setImgSrc(R.drawable.wings);
        p1.setFeedback(populateFeedbackList(114, 3.5F));

        Product p2 = new Product();
        p2.setTitle(getResources().getString(R.string.pb_name));
        p2.setPrice(new BigDecimal("4.49"));
        p2.setDescription(getResources().getString(R.string.pb_description));
        p2.setImgSrc(R.drawable.pb);
        p2.setFeedback(populateFeedbackList(76, 4.5F));

        Product p3 = new Product();
        p3.setTitle(getResources().getString(R.string.pickled_pups_name));
        p3.setPrice(new BigDecimal("4.99"));
        p3.setDescription(getResources().getString(R.string.pickled_pups_description));
        p3.setImgSrc(R.drawable.pups);
        p3.setFeedback(populateFeedbackList(34, 5.0F));

        Product p4 = new Product();
        p4.setTitle(getResources().getString(R.string.sb_preserve_name));
        p4.setPrice(new BigDecimal("3.69"));
        p4.setDescription(getResources().getString(R.string.sb_preserve_description));
        p4.setImgSrc(R.drawable.strawberry);
        p4.setFeedback(populateFeedbackList(1123, 4.0F));

        productList.add(p1);
        productList.add(p2);
        productList.add(p3);
        productList.add(p4);
        return productList;
    }

    private List<Feedback> populateFeedbackList(int numOfRatings, float avgRating) {
        List<Feedback> list = new ArrayList<>();

        for(int i = 0; i < numOfRatings; i++) {
            Feedback feedback = new Feedback();
            feedback.setRating(avgRating);
            list.add(feedback);
        }

        return list;
    }
}