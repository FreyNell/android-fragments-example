package com.example.practica4_yharyarias;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practica4_yharyarias.persistencia.Articulo;
import com.example.practica4_yharyarias.persistencia.Conexion;

import java.util.List;

public class AdaptadorArticulos extends RecyclerView.Adapter<AdaptadorArticulos.ViewHolderArticulos> {

    List<Articulo> listaArticulos;
    FragmentActivity context;

    public AdaptadorArticulos(List<Articulo> listaArticulos, FragmentActivity context) {
        this.listaArticulos = listaArticulos;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderArticulos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_articulos, null, false);
        return new ViewHolderArticulos(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderArticulos holder, int position) {
        holder.asignarArticulos(listaArticulos.get(position));
    }

    @Override
    public int getItemCount() {
        return listaArticulos.size();
    }

    public class ViewHolderArticulos extends RecyclerView.ViewHolder {

        TextView nombre;
        TextView codigo;
        TextView descripcion;
        TextView precio;
        Button btnEdit;
        Button btnRemove;
        FragmentActivity context;

        public ViewHolderArticulos(@NonNull View itemView, FragmentActivity context) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nombre);
            codigo = itemView.findViewById(R.id.codigo);
            descripcion = itemView.findViewById(R.id.descripcion);
            precio = itemView.findViewById(R.id.precio);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnRemove = itemView.findViewById(R.id.btnRemove);
            this.context = context;
        }

        public void asignarArticulos(Articulo a) {
            nombre.setText(a.getNombre().trim());
            codigo.setText(Integer.toString(a.getCodigo()).trim());
            descripcion.setText(a.getDescripcion().trim());
            precio.setText(Float.toString(a.getPrecio()).trim());
            btnEdit.setOnClickListener(view -> ((MainActivity) context).switchWindow(new ActualizarArticulo(a)));
            btnRemove.setOnClickListener(view -> {
                Conexion c = new Conexion(context);
                c.eliminarArticulo(Integer.toString(a.getCodigo()));
                Toast.makeText(context, "Se ha eliminado: " + a.getNombre(), Toast.LENGTH_SHORT).show();
                c.close();
                ((MainActivity) context).switchWindow(new VerArticulos());
            });
        }
    }
}
