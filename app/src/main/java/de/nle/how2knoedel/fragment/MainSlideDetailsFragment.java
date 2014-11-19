package de.nle.how2knoedel.fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import de.nle.how2knoedel.R;

/**
 * Created by T500 on 17.11.2014.
 */
public class MainSlideDetailsFragment extends Fragment {

    private Resources resources;
    private TextView name;
    private TextView date;
    private TextView rating;
    private ImageView menuButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String data = getArguments().getString("somedata");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_details_main_slide, container, false);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initVars();
    }

    /**
     * initialize global variables
     */
    private void initVars() {

        resources = getResources();
        View view = getView();

//        name = (TextView) view.findViewById(R.id.name);
//        date = (TextView) view.findViewById(R.id.date);
//        rating = (TextView) view.findViewById(R.id.rating);
        menuButton = (ImageView) view.findViewById(R.id.menuButton);

        // footer
//        TextView[] footer = {name, date, rating};
//        View.OnClickListener focl = new FooterOnClickListener(footer);
//        name.setOnClickListener(focl);
//        date.setOnClickListener(focl);
//        rating.setOnClickListener(focl);

        // thumbies
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().openOptionsMenu();
            }
        });
    }
}
