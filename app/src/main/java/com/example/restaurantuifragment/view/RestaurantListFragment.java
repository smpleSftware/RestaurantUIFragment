package com.example.restaurantuifragment.view;

import static com.example.restaurantuifragment.data.BasicDatabase.loginEmail;
import static com.example.restaurantuifragment.data.BasicDatabase.loginPassword;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.restaurantuifragment.R;
import com.example.restaurantuifragment.adapter.RecyclerAdapter;
import com.example.restaurantuifragment.data.BasicDatabase;
import com.example.restaurantuifragment.databinding.FragmentRestaurantListBinding;
import com.example.restaurantuifragment.model.RestaurantModel;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;

public class RestaurantListFragment extends Fragment {

    private FragmentRestaurantListBinding binding;
    private ArrayList<RestaurantModel> restaurantModels;

    private SharedPreferences preferences;



    public RestaurantListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRestaurantListBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        preferences = BasicDatabase.getInstance(requireContext());
        String email = preferences.getString(loginEmail,"");
        String password = preferences.getString(loginPassword,"");

        if (email.isEmpty() || password.isEmpty()){
            NavDirections navDirections = RestaurantListFragmentDirections.actionRestaurantListFragmentToLoginFragment();
            Navigation.findNavController(view).navigate(navDirections);
            return;
        }

        binding.menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preferences.edit().remove(loginEmail).apply();
                preferences.edit().remove(loginPassword).apply();

                NavDirections navDirections = RestaurantListFragmentDirections.actionRestaurantListFragmentToLoginFragment();
                Navigation.findNavController(view).navigate(navDirections);
            }
        });

        restaurantModels = new ArrayList<>();
        getData();
    }

    private void getData() {
        RestaurantModel model1 = new RestaurantModel("ABC Restaurant",5,true,18,R.drawable.rectangle);
        RestaurantModel model2 = new RestaurantModel("Italian Restaurant",3,false,10,R.drawable.rectangle);
        RestaurantModel model3 = new RestaurantModel("Mumbai Restaurant",4,true,15,R.drawable.rectangle);
        RestaurantModel model4 = new RestaurantModel("Nemo Restaurant",2,false,12,R.drawable.rectangle);

        restaurantModels.add(model1);
        restaurantModels.add(model2);
        restaurantModels.add(model3);
        restaurantModels.add(model4);
        restaurantModels.add(model1);
        restaurantModels.add(model2);
        restaurantModels.add(model3);
        restaurantModels.add(model4);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        RecyclerAdapter adapter = new RecyclerAdapter(restaurantModels);
        binding.recyclerView.setAdapter(adapter);
    }
}