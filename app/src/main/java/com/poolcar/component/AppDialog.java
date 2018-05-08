package com.poolcar.component;

import android.app.Activity;
import android.app.Dialog;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.AutocompletePredictionBufferResponse;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.poolcar.R;
import com.poolcar.adapter.AddressAdapter;
import com.poolcar.utils.AppUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AppDialog {


    private  List<String> locationString = null;
    private  String query = null;
    private final static String TAG = AppDialog.class.getName();

    public void showAddressAutoCompleteDialog(final Activity activity){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.auto_complete_dialog);
        final EditText searchText = (EditText)dialog.findViewById(R.id.searchText);


        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                query=null;
                query = searchText.getText().toString();
                LatLngBounds mBounds = new LatLngBounds(new LatLng(-0, 0), new LatLng(0, -1));
                AutocompleteFilter typeFilter = new AutocompleteFilter.Builder().setTypeFilter(AutocompleteFilter.TYPE_FILTER_ADDRESS).setCountry(AppUtils.getCountryCode(activity).toUpperCase()).build();
                GeoDataClient mGeoDataClient = Places.getGeoDataClient(activity, null);
                Task<AutocompletePredictionBufferResponse> results = mGeoDataClient.getAutocompletePredictions(query, mBounds, typeFilter);

                results.addOnSuccessListener(new OnSuccessListener<AutocompletePredictionBufferResponse>() {
                    @Override
                    public void onSuccess(AutocompletePredictionBufferResponse autocompletePredictions) {
                        Iterator<AutocompletePrediction> iterator = autocompletePredictions.iterator();
                        locationString = new ArrayList<String>(autocompletePredictions.getCount());
                        if (iterator.hasNext()) {
                            while (iterator.hasNext()) {
                                AutocompletePrediction prediction = iterator.next();
                                Log.d(TAG, "getPlaceId::"+prediction.getPrimaryText(null)+prediction.getSecondaryText(null));
                                locationString.add(prediction.getFullText(null).toString());
                            }
                            if(locationString!=null) {
                                AddressAdapter adapter = new AddressAdapter(activity, (ArrayList)locationString, query);
                                ListView listView = (ListView) dialog.findViewById(R.id.searchList);
                                listView.setAdapter(adapter);
                                locationString=null;
                            }
                        }else {

                        }
                        autocompletePredictions.release();
                    }
                });

                results.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "results returned Exception::"+e.getMessage());
                    }
                });



            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        dialog.show();
    }

}
