package com.example.restaurantuifragment.view;

import static com.example.restaurantuifragment.data.BasicDatabase.signUpEmail;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.restaurantuifragment.R;
import com.example.restaurantuifragment.data.BasicDatabase;
import com.example.restaurantuifragment.databinding.FragmentForgotPasswordBinding;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class ForgotPasswordFragment extends Fragment {

    private FragmentForgotPasswordBinding binding;
    private SharedPreferences preferences;

    public ForgotPasswordFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentForgotPasswordBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        preferences = BasicDatabase.getInstance(requireContext());

        binding.forgotSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailText = binding.forgotEmail.getText().toString();
                if (emailText.isEmpty()){
                    Toast.makeText(requireContext(),"Enter email", Toast.LENGTH_LONG).show();
                    return;
                }

                String sUpEmail = preferences.getString(signUpEmail,"");
                if (emailText.equals(sUpEmail)){
                    NavDirections navDirections = ForgotPasswordFragmentDirections.actionForgotPasswordFragmentToResetPasswordFragment(emailText);
                    Navigation.findNavController(view).navigate(navDirections);
                }else{
                    Toast.makeText(requireContext(),"Wrong email!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}