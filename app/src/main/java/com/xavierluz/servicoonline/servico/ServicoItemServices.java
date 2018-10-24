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
import com.xavierluz.servicoonline.SimpleDividerItemDecoration;
import com.xavierluz.servicoonline.item.servico.ItemServico;
import com.xavierluz.servicoonline.item.servico.ItemServicoAdapter;

import java.util.ArrayList;
import java.util.List;

public class ServicoItemServices {
    private FirebaseDatabase database;
    private DatabaseReference refServicos;
    private static String TAG ="ServicoItemServices";
    private List<ServicoItem> servicoItems;
    private ItemServicoAdapter adapter;
    private Context context;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;

    private ServicoItemServices(Context context,RecyclerView recyclerView, String servicoId) {
        this.database = FirebaseDatabase.getInstance();
        this.refServicos = database.getReference("servicos").child(servicoId).child("servicoItem");
        servicoItems = new ArrayList<>();
        this.context = context;

        this.recyclerView = recyclerView;
    }
    private ServicoItemServices(Context context, String servicoId) {
        this.database = FirebaseDatabase.getInstance();
        this.refServicos = database.getReference("servicos").child(servicoId).child("servicoItem");
        this.context = context;
        this.recyclerView = recyclerView;
    }

    public static ServicoItemServices createRecycleViewServicoItem(Context context,RecyclerView recyclerView, String servicoId){
        return new ServicoItemServices(context,recyclerView,servicoId);
    }
    public static ServicoItemServices createServicoItem(Context context, String servicoId){
        return new ServicoItemServices(context,servicoId);
    }

    public void Salvar(ServicoItem servicoItem){
        this.refServicos.push().setValue(servicoItem);
    }

    public List<ServicoItem> getServicosItens(){
        return this.servicoItems;
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
    public ItemServicoAdapter getAdapter() {
        return this.adapter;
    }

    public void setServicos(){

        Query query = this.refServicos.orderByChild("nome");

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        //Toast.makeText(context, "Selected Item: " + data.getKey(), Toast.LENGTH_SHORT).show();
                        ServicoItem servicoItem = data.getValue(ServicoItem.class);
                        servicoItem.setServicoId(data.child("servicoItem").getKey());
                        servicoItems.add(servicoItem);
                        Log.i(TAG, servicoItem.getNome());
                    }

                    adapter = new ItemServicoAdapter(servicoItems,context);
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
                    ServicoItem servicoItem = dataSnapshot.getValue( ServicoItem.class);
                    servicoItem.setId(dataSnapshot.getKey());
                    for(ServicoItem _servicoItem : servicoItems){
                        if(_servicoItem.getId().equals(servicoItem.getId())){
                            servicoItems.remove(_servicoItem);
                            break;
                        }
                    }
                    servicoItems.add(servicoItem);
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
