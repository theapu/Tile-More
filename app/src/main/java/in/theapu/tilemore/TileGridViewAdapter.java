package in.theapu.tilemore;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TileGridViewAdapter extends ArrayAdapter<Item> {
    Context context;
    int layoutResourceId;

    ArrayList<Item> data = new ArrayList<Item>();

    public TileGridViewAdapter(Context context, int layoutResourceId,
                                 ArrayList<Item> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        RecordHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new RecordHolder();
            holder.txtTitle = (TextView) row.findViewById(R.id.item_text);
            holder.imageItem = (ImageView) row.findViewById(R.id.item_image);
            holder.buttonItem = (ImageButton) row.findViewById(R.id.button);
            row.setTag(holder);
        } else {
            holder = (RecordHolder) row.getTag();
        }

        Item item = data.get(position);
//        holder.txtTitle.setText(item.getTitle());
//        holder.imageItem.setImageBitmap(item.getImage());

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