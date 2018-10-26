package com.xavierluz.servicoonline.item.servico;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.xavierluz.servicoonline.FechamentoAdapter;
import com.xavierluz.servicoonline.R;
import com.xavierluz.servicoonline.servico.ServicoItem;

import java.util.List;

public class ItemServicoAdapter extends RecyclerView.Adapter{
    private List<ServicoItem> servicoItems;
    private Context context;
    private Double valorTotal=0.0;
    public ItemServicoAdapter(List<ServicoItem> servicoItems, Context context){
        this.context = context;
        this.servicoItems = servicoItems;

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
                .inflate(R.layout.cards_lista_servicos, parent, false);
        ItemServicoViewHolder  itemServicoViewHolder = new ItemServicoViewHolder(view);

        return itemServicoViewHolder;
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
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        final ItemServicoViewHolder itemServicoViewHolder = (ItemServicoViewHolder) holder;
        final ServicoItem servicoItem = this.servicoItems.get(position);
        Log.i("ListaItemServico", Integer.toString(this.servicoItems.size()));
        itemServicoViewHolder.textNomeItemServico.setText(servicoItem.getNome().toString());
        itemServicoViewHolder.textDescricaoItemServico.setText(servicoItem.getDescricao().toString());
        itemServicoViewHolder.textPrecoItemServico.setText(FechamentoAdapter.formatarMoeda(servicoItem.getPreco()));
        CheckBox checkBoxtemServicoSelecionado = (holder.itemView.findViewById(R.id.checkItemSevicoSelecionar));
        checkBoxtemServicoSelecionado.setText(itemServicoViewHolder.textPrecoItemServico.getText().toString());
        itemServicoViewHolder.itemServicoId.setText(servicoItem.getId());

        itemServicoViewHolder.chkItemServicoSelecionado.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                View viewListaItensServico = ((Activity)context).getWindow().getDecorView().findViewById(android.R.id.content);
                View view = viewListaItensServico.findViewById(R.id.layoutListaItensDoServico);
                Double valorDoServico = FechamentoAdapter.formatoDecimalSemTipoMoeda(itemServicoViewHolder.textPrecoItemServico.getText().toString());
                TextView textViewValorTotal =(TextView) view.findViewById(R.id.textViewValorTotalServicoPrestado);
                Double _valor = limparCaracteresInvalidos(textViewValorTotal.getText().toString());

                if (itemServicoViewHolder.chkItemServicoSelecionado.isChecked()) {
                    /*itemServico.somarPreco(valorTotalDoServico);
                    Toast.makeText(context, "Soma Valor Total: " + Double.toString(_valor), Toast.LENGTH_SHORT).show();
                    Toast.makeText(context, "soma Preço: " + Double.toString(valorDoServico), Toast.LENGTH_SHORT).show();*/
                    valorTotal = _valor + valorDoServico;
                }else{
                    valorTotal = _valor - valorDoServico;
                }
                Log.i("ValorTotalServico",FechamentoAdapter.formatarMoeda(servicoItem.getPreco()));

                textViewValorTotal.setText(FechamentoAdapter.formatarMoeda(valorTotal));
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
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return this.servicoItems.size();
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
    private Double limparCaracteresInvalidos(String valor){
        return Double.parseDouble(valor.replace(".","").replace(",",".").replace("R$",""));
    }
}
