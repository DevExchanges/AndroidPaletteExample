package info.devexchanges.androidpalette;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private SwatchesAdapter swatchesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list_color);

        swatchesAdapter = new SwatchesAdapter(this);
        listView.setAdapter(swatchesAdapter);

        //get bitmap from drawable resource
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.hanoi_capital);

        //generating Palette from this bitmap
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {

            @Override
            public void onGenerated(Palette palette) {
                for (Palette.Swatch swatch : palette.getSwatches()) {
                    swatchesAdapter.add(swatch);
                }
                swatchesAdapter.sortSwatches();
                swatchesAdapter.notifyDataSetChanged();
            }
        });
    }
}
