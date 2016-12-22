package in.theapu.tilemore.TileMoreTileService;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Icon;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;
import android.util.Log;
import android.widget.Toast;
import in.theapu.tilemore.R;


/**
 * Created by apu on 18/12/16.
 */

public class TileMoreTileService5 extends TileService {

    private static final String TAG = "QSTILE5";

    @Override
    public void onTileAdded() {
        Log.i(TAG, "Method: onTileAdded()");
    }

    @Override
    public void onTileRemoved() {
        super.onTileRemoved();
        Log.i(TAG, "Method: onTileRemoved()");
    }

    @Override
    public void onStartListening() {
        super.onStartListening();
        changeTileState(getQsTile().getState());
        Log.i(TAG, "Method: onStartListening()");
    }

    @Override
    public void onStopListening() {
        super.onStopListening();
        Log.i(TAG, "Method: onStopListening()");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "Method: onCreate()");
    }

    @Override
    public void onClick() {
        super.onClick();
        Log.i(TAG, "Tile State: " + getQsTile().getState());

        if (!isLocked()) {
            updateTile();
        } else {
            unlockAndRun(new Runnable() {
                @Override
                public void run() {
                    updateTile();
                }
            });
        }
    }

    private void updateTile() {
        if (Tile.STATE_ACTIVE == getQsTile().getState()) {
            Toast.makeText(TileMoreTileService5.this, "New State: INACTIVE", Toast.LENGTH_SHORT).show();
            changeTileState(Tile.STATE_INACTIVE);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setIcon(R.mipmap.ic_launcher);
            builder.setTitle("Android Stammtisch");
            builder.setMessage("Android Stammtisch Quick Settings Tile deactivated!" + getQsTile().getState());
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    // nop
                }
            });
            showDialog(builder.create());
        } else if (Tile.STATE_INACTIVE == getQsTile().getState()) {
            Toast.makeText(TileMoreTileService5.this, "New State: ACTIVE", Toast.LENGTH_SHORT).show();
            changeTileState(Tile.STATE_ACTIVE);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setIcon(R.mipmap.ic_launcher);
            builder.setTitle("Android Stammtisch");
            builder.setMessage("Android Stammtisch Quick Settings Tile activated!" + getQsTile().getState());
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    // nop
                }
            });
            showDialog(builder.create());
        }
    }

    private void changeTileState(int newState) {
        getQsTile().setIcon(Icon.createWithResource(TileMoreTileService5.this, newState == Tile.STATE_INACTIVE ? R.drawable.tile_disabled : R.drawable.tile_enabled));
        getQsTile().setState(newState);
        getQsTile().updateTile();
    }
}
