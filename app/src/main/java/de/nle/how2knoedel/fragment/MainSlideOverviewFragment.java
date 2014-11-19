package de.nle.how2knoedel.fragment;

import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import de.nle.how2knoedel.R;
import de.nle.how2knoedel.listener.FooterOnClickListener;
import de.nle.how2knoedel.util.RecipeDatabaseHelper;

public class MainSlideOverviewFragment extends Fragment {

    private Resources resources;
    private ListView recipeList;
//    private View view;


    // saves data state per bundle
    public static MainSlideOverviewFragment newInstance(String imageUrl) {

        final MainSlideOverviewFragment mf = new MainSlideOverviewFragment ();
        final Bundle args = new Bundle();
        args.putString("somedata", "somedata");
        mf.setArguments(args);

        return mf;
    }

    public MainSlideOverviewFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String data = getArguments().getString("somedata");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_overview_main_slide, container, false);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        initVars();
        sqlTemplate();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        // Inflate the menu; this adds items to the action bar if it is present.

        // Get the SearchView and set the searchable configuration
//        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//        SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
//        Assumes current activity is the searchable activity
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
//        searchView.setIconifiedByDefault(true); // Do not iconify the widget; expand it by default
    }


    /**
     * db sql access
     */
    private void sqlTemplate() {

        final ArrayList<String> list = new ArrayList<String>();

        // SQL strings
        for (String s : resources.getStringArray(R.array.create))
            list.add(s);

        list.add(resources.getString(R.string.app_name));

        // SQL interface
        SQLiteOpenHelper database = new RecipeDatabaseHelper(this.getActivity());
        SQLiteDatabase connection = database.getWritableDatabase();

        String[] recipes = {"Knoedel", "Kuchen", "Pasta", "Brot",
                "Knoedel", "Kuchen", "Pasta", "Brot",
                "Knoedel", "Kuchen", "Pasta", "Brot"};
        String sql = "insert into recipes(name) values(?)";
        SQLiteStatement insert = connection.compileStatement(sql);
        for (String s : recipes) {
            insert.bindString(1, s);
            insert.execute();
        }
        insert.close();

        Cursor result = connection.rawQuery("select name from recipes", null);
        while (result.moveToNext()) {
            list.add(result.getString(0));
        }

        // list items
        final ArrayAdapter adapter = new ArrayAdapter(this.getActivity(),
                android.R.layout.simple_list_item_1, list);
        recipeList.setAdapter(adapter);
        connection.execSQL("delete from "+ "recipes");
        connection.close();
    }

    /**
     * initialize global variables
     */
    private void initVars() {

        resources = getResources();
        final View view = getView();
        final View rootView = view.getRootView();

        TextView name;
        TextView date;
        TextView rating;
        final ImageView menuButton;
        final ImageView searchButton;
        final TextView thumbScroll;
        final RelativeLayout thumbies = (RelativeLayout) view.findViewById(R.id.thumbies);

        recipeList = (ListView) view.findViewById(R.id.recipeList);
        name = (TextView) rootView.findViewById(R.id.name);
        date = (TextView) rootView.findViewById(R.id.date);
        rating = (TextView) rootView.findViewById(R.id.rating);
        menuButton = (ImageView) view.findViewById(R.id.menuButton);
        searchButton = (ImageView) view.findViewById(R.id.searchButton);
        thumbScroll = (TextView) view.findViewById(R.id.thumbScroll);

        // thumbies
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().openOptionsMenu();
            }
        });

        searchButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                searchButton.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        menuButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                menuButton.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        thumbScroll.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                thumbScroll.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        thumbies.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                thumbies.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        // footer
        TextView[] footer = {name, date, rating};
        View.OnClickListener focl = new FooterOnClickListener(footer);
        name.setOnClickListener(focl);
        date.setOnClickListener(focl);
        rating.setOnClickListener(focl);
    }
}