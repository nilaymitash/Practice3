package com.example.practice3.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practice3.R;
import com.example.practice3.config.AppDatabaseHelper;
import com.example.practice3.dao.ProductMaintenanceDao;
import com.example.practice3.model.Product;
import com.example.practice3.util.ProductListAdapter;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class ProductListActivity extends AppCompatActivity {

    private RecyclerView mProductRecyclerView;
    private TextView mLogoutLink;
    private AppDatabaseHelper databaseHelper;
    private LinearLayout linearLayout;
    private Button mNextButton;
    private ProductListAdapter adapter;
    private LinearLayout mBottomLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        mLogoutLink = findViewById(R.id.logout_link);
        mProductRecyclerView = findViewById(R.id.product_recyclerView);
        linearLayout = findViewById(R.id.search_product_layout);
        mNextButton = findViewById(R.id.button_to_next_activity);
        mBottomLayout = findViewById(R.id.layout_horizontal);

        databaseHelper = new AppDatabaseHelper(this);
        ProductMaintenanceDao dao = new ProductMaintenanceDao(databaseHelper);

        mLogoutLink.setOnClickListener(new ProductPageClickListener());
        mNextButton.setOnClickListener(new ProductPageClickListener());

        if(dao.isDatabaseEmpty()) {
            dao.populateDummyProducts(ProductListActivity.this);
        }

        List<Product> allProducts = dao.getAllProducts();
        adapter = new ProductListAdapter(allProducts);
        mProductRecyclerView.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();

        boolean backFromProductPage = getIntent().getBooleanExtra("RETURN_FROM_PRODUCT_PAGE", false);

        if (backFromProductPage) {
            Snackbar.make(linearLayout, "Welcome back to the product page!", Snackbar.LENGTH_LONG).show();
        }
    }

    class ProductPageClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.logout_link: logout(); break;
                case R.id.button_to_next_activity: nextActivity(); break;
                default: break;
            }
        }

        private void logout() {
            Intent intent = new Intent(ProductListActivity.this, MainActivity.class);
            intent.putExtra(getResources().getString(R.string.signing_out_extra), true);
            startActivity(intent);
        }

        private void nextActivity() {
            ArrayList<Product> selectedProducts = adapter.getSelectedProducts();
            if(selectedProducts.isEmpty()) {
                Snackbar.make(mBottomLayout, "Please select a product.", Snackbar.LENGTH_SHORT).show();
            } else {
                //load next intent with selected products;
                Intent intent = new Intent(ProductListActivity.this, SelectedProductsActivity.class);
                intent.putExtra(getResources().getString(R.string.product_list_extra), selectedProducts);
                startActivity(intent);
            }
        }
    }
}