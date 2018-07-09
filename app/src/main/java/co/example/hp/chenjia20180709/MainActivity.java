package co.example.hp.chenjia20180709;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import co.example.hp.chenjia20180709.mvp.view.fragment.ForFragment;
import co.example.hp.chenjia20180709.mvp.view.fragment.FoverFragment;
import co.example.hp.chenjia20180709.mvp.view.fragment.OneFragment;
import co.example.hp.chenjia20180709.mvp.view.fragment.ThereFragment;
import co.example.hp.chenjia20180709.mvp.view.fragment.TwoFragment;


public class MainActivity extends AppCompatActivity {

    private SimpleDraweeView btm01;
    private ImageView btm02;
    private TabLayout mainTab;
    private ViewPager mainViewpage;
    private List<Fragment> fragments=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainTab = findViewById(R.id.main_tab);
        mainViewpage = findViewById(R.id.main_viewpage);
        btm01 = findViewById(R.id.btm01);
        btm02 = findViewById(R.id.btm02);
        OneFragment oneFragment = new OneFragment();
        TwoFragment twoFragment = new TwoFragment();
        ThereFragment thereFragment = new ThereFragment();
        ForFragment forFragment = new ForFragment();
        FoverFragment foverFragment = new FoverFragment();
        fragments.add(oneFragment);
        fragments.add(twoFragment);
        fragments.add(thereFragment);
        fragments.add(forFragment);
        fragments.add(foverFragment);

        MyTabAdapter myTabAdapter = new MyTabAdapter(MainActivity.this.getSupportFragmentManager(), fragments);
        mainViewpage.setAdapter(myTabAdapter);
        mainTab.addTab(mainTab.newTab().setText("热门"));

        mainTab.setupWithViewPager(mainViewpage);



        btm01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, QiTa.class);
                startActivity(intent);
            }
        });
        btm02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, QiTa.class);
                startActivity(intent);
            }
        });


    }

}
