package com.example.wanhao.homemakingapp.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wanhao.homemakingapp.R;
import com.example.wanhao.homemakingapp.config.ApiConstant;
import com.example.wanhao.homemakingapp.customizeview.NoScrollViewPager;
import com.example.wanhao.homemakingapp.fragment.DocumentFragment;
import com.example.wanhao.homemakingapp.fragment.MainFragment;
import com.example.wanhao.homemakingapp.fragment.OtherFragment;
import com.example.wanhao.homemakingapp.presenter.CoursePresenter;
import com.example.wanhao.homemakingapp.util.SaveDataUtil;
import com.example.wanhao.homemakingapp.view.ICourseView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class CourseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener ,View.OnClickListener,ICourseView {
    private static final String TAG = "CourseActivity";

    @BindView(R.id.ac_course_drawer_layout) DrawerLayout drawer;
    @BindView(R.id.ac_course_nav_view) NavigationView navigationView;

    @BindView(R.id.ac_main_bottom)
    BottomNavigationView navigation;
    @BindView(R.id.ac_main_viewpager)
    NoScrollViewPager viewPager;

    private OtherFragment otherFragment;
    private DocumentFragment documentFragment;
    private MainFragment mainFragment;
    private List<Fragment> fragmentList;
    private FragmentPagerAdapter adapter;

    private CircleImageView headImage;
    private TextView nameText;

    private CoursePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        ButterKnife.bind(this);
        init();
        initView();
        initEvent();
        presenter.getData();
    }

    private void init(){


        presenter = new CoursePresenter(this,this);

        navigationView.setNavigationItemSelectedListener(this);

        View view = navigationView.getHeaderView(0);
        headImage = view.findViewById(R.id.ac_course_headimage);
        nameText = view.findViewById(R.id.ac_course_name);

        headImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CourseActivity.this,UserMessageActivity.class));
            }
        });
    }

    private void initView() {

        fragmentList = new ArrayList<>();
        mainFragment = new MainFragment();
        otherFragment = new OtherFragment();
        documentFragment = new DocumentFragment();

        fragmentList.add(mainFragment);
        fragmentList.add(documentFragment);
        fragmentList.add(otherFragment);

        adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }
            @Override
            public int getCount() {
                return fragmentList.size();
            }
        };
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(5);

    }

    private void initEvent() {
        viewPager.setNoScroll(true);
        viewPager.setOverScrollMode(viewPager.OVER_SCROLL_NEVER);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                invalidateOptionsMenu();
                switch (item.getItemId()) {
                    case R.id.main_menu_home:
                        viewPager.setCurrentItem(0);
                        return true;
                    case R.id.main_menu_message:
                        viewPager.setCurrentItem(1);
                        return true;
                    case R.id.main_menu_bbs:
                        viewPager.setCurrentItem(2);
                        return true;
                }
                return false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.course, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            startActivityForResult(new Intent(this,UserMessageActivity.class),0);
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
            SaveDataUtil.saveToSharedPreferences(this, ApiConstant.USER_TOKEN,"");
            startActivity(new Intent(this,LodingActivity.class));
            finish();
        }

        DrawerLayout drawer = findViewById(R.id.ac_course_drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private long exitTime = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis()-exitTime) > 2000){
                Toast.makeText(this,"再按一次退出",Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    protected void onResume(){
        super.onResume();
        presenter.getData();
    }



    @Override
    public void setData(Bitmap bitmap, String name) {
        headImage.setImageBitmap(bitmap);
        nameText.setText(name);
    }

    @Override
    public void setHead(Bitmap bitmap) {
        headImage.setImageBitmap(bitmap);
    }

    @Override
    public void setName(String name) {
        nameText.setText(name);
    }
}
