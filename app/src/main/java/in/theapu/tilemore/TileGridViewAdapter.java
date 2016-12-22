package in.theapu.tilemore;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TileGridViewAdapter extends ArrayAdapter<TileMoreItem> {

    private static final String TAG = "TileGridViewAdapter";

    Context context;
    int layoutResourceId;

    public TileMore tilemore;

    ArrayList<TileMoreItem> data = new ArrayList<TileMoreItem>();

    public RecordHolder holder = null;

    public TileGridViewAdapter(Context context, int layoutResourceId,
                                 ArrayList<TileMoreItem> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;

        tilemore = new TileMore(context);

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new RecordHolder();
            holder.txtTitle = (TextView) row.findViewById(R.id.item_text);
            holder.imageItem = (ImageView) row.findViewById(R.id.item_image);
            holder.buttonItem = (ImageButton) row.findViewById(R.id.button);
            holder.buttonItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final RecordHolder holder = (RecordHolder) ((View) v.getParent()).getTag();

                    TileMoreConfirmationDialog.promptForResult("Confirmation",
                            "Are you sure you want to delete this tile?",
                            new TileMoreConfirmationDialog.DialogInputInterface(){

                        public void onCancel() {
                            // user has canceled the dialog by hitting cancel
                        }
                        public void onResult() {

                            holder.buttonItem.setVisibility(View.GONE);
                            holder.imageItem.setImageResource(R.drawable.add_tile);
                            tilemore.preferences.put("tile_enabled" + "_" + position, false);
                            TileMoreMainActivity.DisableTile(position, context);

                        }
                        },context);

                }
            });
            row.setTag(holder);
        } else {
            holder = (RecordHolder) row.getTag();
        }

        TileMoreItem item = data.get(position);

        holder.txtTitle.setText(item.getTitle());
        holder.imageItem.setImageBitmap(item.getImage());
        holder.imageItem.setLayoutParams(new RelativeLayout.LayoutParams(200, 150));
        Boolean buttonstatus = item.getButtonStatus();
        if (buttonstatus) {
            holder.buttonItem.setVisibility(View.VISIBLE);
        } else
        {
            holder.buttonItem.setVisibility(View.GONE);
        }

        return row;

    }

    static class RecordHolder {
        TextView txtTitle;
        ImageView imageItem;
        ImageButton buttonItem;
    }

}