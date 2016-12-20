package in.theapu.tilemore;

/**
 * Created by apu on 18/12/16.
 */

import android.graphics.Bitmap;
import android.widget.Button;
import android.widget.ImageButton;

/**
 *
 * @author manish.s
 *
 */

public class Item {
    Bitmap image;
    String title;
    ImageButton delete;
    Boolean buttonstatus;

 /*   public Item(Bitmap image, String title, Button delete) {
        super();
        this.image = image;
        this.title = title;
        this.delete = delete;
    }*/
    public Item(Bitmap image, String title, ImageButton delete, Boolean buttonstatus) {
        super();
        this.image = image;
        this.title = title;
        this.delete = delete;
        this.buttonstatus = buttonstatus;
    }
    public Bitmap getImage() {
        return image;
    }
 //   public void setImage(Bitmap image) {
 //       this.image = image;
 //   }
    public String getTitle() {
        return title;
    }
 //   public void setTitle(String title) {
 //       this.title = title;
 //   }
    public ImageButton getButton() {
        return delete;
    }
    public Boolean getButtonStatus() {
        return buttonstatus;
    }
 //   public void setButton(ImageButton delete) {
 //      this.delete = delete;
 //   }

}