package com.example.restaurantuifragment.view;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.restaurantuifragment.R;
import com.example.restaurantuifragment.databinding.FragmentSignUpSuccessBinding;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class SignUpSuccessFragment extends Fragment {

    private FragmentSignUpSuccessBinding binding;

    public SignUpSuccessFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSignUpSuccessBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                navLogin();
            }
        });

        binding.sigUpSuccessLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navLogin();
            }
        });
    }

    private void navLogin(){
        NavDirections navDirections = SignUpSuccessFragmentDirections.actionSignUpSuccessFragmentToLoginFragment();
        Navigation.findNavController(requireView()).navigate(navDirections);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}