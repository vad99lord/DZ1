package ru.mail.techpark.android;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewFragment extends Fragment {

    private NumbersSource.OnItemClickListener mItemClickListener;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mItemClickListener = (NumbersSource.OnItemClickListener) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recycler, container, false);
        Button addItemButton = view.findViewById(R.id.add_item_button);

        RecyclerView recyclerView = view.findViewById(R.id.recycler);

        //recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
        final int columns = getResources().getInteger(R.integer.grid_columns);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), columns));

        NumbersSource data = NumbersSource.getInstance();
        final NumbersAdapter adapter = new NumbersAdapter(data, mItemClickListener);
        recyclerView.setAdapter(adapter);


        addItemButton.setOnClickListener(v -> adapter.addItem());

        return view;
    }


    @Override
    public void onDetach() {
        mItemClickListener = null;
        super.onDetach();
    }

}
