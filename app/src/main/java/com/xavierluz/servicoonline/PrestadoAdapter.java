package com.xavierluz.servicoonline;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.xavierluz.servicoonline.prestados.ServicoPrestado;
import com.xavierluz.servicoonline.prestados.ServicoPrestadoViewHolder;

import java.util.List;

public class PrestadoAdapter extends RecyclerView.Adapter{
    private List<ServicoPrestado> servicosPrestados;
    private Context context;
    public PrestadoAdapter(List<ServicoPrestado> servicosPrestados,Context context){
        this.context = context;
        this.servicosPrestados = servicosPrestados;
    }
    /**
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     * <p>
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     * <p>
     * The new ViewHolder will be used to display items of the adapter using
     * {@link #onBindViewHolder(ViewHolder, int, List)}. Since it will be re-used to display
     * different items in the data set, it is a good idea to cache references to sub views of
     * the View to avoid unnecessary {@link View#findViewById(int)} calls.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     * @see #getItemViewType(int)
     * @see #onBindViewHolder(ViewHolder, int)
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.cards_servico_prestado_layout, parent, false);
        ServicoPrestadoViewHolder servicoPrestadoViewHolder = new ServicoPrestadoViewHolder(view);

        return servicoPrestadoViewHolder;
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link ViewHolder#itemView} to reflect the item at the given
     * position.
     * <p>
     * Note that unlike {@link ListView}, RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the <code>position</code> parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use {@link ViewHolder#getAdapterPosition()} which will
     * have the updated adapter position.
     * <p>
     * Override {@link #onBindViewHolder(ViewHolder, int, List)} instead if Adapter can
     * handle efficient partial bind.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ServicoPrestadoViewHolder servicoPrestadoViewHolder =(ServicoPrestadoViewHolder) holder;

        final ServicoPrestado servicoPrestado = servicosPrestados.get(position);
        servicoPrestadoViewHolder.nomeServico.setText(servicoPrestado.getServico().getNome());
        //servicoPrestadoViewHolder.nomeCliente.setText(servicoPrestado.getNomeCliente());
        servicoPrestadoViewHolder.servicoValorPrestado.setText(FechamentoAdapter.formatarMoeda(servicoPrestado.getServicoValor()));
        servicoPrestadoViewHolder.descricaoServico.setText(servicoPrestado.getServico().getNome());
        servicoPrestadoViewHolder.servicoStatus.setText(servicoPrestado.getStatus());
        servicoPrestadoViewHolder.dataServico.setText(servicoPrestado.getDataServicoCadastrado());
        servicoPrestadoViewHolder.servicoPrestadoId.setText(servicoPrestado.getId());
        //Toast.makeText(context, "Selected prestadores: " + servicoPrestado.getNomeServico(), Toast.LENGTH_SHORT).show();
        ImageButton imageButtonDetalhe = ((ServicoPrestadoViewHolder) holder).imageButtonPrestadorDetalhe;
        imageButtonDetalhe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetalheServicoPrestadoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("servicoPrestadoId",servicoPrestado.getId());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return this.servicosPrestados.size();
    }
    private Double limparCaracteresInvalidos(String valor){
        return Double.parseDouble(valor.replace(".","").replace(",",".").replace("R$",""));
    }
}

