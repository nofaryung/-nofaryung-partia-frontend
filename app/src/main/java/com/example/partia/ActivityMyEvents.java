package com.example.partia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.partia.model.Event;
import com.example.partia.model.LoginRequest;
import com.example.partia.model.LoginResponse;
import com.example.partia.model.UserEmailHolder;
import com.example.partia.model.UserEventstList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityMyEvents extends AppCompatActivity {
    //String items[] = new String[]{"nofar","or","inbar"};
    ListView listView_MyEvents;
    ListView listView_NextEvents;
    String userSessionEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_events);
        listView_MyEvents = findViewById(R.id.listView_MyEvents);
        listView_NextEvents = findViewById(R.id.listView_NextEvents);
        userSessionEmail = getIntent().getStringExtra("EXTRA_USER_SESSION_EMAIL");
        fetchEvents();
    }

    private void fetchEvents() {
        //get all events - owns and participate
        //Call<Map<String, List<Event>>> userEventsResponseCall = APIClient.getAPIInterface().doGetUserEvents(userSessionEmail);

//        userEventsResponseCall.enqueue(new Callback<Map<String, List<Event>>>() {
//            @Override
//            public void onResponse(Call<Map<String, List<Event>>> call, Response<Map<String, List<Event>>> response) {
//                Map<String, List<Event>> map = new HashMap<>();
//                map = response.body();
//            }
//
//            @Override
//            public void onFailure(Call<Map<String, List<Event>>> call, Throwable t) {
//
//            }
//        });
        UserEmailHolder userEmailHolder = new UserEmailHolder(userSessionEmail);
       // RequestBody body = RequestBody.create(MediaType.parse("application/json"),userEmailHolder);
        Call<UserEventstList> userEventsResponseCall = APIClient.getAPIInterface().doGetUserEvents(userEmailHolder);
        userEventsResponseCall.enqueue(new Callback<UserEventstList>() {
            @Override
            public void onResponse(Call<UserEventstList> call, Response<UserEventstList> response) {
                UserEventstList map;
                map = response.body();
                fetchMyEvent(map.getOwner());
                fetchNextEvents(map.getParticipates());

            }

            @Override
            public void onFailure(Call<UserEventstList> call, Throwable t) {

            }
        });
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,items);
//        listView_MyEvents.setAdapter(adapter);
//        listView_NextEvents.setAdapter(adapter);
//        listView_MyEvents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(ActivityMyEvents.this,items[position],Toast.LENGTH_LONG).show();
//            }
//        });
    }

    private void fetchMyEvent(List<Event> owner) {
        ArrayAdapter<Event> myEvents = new ArrayAdapter<Event>(this, android.R.layout.simple_list_item_1,owner);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,owner);

        listView_MyEvents.setAdapter(myEvents);
        //listView_NextEvents.setAdapter(myEvents);
        listView_MyEvents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ActivityMyEvents.this,owner.get(position).toString(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private void fetchNextEvents(List<Event> participates) {
    }

    public void joinEvent_btn_clicked(android.view.View view){
        Intent intent = new Intent(this, ActivityJoinEvent.class);
        this.startActivity(intent);
    }
}
