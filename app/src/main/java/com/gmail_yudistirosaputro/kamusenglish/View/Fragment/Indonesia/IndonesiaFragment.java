package com.gmail_yudistirosaputro.kamusenglish.View.Fragment.Indonesia;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.gmail_yudistirosaputro.kamusenglish.Adapter.ListAdapter;
import com.gmail_yudistirosaputro.kamusenglish.Model.BahasaModel;
import com.gmail_yudistirosaputro.kamusenglish.R;
import com.gmail_yudistirosaputro.kamusenglish.View.Fragment.English.EnglishPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class IndonesiaFragment extends Fragment implements IndonesiaInterface, SearchView.OnQueryTextListener {
    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    private ListAdapter adapter;
    private List<BahasaModel> modelList = new ArrayList<>();
    private IndonesiaPresenter presenter;

    public IndonesiaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_indonesia, container, false);
        recyclerView = view.findViewById(R.id.recyclerview);
        progressBar = view.findViewById(R.id.progress_bar);
        SearchView searchView = view.findViewById(R.id.search_view);


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ListAdapter(modelList,getContext());
        recyclerView.setAdapter(adapter);
        presenter = new IndonesiaPresenter(this,getContext());


        searchView.setQueryHint("Cari kata");
        searchView.setOnQueryTextListener(this);
        return view;
    }

    @Override
    public void setProgressbar(int progress) {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void setData(List<BahasaModel> model) {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        adapter.addItem(model);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        presenter.cariKata(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
