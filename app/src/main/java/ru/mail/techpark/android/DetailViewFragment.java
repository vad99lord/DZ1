package ru.mail.techpark.android;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DetailViewFragment extends Fragment {

    public static final String EMPTY_ARGS = "No number supplied to fragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle args = getArguments();
        TextView textView = view.findViewById(R.id.number_text);
        if (args == null){
            textView.setText(EMPTY_ARGS);
            return;
        }
        NumbersSource.NumberEntity number = NumbersSource.NumberEntity.getFromBundle(args);
        int color  = textView.getResources().getColor(number.getColorId());
        textView.setTextColor(color);
        textView.setText(String.valueOf(number.getNumber()));
    }
}
