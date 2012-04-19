package com.sensor.test;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class SensorsTestActivity extends Activity implements SensorEventListener{
    /** Called when the activity is first created. */
	SensorManager sensorManager = null;
	
	//for accelerometer values
	TextView accX;
	TextView accY;
	TextView accZ;
	//for orientation values
	TextView rotX;
	TextView rotY;
	TextView rotZ;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        setContentView(R.layout.main);
        
        accX = (TextView)findViewById(R.id.textView01);
        accY = (TextView)findViewById(R.id.textView02);
        accZ = (TextView)findViewById(R.id.textView03);
        
        rotX = (TextView)findViewById(R.id.textView04);
        rotY = (TextView)findViewById(R.id.textView05);
        rotZ = (TextView)findViewById(R.id.textView06);
    }
	
	@Override
	protected void onResume()
	{
		super.onResume();
		sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), sensorManager.SENSOR_DELAY_GAME);
		sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), sensorManager.SENSOR_DELAY_GAME);
	}
	
	@Override
	protected void onStop()
	{
		super.onStop();
		sensorManager.unregisterListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER));
		sensorManager.unregisterListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION));
	}
	
	public void onSensorChanged(SensorEvent event)
	{
		synchronized (this) {
			switch (event.sensor.getType())
			{
				case Sensor.TYPE_ACCELEROMETER :
					accX.setText("Acc X : "+Float.toString(event.values[0]));
					accY.setText("Acc Y : "+Float.toString(event.values[1]));
					accZ.setText("Acc Z : "+Float.toString(event.values[2]));
				break;
				case Sensor.TYPE_ORIENTATION :
					rotX.setText("Rot X : "+Float.toString(event.values[0]));
					rotY.setText("Rot Y : "+Float.toString(event.values[1]));
					rotZ.setText("Rot Z : "+Float.toString(event.values[2]));
				break;
			}
		}
	}
	
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy)
	{
		
	}
}