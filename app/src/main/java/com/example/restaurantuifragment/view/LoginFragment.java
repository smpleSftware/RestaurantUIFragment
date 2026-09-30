package com.example.restaurantuifragment.view;

import static com.example.restaurantuifragment.data.BasicDatabase.choice;
import static com.example.restaurantuifragment.data.BasicDatabase.loginEmail;
import static com.example.restaurantuifragment.data.BasicDatabase.loginPassword;
import static com.example.restaurantuifragment.data.BasicDatabase.signUpPassword;
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

import com.example.restaurantuifragment.data.BasicDatabase;
import com.example.restaurantuifragment.databinding.FragmentLoginBinding;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;


public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private SharedPreferences preferences;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        preferences = BasicDatabase.getInstance(requireContext());
        if (!preferences.getString(loginEmail, "").isEmpty() && !preferences.getString(loginPassword,"").isEmpty()){
            //Nav directions list
            NavDirections navDirections = LoginFragmentDirections.actionLoginFragmentToRestaurantListFragment();
            Navigation.findNavController(view).navigate(navDirections);
        }

        binding.loginContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String loginEmailText = binding.loginEmail.getText().toString();
                String loginPasswordText = binding.loginPassword.getText().toString();

                String sEmail = preferences.getString(signUpEmail,"");
                String sPassword = preferences.getString(signUpPassword,"");

                if (loginEmailText.isEmpty() || loginPasswordText.isEmpty()){
                    Toast.makeText(requireContext(), "It can't be empty!", Toast.LENGTH_LONG).show();
                    return;
                }

                if (loginEmailText.equals(sEmail) && loginPasswordText.equals(sPassword)){

                    preferences.edit().putString(loginEmail,loginEmailText).apply();
                    preferences.edit().putString(loginPassword,loginPasswordText).apply();

                    //Nav direction list
                    NavDirections navDirections = LoginFragmentDirections.actionLoginFragmentToRestaurantListFragment();
                    Navigation.findNavController(view).navigate(navDirections);

                    Toast.makeText(requireContext(), "Welcome " + preferences.getString(choice,""), Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(requireContext(), "Wrong email or password!", Toast.LENGTH_LONG).show();
                }
            }
        });

        binding.loginForgotText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Nav direction forgot password
                NavDirections navDirections = LoginFragmentDirections.actionLoginFragmentToForgotPasswordFragment();
                Navigation.findNavController(view).navigate(navDirections);
            }
        });

        binding.loginRegisterText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Nav direction sign up
                NavDirections navDirections = LoginFragmentDirections.actionLoginFragmentToSignUpFragment();
                Navigation.findNavController(view).navigate(navDirections);
            }
        });
    }
}