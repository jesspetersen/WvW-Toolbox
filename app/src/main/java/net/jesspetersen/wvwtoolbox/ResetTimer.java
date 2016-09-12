package net.jesspetersen.wvwtoolbox;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ResetTimer.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ResetTimer#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResetTimer extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    protected Activity mActivity;
    TextView textView;
    TextView time;
    ToggleButton tog;
    Button but;

    public ResetTimer() {
        // Required empty public constructor
    }


    public static ResetTimer newInstance() {
        ResetTimer fragment = new ResetTimer();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_reset_timer, container, false);
        textView = (TextView) v.findViewById(R.id.tur);
        time = (TextView) v.findViewById(R.id.timer);
        tog = (ToggleButton) v.findViewById(R.id.serverToggle);
        but = (Button) v.findViewById(R.id.reloadTime);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnButtClick(v);
            }
        });
        LoadResetTimeNA();
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
            case R.id.reloadTime:
            {
                if (tog.isChecked() == true)
                    LoadResetTimeEU();
                else
                    LoadResetTimeNA();
                break;
            }

        }
    }

    public void LoadResetTimeNA()
    {
        //Target time is Friday 02.00
        int days, hours, mins;
        DateTime now = new DateTime();
        DateTime nowUTC = now.withZone(DateTimeZone.forID("Etc/UTC"));
        int dayOfWeekNumber = nowUTC.getDayOfWeek();
        int hour = nowUTC.getHourOfDay();
        int min = nowUTC.getMinuteOfHour();
        //Monday = 1; Tuesday = 2; Wednesday = 3; Thursday = 4; Friday = 5; Saturday = 6; Sunday = 7
        switch (dayOfWeekNumber) {
            case 1:
                days = 4;
                break;

            case 2:
                days = 3;
                break;

            case 3:
                days = 2;
                break;

            case 4:
                days = 1;
                break;

            case 5:
                if (hour >= 2)
                    days = 7;
                else
                    days = 0;
                break;

            case 6:
                days = 6;
                break;

            case 7:
                days = 5;
                break;

            default:
                days = 100;
                break;
        }

        if (hour <= 2)
            hours = 2 - hour;
        else
        {
            hours = 26 - hour;
            days = days - 1;
        }
        mins = 60 - min;
        if (min != 60)
            hours = hours - 1;
        time.setText(days + "D " + hours + "H " + mins + "M");
    }

    public void LoadResetTimeEU()
    {
        //Target time is Friday @ 18.00
        int days, hours, mins;
        DateTime now = new DateTime();
        DateTime nowUTC = now.withZone(DateTimeZone.forID("Etc/UTC"));
        int dayOfWeekNumber = nowUTC.getDayOfWeek();
        int hour = nowUTC.getHourOfDay();
        int min = nowUTC.getMinuteOfHour();
        switch (dayOfWeekNumber) {
            case 1:
                days = 4;
                break;

            case 2:
                days = 3;
                break;

            case 3:
                days = 2;
                break;

            case 4:
                days = 1;
                break;

            case 5:
                if (hour >= 18)
                    days = 7;
                else
                    days = 0;
                break;

            case 6:
                days = 6;
                break;

            case 7:
                days = 5;
                break;

            default:
                days = 100;
                break;
        }

        if (hour <= 18)
            hours = 18 - hour;
        else
        {
            hours = 42 - hour;
            days = days - 1;
        }
        mins = 60 - min;
        if (min != 60)
            hours = hours - 1;
        time.setText(days + "D " + hours + "H " + mins + "M");
    }
}
