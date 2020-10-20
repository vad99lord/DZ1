package ru.mail.techpark.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {


    public static final String RECYCLER_TAG = "RecyclerFragment";
    private final NumbersSource.OnItemClickListener itemClickListener = numberEntity -> {
        Bundle args = new Bundle();
        numberEntity.putToBundle(args);
        DetailViewFragment detailViewFragment = new DetailViewFragment();
        detailViewFragment.setArguments(args);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragments_container, detailViewFragment)
                .addToBackStack(null)
                .commit();
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerViewFragment recyclerViewFragment = (RecyclerViewFragment) getSupportFragmentManager().findFragmentByTag(RECYCLER_TAG);
        if (recyclerViewFragment == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            recyclerViewFragment = new RecyclerViewFragment();
            transaction.add(R.id.fragments_container, recyclerViewFragment,RECYCLER_TAG)
                    .commit();
        }
        recyclerViewFragment.setOnItemClickListener(itemClickListener);
    }

}