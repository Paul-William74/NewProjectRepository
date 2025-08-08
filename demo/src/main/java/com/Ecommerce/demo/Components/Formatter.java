package com.Ecommerce.demo.Components;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.text.NumberFormat;
import java.util.Locale;

@RequiredArgsConstructor
@Component
public final class Formatter {

    private final Locale southAfrica;

    public String getFormattedPrice(Double price) {
        if(price==null)
            return "";
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(southAfrica);
        return currencyFormatter.format(price);
    }
}
