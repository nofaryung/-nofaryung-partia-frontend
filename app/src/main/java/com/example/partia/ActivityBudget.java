package com.example.partia;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.partia.model.Event;
import com.example.partia.model.UserEmailHolder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import org.parceler.Parcels;

public final class ActivityBudget extends AppCompatActivity{
    Event event;
    String userSessionEmail;
    TextView textview_balance;
    TextView textview_currentBalance;
    Button split;
    PopupWindow popUp;
    LinearLayout layout;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);
        Parcelable parcelable = getIntent().getParcelableExtra("EXTRA_EVENT");
        event = Parcels.unwrap(parcelable);
        userSessionEmail = getIntent().getStringExtra("EXTRA_USER_SESSION_EMAIL");
        textview_balance = findViewById(R.id.textview_balance);
        textview_currentBalance = findViewById(R.id.textview_currentBalance);
        layout = new LinearLayout(ActivityBudget.this);
        listView = new ListView(this);
        popUp = new PopupWindow(this);
        split = findViewById(R.id.button_split);
        if(userSessionEmail.equals(event.getOwner())) {
            textview_balance.setVisibility(View.GONE);
            textview_currentBalance.setVisibility(View.GONE);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            layout.setOrientation(LinearLayout.VERTICAL);
            createListView();
            layout.addView(listView, params);
            popUp.setContentView(layout);
            // popUp.showAtLocation(layout, Gravity.BOTTOM, 10, 10);
        }
    }

    private void createListView() {
        Call<ArrayList<String>> userEventsResponseCall = APIClient.getAPIInterface().doGetParticipantsBalance(event.getPin_code());
        userEventsResponseCall.enqueue(new Callback<ArrayList<String>>() {
            @Override
            public void onResponse(Call<ArrayList<String>> call, Response<ArrayList<String>> response) {
                ArrayList<String> map = response.body();
                ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(ActivityBudget.this,android.R.layout.simple_list_item_1,map);
                listView.setAdapter(itemsAdapter);
            }
            @Override
            public void onFailure(Call<ArrayList<String>> call, Throwable t) {

            }
        });
    }

    public void split_btn_clicked(View view) {
        if(userSessionEmail == event.getOwner()) {
            popUp.showAtLocation(layout, Gravity.BOTTOM, 10, 10);
            popUp.update(50, 50, 300, 80);
        } else {
            Call<String> userEventsResponseCall = APIClient.getAPIInterface().doGetParticipantBalance(event.getPin_code(),new UserEmailHolder(userSessionEmail));
            userEventsResponseCall.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    textview_currentBalance.setText(response.body());
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                }
            });

        }
    }
}
