package com.example.practica4_yharyarias;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VolverInicio#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VolverInicio extends Fragment {

    public VolverInicio() {
        // Required empty public constructor
    }

    public static VolverInicio newInstance() {
        VolverInicio fragment = new VolverInicio();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_volver_inicio, container, false);

        Button btnVolver = (Button) root.findViewById(R.id.btnVolver);
        btnVolver.setOnClickListener(view -> ((MainActivity) getActivity()).switchWindow(new VerArticulos()));

        return root;
    }
}