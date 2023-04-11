package com.example.practice3.util;

import com.example.practice3.model.Feedback;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;

public class Utility {

    public static String truncateString(String originalStr, int numOfChars) {
        return originalStr.substring(0, Math.min(originalStr.length(), (numOfChars - 3))) + "...";
    }

    public static String formatCurrency(BigDecimal amount) {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return currency.format(amount);
    }

    public static float getAverageRating(List<Feedback> feedbackList) {
        float totalRating = 0;
        for(int i = 0; i < feedbackList.size(); i++) {
            totalRating += feedbackList.get(i).getRating();
        }
        return (totalRating/feedbackList.size());
     }
}
