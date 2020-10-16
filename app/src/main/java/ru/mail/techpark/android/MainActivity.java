package ru.mail.techpark.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity
        implements NumbersSource.OnItemClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportFragmentManager().findFragmentById(R.id.fragments_container) == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fragments_container, new RecyclerViewFragment()).commit();
        }
    }


    @Override
    public void onItemClick(NumbersSource.NumberModel numberModel) {
        Bundle args = new Bundle();
        numberModel.putToBundle(args);
        DetailViewFragment detailViewFragment = new DetailViewFragment();
        detailViewFragment.setArguments(args);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragments_container,detailViewFragment).addToBackStack(null).commit();
    }
}