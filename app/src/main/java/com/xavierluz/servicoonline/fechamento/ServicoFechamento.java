package com.xavierluz.servicoonline.fechamento;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.xavierluz.servicoonline.PrestadoAdapter;
import com.xavierluz.servicoonline.SimpleDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class ServicoFechamento {
    private Fechamento fechamento;
    private FirebaseDatabase database;
    private DatabaseReference refServicos;
    private PrestadoAdapter adapter;
    private Context context;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;


    public ServicoFechamento() {

    }

    private ServicoFechamento(Context context, RecyclerView recyclerView) {
        this.database = FirebaseDatabase.getInstance();
        this.refServicos = database.getReference("servicosFechado");
        this.context = context;
        this.recyclerView = recyclerView;
    }
    private ServicoFechamento(Context context) {
        this.database = FirebaseDatabase.getInstance();
        this.refServicos = database.getReference("servicosFechado");
        this.context = context;
    }



    public static List<ServicoFechamento> getServicosFechamentos(){
        ServicoFechamento servicoFechamento =  null;
        List<ServicoFechamento> servicoFechamentos =  new ArrayList<ServicoFechamento>();
        for(Integer i = 1; i < 10; i++) {
            servicoFechamento = new ServicoFechamento();


            servicoFechamentos.add(servicoFechamento);
        }
        return servicoFechamentos;
    }

    public void setRecyclerView(){
        layoutManager = new LinearLayoutManager(context);
        this.recyclerView.setLayoutManager(layoutManager);
        this.recyclerView.setItemAnimator(new DefaultItemAnimator());
        this.recyclerView.addItemDecoration(new SimpleDividerItemDecoration(
                context
        ));

        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(context,
                LinearLayoutManager.VERTICAL, false);
        this.recyclerView.setLayoutManager(linearLayoutManager);
        this.recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void setServicosFechados(){
        Query query = this.refServicos.orderByChild("dataFechamento");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        if(dataSnapshot.exists()){
                            fechamento = dataSnapshot.getValue(Fechamento.class);
                        }
                    }
                    setRecyclerView();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
