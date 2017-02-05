package nyc.c4q.huilin.neighborhoodhub.chat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import nyc.c4q.huilin.neighborhoodhub.R;

/**
 * Created by ashiquechowdhury on 2/5/17.
 */

public class ChatFragment extends Fragment{
    private ProgressBar mProgressBar;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private ChildEventListener childListener;

    public static final String ANONYMOUS = "ananymous";
    public static final int DEFAULT_MSG_LENGTH_LIMIT = 1000;

    private EditText mMessageEditText;
    private Button mSendButton;
    private ListView mMessageListView;
    private MessageAdapter mMessageAdapter;

    private String mUsername;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        mProgressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        mMessageEditText = (EditText) view.findViewById(R.id.messageEditText);
        mSendButton = (Button) view.findViewById(R.id.sendButton);
        mMessageListView = (ListView) view.findViewById(R.id.messageListView);

        mUsername = getArguments().getString("nyc.c4q.USERNAME");

        return view;
    }

    public static Fragment newInstance() {
        ChatFragment chatFrag = new ChatFragment();
        Bundle args = new Bundle();
        chatFrag.setArguments(args);
        return chatFrag;
    }


    public ChatFragment() {

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        mProgressBar.setVisibility(View.INVISIBLE);

//        mUsername = ANONYMOUS;

        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("messages");

        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FriendlyMessage friendlyMessage = new FriendlyMessage(
                        mMessageEditText.getText().toString(), mUsername, null);
                // Clear input box
                reference.push().setValue(friendlyMessage);
                mMessageEditText.setText("");
            }
        });

        childListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                FriendlyMessage friendlyMessage = dataSnapshot.getValue(FriendlyMessage.class);
                mMessageAdapter.add(friendlyMessage);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        reference.addChildEventListener(childListener);

        List<FriendlyMessage> friendlyMessages = new ArrayList<>();
        mMessageAdapter = new MessageAdapter(getActivity(), R.layout.item_message, friendlyMessages);
        mMessageListView.setAdapter(mMessageAdapter);

        mMessageEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().length() > 0) {
                    mSendButton.setEnabled(true);
                } else {
                    mSendButton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        mMessageEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});
    }
}
