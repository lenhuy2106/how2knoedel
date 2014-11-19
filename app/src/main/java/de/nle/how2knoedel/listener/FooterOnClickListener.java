package de.nle.how2knoedel.listener;

import android.content.res.Resources;
import android.view.View;
import android.widget.TextView;

import de.nle.how2knoedel.KnoedelContext;
import de.nle.how2knoedel.R;

/**
 * Created by T500 on 16.11.2014.
 */
public class FooterOnClickListener implements View.OnClickListener {

    private final Resources resources = KnoedelContext.getContext().getResources();
    private TextView[] footer;
    private TextView select;

    public FooterOnClickListener(TextView... footer) {
      this.footer = footer;
    }

    @Override
    public void onClick(View v) {

        select = (TextView) v;

        for (TextView tv : footer) {
            ((TextView) tv).setTextColor(resources.getColor(R.color.grey));
        }

        select.setTextColor(resources.getColor(R.color.white));
    }
}
