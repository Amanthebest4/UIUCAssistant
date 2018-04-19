package edu.illinois.cs.cs125.uiuc_assistant;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private SectionsPageAdapter msetionsPageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        msetionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        ViewPager mViewPager;

        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new HomeTab(), "HOME");
        adapter.addFragment(new MapTab(), "MAP");
        adapter.addFragment(new WeatherTab(), "WEATHER");
        adapter.addFragment(new ClassesTab(), "CLASSES");
        adapter.addFragment(new AssignmentsTab(), "ASSIGNMENTS");
        adapter.addFragment(new AlarmsTab(), "ALARMS");
        viewPager.setAdapter(adapter);
    }
}
