package co.example.hp.chenjia20180709.mvp.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import co.example.hp.chenjia20180709.R;
import co.example.hp.chenjia20180709.app.MyApp;
import co.example.hp.chenjia20180709.mvp.model.OneBean;

/**
 * Created by hp on 2018/7/9.
 */

public class OneRecyclerAdapter extends RecyclerView.Adapter<OneRecyclerAdapter.OneViewHolder>{
         public List<OneBean.DataBean.TuijianBean.ListBeanX> list;
         public Context context;
         private View itemView;

    public OneRecyclerAdapter(List<OneBean.DataBean.TuijianBean.ListBeanX> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return list.size()==0?0:list.size();
    }

    @Override
    public OneViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_recycler, null);
        return new OneViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(OneViewHolder holder, int position) {
        String[] split = list.get(position).getImages().split("\\|");
        ImageLoader.getInstance().displayImage(split[0],holder.oneRecyclerSimple, MyApp.getOptions());
        holder.oneRecyclerName.setText(list.get(position).getTitle());
        holder.oneRecyclerPrice.setText("￥"+list.get(position).getPrice());

    }


    public class OneViewHolder extends RecyclerView.ViewHolder{

        private final SimpleDraweeView oneRecyclerSimple;
        private final TextView oneRecyclerName;
        private final TextView oneRecyclerPrice;

        public OneViewHolder(View itemView) {
            super(itemView);
            oneRecyclerSimple = itemView.findViewById(R.id.one_recycler_simple);
            oneRecyclerName = itemView.findViewById(R.id.one_recycler_name);
            oneRecyclerPrice = itemView.findViewById(R.id.one_recycler_price);
        }
    }
}
