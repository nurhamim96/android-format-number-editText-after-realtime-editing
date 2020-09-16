package com.tutorial.examplecurrency;

import android.annotation.TargetApi;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class MoneyTextWatcher implements TextWatcher {
    private final WeakReference<EditText> editTextWeakReference;
    private String current = "";
    public MoneyTextWatcher(EditText editText) {
        editTextWeakReference = new WeakReference<EditText>(editText);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        EditText editText = editTextWeakReference.get();

        if (!editable.toString().equals(current)) {
            editText.removeTextChangedListener(this);
            Locale locale = new Locale("id", "id");
            String replaceable = String.format("[Rp,.\\s]", NumberFormat.getCurrencyInstance().getCurrency().getSymbol(locale));
            String cleanString = editable.toString().replaceAll(replaceable, "");
            double parsed;
            try {
                parsed = Double.parseDouble(cleanString);
            } catch (NumberFormatException ex) {
                parsed = 0.00;
            }

            NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
            formatter.setMaximumFractionDigits(0);
            formatter.setParseIntegerOnly(true);
            String formatted = formatter.format(parsed);
            String replace = String.format("[Rp\\s]", NumberFormat.getCurrencyInstance().getCurrency().getSymbol(locale));
            String clean = formatted.replaceAll(replace, "");
            current = formatted;
            editText.setText(clean);
            editText.setSelection(clean.length());
            editText.addTextChangedListener(this);
        }

    }
}
