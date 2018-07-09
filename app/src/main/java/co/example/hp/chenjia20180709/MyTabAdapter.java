package co.example.hp.chenjia20180709;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by hp on 2018/7/9.
 */

public class MyTabAdapter extends FragmentPagerAdapter{
    private List<Fragment> list;
    private String[] strings=new String[]{"热销","招牌","主食","小吃","饮品"};

    public MyTabAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return strings[position];
    }
}
