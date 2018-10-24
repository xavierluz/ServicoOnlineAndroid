package com.xavierluz.servicoonline.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.xavierluz.servicoonline.CustomAdapter;
import com.xavierluz.servicoonline.DataModel;
import com.xavierluz.servicoonline.MyData;
import com.xavierluz.servicoonline.R;
import com.xavierluz.servicoonline.RecyclerViewClickListener;
import com.xavierluz.servicoonline.SimpleDividerItemDecoration;
import com.xavierluz.servicoonline.SingInActivity;
import com.xavierluz.servicoonline.servico.Servico;
import com.xavierluz.servicoonline.servico.ServicoServices;

import java.util.ArrayList;
import java.util.List;


public class OneFragment extends Fragment{
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private List<Servico> servicos;
    public static View.OnClickListener myOnClickListener;
    private static ArrayList<Integer> removedItems;
    private ServicoServices servicoServices;
    public OneFragment() {
        // Required empty public constructor

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewOne = inflater.inflate(R.layout.fragment_one, container, false);

        recyclerView = (RecyclerView) viewOne.findViewById(R.id.recycleViewServico);
        getActivity().getApplicationContext();
        recyclerView.setHasFixedSize(true);
        servicoServices = new ServicoServices(viewOne.getContext(),viewOne,recyclerView);
        servicoServices.setServicos();
        //Toast.makeText(viewOne.getContext(), "Selected Item:145", Toast.LENGTH_SHORT).show();

        //servicoServices.setRecyclerView();
        /*
        myOnClickListener = new MyOnClickListener(viewOne.getContext());

        layoutManager = new LinearLayoutManager(viewOne.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        /*recyclerView.addItemDecoration(new SimpleDividerItemDecoration(
                getActivity().getApplicationContext()
        ));

        removedItems = new ArrayList<Integer>();

        //adapter = new CustomAdapter(servicos,this.getContext());
        recyclerView.setAdapter(servicoServices.getAdapter());
        recyclerView.setLayoutManager(new LinearLayoutManager(viewOne.getContext(), LinearLayoutManager.VERTICAL, false));
        // Inflate the layout for this fragment
        */


        return viewOne;
    }

    /**
     * Called when the Fragment is visible to the user.  This is generally
     * tied to {@link Activity#onStart() Activity.onStart} of the containing
     * Activity's lifecycle.
     */
    @Override
    public void onStart() {
        super.onStart();

    }

    private static class MyOnClickListener implements View.OnClickListener {

        private final Context context;

        private MyOnClickListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            int selectedItemPosition = recyclerView.getChildPosition(v);
            RecyclerView.ViewHolder viewHolder
                    = recyclerView.findViewHolderForPosition(selectedItemPosition);
            TextView textViewName
                    = (TextView) viewHolder.itemView.findViewById(R.id.textServicoName);
            String selectedName = (String) textViewName.getText();

        }

        private void removeItem(View v) {
            int selectedItemPosition = recyclerView.getChildLayoutPosition(v);
            RecyclerView.ViewHolder viewHolder
                    = recyclerView.findViewHolderForAdapterPosition(selectedItemPosition); //recyclerView.findViewHolderForPosition(selectedItemPosition);
            TextView textViewName
                    = (TextView) viewHolder.itemView.findViewById(R.id.textServicoName);
            String selectedName = (String) textViewName.getText();
            int selectedItemId = -1;
            for (int i = 0; i < MyData.servicoArray.length; i++) {
                if (selectedName.equals(MyData.servicoArray[i])) {
                    selectedItemId = MyData.idArray[i];
                }
            }
            removedItems.add(selectedItemId);
            //data.remove(selectedItemPosition);
            adapter.notifyItemRemoved(selectedItemPosition);
        }
    }
    private void addRemovedItemToList() {
        /*
        int addItemAtListPosition = 3;
        servicos.add(addItemAtListPosition, new Servico(
                MyData.servicoArray[removedItems.get(0)],
                MyData.eslogamArray[removedItems.get(0)],
                MyData.idArray[removedItems.get(0)],
                MyData.imagemArray[removedItems.get(0)]
        ));*/
       // adapter.notifyItemInserted(null);
        removedItems.remove(0);
    }
}
