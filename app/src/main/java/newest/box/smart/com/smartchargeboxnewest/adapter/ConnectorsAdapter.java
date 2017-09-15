package newest.box.smart.com.smartchargeboxnewest.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import newest.box.smart.com.smartchargeboxnewest.R;
import newest.box.smart.com.smartchargeboxnewest.events.OnConnectorSelectedEvent;
import newest.box.smart.com.smartchargeboxnewest.retrofit.models.locations.Connectors;

/**
 * Created by Deividas on 2017-09-10.
 */

public class ConnectorsAdapter extends RecyclerView.Adapter<ConnectorsAdapter.ItemViewHolder> {
    private List<Connectors> list;

    public ConnectorsAdapter(List<Connectors> connectorses) {
        this.list = connectorses;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.connector_item, parent, false);
        return new ItemViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(ConnectorsAdapter.ItemViewHolder holder, int position) {
        Connectors evse = list.get(position);
        holder.bind(evse);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView id;
        private TextView traffic;
        private TextView terms;

        public ItemViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            id = ButterKnife.findById(itemView, R.id.id);
            traffic = ButterKnife.findById(itemView, R.id.traffic);
            terms = ButterKnife.findById(itemView, R.id.terms);

//            profileImg = ButterKnife.findById(itemView, R.id.comment_image);
//            name = ButterKnife.findById(itemView, R.id.comment_name);
//            comment = ButterKnife.findById(itemView, R.id.comment_text);
//            timeStamp = ButterKnife.findById(itemView, R.id.time_stamp);
//            editComment = ButterKnife.findById(itemView, R.id.edit_comment);
//            deleteComment = ButterKnife.findById(itemView, R.id.delete_comment);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EventBus.getDefault().post(new OnConnectorSelectedEvent(list.get(getAdapterPosition()).getId()));
                }
            });
        }

        public void bind(Connectors obj) {
            id.setText(obj.getId());
            traffic.setText(obj.getTariffId());
            terms.setText(obj.getTermsAndConditions());
            //comment.setText(txt);
        }
    }
}
