package in.theapu.tilemore;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import android.support.v4.app.FragmentManager;

public class TileMoreMainActivity extends AppCompatActivity {

    private static final String TAG = "TileMoreActivity";

    public TileMore tilemore;

    Context context;

    public Bitmap AddIcon;
    public ImageButton AddButton;
    public ProgressBar spinner;


    FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tile_more_main);

        spinner = (ProgressBar)findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        GridView gridView;

        ArrayList<TileMoreItem> gridArray = new ArrayList<TileMoreItem>();
        TileGridViewAdapter customGridAdapter;

        tilemore = new TileMore(this);


        setSupportActionBar(toolbar);


        for(int i = 0; i < 10; i++) {
            if (tilemore.isTileEnabled(i)) {
                AddIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.tile_disabled);
                AddButton = (ImageButton) findViewById(R.id.button);
            } else {
                AddIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.add_tile);
                AddButton = (ImageButton) findViewById(R.id.button);
            }
            gridArray.add(new TileMoreItem(AddIcon,"Add Tile",AddButton,tilemore.isTileEnabled(i)));
        }

        gridView = (GridView) findViewById(R.id.gridview);
        customGridAdapter = new TileGridViewAdapter(this, R.layout.row_grid, gridArray);
        gridView.setAdapter(customGridAdapter);
        context = this;

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {
                EnableTile(position, context);
                ImageView imageView = (ImageView) v.findViewById(R.id.item_image);
                imageView.setImageResource(R.drawable.tile_disabled);
                ImageButton button = (ImageButton) v.findViewById(R.id.button);
                button.setVisibility(View.VISIBLE);
                tilemore.preferences.put("tile_enabled" + "_" + position, true);

                spinner = (ProgressBar) findViewById(R.id.progressBar);
                spinner.setVisibility(View.VISIBLE);


                //GenerateActivityList(pm);

                ShowActivityList();

                //spinner.setVisibility(View.GONE);

                }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tile_more_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static void EnableTile(Integer position, Context context) {
        Log.v(TAG, "in onItemClick fab1" + context);
        Toast.makeText(context, "Hello Toast Message! This is fab 1", Toast.LENGTH_LONG).show();
        try {
            Class<?> theClass = Class.forName("in.theapu.tilemore.TileMoreTileService.TileMoreTileService" + position );
            enableComponent(context, theClass, true);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void DisableTile(Integer position, Context context) {
        Log.v(TAG, "in onItemClick fab2" + context);
        Toast.makeText(context, "Hello Toast Message! This is fab 2", Toast.LENGTH_LONG).show();
        try {
            Class<?> theClass = Class.forName("in.theapu.tilemore.TileMoreTileService.TileMoreTileService" + position );
            enableComponent(context, theClass, false);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Enable or disable an app component (Activity, BroadcastReceiver,
     * ContentProvider, Service).
     */
    public static void enableComponent(Context context, Class<?> componentClass,
                                       boolean isEnable) {
        int enableFlag = isEnable ? PackageManager.COMPONENT_ENABLED_STATE_ENABLED :
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED;
        context.getPackageManager().setComponentEnabledSetting(
                new ComponentName(context, componentClass),
                enableFlag, PackageManager.DONT_KILL_APP);
    }

 //   public void resetTileImage(int i) {
 //       tilemore.preferences.put("tile_enabled" + "_" + i, false);
 //   }

    private void ShowActivityList() {
//        spinner.setVisibility(View.VISIBLE);

        ActivitiesExpandableListFragment fragment = new ActivitiesExpandableListFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.your_placeholder, fragment)
                .addToBackStack(null)
                .commit();

    }

}
