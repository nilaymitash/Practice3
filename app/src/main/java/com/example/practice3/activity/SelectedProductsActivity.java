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
    private ArrayList<Product> selectedProducts = new ArrayList<>();

    private final String CURRENT_STATE = "Current State: ";
    private final String CALLED_WHEN = "Called When: ";

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
        this.selectedProducts = (ArrayList<Product>) intent.getSerializableExtra(getResources().getString(R.string.product_list_extra));
        initializeListView();
    }

    private void initializeListView() {
        SelectedProductListAdapter adapter = new SelectedProductListAdapter(SelectedProductsActivity.this, this.selectedProducts);
        mSelectedProductsListView.setAdapter(adapter);
    }

    private class ProductPageListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.logout_link: logout(); break;
                case R.id.back_to_all_products: backToAllProducts(); break;
                case R.id.send_email_btn: sendEmail(); break;
                default: break;
            }
        }

        private void backToAllProducts() {
            Intent intent = new Intent(SelectedProductsActivity.this, ProductListActivity.class);
            startActivity(intent);
        }

        private void logout() {
            Intent intent = new Intent(SelectedProductsActivity.this, MainActivity.class);
            intent.putExtra(getResources().getString(R.string.signing_out_extra), true);
            startActivity(intent);
        }

        private void sendEmail() {
            String subject = "Neil Mitash Practice III";
            String[] email = { "sweng888mobileapps@gmail.com" };
            StringBuilder messageBuilder = new StringBuilder();
            messageBuilder.append("Here are the item details you asked for...\n\n");
            for (Product product : selectedProducts) {
                messageBuilder.append(product.getTitle()).append(" - $")
                        .append(product.getPrice().floatValue()).append("\n\n");
            }

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("*/*");
            intent.putExtra(Intent.EXTRA_EMAIL, email);
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
            intent.putExtra(Intent.EXTRA_TEXT, messageBuilder.toString());

            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
                selectedProducts = new ArrayList<>();
                initializeListView();
            }
        }
    }
}