package net.jesspetersen.wvwtoolbox;


import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;


/**
 * A simple {@link Fragment} subclass.
 */
public class RankCalc extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    protected Activity mActivity;
    TextView text1;
    EditText rankSearch;
    Button but;
    TextView rankNum;
    TextView currentTitle;
    TextView nextTitleNum;
    TextView nextTitleName;
    TextView nextRankNum;
    TextView nextRankName;
    TextView text2;

    public RankCalc() {
        // Required empty public constructor
    }


    public static RankCalc newInstance() {
        RankCalc fragment = new RankCalc();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_rank_calc, container, false);
        text1 = (TextView)v.findViewById(R.id.text1);
        rankSearch = (EditText)v.findViewById(R.id.rankSearch);
        but = (Button) v.findViewById(R.id.sb);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnButtClick(v);
            }
        });
        rankNum = (TextView)v.findViewById(R.id.rankNum);
        currentTitle = (TextView)v.findViewById(R.id.currentTitle);
        nextTitleNum = (TextView)v.findViewById(R.id.nextTitleNum);
        nextTitleName = (TextView)v.findViewById(R.id.nextTitleName);
        nextRankNum = (TextView)v.findViewById(R.id.nextRankNum);
        nextRankName = (TextView)v.findViewById(R.id.nextRankName);
        text2 = (TextView)v.findViewById(R.id.text2);
        return v;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void OnButtClick(View v)
    {
        switch (v.getId())
        {
            case R.id.sb:
            {
                if (rankSearch.getText().toString().matches("[0-9]+") && Integer.parseInt(rankSearch.getText().toString()) >= 0 )
                    SearchRank(rankSearch.getText().toString());
                else{
                    Context context = getContext();
                    Toast toast = Toast.makeText(context, "Please enter a valid rank number.", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        }
    }

    public void SearchRank(String rank)
    {
        Integer rankInt = Integer.parseInt(rank);
        rankNum.setText("For Rank: " +rank + "");
        String currentRank;
        if (rankInt >= 1 && rankInt <150)
            currentRank = "";
        else if (rankInt >= 150 && rankInt <620)
            currentRank = "Bronze";
        else if (rankInt >= 620 && rankInt <1395)
            currentRank = "Silver";
        else if (rankInt >= 1395 && rankInt <2545)
            currentRank = "Gold";
        else if (rankInt >= 2545 && rankInt <4095)
            currentRank = "Platinum";
        else if (rankInt >= 4095 && rankInt <6445)
            currentRank = "Mithril";
        else
            currentRank = "Diamond";
        if (rankInt == 1 && rankInt < 5 ^ rankInt >=150 && rankInt < 180 ^ rankInt >=620 && rankInt < 670 ^ rankInt >=1395 && rankInt < 1470 ^ rankInt >=2545 && rankInt < 2645 ^ rankInt >=4095 && rankInt < 4245 ^ rankInt >=6445 && rankInt < 6695)
        {
            currentTitle.setText("You are currently a " +currentRank + " Invader.");
            if (currentRank == "")
            {
                nextTitleNum.setText("You are " +(5 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Assaulter.");
                nextRankNum.setText("You are " +(150 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Bronze.");
            }
            if (currentRank == "Bronze")
            {
                nextTitleNum.setText("You are " +(180 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Bronze Assaulter.");
                nextRankNum.setText("You are " +(620 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Silver.");
            }
            if (currentRank == "Silver")
            {
                nextTitleNum.setText("You are " +(670 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Silver Assaulter.");
                nextRankNum.setText("You are " +(1395 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Gold.");
            }
            if (currentRank == "Gold")
            {
                nextTitleNum.setText("You are " +(1470 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Gold Assaulter.");
                nextRankNum.setText("You are " +(2545 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Platinum.");
            }
            if (currentRank == "Platinum")
            {
                nextTitleNum.setText("You are " +(2645 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Platinum Assaulter.");
                nextRankNum.setText("You are " +(4095 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Mithril.");
            }
            if (currentRank == "Mithril")
            {
                nextTitleNum.setText("You are " +(4245 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Mithril Assaulter.");
                nextRankNum.setText("You are " +(6445 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Diamond.");
            }
            if (currentRank == "Diamond")
            {
                nextTitleNum.setText("You are " +(6695 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Diamond Assaulter.");
                nextRankNum.setText("You are " +(9945 - rankInt) + " away from Diamond Legend.");
                nextRankName.setText("Congratulations, there are no higher ranks.");
            }
        }
        else if (rankInt >=5 && rankInt < 10 ^ rankInt >=180 && rankInt < 210 ^ rankInt >=670 && rankInt < 720 ^ rankInt >=1470 && rankInt < 1545 ^ rankInt >=2645 && rankInt < 2745 ^ rankInt >=4245 && rankInt < 4395 ^ rankInt >=6695 && rankInt < 6945)
        {
            currentTitle.setText("You are currently a " +currentRank + " Assaulter.");
            if (currentRank == "")
            {
                nextTitleNum.setText("You are " +(10 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Raider.");
                nextRankNum.setText("You are " +(150 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Bronze.");
            }
            if (currentRank == "Bronze")
            {
                nextTitleNum.setText("You are " +(210 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Bronze Raider.");
                nextRankNum.setText("You are " +(620 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Silver.");
            }
            if (currentRank == "Silver")
            {
                nextTitleNum.setText("You are " +(720 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Silver Raider.");
                nextRankNum.setText("You are " +(1395 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Gold.");
            }
            if (currentRank == "Gold")
            {
                nextTitleNum.setText("You are " +(1545 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Gold Raider.");
                nextRankNum.setText("You are " +(2545 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Platinum.");
            }
            if (currentRank == "Platinum")
            {
                nextTitleNum.setText("You are " +(2745 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Platinum Raider.");
                nextRankNum.setText("You are " +(4095 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Mithril.");
            }
            if (currentRank == "Mithril")
            {
                nextTitleNum.setText("You are " +(4395 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Mithril Raider.");
                nextRankNum.setText("You are " +(6445 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Diamond.");
            }
            if (currentRank == "Diamond")
            {
                nextTitleNum.setText("You are " +(6945 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Diamond Raider.");
                nextRankNum.setText("You are " +(9945 - rankInt) + " away from Diamond Legend.");
                nextRankName.setText("Congratulations, there are no higher ranks.");
            }
        }
        else if (rankInt >=10 && rankInt < 15 ^ rankInt >=210 && rankInt < 240 ^ rankInt >=720 && rankInt < 770 ^ rankInt >=1545 && rankInt < 1620 ^ rankInt >=2745 && rankInt < 2845 ^ rankInt >=4395 && rankInt < 4545 ^ rankInt >=6945 && rankInt < 7195)
        {
            currentTitle.setText("You are currently a " +currentRank + " Raider.");
            if (currentRank == "")
            {
                nextTitleNum.setText("You are " +(15 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Recruit.");
                nextRankNum.setText("You are " +(150 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Bronze.");
            }
            if (currentRank == "Bronze")
            {
                nextTitleNum.setText("You are " +(240 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Bronze Recruit.");
                nextRankNum.setText("You are " +(620 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Silver.");
            }
            if (currentRank == "Silver")
            {
                nextTitleNum.setText("You are " +(770 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Silver Recruit.");
                nextRankNum.setText("You are " +(1395 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Gold.");
            }
            if (currentRank == "Gold")
            {
                nextTitleNum.setText("You are " +(1620 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Gold Recruit.");
                nextRankNum.setText("You are " +(2545 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Platinum.");
            }
            if (currentRank == "Platinum")
            {
                nextTitleNum.setText("You are " +(2845 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Platinum Recruit.");
                nextRankNum.setText("You are " +(4095 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Mithril.");
            }
            if (currentRank == "Mithril")
            {
                nextTitleNum.setText("You are " +(4545 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Mithril Recruit.");
                nextRankNum.setText("You are " +(6445 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Diamond.");
            }
            if (currentRank == "Diamond")
            {
                nextTitleNum.setText("You are " +(7195 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Diamond Recruit.");
                nextRankNum.setText("You are " +(9945 - rankInt) + " away from Diamond Legend.");
                nextRankName.setText("Congratulations, there are no higher ranks.");
            }
        }
        else if (rankInt >=15 && rankInt < 20 ^ rankInt >=240 && rankInt < 270 ^ rankInt >=770 && rankInt < 820 ^ rankInt >=1620 && rankInt < 1695 ^ rankInt >=2845 && rankInt < 2945 ^ rankInt >=4545 && rankInt < 4695 ^ rankInt >=7195 && rankInt < 7445)
        {
            currentTitle.setText("You are currently a " +currentRank + " Recruit.");
            if (currentRank == "")
            {
                nextTitleNum.setText("You are " +(20 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Scout.");
                nextRankNum.setText("You are " +(150 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Bronze.");
            }
            if (currentRank == "Bronze")
            {
                nextTitleNum.setText("You are " +(270 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Bronze Scout.");
                nextRankNum.setText("You are " +(620 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Silver.");
            }
            if (currentRank == "Silver")
            {
                nextTitleNum.setText("You are " +(820 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Silver Scout.");
                nextRankNum.setText("You are " +(1395 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Gold.");
            }
            if (currentRank == "Gold")
            {
                nextTitleNum.setText("You are " +(1695 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Gold Scout.");
                nextRankNum.setText("You are " +(2545 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Platinum.");
            }
            if (currentRank == "Platinum")
            {
                nextTitleNum.setText("You are " +(2945 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Platinum Scout.");
                nextRankNum.setText("You are " +(4095 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Mithril.");
            }
            if (currentRank == "Mithril")
            {
                nextTitleNum.setText("You are " +(4695 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Mithril Scout.");
                nextRankNum.setText("You are " +(6445 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Diamond.");
            }
            if (currentRank == "Diamond")
            {
                nextTitleNum.setText("You are " +(7445 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Diamond Scout.");
                nextRankNum.setText("You are " +(9945 - rankInt) + " away from Diamond Legend.");
                nextRankName.setText("Congratulations, there are no higher ranks.");
            }
        }
        else if (rankInt >=20 && rankInt < 30 ^ rankInt >=270 && rankInt < 300 ^ rankInt >=820 && rankInt < 870 ^ rankInt >=1695 && rankInt < 1770 ^ rankInt >=2945 && rankInt < 3045 ^ rankInt >=4695 && rankInt < 4845 ^ rankInt >=7445 && rankInt < 7695)
        {
            currentTitle.setText("You are currently a " +currentRank + " Scout.");
            if (currentRank == "")
            {
                nextTitleNum.setText("You are " +(30 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Soldier.");
                nextRankNum.setText("You are " +(150 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Bronze.");
            }
            if (currentRank == "Bronze")
            {
                nextTitleNum.setText("You are " +(300 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Bronze Soldier.");
                nextRankNum.setText("You are " +(620 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Silver.");
            }
            if (currentRank == "Silver")
            {
                nextTitleNum.setText("You are " +(870 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Silver Soldier.");
                nextRankNum.setText("You are " +(1395 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Gold.");
            }
            if (currentRank == "Gold")
            {
                nextTitleNum.setText("You are " +(1770 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Gold Soldier.");
                nextRankNum.setText("You are " +(2545 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Platinum.");
            }
            if (currentRank == "Platinum")
            {
                nextTitleNum.setText("You are " +(3045 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Platinum Soldier.");
                nextRankNum.setText("You are " +(4095 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Mithril.");
            }
            if (currentRank == "Mithril")
            {
                nextTitleNum.setText("You are " +(4845 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Mithril Soldier.");
                nextRankNum.setText("You are " +(6445 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Diamond.");
            }
            if (currentRank == "Diamond")
            {
                nextTitleNum.setText("You are " +(7695 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Diamond Soldier.");
                nextRankNum.setText("You are " +(9945 - rankInt) + " away from Diamond Legend.");
                nextRankName.setText("Congratulations, there are no higher ranks.");
            }
        }
        else if (rankInt >=30 && rankInt < 40 ^ rankInt >=300 && rankInt < 330 ^ rankInt >=870 && rankInt < 920 ^ rankInt >=1770 && rankInt < 1845 ^ rankInt >=3045 && rankInt < 3145 ^ rankInt >=4845 && rankInt < 4995 ^ rankInt >=7695 && rankInt < 7945)
        {
            currentTitle.setText("You are currently a " +currentRank + " Soldier.");
            if (currentRank == "")
            {
                nextTitleNum.setText("You are " +(40 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Squire.");
                nextRankNum.setText("You are " +(150 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Bronze.");
            }
            if (currentRank == "Bronze")
            {
                nextTitleNum.setText("You are " +(330 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Bronze Squire.");
                nextRankNum.setText("You are " +(620 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Silver.");
            }
            if (currentRank == "Silver")
            {
                nextTitleNum.setText("You are " +(920 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Silver Squire.");
                nextRankNum.setText("You are " +(1395 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Gold.");
            }
            if (currentRank == "Gold")
            {
                nextTitleNum.setText("You are " +(1845 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Gold Squire.");
                nextRankNum.setText("You are " +(2545 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Platinum.");
            }
            if (currentRank == "Platinum")
            {
                nextTitleNum.setText("You are " +(3145 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Platinum Squire.");
                nextRankNum.setText("You are " +(4095 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Mithril.");
            }
            if (currentRank == "Mithril")
            {
                nextTitleNum.setText("You are " +(4995 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Mithril Squire.");
                nextRankNum.setText("You are " +(6445 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Diamond.");
            }
            if (currentRank == "Diamond")
            {
                nextTitleNum.setText("You are " +(7945 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Diamond Squire.");
                nextRankNum.setText("You are " +(9945 - rankInt) + " away from Diamond Legend.");
                nextRankName.setText("Congratulations, there are no higher ranks.");
            }
        }
        else if (rankInt >=40 && rankInt < 50 ^ rankInt >=330 && rankInt < 360 ^ rankInt >=920 && rankInt < 970 ^ rankInt >=1845 && rankInt < 1920 ^ rankInt >=3145 && rankInt < 3245 ^ rankInt >=4995 && rankInt < 5145 ^ rankInt >=7945 && rankInt < 8195)
        {
            currentTitle.setText("You are currently a " +currentRank + " Squire.");
            if (currentRank == "")
            {
                nextTitleNum.setText("You are " +(50 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Footman.");
                nextRankNum.setText("You are " +(150 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Bronze.");
            }
            if (currentRank == "Bronze")
            {
                nextTitleNum.setText("You are " +(360 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Bronze Footman.");
                nextRankNum.setText("You are " +(620 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Silver.");
            }
            if (currentRank == "Silver")
            {
                nextTitleNum.setText("You are " +(970 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Silver Footman.");
                nextRankNum.setText("You are " +(1395 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Gold.");
            }
            if (currentRank == "Gold")
            {
                nextTitleNum.setText("You are " +(1920 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Gold Footman.");
                nextRankNum.setText("You are " +(2545 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Platinum.");
            }
            if (currentRank == "Platinum")
            {
                nextTitleNum.setText("You are " +(3245 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Platinum Footman.");
                nextRankNum.setText("You are " +(4095 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Mithril.");
            }
            if (currentRank == "Mithril")
            {
                nextTitleNum.setText("You are " +(5145 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Mithril Footman.");
                nextRankNum.setText("You are " +(6445 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Diamond.");
            }
            if (currentRank == "Diamond")
            {
                nextTitleNum.setText("You are " +(8195 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Diamond Footman.");
                nextRankNum.setText("You are " +(9945 - rankInt) + " away from Diamond Legend.");
                nextRankName.setText("Congratulations, there are no higher ranks.");
            }
        }
        else if (rankInt >=50 && rankInt < 60 ^ rankInt >=360 && rankInt < 390 ^ rankInt >=970 && rankInt < 1020 ^ rankInt >=1920 && rankInt < 1995 ^ rankInt >=3245 && rankInt < 3345 ^ rankInt >=5145 && rankInt < 5295 ^ rankInt >=8195 && rankInt < 8445)
        {
            currentTitle.setText("You are currently a " +currentRank + " Footman.");
            if (currentRank == "")
            {
                nextTitleNum.setText("You are " +(60 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Knight.");
                nextRankNum.setText("You are " +(150 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Bronze.");
            }
            if (currentRank == "Bronze")
            {
                nextTitleNum.setText("You are " +(390 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Bronze Knight.");
                nextRankNum.setText("You are " +(620 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Silver.");
            }
            if (currentRank == "Silver")
            {
                nextTitleNum.setText("You are " +(1020 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Silver Knight.");
                nextRankNum.setText("You are " +(1395 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Gold.");
            }
            if (currentRank == "Gold")
            {
                nextTitleNum.setText("You are " +(1995 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Gold Knight.");
                nextRankNum.setText("You are " +(2545 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Platinum.");
            }
            if (currentRank == "Platinum")
            {
                nextTitleNum.setText("You are " +(3345 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Platinum Knight.");
                nextRankNum.setText("You are " +(4095 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Mithril.");
            }
            if (currentRank == "Mithril")
            {
                nextTitleNum.setText("You are " +(5295 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Mithril Knight.");
                nextRankNum.setText("You are " +(6445 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Diamond.");
            }
            if (currentRank == "Diamond")
            {
                nextTitleNum.setText("You are " +(8445 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Diamond Knight.");
                nextRankNum.setText("You are " +(9945 - rankInt) + " away from Diamond Legend.");
                nextRankName.setText("Congratulations, there are no higher ranks.");
            }
        }
        else if (rankInt >=60 && rankInt < 70 ^ rankInt >=390 && rankInt < 420 ^ rankInt >=1020 && rankInt < 1070 ^ rankInt >=1995 && rankInt < 2070 ^ rankInt >=3345 && rankInt < 3445 ^ rankInt >=5295 && rankInt < 5445 ^ rankInt >8445 && rankInt < 8695)
        {
            currentTitle.setText("You are currently a " +currentRank + " Knight.");
            if (currentRank == "")
            {
                nextTitleNum.setText("You are " +(70 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Major.");
                nextRankNum.setText("You are " +(150 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Bronze.");
            }
            if (currentRank == "Bronze")
            {
                nextTitleNum.setText("You are " +(420 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Bronze Major.");
                nextRankNum.setText("You are " +(620 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Silver.");
            }
            if (currentRank == "Silver")
            {
                nextTitleNum.setText("You are " +(1070 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Silver Major.");
                nextRankNum.setText("You are " +(1395 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Gold.");
            }
            if (currentRank == "Gold")
            {
                nextTitleNum.setText("You are " +(2070 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Gold major.");
                nextRankNum.setText("You are " +(2545 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Platinum.");
            }
            if (currentRank == "Platinum")
            {
                nextTitleNum.setText("You are " +(3445 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Platinum Major.");
                nextRankNum.setText("You are " +(4095 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Mithril.");
            }
            if (currentRank == "Mithril")
            {
                nextTitleNum.setText("You are " +(5445 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Mithril Major.");
                nextRankNum.setText("You are " +(6445 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Diamond.");
            }
            if (currentRank == "Diamond")
            {
                nextTitleNum.setText("You are " +(8695 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Diamond Major.");
                nextRankNum.setText("You are " +(9945 - rankInt) + " away from Diamond Legend.");
                nextRankName.setText("Congratulations, there are no higher ranks.");
            }
        }
        else if (rankInt >=70 && rankInt < 80 ^ rankInt >=420 && rankInt < 450 ^ rankInt >=1070 && rankInt < 1120 ^ rankInt >=2070 && rankInt < 2145 ^ rankInt >=3445 && rankInt < 3545 ^ rankInt >=5445 && rankInt < 5595 ^ rankInt >=8695 && rankInt < 8945)
        {
            currentTitle.setText("You are currently a " +currentRank + " Major.");
            if (currentRank == "")
            {
                nextTitleNum.setText("You are " +(80 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Colonel.");
                nextRankNum.setText("You are " +(150 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Bronze.");
            }
            if (currentRank == "Bronze")
            {
                nextTitleNum.setText("You are " +(450 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Bronze Colonel.");
                nextRankNum.setText("You are " +(620 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Silver.");
            }
            if (currentRank == "Silver")
            {
                nextTitleNum.setText("You are " +(1120 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Silver Colonel.");
                nextRankNum.setText("You are " +(1395 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Gold.");
            }
            if (currentRank == "Gold")
            {
                nextTitleNum.setText("You are " +(2145 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Gold Colonel.");
                nextRankNum.setText("You are " +(2545 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Platinum.");
            }
            if (currentRank == "Platinum")
            {
                nextTitleNum.setText("You are " +(3545 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Platinum Colonel.");
                nextRankNum.setText("You are " +(4095 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Mithril.");
            }
            if (currentRank == "Mithril")
            {
                nextTitleNum.setText("You are " +(5595 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Mithril Colonel.");
                nextRankNum.setText("You are " +(6445 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Diamond.");
            }
            if (currentRank == "Diamond")
            {
                nextTitleNum.setText("You are " +(8945 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Diamond Colonel.");
                nextRankNum.setText("You are " +(9945 - rankInt) + " away from Diamond Legend.");
                nextRankName.setText("Congratulations, there are no higher ranks.");
            }
        }
        else if (rankInt >=80 && rankInt < 90 ^ rankInt >=450 && rankInt < 480 ^ rankInt >=1120 && rankInt < 1170 ^ rankInt >=2145 && rankInt < 2220 ^ rankInt >=3545 && rankInt < 3645 ^ rankInt >=5595 && rankInt < 5745 ^ rankInt >=8945 && rankInt < 9195)
        {
            currentTitle.setText("You are currently a " +currentRank + " Colonel.");
            if (currentRank == "")
            {
                nextTitleNum.setText("You are " +(90 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is General.");
                nextRankNum.setText("You are " +(150 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Bronze.");
            }
            if (currentRank == "Bronze")
            {
                nextTitleNum.setText("You are " +(480 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Bronze General.");
                nextRankNum.setText("You are " +(620 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Silver.");
            }
            if (currentRank == "Silver")
            {
                nextTitleNum.setText("You are " +(1170 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Silver General.");
                nextRankNum.setText("You are " +(1395 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Gold.");
            }
            if (currentRank == "Gold")
            {
                nextTitleNum.setText("You are " +(2220 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Gold General.");
                nextRankNum.setText("You are " +(2545 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Platinum.");
            }
            if (currentRank == "Platinum")
            {
                nextTitleNum.setText("You are " +(3645 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Platinum General.");
                nextRankNum.setText("You are " +(4095 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Mithril.");
            }
            if (currentRank == "Mithril")
            {
                nextTitleNum.setText("You are " +(5745 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Mithril General.");
                nextRankNum.setText("You are " +(6445 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Diamond.");
            }
            if (currentRank == "Diamond")
            {
                nextTitleNum.setText("You are " +(9195 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Diamond General.");
                nextRankNum.setText("You are " +(9945 - rankInt) + " away from Diamond Legend.");
                nextRankName.setText("Congratulations, there are no higher ranks.");
            }
        }
        else if (rankInt >=90 && rankInt < 100 ^ rankInt >=480 && rankInt < 510 ^ rankInt >=1170 && rankInt < 1220 ^ rankInt >=2220 && rankInt < 2295^ rankInt >=3645 && rankInt < 3745 ^ rankInt >=5745 && rankInt < 5895 ^ rankInt >=9195 && rankInt < 9445)
        {
            currentTitle.setText("You are currently a " +currentRank + " General.");
            if (currentRank == "")
            {
                nextTitleNum.setText("You are " +(100 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Veteran.");
                nextRankNum.setText("You are " +(150 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Bronze.");
            }
            if (currentRank == "Bronze")
            {
                nextTitleNum.setText("You are " +(510 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Bronze Veteran.");
                nextRankNum.setText("You are " +(620 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Silver.");
            }
            if (currentRank == "Silver")
            {
                nextTitleNum.setText("You are " +(1220 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Silver Veteran.");
                nextRankNum.setText("You are " +(1395 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Gold.");
            }
            if (currentRank == "Gold")
            {
                nextTitleNum.setText("You are " +(2295 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Gold Veteran.");
                nextRankNum.setText("You are " +(2545 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Platinum.");
            }
            if (currentRank == "Platinum")
            {
                nextTitleNum.setText("You are " +(3745 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Platinum Veteran.");
                nextRankNum.setText("You are " +(4095 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Mithril.");
            }
            if (currentRank == "Mithril")
            {
                nextTitleNum.setText("You are " +(5895 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Mithril Veteran.");
                nextRankNum.setText("You are " +(6445 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Diamond.");
            }
            if (currentRank == "Diamond")
            {
                nextTitleNum.setText("You are " +(9445 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Diamond Veteran.");
                nextRankNum.setText("You are " +(9945 - rankInt) + " away from Diamond Legend.");
                nextRankName.setText("Congratulations, there are no higher ranks.");
            }
        }
        else if (rankInt >=100 && rankInt < 110 ^ rankInt >=510 && rankInt < 540 ^ rankInt >=1220 && rankInt < 1270 ^ rankInt >=2295 && rankInt < 2370 ^ rankInt >=3745 && rankInt < 3845 ^ rankInt >=5895 && rankInt < 6045 ^ rankInt >=9445 && rankInt < 9695)
        {
            currentTitle.setText("You are currently a " +currentRank + " Veteran.");
            if (currentRank == "")
            {
                nextTitleNum.setText("You are " +(110 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Champion.");
                nextRankNum.setText("You are " +(150 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Bronze.");
            }
            if (currentRank == "Bronze")
            {
                nextTitleNum.setText("You are " +(540 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Bronze Champion.");
                nextRankNum.setText("You are " +(620 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Silver.");
            }
            if (currentRank == "Silver")
            {
                nextTitleNum.setText("You are " +(1270 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Silver Champion.");
                nextRankNum.setText("You are " +(1395 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Gold.");
            }
            if (currentRank == "Gold")
            {
                nextTitleNum.setText("You are " +(2370 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Gold Champion.");
                nextRankNum.setText("You are " +(2545 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Platinum.");
            }
            if (currentRank == "Platinum")
            {
                nextTitleNum.setText("You are " +(3845 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Platinum Champion.");
                nextRankNum.setText("You are " +(4095 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Mithril.");
            }
            if (currentRank == "Mithril")
            {
                nextTitleNum.setText("You are " +(6045 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Mithril Champion.");
                nextRankNum.setText("You are " +(6445 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Diamond.");
            }
            if (currentRank == "Diamond")
            {
                nextTitleNum.setText("You are " +(9695 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Diamond Champion.");
                nextRankNum.setText("You are " +(9945 - rankInt) + " away from Diamond Legend.");
                nextRankName.setText("Congratulations, there are no higher ranks.");
            }
        }
        else if (rankInt >=110 && rankInt < 120 ^ rankInt >=540 && rankInt < 570 ^ rankInt >=1270 && rankInt < 1320 ^ rankInt >=2370 && rankInt < 2445 ^ rankInt >=3845 && rankInt < 3945 ^ rankInt >=6045 && rankInt < 6195 ^ rankInt >=9695 && rankInt < 9445)
        {
            currentTitle.setText("You are currently a " +currentRank + " Champion.");
            if (currentRank == "")
            {
                nextTitleNum.setText("You are " +(120 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Legend.");
                nextRankNum.setText("You are " +(150 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Bronze.");
            }
            if (currentRank == "Bronze")
            {
                nextTitleNum.setText("You are " +(570 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Bronze Legend.");
                nextRankNum.setText("You are " +(620 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Silver.");
            }
            if (currentRank == "Silver")
            {
                nextTitleNum.setText("You are " +(1320 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Silver Legend.");
                nextRankNum.setText("You are " +(1395 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Gold.");
            }
            if (currentRank == "Gold")
            {
                nextTitleNum.setText("You are " +(2445 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Gold Legend.");
                nextRankNum.setText("You are " +(2545 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Platinum.");
            }
            if (currentRank == "Platinum")
            {
                nextTitleNum.setText("You are " +(3945 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Platinum Legend.");
                nextRankNum.setText("You are " +(4095 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Mithril.");
            }
            if (currentRank == "Mithril")
            {
                nextTitleNum.setText("You are " +(6195 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Mithril Legend.");
                nextRankNum.setText("You are " +(6445 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Diamond.");
            }
            if (currentRank == "Diamond")
            {
                nextTitleNum.setText("You are " +(9945 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Diamond Legend.");
                nextRankNum.setText("You are " +(9945 - rankInt) + " away from Diamond Legend.");
                nextRankName.setText("Congratulations, there are no higher ranks.");
            }
        }
        else if (rankInt >=120 && rankInt < 150 ^ rankInt >=570 && rankInt < 620 ^ rankInt >=1320 && rankInt < 1395 ^ rankInt >=2445 && rankInt < 2545 ^ rankInt >=3945 && rankInt < 4095 ^ rankInt >=6195 && rankInt < 6445 ^ rankInt >=9445)
        {
            currentTitle.setText("You are currently a " +currentRank + " Legend.");
            if (currentRank == "")
            {
                nextTitleNum.setText("You are " +(150 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Bronze Invader.");
                nextRankNum.setText("You are " +(150 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Bronze.");
            }
            if (currentRank == "Bronze")
            {
                nextTitleNum.setText("You are " +(620 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Silver Invader.");
                nextRankNum.setText("You are " +(620 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Silver.");
            }
            if (currentRank == "Silver")
            {
                nextTitleNum.setText("You are " +(1395 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Gold Invader.");
                nextRankNum.setText("You are " +(1395 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Gold.");
            }
            if (currentRank == "Gold")
            {
                nextTitleNum.setText("You are " +(2545 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Platinum Invader.");
                nextRankNum.setText("You are " +(2545 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Platinum.");
            }
            if (currentRank == "Platinum")
            {
                nextTitleNum.setText("You are " +(4095 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Mithril Invader.");
                nextRankNum.setText("You are " +(4095 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Mithril.");
            }
            if (currentRank == "Mithril")
            {
                nextTitleNum.setText("You are " +(6445 - rankInt) + " away from the next title.");
                nextTitleName.setText("The next title is Diamond Invader.");
                nextRankNum.setText("You are " +(6445 - rankInt) + " away from the next rank.");
                nextRankName.setText("The next rank is Diamond.");
            }
            if (currentRank == "Diamond")
            {
                nextTitleNum.setText("There is no higher title.");
                nextTitleName.setText("Congratulations.");
                nextRankNum.setText("You have achieved maximum WvW Rank.");
                nextRankName.setText("Congratulations, there are no higher ranks.");
            }
        }
    }
}

