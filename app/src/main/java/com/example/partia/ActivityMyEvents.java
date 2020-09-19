package com.example.partia;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.partia.model.Event;
import com.example.partia.model.LoginRequest;
import com.example.partia.model.LoginResponse;
import com.example.partia.model.MyAdapter;
import com.example.partia.model.UserEmailHolder;
import com.example.partia.model.UserEventstList;

import org.parceler.Parcels;

import java.util.ArrayList;
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

        UserEmailHolder userEmailHolder = new UserEmailHolder(userSessionEmail);
        Call<UserEventstList> userEventsResponseCall = APIClient.getAPIInterface().doGetUserEvents(userEmailHolder);
        userEventsResponseCall.enqueue(new Callback<UserEventstList>() {
            @Override
            public void onResponse(Call<UserEventstList> call, Response<UserEventstList> response) {
                UserEventstList map;
                map = response.body();
//                ArrayList<Event> own = map.getOwner();
//                //own.remove(3);
//                //own.remove(2);
//
//                fetchMyEvent(own);
                fetchMyEvent(map.getOwner());
                fetchNextEvents(map.getParticipates());

            }

            @Override
            public void onFailure(Call<UserEventstList> call, Throwable t) {

            }
        });
    }

    private void fetchMyEvent(ArrayList<Event> owner) {
        //ArrayAdapter<Event> myEvents = new ArrayAdapter<Event>(this, android.R.layout.simple_list_item_1,owner);
        //listView_MyEvents.setAdapter(myEvents);

        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,owner);
        //listView_NextEvents.setAdapter(adapter);

         listView_MyEvents.setAdapter(new MyAdapter(this, owner));


        listView_MyEvents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ActivityMyEvents.this,owner.get(position).toString(),Toast.LENGTH_LONG).show();
                Parcelable parcelable = Parcels.wrap(owner.get(position));
                Intent intent = new Intent(ActivityMyEvents.this, ActivityExistEvent.class);
                intent.putExtra("EXTRA_EVENT",parcelable);
                intent.putExtra("EXTRA_USER_SESSION_EMAIL",userSessionEmail);
                startActivity(intent);
            }
        });
    }

    private void fetchNextEvents(ArrayList<Event> participates) {
        //ArrayAdapter<Event> myEvents = new ArrayAdapter<Event>(this, android.R.layout.simple_list_item_1,participates);
        //listView_NextEvents.setAdapter(myEvents);

        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,owner);
        //listView_NextEvents.setAdapter(new MyAdapter(this, participates));

        listView_NextEvents.setAdapter(new MyAdapter(this, participates));

        listView_NextEvents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ActivityMyEvents.this,participates.get(position).toString(),Toast.LENGTH_LONG).show();
                Parcelable parcelable = Parcels.wrap(participates.get(position));
                Intent intent = new Intent(ActivityMyEvents.this, ActivityExistEvent.class);
                intent.putExtra("EXTRA_EVENT",parcelable);
                intent.putExtra("EXTRA_USER_SESSION_EMAIL",userSessionEmail);
                startActivity(intent);
            }
        });
    }

    public void joinEvent_btn_clicked(android.view.View view){
        Intent intent = new Intent(this, ActivityJoinEvent.class);
        intent.putExtra("EXTRA_USER_SESSION_EMAIL",userSessionEmail);
        this.startActivity(intent);
    }
}
