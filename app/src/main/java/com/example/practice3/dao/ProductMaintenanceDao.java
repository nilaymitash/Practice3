package com.example.practice3.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.example.practice3.R;
import com.example.practice3.config.AppDatabaseHelper;
import com.example.practice3.model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductMaintenanceDao {

    private AppDatabaseHelper databaseHelper;
    private static final String TABLE_NAME = "product";
    private static final String PK_SKU = "sku";
    private static final String TITLE = "title";
    private static final String IMG = "image";
    private static final String PRICE = "price";
    private static final String DESC = "description";
    private static final String AVG_RATING = "avg_rating";
    private static final String NUM_OF_RATING = "num_of_rating";

    public ProductMaintenanceDao(AppDatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    /**
     * Returns a String representing the create query for Product table
     * @return
     */
    public static String getProductTableCreateQuery() {
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE ")
                .append(TABLE_NAME).append("(")
                .append(PK_SKU).append(" INTEGER PRIMARY KEY, ")
                .append(TITLE).append(" TEXT, ")
                .append(IMG).append(" INT, ")
                .append(PRICE).append(" REAL, ")
                .append(DESC).append(" TEXT, ")
                .append(AVG_RATING).append(" REAL, ")
                .append(NUM_OF_RATING).append(" INTEGER ")
                .append(")");

        return builder.toString();
    }

    public void saveProduct(Product product) {
        SQLiteDatabase db = this.databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TITLE, product.getTitle());
        values.put(IMG, product.getImgSrc());
        values.put(PRICE, product.getPrice().floatValue());
        values.put(DESC, product.getDescription());
        values.put(AVG_RATING, product.getRating());
        values.put(NUM_OF_RATING, product.getNumOfRatings());
        db.insert(TABLE_NAME, null, values);
    }

    public ArrayList<Product> getAllProducts(){
        ArrayList<Product> productList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase database = this.databaseHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {
                Product product = new Product();
                product.setTitle(cursor.getString(1));
                product.setImgSrc(cursor.getInt(2));
                product.setPrice(new BigDecimal(cursor.getFloat(3)));
                product.setDescription(cursor.getString(4));
                product.setRating(cursor.getFloat(5));
                product.setNumOfRatings(cursor.getInt(6));
                productList.add(product);
            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();
        return productList;
    }

    public boolean isDatabaseEmpty() {
        boolean isEmpty = true;
        SQLiteDatabase database = this.databaseHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT COUNT(*) FROM " + TABLE_NAME, null);
        if (cursor != null) {
            cursor.moveToFirst();
            int count = cursor.getInt(0);
            if (count > 0) {
                isEmpty = false;
            }
            cursor.close();
        }
        return isEmpty;
    }

    public void populateDummyProducts(AppCompatActivity activity) {
        SQLiteDatabase database = this.databaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TITLE, activity.getResources().getString(R.string.wings_name));
        values.put(IMG, R.drawable.wings);
        values.put(PRICE, 9.99);
        values.put(DESC, activity.getResources().getString(R.string.wings_description));
        values.put(AVG_RATING, 3.5);
        values.put(NUM_OF_RATING, 1123);
        database.insert(TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(TITLE, activity.getResources().getString(R.string.pb_name));
        values.put(IMG, R.drawable.pb);
        values.put(PRICE, 4.99);
        values.put(DESC, activity.getResources().getString(R.string.pb_description));
        values.put(AVG_RATING, 4.5);
        values.put(NUM_OF_RATING, 104);
        database.insert(TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(TITLE, activity.getResources().getString(R.string.pickled_pups_name));
        values.put(IMG, R.drawable.pups);
        values.put(PRICE, 4.99);
        values.put(DESC, activity.getResources().getString(R.string.pickled_pups_description));
        values.put(AVG_RATING, 5.0);
        values.put(NUM_OF_RATING, 34);
        database.insert(TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(TITLE, activity.getResources().getString(R.string.sb_preserve_name));
        values.put(IMG, R.drawable.strawberry);
        values.put(PRICE, 3.69);
        values.put(DESC, activity.getResources().getString(R.string.sb_preserve_description));
        values.put(AVG_RATING, 4.0);
        values.put(NUM_OF_RATING, 456);
        database.insert(TABLE_NAME, null, values);

        database.close();
    }
}
