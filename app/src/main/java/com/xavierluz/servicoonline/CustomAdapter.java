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


import com.xavierluz.servicoonline.servico.Servico;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private List<Servico> servicos;
    private Context context;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textServicoName;
        TextView textSlogan;
        ImageView imageViewIcon;
        ImageButton imageButtonAdicionarServico;
        TextView textViewServicoId;
        public MyViewHolder(View itemView) {
            super(itemView);
            this.textServicoName = (TextView) itemView.findViewById(R.id.textServicoName);
            this.textSlogan = (TextView) itemView.findViewById(R.id.textSlogan);
            this.imageButtonAdicionarServico =(ImageButton) itemView.findViewById(R.id.imageButtonAdicionarServico);
            this.textViewServicoId = (TextView) itemView.findViewById(R.id.textServicoId);
            //this.imageViewIcon = (ImageView) itemView.findViewById(R.id.imageView);
            //Toast.makeText(itemView.getContext(), "Nome do serviço: " +  this.textViewId.getText(), Toast.LENGTH_LONG).show();

        }


    }

    public CustomAdapter(List<Servico> servicos, Context context) {
        this.servicos = servicos;
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
        final TextView textViewServicoId = holder.textViewServicoId;
        final TextView textViewName = holder.textServicoName;
        final TextView textViewVersion = holder.textSlogan;
        textViewServicoId.setText(servicos.get(listPosition).getId());
        textViewName.setText(servicos.get(listPosition).getNome());
        textViewVersion.setText(servicos.get(listPosition).getDescricao());
        ImageButton imageButtonAdicionar = holder.imageButtonAdicionarServico;
        imageButtonAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ListaItemServicoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("ServicoId",textViewServicoId.getText().toString());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

    }

    /**
     * Return the view type of the item at <code>position</code> for the purposes
     * of view recycling.
     * <p>
     * <p>The default implementation of this method returns 0, making the assumption of
     * a single view type for the adapter. Unlike ListView adapters, types need not
     * be contiguous. Consider using id resources to uniquely identify item view types.
     *
     * @param position position to query
     * @return integer value identifying the type of the view needed to represent the item at
     * <code>position</code>. Type codes need not be contiguous.
     */
    @Override
    public int getItemViewType(int position) {
        return position;
    }

    /**
     * Return the stable ID for the item at <code>position</code>. If {@link #hasStableIds()}
     * would return false this method should return {@link #NO_ID}. The default implementation
     * of this method returns {@link #NO_ID}.
     *
     * @param position Adapter position to query
     * @return the stable ID of the item at position
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return servicos.size();
    }
}
