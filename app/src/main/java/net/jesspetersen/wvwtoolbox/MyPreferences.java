package net.jesspetersen.wvwtoolbox;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MyPreferences extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner worldChoice;
    String worldChoiceID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_preferences);
        worldChoice = (Spinner) findViewById(R.id.worldChoiceSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.listArray, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        worldChoice.setAdapter(adapter);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        Resources res = getResources();
        String[] worldIDs = res.getStringArray(R.array.listValues);
        worldChoiceID = worldIDs[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Toast.makeText(getApplicationContext(), "Please go to settings by clicking the dots on the top right corner and select your server.", Toast.LENGTH_LONG).show();
    }
}
