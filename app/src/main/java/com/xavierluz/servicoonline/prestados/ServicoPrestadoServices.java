package com.xavierluz.servicoonline.prestados;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.xavierluz.servicoonline.DetalheServicoPrestadoActivity;
import com.xavierluz.servicoonline.PrestadoAdapter;
import com.xavierluz.servicoonline.R;
import com.xavierluz.servicoonline.SimpleDividerItemDecoration;
import com.xavierluz.servicoonline.item.servico.ItemServico;
import com.xavierluz.servicoonline.item.servico.Servico;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ServicoPrestadoServices {
    private FirebaseDatabase database;
    private DatabaseReference refServicos;
    private static String TAG ="ServicoPrestadoServices";
    private List<ServicoPrestado> servicoPrestados;
    private PrestadoAdapter adapter;
    private ServicoPrestadoDetalheAdpater adapterDetalhe;
    private Context context;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private String servicoNome;
    private ServicoPrestado servicoPrestado;
    private List<ItemServico> itemServicos;

    private ServicoPrestadoServices(Context context,RecyclerView recyclerView) {
        this.database = FirebaseDatabase.getInstance();
        this.refServicos = database.getReference("servicosPrestado");
        this.servicoPrestados = new ArrayList<ServicoPrestado>();
        this.itemServicos = new ArrayList<ItemServico>();
        this.context = context;
        this.recyclerView = recyclerView;
    }
    private ServicoPrestadoServices(Context context) {
        this.database = FirebaseDatabase.getInstance();
        this.refServicos = database.getReference("servicosPrestado");
        this.context = context;
    }
    public static ServicoPrestadoServices createRecycleViewServicoPrestado(Context context, RecyclerView recyclerView){
        return new ServicoPrestadoServices(context,recyclerView);
    }
    public static ServicoPrestadoServices createServicoPrestado(Context context){
        return new ServicoPrestadoServices(context);
    }
    public void setNomeDoServico(String servicoId){
        DatabaseReference refServicoNome;
        refServicoNome = database.getReference("servicos");

        Query query = refServicoNome.child(servicoId).orderByChild("nome");

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        //Servico servico = data.getValue( Servico.class);
                        //servico.setId(data.child("servicos").getKey());

                        servicoNome = dataSnapshot.child("nome").getValue().toString();
                        Log.i(TAG, "Retorno Nome: " + servicoNome);

                    }
                    Salvar();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void Salvar(){
        List<Servico> servicos = new ArrayList<Servico>();
        Servico servico = new Servico();
        servico.setId(servicoPrestado.getServicoId());
        servico.setNome(servicoNome);
        servico.setItemServicos(itemServicos);
        servicoPrestado.setServico(servico);
        Calendar calender = Calendar.getInstance();
        Date data = calender.getTime();
        servicoPrestado.setStatus("ABERTO");
        servicoPrestado.setDataServicoCadastrado(getDataFormatadaPadrao(data));
        servicoPrestado.setServicoId(null);
        refServicos.push().setValue(servicoPrestado);
    }
    public void Salvar(ServicoPrestado servicoPrestado, List<ItemServico> itemServicos){
        this.servicoPrestado = servicoPrestado;
        this.itemServicos = itemServicos;
        setNomeDoServico(servicoPrestado.getServicoId());


    }
    public List<ServicoPrestado> getServicoPrestados(){
        return this.servicoPrestados;
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
    public void setRecyclerViewDetalhe(){
        layoutManager = new LinearLayoutManager(context);
        this.recyclerView.setLayoutManager(layoutManager);
        this.recyclerView.setItemAnimator(new DefaultItemAnimator());
        this.recyclerView.addItemDecoration(new SimpleDividerItemDecoration(
                context
        ));

        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(context,
                LinearLayoutManager.VERTICAL, false);
        this.recyclerView.setLayoutManager(linearLayoutManager);
        this.recyclerView.setAdapter(adapterDetalhe);
        //adapterDetalhe.notifyDataSetChanged();
    }
    public PrestadoAdapter getAdapter() {
        return this.adapter;
    }
    public ServicoPrestadoDetalheAdpater getAdapterDetalhe() {
        return this.adapterDetalhe;
    }
    public void setServicosPrestado( ){

        Query query = this.refServicos.orderByChild("nome");

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        //Toast.makeText(context, "Selected Item: " + data.getKey(), Toast.LENGTH_SHORT).show();
                        ServicoPrestado servicoPrestado = data.getValue(ServicoPrestado.class);
                        Servico servico = data.child("servico").getValue(Servico.class);
                        /*
                        for (DataSnapshot dataSerico : data.child("servico").getChildren()) {
                            ItemServico itemServico = dataSerico.child("itemServicos").getValue(ItemServico.class);
                            itemServicos.add(itemServico);
                        }
                        */
                        servico.setItemServicos(itemServicos);
                        servicoPrestado.setServico(servico);
                        servicoPrestado.setId(data.getKey());
                        servicoPrestados.add(servicoPrestado);
                        //Log.i(TAG, servicoPrestado.getDescricaoServico());
                    }

                    adapter = new PrestadoAdapter(servicoPrestados,context);
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
                    ServicoPrestado servicoPrestado = dataSnapshot.getValue(ServicoPrestado.class);
                    servicoPrestado.setServicoId(dataSnapshot.getKey());
                    for(ServicoPrestado _servicoPrestado : servicoPrestados){
                        if(servicoPrestado.getId().equals(_servicoPrestado.getId())){
                            servicoPrestados.remove(_servicoPrestado);
                            break;
                        }
                    }
                    servicoPrestados.add(servicoPrestado);
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

    public void setServicoPrestado(final View view,String servicosPrestadoId){
        DatabaseReference refServicosPrestados = database.getReference("servicosPrestado")
                .child(servicosPrestadoId).child("servico");
        Log.i("SevicoPrestadoId",servicosPrestadoId);
        Query query = refServicosPrestados.orderByChild("dataServicoCadastrado");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){

                        Servico servico = dataSnapshot.getValue(Servico.class);
                        TextView textDescricaoServicoPrestadoDetalhe =(TextView) view.findViewById(R.id.textDescricaoServicoPrestadoDetalhe);
                        textDescricaoServicoPrestadoDetalhe.setText(servico.getNome());

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void setServicosPrestadoDetalhe(String servicosPrestadoId){
        DatabaseReference refServicosPrestados = database.getReference("servicosPrestado")
                .child(servicosPrestadoId).child("servico").child("itemServicos");
        Log.i("SevicoPrestadoId",servicosPrestadoId);
        Query query = refServicosPrestados.orderByChild("nome");

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Log.i("SevicoPrestadoSnapshot",dataSnapshot.getKey());
                if(dataSnapshot.exists()){

                        for (DataSnapshot dataItem : dataSnapshot.getChildren()) {
                            Log.i("SevicoPrestadoSnapshot", dataItem.getValue().toString());
                            ItemServico itemServico = dataItem.getValue(ItemServico.class);
                            Log.i("getNomeItemServico", itemServico.getNomeItemServico());
                            itemServicos.add(itemServico);
                        }
                        adapterDetalhe = new ServicoPrestadoDetalheAdpater(itemServicos, context);
                        setRecyclerViewDetalhe();

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
                    ServicoPrestado servicoPrestado = dataSnapshot.getValue(ServicoPrestado.class);
                    servicoPrestado.setServicoId(dataSnapshot.getKey());
                    for(ServicoPrestado _servicoPrestado : servicoPrestados){
                        if(servicoPrestado.getId().equals(_servicoPrestado.getId())){
                            servicoPrestados.remove(_servicoPrestado);
                            break;
                        }
                    }
                    servicoPrestados.add(servicoPrestado);
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

    private String getDataFormatadaPadrao(Date data){
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return formato.format(data);
    }
}
