package com.guma.desarrollo.gvm.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.ActivityRecognition;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.guma.desarrollo.gvm.DATABASE.SQLiteHelper;
import com.guma.desarrollo.gvm.LIB.Clock;
import com.guma.desarrollo.gvm.MODEL.Articulos_model;
import com.guma.desarrollo.gvm.MODEL.Llaves_model;
import com.guma.desarrollo.gvm.MODEL.LogActividades_model;
import com.guma.desarrollo.gvm.POJO.Articulo;
import com.guma.desarrollo.gvm.POJO.DetalleLog;
import com.guma.desarrollo.gvm.POJO.Llaves;
import com.guma.desarrollo.gvm.POJO.Log_Actividades;
import com.guma.desarrollo.gvm.adapters.ReporteVisitaAdapter;
import com.guma.desarrollo.gvm.services.ManagerURI;
import com.guma.desarrollo.gvm.R;

import java.util.ArrayList;
import java.util.List;

public class LocationActivity extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener, ResultCallback<Status> {

    private static final String TAG = LocationActivity.class.getSimpleName();

    private static final String LOCATION_KEY = "location-key";
    private static final String ACTIVITY_KEY = "activity-key";

    // Location API
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private LocationSettingsRequest mLocationSettingsRequest;
    private Location mLastLocation;

    // Activity Recognition API
    private ActivityDetectionBroadcastReceiver mBroadcastReceiver;

    // UI
    private TextView mLatitude,mlblLatitude;
    private TextView mLongitude,mlblLongitude;
    private TextView mComentarios;

    // Códigos de petición
    public static final int REQUEST_LOCATION = 1;
    public static final int REQUEST_CHECK_SETTINGS = 2;
    private List<Articulo> oArticulo;
    RecyclerView recyclerViewArticulos;
    //List<Articulo> lst = new ArrayList<>();
    ReporteVisitaAdapter articulosAdapter;
   String[] myarray;
   String user,IDFarmacias,opView,nmCliente;

