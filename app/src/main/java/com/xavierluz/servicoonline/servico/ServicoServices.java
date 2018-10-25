package com.xavierluz.servicoonline.servico;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.xavierluz.servicoonline.CustomAdapter;
import com.xavierluz.servicoonline.ListaItemServicoActivity;

import java.util.ArrayList;
import java.util.List;

public class ServicoServices {
    private FirebaseDatabase database;
    private DatabaseReference refServicos;
    private static String TAG ="ServicoServices";
    private  List<Servico> _servicos;
    private CustomAdapter adapter;
    private Context context;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private View viewOne;
    private String servicoNome;
    public ServicoServices(Context context, View viewOne,RecyclerView recyclerView) {
        this.database = FirebaseDatabase.getInstance();
        this.refServicos = database.getReference("servicos");
        _servicos = new ArrayList<>();
        this.context = context;
        this.viewOne = viewOne;
        this.recyclerView = recyclerView;
    }
    public ServicoServices(Context context) {
        this.database = FirebaseDatabase.getInstance();
        this.refServicos = database.getReference("servicos");
        _servicos = new ArrayList<>();
        this.context = context;
    }
    public String getNomeDoServico(String servicoId){
        Query query = this.refServicos.child(servicoId).orderByChild("nome");

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        //Servico servico = data.getValue( Servico.class);
                        //servico.setId(data.child("servicos").getKey());
                        //Log.i(TAG, servico.getNome());
                        servicoNome = dataSnapshot.child("nome").getValue().toString();

                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return servicoNome;
    }
    public List<Servico> getServicos(){
        return this._servicos;
    }
    public void setRecyclerView(){
        layoutManager = new LinearLayoutManager(viewOne.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        /*recyclerView.addItemDecoration(new SimpleDividerItemDecoration(
                getActivity().getApplicationContext()
        ));*/

        //adapter = new CustomAdapter(servicos,this.getContext());
        recyclerView.setAdapter(getAdapter());
        recyclerView.setLayoutManager(new LinearLayoutManager(viewOne.getContext(), LinearLayoutManager.VERTICAL, false));
        adapter.notifyDataSetChanged();
    }
    public CustomAdapter getAdapter() {
        return this.adapter;
    }

    public void setServicos(){

        Query query = this.refServicos.orderByChild("nome");

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        Servico servico = data.getValue( Servico.class);
                        servico.setId(data.getKey());
                        _servicos.add(servico);
                        Log.i(TAG, servico.getNome());
                    }

                    adapter = new CustomAdapter(_servicos,context);
                    setRecyclerView();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {


            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                if(dataSnapshot.exists()) {
                    Servico servico = dataSnapshot.getValue( Servico.class);
                    servico.setId(dataSnapshot.getKey());
                    for(Servico _servico : _servicos){
                        if(_servico.getId().equals(servico.getId())){
                            _servicos.remove(_servico);
                            break;
                        }
                    }
                    _servicos.add(servico);
                    setRecyclerView();
                }
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
            // TODO: implement the ChildEventListener methods as documented above
            // ...
        });
        // My top posts by number of stars
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        });
    }
}
