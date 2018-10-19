package com.xavierluz.servicoonline;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private ArrayList<DataModel> dataSet;
    private Context context;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textServicoName;
        TextView textSlogan;
        ImageView imageViewIcon;
        ImageButton imageButtonAdicionarServico;
        TextView textViewId;
        public MyViewHolder(View itemView) {
            super(itemView);
            this.textServicoName = (TextView) itemView.findViewById(R.id.textServicoName);
            this.textSlogan = (TextView) itemView.findViewById(R.id.textSlogan);
            this.imageButtonAdicionarServico =(ImageButton) itemView.findViewById(R.id.imageButtonAdicionarServico);
            this.textViewId = (TextView) itemView.findViewById(R.id.textServicoId);
            //this.imageViewIcon = (ImageView) itemView.findViewById(R.id.imageView);
            //Toast.makeText(itemView.getContext(), "Nome do serviço: " +  this.textViewId.getText(), Toast.LENGTH_LONG).show();

        }


    }

    public CustomAdapter(ArrayList<DataModel> data, Context context) {
        this.dataSet = data;
        this.context = context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cards_layout, parent, false);

        //view.setOnClickListener(OneFragment.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView textViewName = holder.textServicoName;
        TextView textViewVersion = holder.textSlogan;
        TextView textViewId = holder.textViewId;
        textViewId.setText(Integer.toString(dataSet.get(listPosition).getId()));
        textViewName.setText(dataSet.get(listPosition).getNomeServico());
        textViewVersion.setText(dataSet.get(listPosition).getSloganServico());
        ImageButton imageButtonAdicionar = holder.imageButtonAdicionarServico;
        imageButtonAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ListaItemServicoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("ServicoId",Integer.parseInt(holder.textViewId.getText().toString()));
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
