package com.xavierluz.servicoonline.bibliotecas;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.xavierluz.servicoonline.FechamentoAdapter;

import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class EditTextDecimalBrasil implements TextWatcher {
    private WeakReference<EditText> editTextWeakReference = null;
    private Locale locale = null;
    public EditTextDecimalBrasil(EditText editText, Locale locale) {
        this.editTextWeakReference = new WeakReference<EditText>(editText);
        this.locale = locale != null ? locale : Locale.getDefault();
    }

    public EditTextDecimalBrasil(EditText editText) {
        this.editTextWeakReference = new WeakReference<EditText>(editText);
        this.locale = Locale.getDefault();
    }
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        EditText editText = editTextWeakReference.get();
        if (editText == null) return;
        editText.removeTextChangedListener(this);

        BigDecimal parsed = FechamentoAdapter.formatBigDecimalSemTipoMoeda(editable.toString());
        String formatted = NumberFormat.getNumberInstance(locale).format(parsed);

        editText.setText(formatted);
        editText.setSelection(formatted.length());
        editText.addTextChangedListener(this);
    }
    private BigDecimal parseToBigDecimal(String value, Locale locale) {
        //String replaceable = String.format("[%s,.\\s]", NumberFormat.getNumberInstance(locale).getCurrency().getSymbol());
        String replaceable = NumberFormat.getCurrencyInstance(locale).format(value);//.replace("R","").replace("$","");
        String cleanString = value.replaceAll(replaceable, "");

        return new BigDecimal(cleanString).setScale(
                2, BigDecimal.ROUND_FLOOR).divide(new BigDecimal(100), BigDecimal.ROUND_HALF_EVEN
        );
    }
}
