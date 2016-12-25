package in.theapu.tilemore;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.common.images.WebImage;

import java.util.HashMap;
import java.util.List;

/**
 * Created by apu on 25/12/16.
 */

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<ExpandableListParentItem> listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<ExpandableListParentItem, List<ExpandableListChildItem>> listDataChild;

    public ExpandablelistParentRecordHolder expandablelistparentholder = null;

    public ExpandablelistChildRecordHolder expandablelistchildholder = null;

    public ExpandableListAdapter(Context context, List<ExpandableListParentItem> listDataHeader,
                                 HashMap<ExpandableListParentItem, List<ExpandableListChildItem>> listChildData) {
        this.context = context;
        this.listDataHeader = listDataHeader;
        this.listDataChild = listChildData;
    }
    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.listDataChild.get(this.listDataHeader.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }
    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final ExpandableListChildItem childItem = (ExpandableListChildItem) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.expanded_list_item, null);
        }

        expandablelistchildholder = new ExpandablelistChildRecordHolder();

        expandablelistchildholder.txtTitle = (TextView) convertView
                .findViewById(R.id.lblListItem);
        expandablelistchildholder.imageItem = (ImageView) convertView
                .findViewById(R.id.lblListImage);

        convertView.setTag(expandablelistchildholder);

        expandablelistchildholder.txtTitle.setText(childItem.getExpandableListParentItemTitle());
        expandablelistchildholder.imageItem.setImageBitmap(childItem.getExpandableListParentItemIcon());
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.listDataChild.get(this.listDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        ExpandableListParentItem headerTitle = (ExpandableListParentItem) getGroup(groupPosition);

        if (convertView == null) {
            //context = parent.getContext();
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.expanded_list_group, null);
        }

        expandablelistparentholder = new ExpandablelistParentRecordHolder();

        expandablelistparentholder.txtTitle = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        expandablelistparentholder.imageItem = (ImageView) convertView
                .findViewById(R.id.lblListHeaderImage);

        convertView.setTag(expandablelistparentholder);

        expandablelistparentholder.txtTitle.setTypeface(null, Typeface.BOLD);
        expandablelistparentholder.txtTitle.setText(headerTitle.getExpandableListParentItemTitle());
        expandablelistparentholder.imageItem.setImageBitmap(headerTitle.getExpandableListParentItemIcon());

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    static class ExpandablelistParentRecordHolder {
        ImageView imageItem;
        TextView txtTitle;
        int count;
    }

    static class ExpandablelistChildRecordHolder {
        ImageView imageItem;
        TextView txtTitle;
    }

}
