package newest.box.smart.com.smartchargeboxnewest.detailLocation;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.parceler.Parcel;
import org.parceler.Parcels;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import newest.box.smart.com.smartchargeboxnewest.R;
import newest.box.smart.com.smartchargeboxnewest.adapter.StationAdapter;
import newest.box.smart.com.smartchargeboxnewest.events.ConnectrosChooseEvent;
import newest.box.smart.com.smartchargeboxnewest.events.OnConnectorSelectedEvent;
import newest.box.smart.com.smartchargeboxnewest.events.TimeRangeSelectedEvent;
import newest.box.smart.com.smartchargeboxnewest.modals.ConnectorModal;
import newest.box.smart.com.smartchargeboxnewest.modals.TimeRangePickerDialog;
import newest.box.smart.com.smartchargeboxnewest.preferences.PreferencesManager;
import newest.box.smart.com.smartchargeboxnewest.retrofit.ApiService;
import newest.box.smart.com.smartchargeboxnewest.retrofit.models.RetrofitSingleton;
import newest.box.smart.com.smartchargeboxnewest.retrofit.models.addReservation.Reservation;
import newest.box.smart.com.smartchargeboxnewest.retrofit.models.addReservation.ReserveResponse;
import newest.box.smart.com.smartchargeboxnewest.retrofit.models.location.LocationResponse;
import newest.box.smart.com.smartchargeboxnewest.retrofit.models.location.LocationResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailLocationFragment extends Fragment {
    private Unbinder unbinder;
    private String markerId;

    private String stationid;
    public static final String MARKER_ID = "MARKER_ID";

    private String token;
    private RetrofitSingleton singleton = RetrofitSingleton.getInstance();
    private ApiService service = singleton.getService();

    public DetailLocationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_location, container, false);
        unbinder = ButterKnife.bind(this, view);

        final TextView name = ButterKnife.findById(view, R.id.name);
        final TextView locationAddress = ButterKnife.findById(view, R.id.locationAddress);
        final TextView description = ButterKnife.findById(view, R.id.description);
        final TextView type = ButterKnife.findById(view, R.id.type);
        final ImageView navigate = ButterKnife.findById(view, R.id.navigate);
        final ImageView mapImg = ButterKnife.findById(view, R.id.mapImg);

        final RecyclerView stationsList = ButterKnife.findById(view, R.id.stationsList);
        stationsList.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        markerId = getArguments().getString(MARKER_ID);
        Log.d("SDSDYUSDSD", markerId + "");
        token = PreferencesManager.getInstance(getActivity().getApplicationContext()).getString(PreferencesManager.SESSION_TOKEN);

        Call<LocationResponse> call = service.getLocation(token, markerId);
        call.enqueue(new Callback<LocationResponse>() {
            @Override
            public void onResponse(Call<LocationResponse> call, Response<LocationResponse> response) {
                if(response.isSuccessful()) {
                    LocationResult locationResult = response.body().getResult();

                    name.setText(locationResult.getData().getName());
                    locationAddress.setText(locationResult.getData().getAddress());
                    type.setText(locationResult.getData().getType());

//                    Log.d("SDSIDGDSD", locationResult.getData().getName() + " " + locationResult.getData().getAddress() + " " + locationResult.getData().getCity()
//                            + " " + locationResult.getData().getCity() + " "
//                            + locationResult.getData().getCountry() + " "
//                            + locationResult.getData().getEvses().size()
//                    );

                    final double lat = locationResult.getData().getLatitude();
                    final double lng = locationResult.getData().getLongitude();

                    navigate.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent navigation = new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("http://maps.google.com/maps?daddr=" + lat + "," + lng));
                            startActivity(navigation);
                        }
                    });

                    StationAdapter adapter = new StationAdapter(locationResult.getData().getEvses());
                    stationsList.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<LocationResponse> call, Throwable t) {
                Log.d("SDSIDGDSD", "onFailure" + " " + t.getMessage());
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        if(!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        if(EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        unbinder.unbind();
    }

    @Subscribe
    public void onConectorSelectEvent(ConnectrosChooseEvent obj) {
        stationid = obj.getId();
        ConnectorModal dialog = new ConnectorModal();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ConnectorModal.DATA, Parcels.wrap(obj.getConnectors()));
        dialog.setArguments(bundle);
        dialog.show(getActivity().getFragmentManager(), "ConnectorModal");
        obj.getId();
    }

    @Subscribe
    public void onConnectorClicked(OnConnectorSelectedEvent obj) {

        setCalendarAndTimeDialogs(obj.getId());
    }

    public void setCalendarAndTimeDialogs(final String id) {
        Calendar now = Calendar.getInstance();
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        final DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), null
                , year, month, day);
        datePickerDialog.getDatePicker().setMinDate(new Date().getTime());
        final Date date = new Date();
        date.setDate(now.get(Calendar.DAY_OF_MONTH) + 7);
        datePickerDialog.getDatePicker().setMaxDate(date.getTime());
        datePickerDialog.show();
        date.setDate(now.get(Calendar.DAY_OF_MONTH) + 7);
        final Calendar futureCal = Calendar.getInstance();
        futureCal.setTimeInMillis(date.getTime());

        datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        datePickerDialog.getDatePicker().clearFocus();

                        if (which == DialogInterface.BUTTON_POSITIVE) {
                            String day = String.valueOf(datePickerDialog.getDatePicker().getDayOfMonth());
                            String year = String.valueOf(datePickerDialog.getDatePicker().getYear());
                            String month = String.valueOf(datePickerDialog.getDatePicker().getMonth() + 1);

                            TimeRangePickerDialog timeDialog = TimeRangePickerDialog.newInstance();
                            timeDialog.setCalendarValues(year, month, day, id);
                            timeDialog.show(getFragmentManager(), "Time");
                        }
                    }
                });
    }

    @Subscribe
    public void onTimeSelected(TimeRangeSelectedEvent obj) {
        Log.d("SDSDSDSD", obj.getStartTime() + " " + obj.getEndTime() + " " + obj.getConnectorId() + " " + stationid);
        Reservation reservation = new Reservation("", token, obj.getStartTime(), obj.getEndTime(), markerId, obj.getConnectorId(), stationid);
        Call<ReserveResponse> call = service.addReservation(token, reservation);
        call.enqueue(new Callback<ReserveResponse>() {
            @Override
            public void onResponse(Call<ReserveResponse> call, Response<ReserveResponse> response) {
               // Log.d("SDSIGDSD", response.body().toString());
            }

            @Override
            public void onFailure(Call<ReserveResponse> call, Throwable t) {

            }
        });
    }
}
