package com.xavierluz.servicoonline.fragments;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.xavierluz.servicoonline.PrestadoAdapter;
import com.xavierluz.servicoonline.R;
import com.xavierluz.servicoonline.SimpleDividerItemDecoration;
import com.xavierluz.servicoonline.prestados.ServicoPrestado;
import com.xavierluz.servicoonline.prestados.ServicoPrestadoServices;

import java.util.List;

public class PrestadosActivity extends Fragment {

    private RecyclerView recycleViewPrestados;
    private RecyclerView.LayoutManager layoutManager;
    private  View view;

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
        view = inflater.inflate(R.layout.activity_prestados, container, false);

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

    /**
     * Called immediately after {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}
     * has returned, but before any saved state has been restored in to the view.
     * This gives subclasses a chance to initialize themselves once
     * they know their view hierarchy has been completely created.  The fragment's
     * view hierarchy is not however attached to its parent at this point.
     *
     * @param view               The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    /**
     * Called when all saved state has been restored into the view hierarchy
     * of the fragment.  This can be used to do initialization based on saved
     * state that you are letting the view hierarchy track itself, such as
     * whether check box widgets are currently checked.  This is called
     * after {@link #onActivityCreated(Bundle)} and before
     * {@link #onStart()}.
     *
     * @param savedInstanceState If the fragment is being re-created from
     *                           a previous saved state, this is the state.
     */
    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

    }

    /**
     * Called when the Fragment is visible to the user.  This is generally
     * tied to {@link Activity#onStart() Activity.onStart} of the containing
     * Activity's lifecycle.
     */
    @Override
    public void onStart() {
        super.onStart();
        ServicoPrestadoServices servicoPrestadoServices = ServicoPrestadoServices.createRecycleViewServicoPrestado(view.getContext(), this.recycleViewPrestados);
        servicoPrestadoServices.setServicosPrestado();
    }
}
