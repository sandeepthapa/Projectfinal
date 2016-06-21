package com.example.user.cn;

import android.app.DatePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;



public class Adventurousactivities extends Fragment implements ImageButton.OnClickListener, AdapterView.OnItemSelectedListener {
    private static final String TAG = "Tour";
    Button c;
    EditText msg , emailet, phone ,name,customMsg;
    Spinner spinner;


    private String mParam1;
    private String mParam2;
    private TextView txtdate;
    private int dd;
    private int mm;
    private int yy;
    private DatePickerDialog mDatePickerDialog;
    private ImageButton dates;
    private SimpleDateFormat dateFormatter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        setDateTimeField();
    }

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

//        return inflater.inflate(R.layout.fragment_blank, container, false);
        View view = null;
        view = inflater.inflate(R.layout.fragment_adventurousactivities, container, false);
        c = (Button) view.findViewById(R.id.Conf);
        dates = (ImageButton) view.findViewById(R.id.imgbtncal);
        msg = (EditText) view.findViewById(R.id.nme);
        emailet = (EditText) view.findViewById(R.id.email);
        phone = (EditText) view.findViewById(R.id.num);
        name = (EditText) view.findViewById(R.id.nme);
        customMsg = (EditText) view.findViewById(R.id.customMsg);
        dates = (ImageButton) view.findViewById(R.id.imgbtncal);
        dates.setOnClickListener(this);

        txtdate = (TextView) view.findViewById(R.id.txtappdate);
        txtdate.setInputType(InputType.TYPE_NULL);
        String[]  AD =
                {"Rockclimbing","paragliding","BUnjee Jump","Hanggliding",
                        "ultraligh",
                        "Mountainflight",   "Kiking"
                        ,"White water Rafting"
                        ,"Cannouying"
                        ,"Mountain climbing"
                        ,"zipline",
                        "Golf",
                        "HOrse Riding"};
        spinner = (Spinner) view.findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, AD);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_item);
        spinner.setAdapter(adapter);
        return view;
    }

    public void onClick(View view) {
        mDatePickerDialog.show();
        String message = msg.getText().toString();
        String email = emailet.getText().toString();
        String ph = phone.getText().toString();
        String nm = name.getText().toString();
        String customMessage = customMsg.getText().toString();

        String valueFromSpinner = spinner.getSelectedItem().toString();
        int day = dd;
        int month = mm;
        int year = yy;
        Log.d(TAG, "onClick: "+message+">>"+valueFromSpinner+">>"+day+">>"+">>"+month+">>"+year);
        sendEmail(message,valueFromSpinner,day,month,year,email,ph,nm,customMessage );

    }
    public void sendEmail(String extraMsg, String mValueFromSpinner, int day, int month, int year , String emailet, String ph, String nm,String customMessage) {

        /*String[] to = new String[]{"stc2065@gmail.com"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
        emailIntent.putExtra(Intent.EXTRA_TEXT, messsage);
        emailIntent.setType("message/rfc822");
        startActivity(Intent.createChooser(emailIntent, "Email"));*/


        try {


            Log.d(TAG, "sendEmail: " + "Ticket Booked for " + mValueFromSpinner + " Adventurous Actiivities" +
                    "Your ticket is booked for " + year + "/" + month + "/" + day + "/n" + extraMsg);
            GMailSender sender = new GMailSender("aegeus11@gmail.com", "lordsaveus2");
            sender.sendMail("Ticket Booked for " + mValueFromSpinner + " Adv. Activity",
                    "Please book the Ticket for " + year + "/" + month + "/" + day + "\n"
                            + "Name =" + nm
                              + "Email =" + emailet
                            +"Phone no:"+ph
                            + "Message :"+customMessage,
                    "aegeus11@gmail.com",
                    "theanilpaudel@gmail.com");
        } catch (Exception e) {
            Log.e("SendMail", e.getMessage(), e);
        }
    }

    private void setDateTimeField() {
//        dates.setOnClickListener(this);
        mDatePickerDialog = new DatePickerDialog(getActivity(), mDateSetListener, 2016, 05, 16);
        Calendar newCalendar = Calendar.getInstance();
    }

    DatePickerDialog.OnDateSetListener mDateSetListener
            = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            yy = selectedYear;
            mm = selectedMonth;
            dd = selectedDay;
            //age.setDateOfBirth(startYear, startMonth, startDay);
            txtdate.setText("" + dd + "-" + (mm + 1) + "-" + yy);
            //calculateAge();
        }
    };

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}