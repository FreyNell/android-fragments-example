package com.example.practica4_yharyarias;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.practica4_yharyarias.persistencia.Articulo;
import com.example.practica4_yharyarias.persistencia.Conexion;
import com.google.android.material.textfield.TextInputEditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ActualizarArticulo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ActualizarArticulo extends Fragment {

    Articulo a;

    public ActualizarArticulo() {
        // Required empty public constructor
    }

    public ActualizarArticulo(Articulo a) {
        this.a = a;
    }

    public static ActualizarArticulo newInstance() {
        ActualizarArticulo fragment = new ActualizarArticulo();
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
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_actualizar_articulo, container, false);

        TextInputEditText tfNombre = root.findViewById(R.id.tfNombre);
        TextInputEditText tfDescripcion = root.findViewById(R.id.tfDescripcion);
        TextInputEditText tfPrecio = root.findViewById(R.id.tfPrecio);
        Button btnActualizar = root.findViewById(R.id.btnActualizar);

        tfNombre.setText(a.getNombre());
        tfNombre.setEnabled(false);
        tfDescripcion.setText(a.getDescripcion());
        tfPrecio.setText(Float.toString(a.getPrecio()));

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!TextUtils.isEmpty(tfNombre.getText().toString().trim()) &&
                        !TextUtils.isEmpty(tfDescripcion.getText().toString().trim()) &&
                        !TextUtils.isEmpty(tfPrecio.getText().toString().trim())) {

                    Conexion db = new Conexion(root.getContext());
                    db.actualizarArticulo(Integer.toString(a.getCodigo()).trim(), tfDescripcion.getText().toString().trim(), tfPrecio.getText().toString().trim());
                    Toast.makeText(getActivity(),"Actualizado: "+a.getNombre(),Toast.LENGTH_SHORT).show();
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