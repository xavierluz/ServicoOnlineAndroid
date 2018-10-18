package com.xavierluz.servicoonline;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MascaraMonetaria implements TextWatcher {
    final EditText editCampo;
    private boolean isUpdating = false;
    // Pega a formatacao do sistema, se for brasil R$ se EUA US$
    private NumberFormat nf = NumberFormat.getCurrencyInstance();

    public MascaraMonetaria(EditText editCampo) {
        super();
        this.editCampo = editCampo;
    }

    /**
     * This method is called to notify you that, within <code>s</code>,
     * the <code>count</code> characters beginning at <code>start</code>
     * are about to be replaced by new text with length <code>after</code>.
     * It is an error to attempt to make changes to <code>s</code> from
     * this callback.
     *
     * @param s
     * @param start
     * @param count
     * @param after
     */
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    /**
     * This method is called to notify you that, within <code>s</code>,
     * the <code>count</code> characters beginning at <code>start</code>
     * have just replaced old text that had length <code>before</code>.
     * It is an error to attempt to make changes to <code>s</code> from
     * this callback.
     *
     * @param s
     * @param start
     * @param before
     * @param count
     */
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String str = "";

        try {
            double value = valorSemMascara(s.toString());
            str = setFormatoDecimalSemTipoMoeda(value);

            // essa e a maneira correta de remover
            // e adicionar o evento do TextWatcher
            editCampo.removeTextChangedListener(this);
            editCampo.setText(str);
            editCampo.addTextChangedListener(this);
            editCampo.setSelection(str.length());
        } catch (Exception e) {
            e.printStackTrace();
            s = "";
        }
    }

    /**
     * This method is called to notify you that, somewhere within
     * <code>s</code>, the text has been changed.
     * It is legitimate to make further changes to <code>s</code> from
     * this callback, but be careful not to get yourself into an infinite
     * loop, because any changes you make will cause this method to be
     * called again recursively.
     * (You are not told where the change took place because other
     * afterTextChanged() methods may already have made other changes
     * and invalidated the offsets.  But if you need to know here,
     * you can use {@link Spannable#setSpan} in {@link #onTextChanged}
     * to mark your place and then look up from here where the span
     * ended up.
     *
     * @param s
     */
    @Override
    public void afterTextChanged(Editable s) {

    }
    public Double valorSemMascara() {
        return valorSemMascara(this.editCampo.getText().toString());
    }

    private Double valorSemMascara(String valor) {
        BigDecimal parsed = null;
        try {
            String cleanString = valor.replaceAll("[R,$,.]", "");
            parsed = new BigDecimal(cleanString).setScale(2,
                    BigDecimal.ROUND_FLOOR).divide(new BigDecimal(100),
                    BigDecimal.ROUND_FLOOR);
        } catch (Exception e) {
            parsed = new BigDecimal("0.0");
        }
        return parsed.doubleValue();
    }

    private String setFormatoDecimalSemTipoMoeda(double valor){
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        return decimalFormat.format(valor);
    }

}
