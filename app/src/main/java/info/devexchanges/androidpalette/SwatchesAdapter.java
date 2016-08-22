package info.devexchanges.androidpalette;

import android.content.Context;
import android.support.v7.graphics.Palette;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.Comparator;

public class SwatchesAdapter extends ArrayAdapter<Palette.Swatch> {

    public SwatchesAdapter(Context context) {
        super(context, 0);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.color_item, parent, false);
            holder.view = (TextView) convertView.findViewById(R.id.view);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.view.setBackgroundColor(getItem(position).getRgb());
        holder.view.setTextColor(getItem(position).getBodyTextColor());
        holder.view.setText(String.format("Population: %d", getItem(position).getPopulation()));

        return convertView;
    }

    public void sortSwatches() {
        sort(new Comparator<Palette.Swatch>() {
            @Override
            public int compare(Palette.Swatch lhs, Palette.Swatch rhs) {
                return rhs.getPopulation() - lhs.getPopulation();
            }
        });
    }

    public class ViewHolder {
        TextView view;
    }
}

