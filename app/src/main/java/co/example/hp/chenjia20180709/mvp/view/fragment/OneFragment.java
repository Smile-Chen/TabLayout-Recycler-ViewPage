package co.example.hp.chenjia20180709.mvp.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import co.example.hp.chenjia20180709.R;
import co.example.hp.chenjia20180709.app.MyApp;
import co.example.hp.chenjia20180709.http.HttpUtils;
import co.example.hp.chenjia20180709.mvp.model.OneBean;
import co.example.hp.chenjia20180709.mvp.view.adapter.MyAdapter;
import co.example.hp.chenjia20180709.mvp.view.adapter.OneRecyclerAdapter;

/**
 * Created by hp on 2018/7/9.
 */

public class OneFragment extends Fragment{

    private View view;
    private RecyclerView oneRecycler;
    private ViewPager oneViewPage;
    private List<ImageView>imageViews=new ArrayList<>();
    String rexiao_url="https://www.zhaoapi.cn/home/getHome";

    public Handler handler=new Handler(){
    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
          oneViewPage.setCurrentItem(oneViewPage.getCurrentItem()+1);
          handler.sendEmptyMessageDelayed(0,2000);
    }
};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.onefragment, null);
        oneRecycler = view.findViewById(R.id.one_recycler);
        oneViewPage = view.findViewById(R.id.one_viewpager);


        HttpUtils.getHttpUtils().get(rexiao_url, new HttpUtils.InOkCallback() {
            @Override
            public void getSuccess(String json) {
                Gson gson = new Gson();
                OneBean oneBean = gson.fromJson(json, OneBean.class);
                //轮播图
                List<OneBean.DataBean.BannerBean> banner = oneBean.getData().getBanner();
                for (int i = 0; i < banner.size(); i++) {
                    String icon = banner.get(i).getIcon();
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    ImageLoader.getInstance().displayImage(icon,imageView, MyApp.getOptions());
                    imageViews.add(imageView);

                }
                MyAdapter myAdapter = new MyAdapter(imageViews);
                oneViewPage.setAdapter(myAdapter);

                //列表
                List<OneBean.DataBean.TuijianBean.ListBeanX> listbean = oneBean.getData().getTuijian().getList();
                OneRecyclerAdapter oneRecyclerAdapter = new OneRecyclerAdapter(listbean,getActivity());
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
                gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                oneRecycler.setLayoutManager(gridLayoutManager);
                oneRecycler.setAdapter(oneRecyclerAdapter);

                handler.sendEmptyMessageDelayed(0,2000);
            }

            @Override
            public void getError(Exception error) {
            }
        });


        return view;
    }
}
