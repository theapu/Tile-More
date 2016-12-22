package in.theapu.tilemore;

/**
 * Created by apu on 18/12/16.
 */

import android.graphics.Bitmap;
import android.widget.ImageButton;


public class TileMoreItem {
    Bitmap image;
    String title;
    ImageButton delete;
    Boolean buttonstatus;


    public TileMoreItem(Bitmap image, String title, ImageButton delete, Boolean buttonstatus) {
        super();
        this.image = image;
        this.title = title;
        this.delete = delete;
        this.buttonstatus = buttonstatus;
    }
    public Bitmap getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

/*
    public ImageButton getButton() {
        return delete;
    }
*/
    public Boolean getButtonStatus() {
        return buttonstatus;
    }
/*    public void setButtonStatus() {
        buttonstatus = false;
    }*/
 //   public void setButton(ImageButton delete) {
 //      this.delete = delete;
 //   }

}