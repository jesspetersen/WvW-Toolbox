package net.jesspetersen.wvwtoolbox;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;


public class MatchupPoints extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    SharedPreferences SP;
    protected Activity mActivity;
    String matchJSON;
    List<World> worldList;
    Matchup thisMatch;
    String serverID;

    //Visual Elements
    TextView myServ;
    Button but;
    TextView redName;
    TextView redPoints;
    TextView redKills;
    TextView redDeaths;
    TextView blueName;
    TextView bluePoints;
    TextView blueKills;
    TextView blueDeaths;
    TextView greenName;
    TextView greenPoints;
    TextView greenKills;
    TextView greenDeaths;

    public MatchupPoints() {}

    public static MatchupPoints newInstance() {
        MatchupPoints fragment = new MatchupPoints();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Context context = getActivity().getApplicationContext();
        worldList = new ArrayList<>();
        SP = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        serverID = SP.getString("userServer", "1");
        WorldNamesDownload wndTask = new WorldNamesDownload();
        wndTask.execute("https://api.guildwars2.com/v2/worlds?ids=all");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_matchup_points, container, false);
        myServ = (TextView) v.findViewById(R.id.myServer);
        redName = (TextView) v.findViewById(R.id.redTeamName);
        redPoints = (TextView) v.findViewById(R.id.redTeamScore);
        redKills = (TextView) v.findViewById(R.id.redTeamKills);
        redDeaths = (TextView) v.findViewById(R.id.redTeamDeaths);
        blueName = (TextView) v.findViewById(R.id.blueTeamName);
        bluePoints = (TextView) v.findViewById(R.id.blueTeamScore);
        blueKills = (TextView) v.findViewById(R.id.blueTeamKills);
        blueDeaths = (TextView) v.findViewById(R.id.blueTeamDeaths);
        greenName = (TextView) v.findViewById(R.id.greenTeamName);
        greenPoints = (TextView) v.findViewById(R.id.greenTeamScore);
        greenKills = (TextView) v.findViewById(R.id.greenTeamKills);
        greenDeaths = (TextView) v.findViewById(R.id.greenTeamDeaths);
        but = (Button) v.findViewById(R.id.refreshBtn);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnButtClick(v);
            }
        });

        return v;
    }

    public void populateServerName(){
        if (serverID.equals("1"))
        {
            myServ.setText("Please go to settings and select your server.");
        }
        else {
            for (World w : worldList) {
                if (w.getId().equals(serverID)) {
                    myServ.setText("Go " + w.getName() + "!");
                }
            }
        }
    }

    public void getMatchupData(){
        serverID = SP.getString("userServer", "1");
        if (serverID == "1")
            Toast.makeText(getActivity().getApplicationContext(), "Please go to settings by clicking the dots on the top right corner and select your server.", Toast.LENGTH_LONG).show();
        else {
            if (worldList.size() < 1)
            {
                WorldNamesDownload wndTask = new WorldNamesDownload();
                wndTask.execute("https://api.guildwars2.com/v2/worlds?ids=all");
            }
            Toast.makeText(getActivity().getApplicationContext(), "Loading, please wait...", Toast.LENGTH_SHORT).show();
            MatchupDataDownload mudTask = new MatchupDataDownload();
            String mudURL = "https://api.guildwars2.com/v2/wvw/matches?world=" + serverID;
            mudTask.execute(mudURL);
        }
    }

    public boolean isNetworkAvailable(Context ctx)
    {
        ConnectivityManager cm = (ConnectivityManager)ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()&& cm.getActiveNetworkInfo().isAvailable()&& cm.getActiveNetworkInfo().isConnected())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void OnButtClick(View v)
    {
        switch (v.getId())
        {
            case R.id.refreshBtn:
            {
                getMatchupData();
            }
        }
    }

    public void populateMatchupData(){

        if (worldList.size() > 0) {
            //Red server name
            for (World w : worldList) {
                if (thisMatch.getRed().mainWorldID.equals(w.getId())) {
                    redName.setText(w.getName());
                }
            }

            //Red server details
            redPoints.setText("SCORE: " + thisMatch.getRed().getPoints());
            redKills.setText("KILLS: " + thisMatch.getRed().getKills());
            redDeaths.setText("DEATHS: " + thisMatch.getRed().getDeaths());

            //Blue server name
            for (World w : worldList) {
                if (thisMatch.getBlue().mainWorldID.equals(w.getId())) {
                    blueName.setText(w.getName());
                }
            }

            //Blue server details
            bluePoints.setText("SCORE: " + thisMatch.getBlue().getPoints());
            blueKills.setText("KILLS: " + thisMatch.getBlue().getKills());
            blueDeaths.setText("DEATHS: " + thisMatch.getBlue().getDeaths());

            //Green server name
            for (World w : worldList) {
                if (thisMatch.getGreen().mainWorldID.equals(w.getId())) {
                    greenName.setText(w.getName());
                }
            }

            //Green server details
            greenPoints.setText("SCORE: " + thisMatch.getGreen().getPoints());
            greenKills.setText("KILLS: " + thisMatch.getGreen().getKills());
            greenDeaths.setText("DEATHS: " + thisMatch.getGreen().getDeaths());
        }
    }

    public class WorldNamesDownload extends AsyncTask<String,Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            if (isNetworkAvailable(getContext())) {
                URL url;
                HttpURLConnection urlConnection = null;

                try {
                    url = new URL(urls[0]);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    InputStream in = urlConnection.getInputStream();

                    InputStreamReader reader = new InputStreamReader(in);
                    int data = reader.read();
                    while (data != -1) {
                        char current = (char) data;
                        result += current;
                        data = reader.read();
                    }

                    return result;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
            return "noInternet";
        }

        @Override
        protected void onPostExecute(String result) {
            if (result == "noInternet") {
                Context context = getActivity().getApplicationContext();
                Toast.makeText(context, "This section requires internet access.", Toast.LENGTH_LONG).show();
            } else {
                try {
                    JSONArray arr = new JSONArray(result);

                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject jsonPart = arr.getJSONObject(i);

                        String id = jsonPart.getString("id");
                        String name = jsonPart.getString("name");
                        String population = jsonPart.getString("population");

                        World w = new World(id, name, population);
                        worldList.add(w);
                    }

                    Log.i("WorldListContent", worldList.toString());
                    Log.i("WebsitecontentWorldName", result);

                    populateServerName();

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public class MatchupDataDownload extends AsyncTask<String,Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            if (isNetworkAvailable(getContext())) {
                URL url;
                HttpURLConnection urlConnection = null;

                if (serverID != "1") {

                    try {
                        url = new URL(urls[0]);
                        urlConnection = (HttpURLConnection) url.openConnection();
                        InputStream in = urlConnection.getInputStream();

                        InputStreamReader reader = new InputStreamReader(in);
                        int data = reader.read();
                        while (data != -1) {
                            char current = (char) data;
                            result += current;
                            data = reader.read();
                        }

                        matchJSON = result;
                        Log.i("MatchupDatadl JSON", result);
                        return result;

                    } catch (Exception e) {
                        e.printStackTrace();
                        return "error";
                    }
                }
                return "error";
            }
            return "noInternet";
        }

        @Override
        protected void onPostExecute(String result) {

            if (result == "noInternet") {
                Context context = getActivity().getApplicationContext();
                Toast.makeText(context, "This section requires internet access.", Toast.LENGTH_LONG).show();
            } else if (result != "error")
                setUpMatch(result);
            else if (result == "error"){
                Context context = getActivity().getApplicationContext();
                Toast.makeText(context, "Sorry, cannot access server.", Toast.LENGTH_LONG).show();
            }
            else {
                Context context = getActivity().getApplicationContext();
                Toast.makeText(context, "Please go to settings and select your server." , Toast.LENGTH_LONG).show();
            }
        }

        public void setUpMatch(String result){
            if (result != null) {
                if(isNetworkAvailable(getContext()) && worldList.size() > 0) {
                    try {
                        JSONObject allJSON = new JSONObject(result);

                        String mainWorldJSON = allJSON.getString("worlds");

                        JSONObject teamMainWorld = new JSONObject(mainWorldJSON);

                        String redMainWorld = teamMainWorld.getString("red");
                        String blueMainWorld = teamMainWorld.getString("blue");
                        String greenMainWorld = teamMainWorld.getString("green");

                        String worldsJSON = allJSON.getString("all_worlds");
                        Log.i("WorldsSetUpMatch", worldsJSON);

                        JSONObject worldIDs = new JSONObject(worldsJSON);

                        JSONArray redIDs = worldIDs.getJSONArray("red");
                        JSONArray blueIDs = worldIDs.getJSONArray("blue");
                        JSONArray greenIDs = worldIDs.getJSONArray("green");

                        List<String> redTeam = new ArrayList<>();
                        int redLength = redIDs.length();
                        for (int i = 0; i < redLength; i++) {
                            redTeam.add(redIDs.get(i).toString());
                        }

                        List<String> blueTeam = new ArrayList<>();
                        int blueLength = blueIDs.length();
                        for (int i = 0; i < blueLength; i++) {
                            blueTeam.add(blueIDs.get(i).toString());
                        }

                        List<String> greenTeam = new ArrayList<>();
                        int greenLength = greenIDs.length();
                        for (int i = 0; i < greenLength; i++) {
                            greenTeam.add(greenIDs.get(i).toString());
                        }

                        String pointsJSON = allJSON.getString("scores");

                        JSONObject teamScores = new JSONObject(pointsJSON);

                        String redScore = teamScores.getString("red");
                        String blueScore = teamScores.getString("blue");
                        String greenScore = teamScores.getString("green");

                        String killsJSON = allJSON.getString("kills");

                        JSONObject teamKills = new JSONObject(killsJSON);

                        String redKills = teamKills.getString("red");
                        String blueKills = teamKills.getString("blue");
                        String greenKills = teamKills.getString("green");

                        String deathsJSON = allJSON.getString("deaths");

                        JSONObject teamDeaths = new JSONObject(deathsJSON);

                        String redDeaths = teamDeaths.getString("red");
                        String blueDeaths = teamDeaths.getString("blue");
                        String greenDeaths = teamDeaths.getString("green");

                        MatchupWorld red = new MatchupWorld(redMainWorld, redTeam, redScore, redKills, redDeaths);
                        MatchupWorld blue = new MatchupWorld(blueMainWorld, blueTeam, blueScore, blueKills, blueDeaths);
                        MatchupWorld green = new MatchupWorld(greenMainWorld, greenTeam, greenScore, greenKills, greenDeaths);
                        thisMatch = new Matchup(red, blue, green);

                        populateMatchupData();
                        populateServerName();

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getActivity().getApplicationContext(), "Unable to connect to server, please try later.", Toast.LENGTH_LONG).show();
                    }

                    Log.i("Setupmatch", result);
                    Log.i("Matchup", thisMatch.toString());
                }
                else
                    Log.i("setupmatch", "worldList empty");
            }
            else {
                Toast.makeText(getActivity().getApplicationContext(), "Loading, please wait.", Toast.LENGTH_SHORT).show();
                Log.i("setupmatch", "matchupdataempty");
            }
        }
    }
}
