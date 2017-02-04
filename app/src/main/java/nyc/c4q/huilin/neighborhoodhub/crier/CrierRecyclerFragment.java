package nyc.c4q.huilin.neighborhoodhub.crier;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import nyc.c4q.huilin.neighborhoodhub.R;
import nyc.c4q.huilin.neighborhoodhub.model.CrierPosts.CrierPost;
import nyc.c4q.huilin.neighborhoodhub.model.database.CrierDatabaseHelper;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by rook on 2/4/17.
 */

public class CrierRecyclerFragment extends Fragment {

    View view;
    RecyclerView rvCrier;
    TextView tvAddCrier;

    CrierAdapter adapter;
    List<CrierPost> crierPostList;
    SQLiteDatabase database;

    public static CrierRecyclerFragment newInstance(int index) {
        CrierRecyclerFragment crierFragment = new CrierRecyclerFragment();
        Bundle args = new Bundle();
        args.putInt("index", index);
        crierFragment.setArguments(args);
        return crierFragment;
    }

    public CrierRecyclerFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(
                R.layout.fragment_crier_rv,
                container,
                false
        );
        initViews();
        return view;
    }

    private void initViews() {
        rvCrier = (RecyclerView) view.findViewById(R.id.rv_crier_recycler);
        tvAddCrier = (TextView) view.findViewById(R.id.tv_add_new_crier);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvAddCrier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewCrier();
            }
        });

        database = instantiateDatabase();

    }

    private SQLiteDatabase instantiateDatabase() {
        CrierDatabaseHelper databaseHelper = CrierDatabaseHelper.getInstance(getContext());
        database = databaseHelper.getWritableDatabase();

        //Populate database if empty
        String count = "SELECT count(*) FROM table";
        Cursor cursor = database.rawQuery(count, null);
        cursor.moveToFirst();
        int iCount = cursor.getInt(0);

        if(iCount > 0) {
            return database;
        } else {
            for (int i = 0; i < crierPostList.size(); i++) {
                cupboard().withDatabase(database).put(crierPostList.get(i));
            }
        }
        return database;
    }

    private void addNewCrier() {
        Toast.makeText(getContext(), "Clicked Add New Crier", Toast.LENGTH_SHORT).show();
    }
}
