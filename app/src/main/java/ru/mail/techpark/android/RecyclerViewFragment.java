package ru.mail.techpark.android;

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            NumbersSource.onRestore(savedInstanceState);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recycler, container, false);
        Button addItemButton = view.findViewById(R.id.add_item_button);

        RecyclerView recyclerView = view.findViewById(R.id.recycler);


        final int columns = getResources().getInteger(R.integer.grid_columns);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), columns));

        NumbersSource data = NumbersSource.getInstance();
        final NumbersAdapter adapter = new NumbersAdapter(data, mItemClickListener);
        recyclerView.setAdapter(adapter);


        addItemButton.setOnClickListener(v -> adapter.addItem());

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        NumbersSource.onSave(outState);
    }


    public static RecyclerViewFragment newInstance(NumbersSource.OnItemClickListener itemClickListener){
        RecyclerViewFragment recyclerViewFragment = new RecyclerViewFragment();
        recyclerViewFragment.setOnItemClickListener(itemClickListener);
        return recyclerViewFragment;
    }

    public void setOnItemClickListener(NumbersSource.OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }
}
