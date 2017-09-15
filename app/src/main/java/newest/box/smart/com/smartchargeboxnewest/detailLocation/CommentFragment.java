package newest.box.smart.com.smartchargeboxnewest.detailLocation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import newest.box.smart.com.smartchargeboxnewest.R;
import newest.box.smart.com.smartchargeboxnewest.adapter.CommentAdapter;

public class CommentFragment extends Fragment {
    private Unbinder unbinder;

    public CommentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comment, container, false);
        unbinder = ButterKnife.bind(this, view);

        RecyclerView commentsList = ButterKnife.findById(view, R.id.commentsList);
        commentsList.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        List<String> stringList = new ArrayList<>();
        for(int i=0; i<10; i++) {
            stringList.add("Komentaras nr " + (i+1));
        }
        CommentAdapter adapter = new CommentAdapter(stringList);
        commentsList.setAdapter(adapter);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        unbinder.unbind();
    }
}
