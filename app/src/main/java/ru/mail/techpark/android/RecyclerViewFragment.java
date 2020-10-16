package ru.mail.techpark.android;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewFragment extends Fragment {

    private static final String DATA_LENGTH = "DATA_LENGTH";
    private NumbersSource.OnItemClickListener mItemClickListener;
    //private NumbersAdapter mAdapter;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mItemClickListener = (NumbersSource.OnItemClickListener) context;
        //Log.d(getLogTag(), "onAttach");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Log.d(getLogTag(), "onCreate");
        View view = inflater.inflate(R.layout.fragment_recycler, container, false);
        Button addItemButton = view.findViewById(R.id.add_item_button);

        RecyclerView recyclerView = view.findViewById(R.id.recycler);
        //recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
        final int columns = getResources().getInteger(R.integer.grid_columns);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), columns));

        NumbersSource data = NumbersSource.getInstance();
        final NumbersAdapter adapter = new NumbersAdapter(data, mItemClickListener);
        recyclerView.setAdapter(adapter);


        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.addItem();
            }
        });

        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    /*@Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        //if mAdapter
        outState.putInt(DATA_LENGTH, mAdapter.getItemCount());
    }*/

    @Override
    public void onDetach() {
        mItemClickListener = null;
        //mAdapter = null;
        //Log.d(getLogTag(), "onDetach");
        super.onDetach();
    }
    protected String getLogTag() {
        return getClass().getSimpleName()+getClass().hashCode();
    }

    public static class NumbersAdapter extends RecyclerView.Adapter<NumbersViewHolder> {

        private final NumbersSource mData;
        final NumbersSource.OnItemClickListener mItemClickListener;
        final OnPositionClickListener mPosClickListener;

        public NumbersAdapter(NumbersSource data, NumbersSource.OnItemClickListener itemClickListener){
            super();
            mData = data;
            mItemClickListener = itemClickListener;
            //outer callback
            mPosClickListener = new OnPositionClickListener() {
                @Override
                public void onPositionClick(int position) {
                    mItemClickListener.onItemClick(mData.getItem(position));
                }
            };
        }

        @NonNull
        @Override
        public NumbersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_number, parent, false);
            return new NumbersViewHolder(view,mPosClickListener);
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

    public static class NumbersViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView mNumber;
        public final OnPositionClickListener mPosClickListener;
        public NumbersViewHolder(@NonNull View itemView, OnPositionClickListener onPosClickListener) {
            super(itemView);
            mNumber = itemView.findViewById(R.id.number_text);
            mPosClickListener = onPosClickListener;
            itemView.setOnClickListener(this);
        }
        public void bindItemData(NumbersSource.NumberModel number){
            int color  = mNumber.getResources().getColor(number.getColorId());
            mNumber.setTextColor(color);
            mNumber.setText(String.valueOf(number.getNumber()));
        }

        @Override
        public void onClick(View view) {
            //callback to Adapter
            mPosClickListener.onPositionClick(getAdapterPosition());
        }
    }

    public interface OnPositionClickListener {
        void onPositionClick(int position);
    }

}
