package com.example.john.barcode.fragment;

import android.annotation.TargetApi;
import android.support.v4.app.ListFragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.john.barcode.R;
import com.example.john.barcode.activity.DetailActivity;
import com.example.john.barcode.activity.MainActivity;

/**
 * Created by john on 1/18/2016.
 */
public class OrdersFrag extends ListFragment {
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setListAdapter(new ListAdapter());
        MainActivity activity = (MainActivity)getActivity();
        activity.mTitle.setText("Orders");
        activity.mLeftArrow.setVisibility(View.GONE);
        activity.mRightArrow.setVisibility(View.GONE);
        activity.mSearchLayout.setVisibility(View.GONE);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onListItemClick(ListView list, View view, int position, long id) {
        Toast.makeText(getContext(), "test" + position, Toast.LENGTH_LONG).show();
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
                view = View.inflate(getActivity(), R.layout.cell,null);
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
