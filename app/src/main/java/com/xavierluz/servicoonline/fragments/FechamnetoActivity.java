package com.xavierluz.servicoonline.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xavierluz.servicoonline.FechamentoAdapter;
import com.xavierluz.servicoonline.R;
import com.xavierluz.servicoonline.SimpleDividerItemDecoration;
import com.xavierluz.servicoonline.fechamento.ServicoFechamento;

import java.util.List;


public class FechamnetoActivity extends Fragment {
    private RecyclerView recycleViewFechamento;
    private RecyclerView.LayoutManager layoutManager;

    public FechamnetoActivity() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fechamneto, container, false);

        this.recycleViewFechamento = (RecyclerView) view.findViewById(R.id.recycleViewFechamento);
        this.recycleViewFechamento.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(view.getContext());
        this.recycleViewFechamento.setLayoutManager(layoutManager);
        this.recycleViewFechamento.setItemAnimator(new DefaultItemAnimator());
        this.recycleViewFechamento.addItemDecoration(new SimpleDividerItemDecoration(
                getActivity().getApplicationContext()
        ));

        GridLayoutManager mGridLayoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 2);
        this.recycleViewFechamento.setLayoutManager(mGridLayoutManager);

        List<ServicoFechamento> servicoFechamentos = ServicoFechamento.getServicosFechamentos();
        Log.i("Count:", Integer.toString(servicoFechamentos.size()));
        this.recycleViewFechamento.setAdapter(new FechamentoAdapter(servicoFechamentos,getActivity().getApplicationContext()));
        return view;
    }
}
