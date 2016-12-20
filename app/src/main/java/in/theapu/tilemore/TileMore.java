package in.theapu.tilemore;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.GridView;
import android.widget.ImageButton;

import java.util.ArrayList;

/**
 * Created by apu on 21/12/16.
 */

public class TileMore {

    private static final String TAG = "TileMore";
    private Context context;

    final TileMoreTrayPreferences preferences;

    public TileMore(Context context) {
        this.context = context;
        preferences = new TileMoreTrayPreferences(this.context);

    }

    public boolean isTileEnabled(int i) {
        Boolean tilestatus = preferences.getBoolean("tile_enabled" + "_" + i, false);
        return tilestatus;
    }

}
