package newest.box.smart.com.smartchargeboxnewest.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import newest.box.smart.com.smartchargeboxnewest.R;

/**
 * Created by Deividas on 2017-09-10.
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ItemViewHolder> {
    private List<String> list;

    public CommentAdapter(List<String> list) {
        this.list = list;
    }

    @Override
    public CommentAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_item, parent, false);
        return new CommentAdapter.ItemViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(CommentAdapter.ItemViewHolder holder, int position) {
        String evse = list.get(position);
        holder.bind(evse);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        CircleImageView profileImg;
        TextView name;
        TextView comment;
        TextView timeStamp;
        ImageView editComment;
        ImageView deleteComment;

        public ItemViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            profileImg = ButterKnife.findById(itemView, R.id.comment_image);
            name = ButterKnife.findById(itemView, R.id.comment_name);
            comment = ButterKnife.findById(itemView, R.id.comment_text);
            timeStamp = ButterKnife.findById(itemView, R.id.time_stamp);
            editComment = ButterKnife.findById(itemView, R.id.edit_comment);
            deleteComment = ButterKnife.findById(itemView, R.id.delete_comment);
        }

        public void bind(String txt) {
            comment.setText(txt);
        }
    }
}
