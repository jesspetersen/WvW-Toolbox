package net.jesspetersen.wvwtoolbox;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class MyPreferences extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner worldChoice;
    Button saveBtn;
    String worldChoiceID;
    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_preferences);
        worldChoice = (Spinner) findViewById(R.id.worldChoiceSpinner);
        saveBtn = (Button) findViewById(R.id.saveBtn);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.listArray, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        worldChoice.setAdapter(adapter);

        if (readDataFromFile() == "1"){
            String ID = readDataFromFile();
            Resources res = getResources();
            String[] worldIDs = res.getStringArray(R.array.listValues);
            for (int i = 0; i<worldIDs.length; i++) {
                if (worldIDs[i] == ID) {
                    worldChoice.setSelection(i);
                }
            }
        }

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int spinner_pos = worldChoice.getSelectedItemPosition();
                String[] world_values = getResources().getStringArray(R.array.listValues);
                worldChoiceID = String.valueOf(world_values[spinner_pos]);
                Log.i("spinner", "Current position is: " + worldChoice.getSelectedItemPosition());
                Log.i("spinner", "Current world ID is: " + worldChoiceID);
                writeDataToFile(worldChoiceID);

            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                showMessageOKCancel("You need to allow access to External Storage",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(getParent(), new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                        MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
                            }
                        });
                return;
            }
            ActivityCompat.requestPermissions(getParent(), new String[] {Manifest.permission.WRITE_CONTACTS},
                    MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
            return;
        }
        int spinner_pos = worldChoice.getSelectedItemPosition();
        String[] world_values = getResources().getStringArray(R.array.listValues);
        worldChoiceID = String.valueOf(world_values[spinner_pos]);
        Log.i("spinner", "Current position is: "+position);
        writeDataToFile(worldChoiceID);
        Toast.makeText(getApplicationContext(), "World Selected.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Toast.makeText(getApplicationContext(), "Please go to settings by clicking the dots on the top right corner and select your server.", Toast.LENGTH_LONG).show();
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    int spinner_pos = worldChoice.getSelectedItemPosition();
                    String[] world_values = getResources().getStringArray(R.array.listValues);
                    worldChoiceID = String.valueOf(world_values[spinner_pos]);
                    writeDataToFile(worldChoiceID);
                    Toast.makeText(getApplicationContext(), "World Selected.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "You need to allow access to external storage to use this feature.", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }

    //Method that allows for world number to be stored indefinitely
    public void writeDataToFile(String worldNumber) {
        FileOutputStream outStream = null;
        File file;
        //If there is external storage available, save to that
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            try {
                File folder = new File(Environment.getExternalStorageDirectory() + "/wvwtoolbox");
                boolean success = true;
                if (!folder.exists()) {
                    success = folder.mkdir();
                }
                if (success) {
                    try {
                        file = new File(Environment.getExternalStorageDirectory() + "/wvwtoolbox", "dataForWvWToolbox.txt");
                        outStream = new FileOutputStream(file);
                        outStream.write(worldNumber.getBytes());
                        outStream.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
        else{
            try {
                outStream = openFileOutput("dataForWvWToolbox.txt", Context.MODE_PRIVATE);
                outStream.write(worldNumber.getBytes());
                outStream.close();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public String readDataFromFile() {
        BufferedReader input = null;
        File file = null;
        String fileData = "1";
        //Read from external storage if it is mounted
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            try {
                file = new File(Environment.getExternalStorageDirectory()+"/wvwtoolbox", "dataForWvWToolbox.txt");
                if(!file.exists()) {
                    file.createNewFile();
                    OutputStream outStream = new FileOutputStream(file);
                    outStream.write("1".getBytes());
                    outStream.close();
                }
                input = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                String line;
                StringBuffer buffer = new StringBuffer();
                while ((line = input.readLine()) != null) {
                    buffer.append(line);
                }

                fileData = buffer.toString();
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
            finally {
                return fileData;
            }
        }
        else {
            try {
                file = new File(getFilesDir(), "dataForWvWToolbox.txt");
                input = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                String line;
                StringBuffer buffer = new StringBuffer();
                while ((line = input.readLine()) != null) {
                    buffer.append(line);
                }

                fileData = buffer.toString();
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
            finally {
                return fileData;
            }
        }
    }
}
