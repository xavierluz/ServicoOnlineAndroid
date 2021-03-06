package com.xavierluz.servicoonline.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    private View viewOne;
    private LayoutInflater inflater;
    private ViewGroup container;
    private Bundle savedInstanceState;
    public OneFragment() {
        // Required empty public constructor

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    /**
     * Called when the view previously created by {@link #onCreateView} has
     * been detached from the fragment.  The next time the fragment needs
     * to be displayed, a new view will be created.  This is called
     * after {@link #onStop()} and before {@link #onDestroy()}.  It is called
     * <em>regardless</em> of whether {@link #onCreateView} returned a
     * non-null view.  Internally it is called after the view's state has
     * been saved but before it has been removed from its parent.
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("FragrementServico","onDestroyView");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.inflater = inflater;
        this.container = container;
        this.savedInstanceState =  savedInstanceState;

        viewOne = inflater.inflate(R.layout.fragment_one, container, false);

        recyclerView = (RecyclerView) viewOne.findViewById(R.id.recycleViewServico);
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
        Log.i("FragrementServico","onStart");
        //onCreateView(this.inflater, this.container,this.savedInstanceState);
    }

    /**
     * Called when the fragment is visible to the user and actively running.
     * This is generally
     * tied to {@link Activity#onResume() Activity.onResume} of the containing
     * Activity's lifecycle.
     */
    @Override
    public void onResume() {
        super.onResume();
        Log.i("FragrementServico","onResume");
        //onCreateView(this.inflater, this.container,this.savedInstanceState);
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
