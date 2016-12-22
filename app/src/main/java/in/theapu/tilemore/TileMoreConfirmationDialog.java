package in.theapu.tilemore;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by apu on 22/12/16.
 */

public class TileMoreConfirmationDialog {

    interface DialogInputInterface {
        void onCancel();
        void onResult();
    }

    public static void promptForResult(String dlgTitle, String dlgMessage, final DialogInputInterface dlg, Context context) {
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle(dlgTitle);
        alert.setMessage(dlgMessage);

        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // ** HERE IS WHERE THE MAGIC HAPPENS! **
                dlg.onResult();
                dialog.dismiss();
                return;
            }
        });

        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dlg.onCancel();
                dialog.dismiss();
                return;
            }
        });
        alert.show();
    }
}
