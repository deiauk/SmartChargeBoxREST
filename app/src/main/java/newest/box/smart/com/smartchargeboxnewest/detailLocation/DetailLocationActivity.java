package newest.box.smart.com.smartchargeboxnewest.detailLocation;

import android.os.Binder;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import butterknife.BindView;
import butterknife.ButterKnife;
import newest.box.smart.com.smartchargeboxnewest.R;

public class DetailLocationActivity extends AppCompatActivity {
    public static final String MARKER_ID = "MARKER_ID";

    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    @BindView(R.id.detail_tabs)
    TabLayout mTabLayout;
    private TabPagerAdapter tabPagerAdapter;
    private String markerId;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_location);
        ButterKnife.bind(this);

        markerId = getIntent().getStringExtra(MARKER_ID);

        Log.d("SDSIDGSDD", markerId + "");

        tabPagerAdapter = new TabPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(tabPagerAdapter);
        mTabLayout.setTabsFromPagerAdapter(tabPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        setToolbar("");


        /*


        token = PreferencesManager.getInstance(getApplicationContext()).getString(PreferencesManager.SESSION_TOKEN);

        RetrofitSingleton singleton = RetrofitSingleton.getInstance();
        ApiService service = singleton.getService();
        Call<LocationResponse> call = service.getLocation(token, id);
        call.enqueue(new Callback<LocationResponse>() {
            @Override
            public void onResponse(Call<LocationResponse> call, Response<LocationResponse> response) {
                if(response.isSuccessful()) {
                    LocationResult locationResult = response.body().getResult();
//
//                    type.setText(locationResult.getData().getType());
//                    address.setText(locationResult.getData().getAddress());
//                    city.setText(locationResult.getData().getCity());
//                    country.setText(locationResult.getData().getCountry());

                    Log.d("SDSIDGDSD", locationResult.getData().getId() + " " + locationResult.getData().getAddress() + " " + locationResult.getData().getCity()
                    + " " + locationResult.getData().getCity() + " " + locationResult.getData().getCountry() + " " + locationResult.getData().getName()
                    );
                }

                Log.d("SDSIDGDSD", response.isSuccessful() + " " + response.message());
            }

            @Override
            public void onFailure(Call<LocationResponse> call, Throwable t) {
                Log.d("SDSIDGDSD", "onFailure" + " " + t.getMessage());
            }
        });


        */

    }

    private void setToolbar(String address) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            setTitle(address);
        }
    }

    private class TabPagerAdapter extends FragmentStatePagerAdapter {

        private TabPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = position == 0 ? new DetailLocationFragment() : new CommentFragment();
            Bundle bundle = new Bundle();
            bundle.putString(DetailLocationFragment.MARKER_ID, markerId);
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return position == 0 ? "Apie" : "Komentarai";
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}

