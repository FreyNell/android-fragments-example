package com.example.practica4_yharyarias;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practica4_yharyarias.persistencia.Articulo;
import com.example.practica4_yharyarias.persistencia.Conexion;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class VerArticulos extends Fragment {

    List<Articulo> listaArticulos;
    RecyclerView recycler;

    public VerArticulos() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_ver_articulos, container, false);

        FloatingActionButton btnCrear = root.findViewById(R.id.btnCrear);
        btnCrear.setOnClickListener(view -> ((MainActivity) getActivity()).switchWindow(new CrearArticulo()));

        Conexion db = new Conexion(root.getContext());
        listaArticulos = db.consultarArticulos();

        recycler = root.findViewById(R.id.recyclerId);
        recycler.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));
        AdaptadorArticulos adaptador = new AdaptadorArticulos(listaArticulos, getActivity());
        recycler.setAdapter(adaptador);

        FloatingActionButton btnSalir = root.findViewById(R.id.btnSalir);
        btnSalir.setOnClickListener(view -> {
            ((MainActivity) getActivity()).finish();
            System.exit(0);
        });

        SearchView vBuscar = root.findViewById(R.id.vBuscar);
        vBuscar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (TextUtils.isEmpty(s)) {
                    recycler.setAdapter(new AdaptadorArticulos(listaArticulos, getActivity()));
                } else {
                    ArrayList<Articulo> lista2 = new ArrayList<Articulo>();
                    listaArticulos.stream().filter(articulo -> articulo.getDescripcion().startsWith(s) || Integer.toString(articulo.getCodigo()).startsWith(s) ).forEach(articulo -> lista2.add(articulo));
                    recycler.setAdapter(new AdaptadorArticulos(lista2, getActivity()));
                }
                return false;
            }
        });

        return root;
    }
}