package ru.mail.techpark.android;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NumbersAdapter extends RecyclerView.Adapter<NumbersViewHolder> {

    private final NumbersSource mData;
    final NumbersSource.OnItemClickListener mItemClickListener;
    final OnPositionClickListener mPosClickListener;

    public NumbersAdapter(NumbersSource data, NumbersSource.OnItemClickListener itemClickListener) {
        super();
        mData = data;
        mItemClickListener = itemClickListener;
        //outer callback
        mPosClickListener = position -> mItemClickListener.onItemClick(mData.getItem(position));
    }

    @NonNull
    @Override
    public NumbersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_number, parent, false);
        return new NumbersViewHolder(view, mPosClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull NumbersViewHolder holder, int position) {
        holder.bindItemData(mData.getItem(position));
        //Log.d("Bind", "onBind"+position);
    }

    @Override
    public int getItemCount() {
        return mData.getSize();
    }

    public void addItem() {
        mData.addItems(1);
        notifyItemInserted(getItemCount());
    }


}




