package com.TeamBBK.onecovid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VaccinationInfoAdapter extends RecyclerView.Adapter<VaccinationInfoAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private List<VaccineModal> list_vaccine;

    public VaccinationInfoAdapter(Context context, List<VaccineModal> list_vaccine) {
        this.layoutInflater = layoutInflater.from(context);
        this.list_vaccine = list_vaccine;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.center_rv_layout,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.vaccinationCenter.setText(list_vaccine.get(position).getVaccineCenter());
            holder.vaccinationCenterAddr.setText(list_vaccine.get(position).getVaccineCenterAddress());
            holder.vaccinationTiming.setText(list_vaccine.get(position).getVaccinationTimings()+" - "+ list_vaccine.get(position).getVaccineCenterTime());
            holder.vaccineName.setText(list_vaccine.get(position).getVaccineName());
            holder.vaccinationAvailable.setText(list_vaccine.get(position).getVaccineAvailable());
            holder.vaccineCharges.setText(list_vaccine.get(position).getVaccinationCharges());
            holder.vaccinationAge.setText(list_vaccine.get(position).getVaccinationAge());


    }

    @Override
    public int getItemCount() {
        return list_vaccine.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView vaccinationCenter;
        TextView vaccinationCenterAddr;
        TextView vaccinationTiming;
        TextView vaccineName;
        TextView vaccineCharges;
        TextView vaccinationAge;
        TextView vaccinationAvailable;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            vaccinationAge = itemView.findViewById(R.id.vaccinationAge);
            vaccinationAvailable = itemView.findViewById(R.id.vaccineAvailable);
            vaccineCharges = itemView.findViewById(R.id.vaccinationCharges);
            vaccineName = itemView.findViewById(R.id.vaccineName);
            vaccinationTiming = itemView.findViewById(R.id.vaccinationTimings);
            vaccinationCenter = itemView.findViewById(R.id.vaccineCenter);
            vaccinationCenterAddr = itemView.findViewById(R.id.vaccinationCenterAddress);

        }
    }
}
