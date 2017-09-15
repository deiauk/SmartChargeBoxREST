package newest.box.smart.com.smartchargeboxnewest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.ButterKnife;
import newest.box.smart.com.smartchargeboxnewest.R;
import newest.box.smart.com.smartchargeboxnewest.events.ConnectrosChooseEvent;
import newest.box.smart.com.smartchargeboxnewest.retrofit.models.locations.Evse;

/**
 * Created by Deividas on 2017-09-09.
 */

public class StationAdapter extends RecyclerView.Adapter<StationAdapter.ItemViewHolder> {
    private List<Evse> list;

    public StationAdapter(List<Evse> list) {
        this.list = list;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.station_item_holder, parent, false);
        return new ItemViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Evse evse = list.get(position);
        holder.bind(evse);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView stationId;
        private String id;
        private LinearLayout connectors;

        public ItemViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            connectors = ButterKnife.findById(itemView, R.id.connectros);
            stationId = ButterKnife.findById(itemView, R.id.station_id);

            connectors.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EventBus.getDefault().post(new ConnectrosChooseEvent(list.get(getAdapterPosition()).getConnectors(), list.get(getAdapterPosition()).getUid()));
                }
            });
        }

        public void bind(Evse evse) {
            String id = evse.getUid();
            if(id != null) {
                stationId.setText(id);
            }
        }
    }
}
