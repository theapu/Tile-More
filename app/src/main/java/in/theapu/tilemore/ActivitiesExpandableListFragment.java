package in.theapu.tilemore;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by apu on 25/12/16.
 */

public class ActivitiesExpandableListFragment extends Fragment {

    private static final String TAG = "TileMoreExpanded List";

    //List<String> packagenamelist = new ArrayList<String>();
    //HashMap<String, List<String>> packagactivitylist;

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    public List<ExpandableListParentItem> listDataHeader = new ArrayList<ExpandableListParentItem>();
    public HashMap<ExpandableListParentItem, List<ExpandableListChildItem>> listDataChild
            = new HashMap<ExpandableListParentItem, List<ExpandableListChildItem>>();

    View v;
//    ExpandableListAdapter mAdapter;
//    ExpandableListView lv;
    Context con;

    public Bitmap AddIcon;
    public String AddTitle;
    public int Addcount;

    //public ProgressBar spinner;

    public ActivitiesExpandableListFragment() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //v= inflater.inflate(R.layout.expanded_list_view,
        //        container, false);

        v = inflater.inflate(R.layout.expanded_list_view, null);

        ProgressBar spinner = (ProgressBar) getActivity().findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);


        expListView = (ExpandableListView) v.findViewById(R.id.lvExp);

        //prepareListData();

        con = v.getContext();
        //PackageManager pm = con.getPackageManager();

        //spinner.setVisibility(View.GONE);


        GenerateActivityList(con);

        listAdapter = new ExpandableListAdapter(con, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);


        // Listview Group click listener
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                Toast.makeText(con, "Hello Toast Message! This is parent item clicked", Toast.LENGTH_LONG).show();
                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(con, "Hello Toast Message! This is parent item expanded", Toast.LENGTH_LONG).show();
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(con, "Hello Toast Message! This is parent item collapsed", Toast.LENGTH_LONG).show();
            }
        });

        // Listview on child click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(
                        con,
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        });

        return v;
    }


 /*   @Override
    public void onCreate(Bundle savedInstanceState) {
        {
            super.onCreate(savedInstanceState);
            //v.setContentView(R.layout.activity_tile_more_main);

            // get the listview
            expListView = (ExpandableListView) v.findViewById(R.id.lvExp);

            prepareListData();


            // preparing list data

            listAdapter = new ExpandableListAdapter(con, listDataHeader, listDataChild);

            // setting list adapter
            expListView.setAdapter(listAdapter);
            // Listview Group click listener
            expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

                @Override
                public boolean onGroupClick(ExpandableListView parent, View v,
                                            int groupPosition, long id) {
                    // Toast.makeText(getApplicationContext(),
                    // "Group Clicked " + listDataHeader.get(groupPosition),
                    // Toast.LENGTH_SHORT).show();
                    return false;
                }
            });
        }


    }

    /*
     * Preparing the list data
     */

    public void GenerateActivityList(Context con) {

        //packagactivitylist = new HashMap<String, List<String>>();
        List<ExpandableListChildItem> packageactivitychildlist;

        PackageManager pm = con.getPackageManager();

        List<ApplicationInfo> packages = pm.getInstalledApplications(0);
        Collections.sort(packages, new ApplicationInfo.DisplayNameComparator(pm));
        for(ApplicationInfo pack : packages) {
            try {
                packageactivitychildlist = new ArrayList<ExpandableListChildItem>();
                String activityname;
                String appname = pm.getApplicationLabel(pm.getApplicationInfo(pack.packageName, PackageManager.GET_META_DATA)).toString();
                Log.v(TAG, appname);
                Log.v(TAG,pack.packageName);
                AddTitle = appname;
                Drawable default_bd = pm.getApplicationIcon(pm.getApplicationInfo(pack.packageName, PackageManager.GET_META_DATA));
                //BitmapDrawable default_bd = (BitmapDrawable)pm.getDefaultActivityIcon();
                Bitmap default_b = drawableToBitmap(default_bd);
                AddIcon = default_b;
                listDataHeader.add(new ExpandableListParentItem(AddIcon,appname,0));
                ActivityInfo[] activityInfo = pm.getPackageInfo(pack.packageName, PackageManager.GET_ACTIVITIES).activities;
                if(activityInfo!=null)
                {
                    packageactivitychildlist.clear();
                    for(int i=0; i<activityInfo.length; i++)
                    {
                        Log.i(TAG,i + "="+ activityInfo[i].name);
                        activityname = activityInfo[i].name;
                        //packageactivitychildlist.add(activityname);
                        //BitmapDrawable act_default_bd = (BitmapDrawable)pm.getDefaultActivityIcon();
                        //Bitmap act_default_b = default_bd.getBitmap();
                        listDataChild.put(listDataHeader.get(packages.indexOf(pack)),packageactivitychildlist);
                        packageactivitychildlist.add(new ExpandableListChildItem(default_b,activityname));
                    }

                } else {
                    listDataChild.put(listDataHeader.get(packages.indexOf(pack)),packageactivitychildlist);
                    packageactivitychildlist.add(new ExpandableListChildItem(default_b,"No Activities"));
                }
                Log.i(TAG, packages.indexOf(pack) + "------------------");
                // Log.i(TAG,"AA"+ listDataChild.toString());
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        //Log.v(TAG,packagenamelist.toString());
    }

    public static Bitmap drawableToBitmap (Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable)drawable).getBitmap();
        }

        int width = drawable.getIntrinsicWidth();
        width = width > 0 ? width : 1;
        int height = drawable.getIntrinsicHeight();
        height = height > 0 ? height : 1;

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }


}