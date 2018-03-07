package com.mediamonks.testcoordinatorrecycler.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mediamonks.testcoordinatorrecycler.GuestFragment;
import com.mediamonks.testcoordinatorrecycler.Host;
import com.mediamonks.testcoordinatorrecycler.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created on 07/03/2018.
 */

public class ListFragment extends Fragment implements GuestFragment{
    public static final String TAG = ListFragment.class.getSimpleName();

    private static final String KEY_ITEMS = "key_items";

    @BindView(R.id.list)
    RecyclerView _list;

    private List<String> _items;
    private Host _host;

    public static ListFragment newInstance(ArrayList<String> items) {
        Bundle args = new Bundle();
        args.putStringArrayList(KEY_ITEMS, items);

        ListFragment fragment = new ListFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        _items = getArguments().getStringArrayList(KEY_ITEMS);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        ListAdapter listAdapter = new ListAdapter(getContext(), _items);
        listAdapter.setItemSelectListener(label -> _host.navigateToDetail(label));

        _list.setLayoutManager(new LinearLayoutManager(getContext()));
        _list.setAdapter(listAdapter);
    }

    @Override
    public void setHost(Host host) {
        _host = host;
    }
}
