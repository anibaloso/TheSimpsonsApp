package cl.inacap.thesimpsonsapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

import cl.inacap.thesimpsonsapp.DTO.Simpsons;
import cl.inacap.thesimpsonsapp.R;

public class SimpsonsListAdapter extends ArrayAdapter<Simpsons> {

    private List<Simpsons> simpsons;
    private Activity contexto;

    public SimpsonsListAdapter(@NonNull Activity context, int resource, @NonNull List<Simpsons> objects) {
        super(context, resource, objects);
        this.simpsons = objects;
        this.contexto = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.contexto.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.listar_simpsons, null, true);
        TextView nombreTxt = rowView.findViewById(R.id.nombre_simpsons);
        TextView fraseTxt = rowView.findViewById(R.id.frase_simpsons);
        ImageView imagenSimpson = rowView.findViewById(R.id.imagen_simpsons);
        nombreTxt.setText(this.simpsons.get(position).getCharacter());
        fraseTxt.setText(this.simpsons.get(position).getQuote());
        Picasso.get().load(this.simpsons.get(position).getImage())
                .resize(300,300)
                .centerCrop()
                .into(imagenSimpson);
        return rowView;
    }
}
