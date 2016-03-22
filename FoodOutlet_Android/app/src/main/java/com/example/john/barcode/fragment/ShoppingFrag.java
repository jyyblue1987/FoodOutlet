package com.example.john.barcode.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.john.barcode.R;
import com.example.john.barcode.activity.DetailActivity;
import com.example.john.barcode.activity.MainActivity;

import java.util.ArrayList;

/**
 * Created by john on 1/17/2016.
 */
public class ShoppingFrag extends ListFragment {

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setListAdapter(new ListAdapter());
        MainActivity activity = (MainActivity)getActivity();
        activity.mRightArrow.setVisibility(View.VISIBLE);
        activity.mLeftArrow.setVisibility(View.VISIBLE);
        activity.mTitle.setText("Shopping");
        activity.mSearchLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onListItemClick(ListView list, View view, int position, long id) {
        Toast.makeText(getContext(),"test"+position ,Toast.LENGTH_LONG).show();
        startActivity(new Intent(getContext(), DetailActivity.class));
    }

    class ListAdapter extends BaseAdapter {

        public ListAdapter() {
        }

        @Override
        public int getCount() {
            return 10;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        class Holder{
            TextView mTv;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = null;
            if(convertView == null){
                view = View.inflate(getActivity(),R.layout.cell,null);
                Holder holder = new Holder();
                holder.mTv = (TextView)view.findViewById(R.id.tv);
                view.setTag(holder);
            }else{
                view = convertView;
            }
            Holder holder = (Holder)view.getTag();
            holder.mTv.setText("" + position);

            return view;  //ToDo
        }
    }
}
