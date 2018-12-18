package com.xavierluz.servicoonline.bibliotecas;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Locale;

public class BrPorcetagemTextWatcher implements TextWatcher {
    public static final Locale DEFAULT_LOCALE = new Locale("pt", "BR");

    public static DecimalFormat NUMBER_FORMAT = (DecimalFormat) DecimalFormat.getPercentInstance(DEFAULT_LOCALE);

    public static class Helper {
        public static String formatNumber(String originalNumber) {
            String number = originalNumber.replaceAll("[^\\d]", "");
            BigDecimal value = new BigDecimal(number).movePointLeft(FRACTION_DIGITS);
            return NUMBER_FORMAT.format(value);
        }
    }

    public static final int FRACTION_DIGITS = 2;

    public static final String DECIMAL_SEPARATOR;

    //public static final String CURRENCY_SYMBOL;

    static {
        NUMBER_FORMAT.setMaximumFractionDigits(FRACTION_DIGITS);
        NUMBER_FORMAT.setMaximumFractionDigits(FRACTION_DIGITS);
        NUMBER_FORMAT.setParseBigDecimal(true);
        DECIMAL_SEPARATOR = String.valueOf(NUMBER_FORMAT.getDecimalFormatSymbols().getDecimalSeparator());
        //CURRENCY_SYMBOL = NUMBER_FORMAT.getCurrency().getSymbol(DEFAULT_LOCALE);
        NUMBER_FORMAT.setMultiplier(1);
    }

    final EditText target;

    public BrPorcetagemTextWatcher(EditText target) {
        target.removeTextChangedListener(this);
        this.target = target;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.length() != 0) {
            target.removeTextChangedListener(this);
            String valueStr = BrPorcetagemTextWatcher.Helper.formatNumber(s.toString());
            target.setText(valueStr);
            if(valueStr.length() > 0)
                target.setSelection(valueStr.length());

            target.addTextChangedListener(this);
        }
    }
}
