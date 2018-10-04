package com.xavierluz.servicoonline;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.xavierluz.servicoonline.fechamento.ServicoFechamento;
import com.xavierluz.servicoonline.fechamento.ServicoFechamentoViewHolder;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class FechamentoAdapter extends RecyclerView.Adapter {
    private List<ServicoFechamento> servicoFechamentos;
    private Context context;
    public FechamentoAdapter(List<ServicoFechamento> servicoFechamentos, Context context){
        this.context = context;
        this.servicoFechamentos = servicoFechamentos;
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
                .inflate(R.layout.cards_servico_fechado_layout, parent, false);
        ServicoFechamentoViewHolder servicoFechamentoViewHolder = new ServicoFechamentoViewHolder(view);

        return servicoFechamentoViewHolder;
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
        ServicoFechamentoViewHolder servicoFechamentoViewHolder =(ServicoFechamentoViewHolder) holder;

        ServicoFechamento servicoFechamento = servicoFechamentos.get(position);
        Log.i("Fechamento",Integer.toString(servicoFechamentos.size()));
        servicoFechamentoViewHolder.textFechamentoId.setText(servicoFechamento.getFechamentoId().toString());
        servicoFechamentoViewHolder.textDataFechamento.setText(servicoFechamento.getDataFechamento().toString());
        servicoFechamentoViewHolder.textDataPorExtenso.setText(formatarDataMesAno(servicoFechamento.getDataFechamento()));
        servicoFechamentoViewHolder.textDescricaoFechamento.setText(servicoFechamento.getDescricaoFechamento());
        servicoFechamentoViewHolder.textFechamentoServicoId.setText(servicoFechamento.getfechamentoServicoId().toString());
        servicoFechamentoViewHolder.textValorFechamento.setText("R$ " + formatarDecimal(servicoFechamento.getValorFechamento()));
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return this.servicoFechamentos.size();
    }
    // ESSE MÉTODO AQUI CASO QUERIA PEGAR SEMPRE
    // A DATA ATUAL JA FORMATADA
    public static String getDataFormatada(){
        DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL, new Locale("pt", "BR"));

        // DATA ATUAL DO SISTEMA : new Date(System.currentTimeMillis())
        String dataExtenso = formatador.format(new Date(System.currentTimeMillis()));
        int index  = dataExtenso.indexOf(",") + 2;
        int lenght = dataExtenso.length();
        return dataExtenso.substring(index, lenght).toLowerCase();
    }


    // ESSE MÉTODO AQUI VC PASSA UMA DATA PARA ELE
    // E ELE TE DEVOLVE ELA FORMATADA !!
    public static String formataData(Date data){
        DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL, new Locale("pt", "BR"));
        String dataExtenso = formatador.format(data);
        int index  = dataExtenso.indexOf(",") + 2;
        int lenght = dataExtenso.length();
        Log.i("DataFormatada",dataExtenso);
        return dataExtenso.substring(index, lenght).toLowerCase();
    }

    public static String formatarDataMesAno(Date data){
        Locale local = new Locale("pt","BR");
        DateFormat dateFormat = new SimpleDateFormat("MMMM 'de' yyyy",local);
        return dateFormat.format(data);
    }

    public static String formatarMoeda(Double valor){
        NumberFormat numberFormat =   NumberFormat.getCurrencyInstance();
        return numberFormat.format(valor);
    }
    public static String formatarDecimal(Double valor){
        DecimalFormat decimalFormat = new DecimalFormat("0.##");
        return decimalFormat.format(valor);
    }


}
