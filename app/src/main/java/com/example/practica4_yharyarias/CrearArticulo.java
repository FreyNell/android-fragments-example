package com.example.practica4_yharyarias;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.practica4_yharyarias.persistencia.Conexion;
import com.google.android.material.textfield.TextInputEditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CrearArticulo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CrearArticulo extends Fragment {

    public CrearArticulo() {
        // Required empty public constructor
    }

    public static CrearArticulo newInstance(SQLiteDatabase param1) {
        CrearArticulo fragment = new CrearArticulo();
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

        View root = inflater.inflate(R.layout.fragment_crear_articulo, container, false);

        TextInputEditText tfNombre = root.findViewById(R.id.tfNombre);
        TextInputEditText tfDescripcion = root.findViewById(R.id.tfDescripcion);
        TextInputEditText tfPrecio = root.findViewById(R.id.tfPrecio);
        Button btnAgregar = root.findViewById(R.id.btnAgregar);


        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!TextUtils.isEmpty(tfNombre.getText().toString().trim()) &&
                        !TextUtils.isEmpty(tfDescripcion.getText().toString().trim()) &&
                        !TextUtils.isEmpty(tfPrecio.getText().toString().trim())) {

                    Conexion db = new Conexion(root.getContext());
                    db.agregarArticulo(tfNombre.getText().toString().trim(), tfDescripcion.getText().toString().trim(), tfPrecio.getText().toString().trim());
                    Toast.makeText(getActivity(), "Creado el articulo: " + tfNombre.getText().toString().trim(), Toast.LENGTH_SHORT).show();
                    ((MainActivity) getActivity()).switchWindow(new VerArticulos());
                    db.close();

                } else {
                    Toast.makeText(root.getContext(), "Error, hay campos sin llenar", Toast.LENGTH_SHORT).show();
                }


            }
        });

        return root;
    }
}