package com.xavierluz.servicoonline.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xavierluz.servicoonline.PrestadoAdapter;
import com.xavierluz.servicoonline.R;
import com.xavierluz.servicoonline.SimpleDividerItemDecoration;
import com.xavierluz.servicoonline.prestados.ServicoPrestado;
import com.xavierluz.servicoonline.prestados.ServicoPrestadoServices;

import java.util.List;

public class PrestadosActivity extends Fragment {

    private RecyclerView recycleViewPrestados;
    private RecyclerView.LayoutManager layoutManager;


    public PrestadosActivity() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_prestados, container, false);

        this.recycleViewPrestados = (RecyclerView) view.findViewById(R.id.recycleViewPrestados);
        this.recycleViewPrestados.setHasFixedSize(true);

        /*
        layoutManager = new LinearLayoutManager(view.getContext());
        this.recycleViewPrestados.setLayoutManager(layoutManager);
        this.recycleViewPrestados.setItemAnimator(new DefaultItemAnimator());
        this.recycleViewPrestados.addItemDecoration(new SimpleDividerItemDecoration(
                getActivity().getApplicationContext()
        ));


        List<ServicoPrestado> servicosPrestados = ServicoPrestado.getServicosPrestados();
        Log.i("Count:", Integer.toString(servicosPrestados.size()));
        this.recycleViewPrestados.setAdapter(new PrestadoAdapter(servicosPrestados,getActivity().getApplicationContext()));
        */
        ServicoPrestadoServices servicoPrestadoServices = ServicoPrestadoServices.createRecycleViewServicoPrestado(view.getContext(), this.recycleViewPrestados);
        servicoPrestadoServices.setServicosPrestado();
        return view;
    }
}
