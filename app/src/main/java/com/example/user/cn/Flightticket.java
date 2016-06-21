package com.example.user.cn;

import android.app.DatePickerDialog;
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


public class Flightticket extends Fragment implements ImageButton.OnClickListener, AdapterView.OnItemSelectedListener {
    private static final String TAG = "Flightticket";
    Button c;
    EditText msg , emailet, phone , from, to,name,customMsg;

    private String mParam1;
    private String mParam2,mValueFromSpinner;
    private TextView txtdate;
    private int dd;
    private int mm;
    private int yy;
    DatePickerDialog mDatePickerDialog;
    private ImageButton dates;
    private SimpleDateFormat dateFormatter;
    Spinner spinner;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
//        setDateTimeField();
    }

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

//        return inflater.inflate(R.layout.fragment_blank, container, false);
        View view = null;
        view = inflater.inflate(R.layout.fragment_flightticket, container, false);
        c = (Button) view.findViewById(R.id.Conf);
        dates = (ImageButton) view.findViewById(R.id.imgbtncal);
        msg = (EditText) view.findViewById(R.id.nme);
        customMsg = (EditText)view.findViewById(R.id.customMsg);
        emailet = (EditText) view.findViewById(R.id.email);
        phone = (EditText) view.findViewById(R.id.num);
        from = (EditText) view.findViewById(R.id.from);
        to = (EditText) view.findViewById(R.id.dest);
        name = (EditText) view.findViewById(R.id.nme);


        dates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatePickerDialog = new DatePickerDialog(getActivity(), mDateSetListener, 2016, 05, 16);
                mDatePickerDialog.show();
            }
        });
        c.setOnClickListener(this);

        txtdate = (TextView) view.findViewById(R.id.txtappdate);
        txtdate.setInputType(InputType.TYPE_NULL);
        String[] Flight =
                {"National", "International", "Chartered"};
        spinner = (Spinner) view.findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, Flight);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_item);
        spinner.setAdapter(adapter);
        return view;
    }

    @Override
    public void onClick(View view) {
//        mDatePickerDialog.show();
//
        String message = msg.getText().toString();
        String email = emailet.getText().toString();
        String ph = phone.getText().toString();
        String fd = from.getText().toString();
        String td = to.getText().toString();
        String nm = name.getText().toString();
        String customMessage = customMsg.getText().toString();

        String valueFromSpinner = spinner.getSelectedItem().toString();
        int day = dd;
        int month = mm;
        int year = yy;
        Log.d(TAG, "onClick: "+message+">>"+valueFromSpinner+">>"+day+">>"+">>"+month+">>"+year);
        sendEmail(message,valueFromSpinner,day,month,year,email,ph,fd,td,nm,customMessage);
    }

    public void sendEmail(String extraMsg, String mValueFromSpinner, int day, int month, int year, String emailet, String ph, String fd, String td, String nm, String customMessage) {

        /*String[] to = new String[]{"stc2065@gmail.com"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
        emailIntent.putExtra(Intent.EXTRA_TEXT, messsage);
        emailIntent.setType("message/rfc822");
        startActivity(Intent.createChooser(emailIntent, "Email"));*/


        try {


            Log.d(TAG, "sendEmail: "+"Ticket Booked for "+mValueFromSpinner+" flight"+
                    "Your ticket is booked for "+year+"/"+month+"/"+day+"\n"+customMessage);
            GMailSender sender = new GMailSender("aegeus11@gmail.com", "lordsaveus2");
            sender.sendMail("Ticket Booked for "+mValueFromSpinner+" flight",
                    "Your ticket is booked for "+year+"/"+month+"/"+day+"\n"
                            +"Name ="+nm
                            +"Email ="+emailet
                            +"Phone no:"+ph
                            +"from destination:"+fd
                            +"To Destination"+td
                            +"Messaage :" +customMessage,
                    "aegeus11@gmail.com",
                    "stc2065@gmail.com");
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

            Log.d(TAG, "onDateSet: year"+yy);

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
