package de.nle.how2knoedel.listener;

import android.view.View;
import android.widget.ImageView;

/**
 * Created by T500 on 17.11.2014.
 */
public class MenuOnClickListener implements View.OnClickListener {

    ImageView menuButton;

    public MenuOnClickListener(ImageView menuButton) {
        this.menuButton = menuButton;
    }

    @Override
    public void onClick(View v) {
    }
}
