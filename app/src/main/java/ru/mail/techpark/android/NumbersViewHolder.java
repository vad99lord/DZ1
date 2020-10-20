package ru.mail.techpark.android;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NumbersViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public final TextView mNumber;
    public final OnPositionClickListener mPosClickListener;

    public NumbersViewHolder(@NonNull View itemView, OnPositionClickListener onPosClickListener) {
        super(itemView);
        mNumber = itemView.findViewById(R.id.number_text);
        mPosClickListener = onPosClickListener;
        itemView.setOnClickListener(this);
    }

    public void bindItemData(NumbersSource.NumberEntity number) {
        int color = mNumber.getResources().getColor(number.getColorId());
        mNumber.setTextColor(color);
        mNumber.setText(String.valueOf(number.getNumber()));
    }

    @Override
    public void onClick(View view) {
        //callback to Adapter
        mPosClickListener.onPositionClick(getAdapterPosition());
    }
}
