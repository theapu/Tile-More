package in.theapu.tilemore;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.List;


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
