package newest.box.smart.com.smartchargeboxnewest.charge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.dd.CircularProgressButton;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import newest.box.smart.com.smartchargeboxnewest.R;

public class ChargeActivity extends AppCompatActivity {
    public static final String Id = "ID";
    public static final String CONNECTOR_ID = "CONNECTOR_ID";

    @BindView(R.id.circularButton1)
    CircularProgressButton circularButton;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charge);

        ButterKnife.bind(this);

        String id = getIntent().getStringExtra(Id);
        String connectorId = getIntent().getStringExtra(CONNECTOR_ID);

        circularButton.setIndeterminateProgressMode(true);
        circularButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(circularButton.getProgress() != 0) {
                   // cancelCharging();
                    circularButton.setProgress(0);
                } else {
//                    if(Calendar.getInstance().getTimeInMillis() >= endTime) {
//                        Toast.makeText(getApplicationContext(), "Krovimo sesija negalioja", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(getApplicationContext(), NetworkingService.class);
//                        intent.setAction(NetworkingService.REMOVE_STATION_RESERVATION);
//                        intent.putExtra("reservationId", reservationId);
//                        intent.putExtra("stationId", stationId);
//                        startService(intent);
//                        finish();
//                        return;
//                    }
//                    Intent intent = new Intent(getApplicationContext(), NetworkingService.class);
//                    intent.setAction(NetworkingService.INIT_CHARGING);
//                    intent.putExtra("stationId", stationId);
//                    intent.putExtra("startTime", startTime);
//                    intent.putExtra("endTime", endTime);
//                    intent.putExtra("reservationId", reservationId);
//                    startService(intent);
                    circularButton.setProgress(5);
                }
            }
        });


    }
}
