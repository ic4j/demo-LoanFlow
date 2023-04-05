package org.ic4j.demos.loanflow.loanclient;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import org.ic4j.demos.loanflow.loanclient.databinding.ActivityMainBinding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;

public class MainActivity extends AppCompatActivity implements ApplyFragment.ApplyDialogListener{
    static final Logger LOG = LoggerFactory.getLogger(MainActivity.class);

    private Context context;

    private ActivityMainBinding binding;

    LoanBrokerService loanBrokerService;
    boolean isBound = false;

    ProgressBar serviceProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.context = this;

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        FrameLayout frame = (FrameLayout) findViewById(R.id.frameLayout);
        frame.removeAllViews();
        LayoutInflater.from(context).inflate(R.layout.fragment_offers, frame, true);

        getSupportActionBar().setTitle(R.string.offers_fragment_label);

        serviceProgressBar = (ProgressBar) findViewById(R.id.serviceProgressBar);

        binding.createApplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an instance of the dialog fragment and show it
                DialogFragment fragment = new ApplyFragment();

                fragment.show(getSupportFragmentManager(), "ApplyDialogFragment");

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Bind to LocalService
        Intent intent = new Intent(this, LoanBrokerService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(id) {
            default:
            case R.id.action_applications: {

                FrameLayout frame = (FrameLayout) findViewById(R.id.frameLayout);
                frame.removeAllViews();
                LayoutInflater.from(context).inflate(R.layout.fragment_applications, frame, true);
                this.loadApplications();

                getSupportActionBar().setTitle(R.string.applications_fragment_label);
                break;
            }
            case R.id.action_offers: {
                FrameLayout frame = (FrameLayout) findViewById(R.id.frameLayout);
                frame.removeAllViews();
                LayoutInflater.from(context).inflate(R.layout.fragment_offers, frame, true);
                this.loadOffers();

                getSupportActionBar().setTitle(R.string.offers_fragment_label);
                break;
            }
        }
        return true;
    }


    /** Defines callbacks for service binding, passed to bindService() */
    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            LoanBrokerService.LocalBinder binder = (LoanBrokerService.LocalBinder) service;
            loanBrokerService = binder.getService();
            isBound = true;
            loadOffers();
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            isBound = false;
        }
    };

    @Override
    public void onDialogApplyClick(DialogFragment dialog) {
        // User touched the dialog's apply button
        try {
            LoanApplication application = new LoanApplication();

            application.applicationId = BigInteger.valueOf(0);
            application.created = 0l;
            application.firstName = ((EditText)dialog.getDialog().findViewById(R.id.first_name)).getText().toString();
            application.lastName = ((EditText)dialog.getDialog().findViewById(R.id.last_name)).getText().toString();
            application.zipcode = ((EditText)dialog.getDialog().findViewById(R.id.zipcode)).getText().toString();
            application.ssn = ((EditText)dialog.getDialog().findViewById(R.id.ssn)).getText().toString();
            application.amount = Double.parseDouble(((EditText)dialog.getDialog().findViewById(R.id.amount)).getText().toString());
            application.term = Short.parseShort(((Spinner)dialog.getDialog().findViewById(R.id.term)).getSelectedItem().toString());

            dialog.dismiss();

            serviceProgressBar.setVisibility(View.VISIBLE);
            loanBrokerService.apply(application).handleAsync((id, t) -> {
                    serviceProgressBar.setVisibility(View.INVISIBLE);
                    if (t != null)
                        LOG.error(t.getLocalizedMessage(),t);
                    return id;
                    }
            );
        } catch (Exception e) {
            LOG.error(e.getLocalizedMessage(),e);
        }
    }

    private void loadApplications()
    {
        if(this.isBound) {
            try {
                final ListView applicationsRecordsView = (ListView) findViewById(R.id.applications_view);

                LoanApplication[] applications = loanBrokerService.getApplications();;
                ApplicationsAdapter recordAdapter = new ApplicationsAdapter(context, applications);
                applicationsRecordsView.setAdapter(recordAdapter);
            } catch (Exception e) {
                LOG.error(e.getLocalizedMessage(),e);
            }
        }
    }

    private void loadOffers()
    {
        if(this.isBound) {
            try {
                final ListView offersRecordsView = (ListView) findViewById(R.id.offers_view);

                LoanOffer[] offers = loanBrokerService.getOffers();
                OffersAdapter offersRecordAdapter = new OffersAdapter(context, offers);
                offersRecordsView.setAdapter(offersRecordAdapter);

            } catch (Exception e) {
                LOG.error(e.getLocalizedMessage(),e);
            }
        }
    }
}