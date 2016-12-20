package in.theapu.tilemore;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;

public class TileMoreMainActivity extends AppCompatActivity {

    private static final String TAG = "TileMoreActivity";
    //private FloatingActionButton fab1, fab2;

    public TileMore tilemore;

    public Bitmap AddIcon;
    public ImageButton AddButton;

 /*   private GridView gridView;
    ArrayList<Item> gridArray = new ArrayList<Item>();
    TileGridViewAdapter customGridAdapter;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tile_more_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        GridView gridView;

        ArrayList<Item> gridArray = new ArrayList<Item>();
        TileGridViewAdapter customGridAdapter;

        tilemore = new TileMore(this);


        setSupportActionBar(toolbar);


        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        //set grid view item
/*        Bitmap AddIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.add_tile);
        ImageButton AddButton = (ImageButton)findViewById(R.id.button);;

*/
       /* if (tilemore.isTileEnabled()) {
            AddIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.tile_disabled);
            AddButton = (ImageButton) findViewById(R.id.button);
        } else {
            AddIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.add_tile);
            AddButton = (ImageButton) findViewById(R.id.button);
        }*/

  /*      gridArray.add(new Item(AddIcon,"Add Tile",AddButton));
        gridArray.add(new Item(AddIcon,"Add Tile",AddButton));
        gridArray.add(new Item(AddIcon,"Add Tile",AddButton));
        gridArray.add(new Item(AddIcon,"Add Tile",AddButton));
        gridArray.add(new Item(AddIcon,"Add Tile",AddButton));
        gridArray.add(new Item(AddIcon,"Add Tile",AddButton));
        gridArray.add(new Item(AddIcon,"Add Tile",AddButton));
        gridArray.add(new Item(AddIcon,"Add Tile",AddButton));
        gridArray.add(new Item(AddIcon,"Add Tile",AddButton));
        gridArray.add(new Item(AddIcon,"Add Tile",AddButton));
        gridArray.add(new Item(AddIcon,"Add Tile",AddButton));
        gridArray.add(new Item(AddIcon,"Add Tile",AddButton));*/

        for(int i = 0; i < 11; i++) {
            if (tilemore.isTileEnabled(i)) {
                AddIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.tile_disabled);
                AddButton = (ImageButton) findViewById(R.id.button);
            } else {
                AddIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.add_tile);
                AddButton = (ImageButton) findViewById(R.id.button);
            }
            gridArray.add(new Item(AddIcon,"Add Tile",AddButton,tilemore.isTileEnabled(i)));
        }

        gridView = (GridView) findViewById(R.id.gridview);
        customGridAdapter = new TileGridViewAdapter(this, R.layout.row_grid, gridArray);
        gridView.setAdapter(customGridAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {
                //Toast.makeText(getApplicationContext(),gridArray.get(position).getTitle(), Toast.LENGTH_SHORT).show();
                AddNewTile(position);
                ImageView imageView = (ImageView) v.findViewById(R.id.item_image);
                imageView.setImageResource(R.drawable.tile_disabled);
                ImageButton button = (ImageButton) v.findViewById(R.id.button);
                button.setVisibility(View.VISIBLE);
                tilemore.preferences.put("tile_enabled" + "_" + position, true);
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

    private void AddNewTile(Integer position) {
        Log.v(TAG, "in onItemClick fab1" + this);
        Toast.makeText(this, "Hello Toast Message! This is fab 1", Toast.LENGTH_LONG).show();
        try {
            Class<?> theClass = Class.forName("in.theapu.tilemore.TileMoreTileService" + position );
            enableComponent(this, theClass, true);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void AddCustomTile(Integer position) {
        Log.v(TAG, "in onItemClick fab2" + this);
        Toast.makeText(this, "Hello Toast Message! This is fab 2", Toast.LENGTH_LONG).show();
        try {
            Class<?> theClass = Class.forName("in.theapu.tilemore.TileMoreTileService" + position );
            enableComponent(this, theClass, true);
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
}
