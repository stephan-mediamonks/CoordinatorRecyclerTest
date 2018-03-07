package com.mediamonks.testcoordinatorrecycler.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mediamonks.testcoordinatorrecycler.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created on 07/03/2018.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {

    private final LayoutInflater _inflater;
    private List<String> _items;
    private ItemSelectListener _listener;

    public ListAdapter(Context context, List<String> items) {
        _items = items;

        _inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(_inflater.inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.bind(_items.get(position));
    }

    @Override
    public int getItemCount() {
        return _items.size();
    }

    public interface ItemSelectListener {
        void onItemSelected(String label);
    }

    void setItemSelectListener (ItemSelectListener listener) {
        _listener = listener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.label)
        TextView _labelText;

        MyViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(v -> _listener.onItemSelected(_items.get(getAdapterPosition())));
        }

        void bind(String label) {
            _labelText.setText(label);
        }
    }
}
