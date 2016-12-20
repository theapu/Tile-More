package in.theapu.tilemore;

/**
 * Created by apu on 21/12/16.
 */

import net.grandcentrix.tray.TrayPreferences;
import android.content.Context;

public class TileMoreTrayPreferences extends TrayPreferences {

    public TileMoreTrayPreferences (final Context context) {
        super(context, "ProfileSwitcherPreferences", 1);
    }

}