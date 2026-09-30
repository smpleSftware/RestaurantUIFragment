package com.example.restaurantuifragment.view;

import static com.example.restaurantuifragment.data.BasicDatabase.signUpPassword;
import static com.example.restaurantuifragment.data.BasicDatabase.signUpEmail;
import static com.example.restaurantuifragment.data.BasicDatabase.signUpFullName;
import static com.example.restaurantuifragment.data.BasicDatabase.signUpMobile;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.restaurantuifragment.R;
import com.example.restaurantuifragment.data.BasicDatabase;
import com.example.restaurantuifragment.databinding.FragmentSignUpBinding;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;


public class SignUpFragment extends Fragment {

    private FragmentSignUpBinding binding;
    private SharedPreferences preferences;

    public SignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSignUpBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        preferences = BasicDatabase.getInstance(requireContext());

        binding.sigUpContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.signUpEmail.getText().toString();
                String fullName = binding.signUpFullName.getText().toString();
                String mobile = binding.signUpMobile.getText().toString();
                String password = binding.signUpPassword.getText().toString();

                if (email.isEmpty() || fullName.isEmpty() || mobile.isEmpty() || password.isEmpty()){
                    Toast.makeText(requireContext(), "They can't be empty", Toast.LENGTH_LONG).show();
                    return;
                }

                preferences.edit().putString(signUpEmail, email).apply();
                preferences.edit().putString(signUpFullName, fullName).apply();
                preferences.edit().putString(signUpMobile, mobile).apply();
                preferences.edit().putString(signUpPassword, password).apply();

                //Nav signup success
                NavDirections navDirections = SignUpFragmentDirections.actionSignUpFragmentToSignUpSuccessFragment();
                Navigation.findNavController(view).navigate(navDirections);
            }
        });

        binding.signUpLoginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections navDirections = SignUpFragmentDirections.actionSignUpFragmentToLoginFragment();
                Navigation.findNavController(view).navigate(navDirections);
            }
        });

        specialText();

    }

    private void specialText(){
        String fullText = requireContext().getString(R.string.privacyPolicy);
        SpannableString spannableString = new SpannableString(fullText);

        String termText = "Term & Conditions";
        int termStart = fullText.indexOf(termText);
        int termEnd = termStart + termText.length();

        String privacyText = "Privacy Policy";
        int privacyStart = fullText.indexOf(privacyText);
        int privacyEnd = privacyStart + privacyText.length();

        ClickableSpan termClickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@androidx.annotation.NonNull View view) {
                Toast.makeText(requireContext(), "Clicked Term & Conditions", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void updateDrawState(@androidx.annotation.NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.BLUE);
                ds.setUnderlineText(false);
            }
        };

        spannableString.setSpan(termClickableSpan, termStart, termEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        ClickableSpan privacyClickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@androidx.annotation.NonNull View view) {
                Toast.makeText(requireContext(), "Clicked Privacy Policy", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void updateDrawState(@androidx.annotation.NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.BLUE);
                ds.setUnderlineText(false);
            }
        };

        spannableString.setSpan(privacyClickableSpan, privacyStart, privacyEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        binding.textview4.setText(spannableString);
        binding.textview4.setMovementMethod(LinkMovementMethod.getInstance());
    }
}