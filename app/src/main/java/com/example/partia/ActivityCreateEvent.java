package com.example.partia;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class ActivityCreateEvent extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        //apiInterface = APIClient.getClient().create(APIInterface.class);
    }

    public void startQueryForPlanner(android.view.View view){
        Intent intent = new Intent(this, ActivityQueryForPlanner.class);
        this.startActivity(intent);

    }


}
