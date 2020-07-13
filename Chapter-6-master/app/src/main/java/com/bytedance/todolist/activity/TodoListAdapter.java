package com.bytedance.todolist.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bytedance.todolist.R;
import com.bytedance.todolist.database.TodoListEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangrui.sh
 * @since Jul 11, 2020
 */
public class TodoListAdapter extends RecyclerView.Adapter<TodoListItemHolder> {
    private List<TodoListEntity> mDatas;

    private List<Boolean> booleanlist = new ArrayList<>();
    //private boolean exist[];
    public Map<Integer, Boolean> map = new HashMap<>();

    public TodoListAdapter() {
        mDatas = new ArrayList<>();
        for (int i = 0; i < mDatas.size(); i++) {
            //设置默认的显示
            booleanlist.add(false);
        }
    }
    @NonNull
    @Override
    public TodoListItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TodoListItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_item_layout, parent, false));
    }




    @Override
    public void onBindViewHolder(@NonNull TodoListItemHolder holder, final int position) {

        holder.bind(mDatas.get(position));
        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    map.put(position, true);

                } else {
                    map.remove(position);
                }


            }
        });

        if (map != null && map.containsKey(position)) {
            holder.mCheckBox.setChecked(true);
        } else {
            holder.mCheckBox.setChecked(false);
        }

        //holder.mCheckBox.setChecked(booleanlist.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void deletingData() {


        for (int i = 0; i < mDatas.size(); i++) {
            if(map.containsKey(i)) {
                mDatas.remove(map.get(i));

            }

        }
        notifyDataSetChanged();
    }

    @MainThread
    public void setData(List<TodoListEntity> list) {
        mDatas = list;
        notifyDataSetChanged();
    }
}

//package com.bytedance.todolist.activity;
//
//import android.content.Context;
//import android.support.annotation.NonNull;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.TextView;
//import com.ied.recyclerview.R;
//import com.ied.recyclerview.entity.Data;
//import java.util.List;
//
//public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> implements View.OnClickListener{
//
//    private List<Data> list;//数据源
//    private Context context;//上下文
//
//    public MyRecyclerViewAdapter(List<Data> list, Context context) {
//        this.list = list;
//        this.context = context;
//    }
//
//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
//        return new MyViewHolder(view);
//    }
//
//    //绑定
//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        Data data = list.get(position);
//        holder.ivIcon.setBackgroundResource(data.getIcon());
//        holder.tvUsername.setText(data.getUsername());
//        holder.tvMessage.setText(data.getMessage());
//
//        holder.itemView.setTag(position);
//        holder.btnAgree.setTag(position);
//        holder.btnRefuse.setTag(position);
//    }
//
//    //有多少个item？
//    @Override
//    public int getItemCount() {
//        return list.size();
//    }
//
//    //创建MyViewHolder继承RecyclerView.ViewHolder
//    public class MyViewHolder extends RecyclerView.ViewHolder{
//        private ImageView ivIcon;
//        private TextView tvUsername,tvMessage;
//        private Button btnAgree,btnRefuse;
//
//        public MyViewHolder(View itemView) {
//            super(itemView);
//
//            ivIcon = itemView.findViewById(R.id.iv_icon);
//            tvUsername = itemView.findViewById(R.id.tv_username);
//            tvMessage = itemView.findViewById(R.id.tv_message);
//            btnAgree = itemView.findViewById(R.id.btn_agree);
//            btnRefuse = itemView.findViewById(R.id.btn_refuse);
//
//            // 为ItemView添加点击事件
//            itemView.setOnClickListener(MyRecyclerViewAdapter.this);
//            btnAgree.setOnClickListener(MyRecyclerViewAdapter.this);
//            btnRefuse.setOnClickListener(MyRecyclerViewAdapter.this);
//        }
//
//    }
//
//    //=======================以下为item中的button控件点击事件处理===================================
//
//    //item里面有多个控件可以点击（item+item内部控件）
//    public enum ViewName {
//        ITEM,
//        PRACTISE
//    }
//
//    //自定义一个回调接口来实现Click和LongClick事件
//    public interface OnItemClickListener  {
//        void onItemClick(View v, ViewName viewName, int position);
//        void onItemLongClick(View v);
//    }
//
//    private OnItemClickListener mOnItemClickListener;//声明自定义的接口
//
//    //定义方法并传给外面的使用者
//    public void setOnItemClickListener(OnItemClickListener  listener) {
//        this.mOnItemClickListener  = listener;
//    }
//
//    @Override
//    public void onClick(View v) {
//        int position = (int) v.getTag();      //getTag()获取数据
//        if (mOnItemClickListener != null) {
//            switch (v.getId()){
//                case R.id.rv_recyclerView:
//                    mOnItemClickListener.onItemClick(v, ViewName.PRACTISE, position);
//                    break;
//                default:
//                    mOnItemClickListener.onItemClick(v, ViewName.ITEM, position);
//                    break;
//            }
//        }
//    }
//}