    List<DetalleLog> dtLogs = new ArrayList<>();;
    private SharedPreferences preferences;
    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.guma.desarrollo.gvm.R.layout.activity_location);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mLatitude = findViewById(R.id.tv_latitude);
        mLongitude = findViewById(R.id.tv_longitude);

        mlblLatitude = findViewById(R.id.tv_latitude_label);
        mlblLongitude = findViewById(R.id.tv_longitude_label);

        if (getSupportActionBar() != null){ getSupportActionBar().setDisplayHomeAsUpEnabled(true); }
        buildGoogleApiClient();
        createLocationRequest();
        buildLocationSettingsRequest();
        checkLocationSettings();
        mBroadcastReceiver = new ActivityDetectionBroadcastReceiver();
        updateValuesFromBundle(savedInstanceState);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        user =preferences.getString("IDVM","");
        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            IDFarmacias = bundle.getString("UID");
            opView = bundle.getString("Accion","");
            nmCliente=bundle.getString("nmCLiente","");
        }
        Button btn = findViewById(R.id.btnRpt);
        //LinearLayout lyt = findViewById(R.id.tlTop);



        recyclerViewArticulos = findViewById(R.id.articulosRecyclerView);

        mComentarios = findViewById(R.id.idComentarios);

        LinearLayoutManager linearLayout = new LinearLayoutManager(getBaseContext());
        linearLayout.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewArticulos.setLayoutManager(linearLayout);

        oArticulo = Articulos_model.get(ManagerURI.getDirDb(),this);
        int i=0;
        myarray = new String[oArticulo.size()];
        for (Articulo rt:oArticulo){
            myarray[i] = rt.getmNam().toUpperCase();
            i++;

        }


        btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (dtLogs.size()>0){
                new android.support.v7.app.AlertDialog.Builder(LocationActivity.this)
                        .setMessage("¿Seguro que decea guardar el registro?")
                        .setCancelable(false)
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Save_log();
                            }
                        })
                        .setNegativeButton("NO", null)
                        .show();
            }else{
                Toast.makeText(LocationActivity.this, "Sin Registros que Guardar.", Toast.LENGTH_SHORT).show();
            }




        }
    });
        if (opView.equals("view")){
            btn.setVisibility(View.GONE);
            mLatitude.setVisibility(View.GONE);
           // mlblLatitude.setVisibility(View.GONE);
            mLongitude.setVisibility(View.GONE);
           // mlblLongitude.setVisibility(View.GONE);
            List<Log_Actividades> lstCommit = LogActividades_model.get_commit(ManagerURI.getDirDb(),getBaseContext(),IDFarmacias);
            List<DetalleLog> dtLogs = LogActividades_model.get_row(ManagerURI.getDirDb(),getBaseContext(),IDFarmacias);
            recyclerViewArticulos.setAdapter(new ReporteVisitaAdapter(dtLogs, getBaseContext(), LocationActivity.this,"View"));
            mComentarios.setText(lstCommit.get(0).getmComentario().toString());
            //setTitle(lstCommit.get(0).getUID().toString());
            mlblLatitude.setText(lstCommit.get(0).getUID().toString());
            final String Fecha = (String) DateFormat.format("EEEE dd 'de' MMMM 'de' yyyy HH:mm:ss 's'", lstCommit.get(0).getmFecha());
            mlblLongitude.setText(Fecha);

        }

    }

    private void Save_log() {
        int D = Integer.valueOf(Llaves_model.getID(ManagerURI.getDirDb(),this).get(0).getmRpt());
        D++;


        String COD = user.concat("-R").concat((String.valueOf(D)));

        ArrayList<Log_Actividades> alog = new ArrayList<>();
        Log_Actividades tmp = new Log_Actividades();

        tmp.setUID(COD);
        tmp.setmLatitud(mLatitude.getText().toString());
        tmp.setmLogitud(mLongitude.getText().toString());
        tmp.setmComentario(mComentarios.getText().toString());
        tmp.setmCliente(IDFarmacias);
        tmp.setmRuta(user);
        tmp.setName(nmCliente);
        tmp.setmFecha(System.currentTimeMillis());

        alog.add(tmp);
        LogActividades_model.Save(LocationActivity.this,alog,dtLogs);
        SQLiteHelper.ExecuteSQL(ManagerURI.getDirDb(),this, "UPDATE Llaves SET REPORTES ='" + D + "'");
        new android.support.v7.app.AlertDialog.Builder(this).setTitle("Notificación").setMessage("Guardado con exito").setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        }).show();
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (mGoogleApiClient.isConnected()) {
            stopLocationUpdates();
            stopActivityUpdates();
        }

        LocalBroadcastManager.getInstance(this)
                .unregisterReceiver(mBroadcastReceiver);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mn_add_item_report, menu);
        MenuItem shareItem = menu.findItem(R.id.action_add_item_report);
        if (opView.equals("view")){shareItem.setVisible(false);}
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item)    {
        int id = item.getItemId();
        switch (id) {
            case 16908332:
                finish();
                return true;
            case R.id.action_add_item_report:
                LayoutInflater li = LayoutInflater.from(LocationActivity.this);
                final View promptsView = li.inflate(R.layout.layout_add_item, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(LocationActivity.this);
                alertDialogBuilder.setView(promptsView);
                alertDialogBuilder.setTitle("PRODUCTOS");

                final TextView Cantidad = promptsView.findViewById(R.id.txtcantidad);

                final Spinner popupSpinner = promptsView.findViewById(R.id.spnnItem);
                ArrayAdapter<String> adapter =new ArrayAdapter<>(LocationActivity.this,android.R.layout.simple_spinner_item, myarray);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                popupSpinner.setAdapter(adapter);

                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(Cantidad.getText().toString().isEmpty()){
                                    Toast.makeText(LocationActivity.this, "El Campo es necesario", Toast.LENGTH_SHORT).show();
                                }else{
                                    DetalleLog tmp = new DetalleLog();
                                    tmp.setArticulos(getItemIndexById(popupSpinner.getSelectedItem().toString()));
                                    tmp.setDescrp(popupSpinner.getSelectedItem().toString());
                                    tmp.setCantidad(Cantidad.getText().toString());
                                    dtLogs.add(tmp);
                                    recyclerViewArticulos.setAdapter(new ReporteVisitaAdapter(dtLogs, getBaseContext(), LocationActivity.this,""));

                                }


                                //String value = (("Cantidad: ").concat(Cantidad.getText().toString()).concat(" Articulos: ").concat(popupSpinner.getSelectedItem().toString())) ;
                                //Log.i(TAG, "onClickSpinn: " + value);
                                //popupSpinner.setSelection(getItemIndexById(popupSpinner.getSelectedItem().toString());


                            }
                        })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                }).create().show();



                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public String getItemIndexById(String id) {
        for (Articulo item : oArticulo) {
            if (item.getmNam().toUpperCase().contains(id)){
                return item.getmCod();
            }
        }
        return "";
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (mGoogleApiClient.isConnected()) {
            startLocationUpdates();
            startActivityUpdates();
        }

        IntentFilter intentFilter = new IntentFilter(Constants.BROADCAST_ACTION);
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(mBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(LOCATION_KEY, mLastLocation);

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CHECK_SETTINGS:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        Log.d(TAG, "El usuario permitió el cambio de ajustes de ubicación.");
                        processLastLocation();
                        startLocationUpdates();
                        break;
                    case Activity.RESULT_CANCELED:
                        Log.d(TAG, "El usuario no permitió el cambio de ajustes de ubicación");
                        break;
                }
                break;
        }
    }

    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions,
                                           int[] grantResults) {
        if (requestCode == REQUEST_LOCATION) {
            if (grantResults.length == 1
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                startLocationUpdates();

            }
        }
    }

    private synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .addApi(ActivityRecognition.API)
                .enableAutoManage(this, this)
                .build();
    }

    private void createLocationRequest() {
        mLocationRequest = new LocationRequest()
                .setInterval(Constants.UPDATE_INTERVAL)
                .setFastestInterval(Constants.UPDATE_FASTEST_INTERVAL)
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    private void buildLocationSettingsRequest() {
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(mLocationRequest)
                .setAlwaysShow(true);
        mLocationSettingsRequest = builder.build();
    }

    private void checkLocationSettings() {
        PendingResult<LocationSettingsResult> result =
                LocationServices.SettingsApi.checkLocationSettings(
                        mGoogleApiClient, mLocationSettingsRequest
                );

        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(@NonNull LocationSettingsResult result) {
                Status status = result.getStatus();

                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        Log.d(TAG, "Los ajustes de ubicación satisfacen la configuración.");
                        startLocationUpdates();
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        try {
                            Log.d(TAG, "Los ajustes de ubicación no satisfacen la configuración. " +
                                    "Se mostrará un diálogo de ayuda.");
                            status.startResolutionForResult(
                                    LocationActivity.this,
                                    REQUEST_CHECK_SETTINGS);
                        } catch (IntentSender.SendIntentException e) {
                            Log.d(TAG, "El Intent del diálogo no funcionó.");
                            // Sin operaciones
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        Log.d(TAG, "Los ajustes de ubicación no son apropiados.");
                        break;

                }
            }
        });
    }

    private void updateValuesFromBundle(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(LOCATION_KEY)) {
                mLastLocation = savedInstanceState.getParcelable(LOCATION_KEY);

                updateLocationUI();
            }

            if (savedInstanceState.containsKey(ACTIVITY_KEY)) {

                updateRecognitionUI();
            }


        }
    }

    private void updateLocationUI() {
        mLatitude.setText(String.valueOf(mLastLocation.getLatitude()));
        mLongitude.setText(String.valueOf(mLastLocation.getLongitude()));
    }

    private void updateRecognitionUI() {

    }

    private void stopActivityUpdates() {
        ActivityRecognition.ActivityRecognitionApi.removeActivityUpdates(
                mGoogleApiClient,
                getActivityDetectionPendingIntent()
        ).setResultCallback(this);
    }

    private void stopLocationUpdates() {
        LocationServices.FusedLocationApi
                .removeLocationUpdates(mGoogleApiClient, this);
    }

    private PendingIntent getActivityDetectionPendingIntent() {
        Intent intent = new Intent(this, DetectedActivitiesIntentService.class);
        return PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private void getLastLocation() {
        if (isLocationPermissionGranted()) {
            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        } else {
            manageDeniedPermission();
        }
    }

    private void processLastLocation() {
        getLastLocation();
        if (mLastLocation != null) {
            updateLocationUI();
        }
    }

    private void startActivityUpdates() {
        ActivityRecognition.ActivityRecognitionApi.requestActivityUpdates(
                mGoogleApiClient,
                Constants.ACTIVITY_RECOGNITION_INTERVAL,
                getActivityDetectionPendingIntent()
        ).setResultCallback(this);
    }

    private void startLocationUpdates() {
        if (isLocationPermissionGranted()) {
            LocationServices.FusedLocationApi.requestLocationUpdates(
                    mGoogleApiClient, mLocationRequest, this);
        } else {
            manageDeniedPermission();
        }
    }

    private void manageDeniedPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            // Aquí muestras confirmación explicativa al usuario
            // por si rechazó los permisos anteriormente
        } else {
            ActivityCompat.requestPermissions(
                    this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION);
        }
    }

    private boolean isLocationPermissionGranted() {
        int permission = ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        return permission == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        processLastLocation();
        startLocationUpdates();
        startActivityUpdates();
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d(TAG, "Conexión suspendida");
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(
                this,
                "Error de conexión con el código:" + connectionResult.getErrorCode(),
                Toast.LENGTH_LONG)
                .show();

    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d(TAG, String.format("Nueva ubicación: (%s, %s)",
                location.getLatitude(), location.getLongitude()));
        mLastLocation = location;
        updateLocationUI();
    }


    @Override
    public void onResult(@NonNull Status status) {
        if (status.isSuccess()) {
            Log.d(TAG, "Detección de actividad iniciada");

        } else {
            Log.e(TAG, "Error al iniciar/remover la detección de actividad: "
                    + status.getStatusMessage());
        }
    }

    public class ActivityDetectionBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            int type = intent.getIntExtra(Constants.ACTIVITY_KEY, -1);
            updateRecognitionUI();
        }

    }
}
