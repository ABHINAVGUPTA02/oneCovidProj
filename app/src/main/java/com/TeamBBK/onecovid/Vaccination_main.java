package com.TeamBBK.onecovid;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Vaccination_main extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    String baseUrl = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByPin";
    private EditText editPin;
    private Button forwardbtn;
    ProgressBar holdOnProgress;
    private ArrayList<VaccineModal> vaccination_centers;
    private RecyclerView resultRecyclerView;
    String areaPIN, avlDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_vaccination_main);
        mapViews();
        onClickSetup();

    }

    private void onClickSetup() {

            forwardbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holdOnProgress.setVisibility(View.VISIBLE);
                    DialogFragment dp = new PickDate();
                    dp.show(getSupportFragmentManager(),"pick a date");
                }
            });
    }

    private void mapViews() {
        VaccinationInfoAdapter vaccinationInfoAdapter = new VaccinationInfoAdapter(getApplicationContext(),vaccination_centers);
        forwardbtn = findViewById(R.id.searchBtn);
        holdOnProgress = findViewById(R.id.PBloading);
        editPin = findViewById(R.id.editPin);
        resultRecyclerView = findViewById(R.id.RVcenters);
        resultRecyclerView.setAdapter(vaccinationInfoAdapter);
        resultRecyclerView.setHasFixedSize(true);
        vaccination_centers = new ArrayList<VaccineModal>();

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar k = Calendar.getInstance();
        k.set(Calendar.YEAR,year);
        k.set(Calendar.MONTH,month);
        k.set(Calendar.DAY_OF_MONTH,dayOfMonth);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setTimeZone((k.getTimeZone()));

        String d = dateFormat.format(k.getTime());
        setup(d);
    }

    private void setup(String d) {

        avlDate = d;
        fetchDataNow();
    }

    private void fetchDataNow() {
        vaccination_centers.clear();
        areaPIN = editPin.getText().toString();
        String url_api = baseUrl + "pincode=" + areaPIN +"&date=" + avlDate;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url_api, new Response.Listener<String>()

        {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray sessionArray = object.getJSONArray("sessions");
                    for (int i = 0; i < sessionArray.length(); i++) {
                        JSONObject sesObject = sessionArray.getJSONObject(i);
                        VaccineModal vaccineModal = new VaccineModal();
                        vaccineModal.setVaccineCenter(sesObject.getString("name"));
                        vaccineModal.setVaccineCenterAddress(sesObject.getString("address"));
                        vaccineModal.setVaccinationTimings(sesObject.getString("from"));
                        vaccineModal.setVaccineCenterTime(sesObject.getString("to"));
                        vaccineModal.setVaccineName(sesObject.getString("vaccine"));
                        vaccineModal.setVaccinationCharges(sesObject.getString("fee_type"));
                        vaccineModal.setVaccinationAge(sesObject.getString("min_age_limit"));
                        vaccineModal.setVaccineAvailable(sesObject.getString("available_capacity"));
                        vaccination_centers.add(vaccineModal);
                    }

                    VaccinationInfoAdapter vaccinationInfoAdapter = new VaccinationInfoAdapter(getApplicationContext(), vaccination_centers);
                    resultRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    resultRecyclerView.setAdapter(vaccinationInfoAdapter);
                    holdOnProgress.setVisibility((View.INVISIBLE));
                } catch (Exception e) {
                    holdOnProgress.setVisibility(View.INVISIBLE);
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               holdOnProgress.setVisibility(View.INVISIBLE);
               Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}