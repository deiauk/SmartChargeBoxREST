package newest.box.smart.com.smartchargeboxnewest.modals;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.parceler.Parcels;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import newest.box.smart.com.smartchargeboxnewest.R;
import newest.box.smart.com.smartchargeboxnewest.adapter.CommentAdapter;
import newest.box.smart.com.smartchargeboxnewest.adapter.ConnectorsAdapter;
import newest.box.smart.com.smartchargeboxnewest.charge.ChargeActivity;
import newest.box.smart.com.smartchargeboxnewest.events.OnConnectorSelectedEvent;
import newest.box.smart.com.smartchargeboxnewest.retrofit.models.locations.Connectors;

/**
 * Created by Deividas on 2017-09-10.
 */

public class ConnectorModal extends DialogFragment {
    private Unbinder unbinder;
    private RecyclerView connectors;

    private String stationId;
    private String connectorId;

    public static final String DATA = "DATA";
    private String STATION_ID = "STATION_ID";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }


        getDialog().getWindow().setLayout(950, WindowManager.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onStop() {
        super.onStop();

        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.connector_modal, container, false);
        unbinder = ButterKnife.bind(this, view);
        connectors = ButterKnife.findById(view, R.id.connectrosRecyclerView);
        connectors.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        List<Connectors> connectorses = Parcels.unwrap(getArguments().getParcelable(DATA));
        stationId = getArguments().getString(STATION_ID);
        ConnectorsAdapter adapter = new ConnectorsAdapter(connectorses);
        connectors.setAdapter(adapter);
        Log.d("SDSGYUDSDUISD", connectorses.size() + "");

        return view;
    }

    @OnClick(R.id.btn)
    public void btnPressed() {
        dismiss();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        unbinder.unbind();
    }

    @Subscribe
    public void onConnectorClicked(OnConnectorSelectedEvent obj) {
//        Intent intent = new Intent(getActivity().getApplicationContext(), ChargeActivity.class);
//        intent.putExtra(ChargeActivity.Id, stationId);
//        startActivity(intent);

//        TimeRangePickerDialog timeDialog = TimeRangePickerDialog.newInstance();
//        timeDialog.setCalendarValues(year, month, day);
//        timeDialog.setId(id);
//        timeDialog.show(getSupportFragmentManager(), "Time");
        dismiss();
    }
}
