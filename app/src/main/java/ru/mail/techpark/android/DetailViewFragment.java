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

   // private static final String LIST_ITEM = "LIST_ITEM";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String text = "no number supplied";
        Bundle args = getArguments();
        TextView textView = view.findViewById(R.id.number_text);
        if (args != null) {
            NumbersSource.NumberModel number = NumbersSource.NumberModel.getFromBundle(args);
            int color  = textView.getResources().getColor(number.getColorId());
            textView.setTextColor(color);
            textView.setText(String.valueOf(number.getNumber()));
        }
        else
            textView.setText(text);
    }
}
