package ru.mail.techpark.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {


    public static final String RECYCLER_TAG = "RecyclerFragment";
    private final NumbersSource.OnItemClickListener itemClickListener = numberModel -> {
        Bundle args = new Bundle();
        numberModel.putToBundle(args);
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
        if (getSupportFragmentManager().findFragmentById(R.id.fragments_container) == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fragments_container, RecyclerViewFragment.newInstance(itemClickListener),RECYCLER_TAG).commit();
        }
        else {
            //restore listener if Recycler fragment is already instantiated
            Fragment recyclerViewFragment = getSupportFragmentManager().findFragmentByTag(RECYCLER_TAG);
            if (recyclerViewFragment instanceof RecyclerViewFragment) {
                ((RecyclerViewFragment) recyclerViewFragment).setOnItemClickListener(itemClickListener);
            }
        }
    }

}