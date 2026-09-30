package com.example.restaurantuifragment.view;

import static com.example.restaurantuifragment.data.BasicDatabase.loginEmail;
import static com.example.restaurantuifragment.data.BasicDatabase.loginPassword;
import static com.example.restaurantuifragment.data.BasicDatabase.signUpEmail;
import static com.example.restaurantuifragment.data.BasicDatabase.signUpPassword;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.restaurantuifragment.R;
import com.example.restaurantuifragment.data.BasicDatabase;
import com.example.restaurantuifragment.databinding.FragmentResetPasswordBinding;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;


public class ResetPasswordFragment extends Fragment {

    private FragmentResetPasswordBinding binding;
    private SharedPreferences preferences;
    private String emailText;

    public ResetPasswordFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentResetPasswordBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {

            }
        });

        if (getArguments() != null) {
            emailText = ResetPasswordFragmentArgs.fromBundle(getArguments()).getEmail();
        }

        preferences = BasicDatabase.getInstance(requireContext());

        binding.resetSubmitting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!emailText.isEmpty()){
                    String password = binding.resetPassword.getText().toString();
                    String newPassword = binding.resetNewPassword.getText().toString();

                    if (password.isEmpty() || newPassword.isEmpty()){
                        Toast.makeText(requireContext(),"Enter password", Toast.LENGTH_LONG).show();
                        return;
                    }

                    if (password.equals(newPassword)){

                        preferences.edit().putString(signUpEmail, emailText).apply();
                        preferences.edit().putString(signUpPassword, password).apply();
                        preferences.edit().remove(loginEmail).apply();
                        preferences.edit().remove(loginPassword).apply();

                        NavDirections navDirections = ResetPasswordFragmentDirections.actionResetPasswordFragmentToLoginFragment();
                        Navigation.findNavController(view).navigate(navDirections);
                        Toast.makeText(requireContext(),"Success", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(requireContext(), "Passwords not match", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(requireContext(),"Email cannot be empty", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}