package in.theapu.tilemore;

import android.graphics.Bitmap;

/**
 * Created by apu on 25/12/16.
 */

public class ExpandableListParentItem {
    Bitmap icon;
    String title;
    int count;


    public ExpandableListParentItem(Bitmap icon, String title, int count) {
        super();
        this.icon = icon;
        this.title = title;
        this.count = count;
    }
    public Bitmap getExpandableListParentItemIcon() {
        return icon;
    }

    public String getExpandableListParentItemTitle() {
        return title;
    }

    /*
        public ImageButton getButton() {
            return delete;
        }
    */
    public int getExpandableListParentItemcount() {
        return count;
    }
/*    public void setButtonStatus() {
        buttonstatus = false;
    }*/
    //   public void setButton(ImageButton delete) {
    //      this.delete = delete;
    //   }

}