package com.xavierluz.servicoonline.pagamento;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.xavierluz.servicoonline.FechamentoAdapter;
import com.xavierluz.servicoonline.R;
import com.xavierluz.servicoonline.item.servico.Servico;
import com.xavierluz.servicoonline.prestados.ServicoPrestado;

public class PagamentoServices {
    private FirebaseDatabase database;
    private DatabaseReference refServicos;
    private static String TAG ="PagamentoServices";
    private final View view;
    private String servicosPrestadoId;
    private PagamentoModel pagamentoModel;
    private PagamentoServices(final View view,String servicosPrestadoId) {
        this.database = FirebaseDatabase.getInstance();
        this.refServicos = database.getReference("servicosPrestado");
        this.view = view;
        this.servicosPrestadoId = servicosPrestadoId;
    }
    public static PagamentoServices getInstance(final View view,String servicosPrestadoId){
        return new PagamentoServices(view,servicosPrestadoId);
    }
    public void setServicoPrestadoPagamento(){

        PagamentoModel pagamentoModel = getServicoPrestadoPagamento(this.servicosPrestadoId);

    }
    private PagamentoModel getServicoPrestadoPagamento(String servicosPrestadoId) {
        this.pagamentoModel = new PagamentoModel();

        DatabaseReference refServicosPrestados = database.getReference("servicosPrestado")
                .child(servicosPrestadoId);
        Log.i("SevicoPrestadoId", servicosPrestadoId);
        Query query = refServicosPrestados.orderByChild("dataServicoCadastrado");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    ServicoPrestado servicoPrestado = dataSnapshot.getValue(ServicoPrestado.class);
                    pagamentoModel.setValorDoServico(servicoPrestado.getServicoValor());

                    TextView textViewValorDoServicoPagamento = (TextView) view.findViewById(R.id.textViewValorDoServicoPagamento);
                    TextView textViewValorDoPagamento =(TextView)  view.findViewById(R.id.textViewValorDoPagamento);
                    String valorDoServico = FechamentoAdapter.formatarMoeda(pagamentoModel.getValorDoServico());
                    textViewValorDoServicoPagamento.setText(valorDoServico);
                    textViewValorDoPagamento.setText(valorDoServico);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return this.pagamentoModel;
    }
}
