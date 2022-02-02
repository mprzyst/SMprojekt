package com.example.sm_projekt.activities;

import android.app.Activity;
import android.content.Intent;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.sm_projekt.R;
import com.example.sm_projekt.models.Note;
import com.google.android.material.button.MaterialButton;

public class DeleteActivity extends Activity implements SensorListener {

    SensorManager sensorMgr;
    Note toDelete;
    TextView titleView;
    TextView descriptionView;
    MaterialButton cancelBtn;
    private long lastUpdate;
    private float x;
    private float y;
    private float z;
    private float last_x;
    private float last_y;
    private float last_z;
    private static final int SHAKE_THRESHOLD = 800;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        sensorMgr = (SensorManager) getSystemService(SENSOR_SERVICE);

        sensorMgr.registerListener(this,
                SensorManager.SENSOR_ACCELEROMETER,
                SensorManager.SENSOR_DELAY_GAME);

        toDelete = (Note) getIntent().getSerializableExtra("note");

        titleView = findViewById(R.id.titleTxt);
        descriptionView = findViewById(R.id.contentTxt);
        cancelBtn = findViewById(R.id.cancelButton);

        titleView.setText(toDelete.getTitle());
        descriptionView.setText(toDelete.getContent());


        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result",false);
                setResult(RESULT_CANCELED,returnIntent);
                finish();
            }
        });


    }
    public void onSensorChanged(int sensor, float[] values) {
        if (sensor == SensorManager.SENSOR_ACCELEROMETER) {
            long curTime = System.currentTimeMillis();
            // only allow one update every 100ms.
            if ((curTime - lastUpdate) > 100) {
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;

                x = values[SensorManager.DATA_X];
                y = values[SensorManager.DATA_Y];
                z = values[SensorManager.DATA_Z];

                float speed = Math.abs(x+y+z - last_x - last_y - last_z) / diffTime * 10000;

                if (speed > SHAKE_THRESHOLD) {
                    Toast.makeText(getApplicationContext(),R.string.noteDeleted, Toast.LENGTH_SHORT).show();
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("result",toDelete );
                    setResult(RESULT_OK,returnIntent);
                    finish();
                }
                last_x = x;
                last_y = y;
                last_z = z;
            }
        }
    }

    @Override
    public void onAccuracyChanged(int sensor, int accuracy) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
