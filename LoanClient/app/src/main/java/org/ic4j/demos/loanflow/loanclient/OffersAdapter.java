package org.ic4j.demos.loanflow.loanclient;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class OffersAdapter extends BaseAdapter {
    Context recordContext;
    LoanOffer[] offers;

    public OffersAdapter(Context context, LoanOffer[] offers) {
        this.offers = offers;
        recordContext = context;
    }

    @Override
    public int getCount() {
        return offers.length;
    }

    @Override
    public Object getItem(int i) {
        return offers[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        OffersAdapter.RecordViewHolder holder;
        NumberFormat numberFormat = NumberFormat.getPercentInstance();
        numberFormat.setMinimumFractionDigits(2);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");

        if (view == null){
            LayoutInflater recordInflater = (LayoutInflater) recordContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = recordInflater.inflate(R.layout.offer_record, null);
            holder = new OffersAdapter.RecordViewHolder();
            holder.idView = (TextView) view.findViewById(R.id.record_id);
            holder.aprView = (TextView) view.findViewById(R.id.record_apr);
            holder.createdView = (TextView) view.findViewById(R.id.record_created);
            view.setTag(holder);
        }else {
            holder = (OffersAdapter.RecordViewHolder) view.getTag();
        }

        LoanOffer record = (LoanOffer) getItem(i);
        holder.idView.setText(record.applicationId.toString());
        holder.aprView.setText(numberFormat.format(record.apr/100));
        long millis = TimeUnit.MILLISECONDS.convert(record.created, TimeUnit.NANOSECONDS);
        holder.createdView.setText(dateFormat.format(new Date(millis)));

        return view;
    }

    private static class RecordViewHolder {
        public TextView idView;
        public TextView aprView;
        public TextView createdView;
    }
}
