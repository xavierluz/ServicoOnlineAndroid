package com.xavierluz.servicoonline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.xavierluz.servicoonline.bibliotecas.BrPorcetagemTextWatcher;
import com.xavierluz.servicoonline.bibliotecas.BrRealMoneyTextWatcher;
import com.xavierluz.servicoonline.bibliotecas.CalcularValor;
import com.xavierluz.servicoonline.pagamento.PagamentoServices;


import java.text.NumberFormat;


public class PagamentoActivity extends AppCompatActivity {
    private PagamentoServices pagamentoServices;
    private String servicoPrestadoId;
    private EditText editTextDescontoPagamento;
    private TextView textViewValorDoServicoPagamento;
    private TextView textViewValorDoPagamento;
    private BrRealMoneyTextWatcher brRealMoneyTextWatcher;
    private NumberFormat formatDecimal;
    private CalcularValor calcularValor;
    private ImageButton imgButtonAplicarDesconto;
    private CheckBox checkDinheiroTipoPagamento;
    private CheckBox checkChequeTipoPagamento;
    private CheckBox checkCartaoTipoPagamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamento);
        //get the intent in the target activity
        Intent intent = getIntent();
        //get the attached bundle from the intent
        Bundle extras = intent.getExtras();
        //Extracting the stored data from the bundle
        this.servicoPrestadoId = extras.getString("servicoPrestadoId");
        // Toast.makeText(this, "Selected Item: " + servicoPrestadoId, Toast.LENGTH_SHORT).show();
        View view =(View) findViewById(R.id.activity_pagamento_layout);
        pagamentoServices = PagamentoServices.getInstance(view,this.servicoPrestadoId);
        pagamentoServices.setServicoPrestadoPagamento();

        this.editTextDescontoPagamento = (EditText) findViewById(R.id.editTextDescontoPagamento);
        this.textViewValorDoServicoPagamento =(TextView) findViewById(R.id.textViewValorDoServicoPagamento);
        this.textViewValorDoPagamento =(TextView) findViewById(R.id.textViewValorDoPagamento);
        this.imgButtonAplicarDesconto =(ImageButton) findViewById(R.id.imgButtonAplicarDesconto);

        editTextDescontoPagamento.setEnabled(false);

        habilitarTipoDesconto();

        this.editTextDescontoPagamento.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){
                    Double valorADescontado = FechamentoAdapter.formatoDecimalSemTipoMoeda(editTextDescontoPagamento.getText().toString());
                    Double valorTotal =FechamentoAdapter.formatoDecimalSemTipoMoeda(textViewValorDoServicoPagamento.getText().toString());
                    calcularValor = CalcularValor.createDescontoDoValor(valorADescontado,valorTotal);
                    textViewValorDoPagamento.setText(FechamentoAdapter.formatarMoeda(calcularValor.getValorCalculado()));
                }

            }
        });

        imgButtonAplicarDesconto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double valorADescontado = FechamentoAdapter.formatoDecimalSemTipoMoeda(editTextDescontoPagamento.getText().toString());
                Double valorTotal =FechamentoAdapter.formatoDecimalSemTipoMoeda(textViewValorDoServicoPagamento.getText().toString());
                calcularValor = CalcularValor.createDescontoDoValor(valorADescontado,valorTotal);
                textViewValorDoPagamento.setText(FechamentoAdapter.formatarMoeda(calcularValor.getValorCalculado()));
            }
        });
    }


    private void habilitarTipoDesconto(){
        final CheckBox checkDinheiroPagamento = (CheckBox) findViewById(R.id.checkDinheiroPagamento);
        final CheckBox checkPorcetagemPagamento = (CheckBox) findViewById(R.id.checkPorcetagemPagamento) ;

        checkDinheiroPagamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextDescontoPagamento.setText("");
                if(checkDinheiroPagamento.isChecked()){
                    checkPorcetagemPagamento.setChecked(false);
                    editTextDescontoPagamento.setEnabled(true);
                    brRealMoneyTextWatcher = new BrRealMoneyTextWatcher(editTextDescontoPagamento);
                    editTextDescontoPagamento.addTextChangedListener(brRealMoneyTextWatcher);

                }else{
                    editTextDescontoPagamento.setEnabled(false);
                }
            }
        });

        checkPorcetagemPagamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextDescontoPagamento.setText("");
                if(checkPorcetagemPagamento.isChecked()){
                    checkDinheiroPagamento.setChecked(false);
                    editTextDescontoPagamento.setEnabled(true);
                    editTextDescontoPagamento.addTextChangedListener(new BrPorcetagemTextWatcher(editTextDescontoPagamento));

                }else{
                    editTextDescontoPagamento.setEnabled(false);
                }
            }
        });
    }
}
