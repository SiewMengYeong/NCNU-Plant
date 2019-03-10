package tw.edu.ncnu.csie.ncnuplant;

import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class map extends AppCompatActivity implements LocationListener{
    TextView txv;
    LocationManager mgr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        mgr =(LocationManager) getSystemService(Context.LOCATION_SERVICE);
        txv = (TextView) findViewById(R.id.textView15);
    }
    public void setup(View V){
        Intent it=new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(it);
    }
    @Override
    protected void onResume(){
        super.onResume();
        String best=mgr.getBestProvider(new Criteria(),true);
        if(best!=null) {
            String str="定位中";
            txv.setText(str);
            mgr.requestLocationUpdates(best,5000,5,this);
        }
        else
            txv.setText("請打開定位");
    }
    @Override
    protected void onPause(){
        super.onPause();
        String best=mgr.getBestProvider(new Criteria(),true);
        mgr.removeUpdates(this);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onLocationChanged(Location location) {
        String str="定位提供者："+location.getProvider();
        str+=String.format("\n緯度：%.5f\n經度：%.5f\n高度：%.2f公尺",location.getLatitude(),location.getLongitude(),location.getAltitude());
        txv.setText(str);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
