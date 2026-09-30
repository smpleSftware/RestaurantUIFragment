package com.example.restaurantuifragment.view;

import static android.content.Context.MODE_PRIVATE;

import static com.example.restaurantuifragment.data.BasicDatabase.choice;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.restaurantuifragment.data.BasicDatabase;
import com.example.restaurantuifragment.databinding.FragmentEntryBinding;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;


public class EntryFragment extends Fragment {

    private FragmentEntryBinding binding;
    private SharedPreferences preferences;

    public EntryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentEntryBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        preferences = BasicDatabase.getInstance(requireContext());
        String result = preferences.getString(choice,"");
        if (!result.isEmpty()){
            //Toast.makeText(requireContext(), "You are a " + result, Toast.LENGTH_SHORT).show();
            navLogin();
        }

        binding.entryRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preferences.edit().putString(choice,  "restaurant").apply();
                navLogin();
            }
        });

        binding.entryCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preferences.edit().putString(choice,  "customer").apply();
                navLogin();
            }
        });
    }

    private void navLogin(){
        NavDirections navDirections = EntryFragmentDirections.actionEntryFragmentToLoginFragment();
        Navigation.findNavController(requireView()).navigate(navDirections);
    }
}