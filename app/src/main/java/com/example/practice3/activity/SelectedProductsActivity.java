package com.example.practice3.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.practice3.R;
import com.example.practice3.model.Product;
import com.example.practice3.util.SelectedProductListAdapter;

import java.util.ArrayList;

public class SelectedProductsActivity extends AppCompatActivity {

    private TextView mLogoutLink;
    private Button mBackButton;
    private ListView mSelectedProductsListView;
    private Button mSendEmailBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_products);

        mLogoutLink = findViewById(R.id.logout_link);
        mBackButton = findViewById(R.id.back_to_all_products);
        mSelectedProductsListView = findViewById(R.id.selected_products_listView);
        mSendEmailBtn = findViewById(R.id.send_email_btn);

        mBackButton.setOnClickListener(new ProductPageListener());
        mLogoutLink.setOnClickListener(new ProductPageListener());
        mSendEmailBtn.setOnClickListener(new ProductPageListener()); //TODO: Add send email logic

        Intent intent = getIntent();
        ArrayList<Product> selectedProducts = (ArrayList<Product>) intent.getSerializableExtra(getResources().getString(R.string.product_list_extra));
        SelectedProductListAdapter adapter = new SelectedProductListAdapter(SelectedProductsActivity.this, selectedProducts);
        mSelectedProductsListView.setAdapter(adapter);
    }

    private class ProductPageListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.logout_link: logout(); break;
                case R.id.back_to_all_products: backToAllProducts(); break;
                default: break;
            }
        }

        private void backToAllProducts() {
            Intent intent = new Intent(SelectedProductsActivity.this, ProductListActivity.class);
            //intent.putExtra("RETURN_FROM_PRODUCT_PAGE", true);
            startActivity(intent);
        }

        private void logout() {
            Intent intent = new Intent(SelectedProductsActivity.this, MainActivity.class);
            intent.putExtra(getResources().getString(R.string.signing_out_extra), true);
            startActivity(intent);
        }
    }
}