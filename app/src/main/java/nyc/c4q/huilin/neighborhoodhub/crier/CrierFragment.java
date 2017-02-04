package nyc.c4q.huilin.neighborhoodhub.crier;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by rook on 2/4/17.
 */

public class CrierFragment extends Fragment {

    public static CrierFragment newInstance(int index) {
        CrierFragment crierFragment = new CrierFragment();
        Bundle args = new Bundle();
        args.putInt("index", index);
        crierFragment.setArguments(args);
        return crierFragment;
    }
}
