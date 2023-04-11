package com.example.practice3.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practice3.R;
import com.example.practice3.config.AppDatabaseHelper;
import com.example.practice3.dao.ProductMaintenanceDao;
import com.example.practice3.model.Feedback;
import com.example.practice3.model.Product;
import com.example.practice3.model.ProductListAdapter;
import com.google.android.material.snackbar.Snackbar;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SearchProductActivity extends AppCompatActivity {

    private RecyclerView mProductRecyclerView;
    private TextView mLogoutLink;
    private AppDatabaseHelper databaseHelper;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_product);

        mLogoutLink = findViewById(R.id.logout_link);
        mProductRecyclerView = findViewById(R.id.product_recyclerView);
        linearLayout = findViewById(R.id.search_product_layout);

        databaseHelper = new AppDatabaseHelper(this);
        ProductMaintenanceDao dao = new ProductMaintenanceDao(databaseHelper);

        mLogoutLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchProductActivity.this, MainActivity.class);
                intent.putExtra(getResources().getString(R.string.signing_out_extra), true);
                startActivity(intent);
            }
        });

        if(dao.isDatabaseEmpty()) {
            dao.populateDummyProducts(SearchProductActivity.this);
        }

        List<Product> products = dao.getAllProducts();

        ProductListAdapter adapter = new ProductListAdapter(products);
        mProductRecyclerView.setAdapter(adapter);


        /*mProductRecyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Product selectedProduct = (Product) adapterView.getItemAtPosition(position);
                Intent intent = new Intent(SearchProductActivity.this, ProductActivity.class);
                intent.putExtra("selectedProduct", selectedProduct);
                //TODO: add search filter/sort criteria as extra
                startActivity(intent);
            }
        });*/
    }

    @Override
    protected void onResume() {
        super.onResume();

        boolean backFromProductPage = getIntent().getBooleanExtra("RETURN_FROM_PRODUCT_PAGE", false);

        if (backFromProductPage) {
            Snackbar.make(linearLayout, "Welcome back to the product page!", Snackbar.LENGTH_LONG).show();
        }
    }


}