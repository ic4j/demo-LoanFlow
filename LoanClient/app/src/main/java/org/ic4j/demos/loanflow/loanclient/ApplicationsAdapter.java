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

public class ApplicationsAdapter extends BaseAdapter {
    Context recordContext;
    LoanApplication[] applications;

    public ApplicationsAdapter(Context context, LoanApplication[] applications) {
        this.applications = applications;
        recordContext = context;
    }

    @Override
    public int getCount() {
        return applications.length;
    }

    @Override
    public Object getItem(int i) {
        return applications[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ApplicationsAdapter.RecordViewHolder holder;
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");

        if (view == null){
            LayoutInflater recordInflater = (LayoutInflater) recordContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = recordInflater.inflate(R.layout.application_record,null);
            holder = new ApplicationsAdapter.RecordViewHolder();
            holder.idView = (TextView) view.findViewById(R.id.record_id);
            holder.amountView = (TextView) view.findViewById(R.id.record_amount);
            holder.termView = (TextView) view.findViewById(R.id.record_term);
            holder.createdView = (TextView) view.findViewById(R.id.record_created);
            view.setTag(holder);
        }else {
            holder = (ApplicationsAdapter.RecordViewHolder) view.getTag();
        }

        LoanApplication record = (LoanApplication) getItem(i);
        holder.idView.setText(record.applicationId.toString());
        holder.amountView.setText(numberFormat.format(record.amount));
        holder.termView.setText(record.term.toString());
        long millis = TimeUnit.MILLISECONDS.convert(record.created, TimeUnit.NANOSECONDS);
        holder.createdView.setText(dateFormat.format(new Date(millis)));

        return view;
    }

    private static class RecordViewHolder {
        public TextView idView;
        public TextView amountView;
        public TextView termView;
        public TextView createdView;
    }
}
