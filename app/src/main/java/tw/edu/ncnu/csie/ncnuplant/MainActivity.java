package tw.edu.ncnu.csie.ncnuplant;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ImageButton btnp,btns, btnm,btnf;

    @Override
    public void onClick(View v) {
        Intent it=new Intent(Intent.ACTION_VIEW);
        if(v.getId()==R.id.buttonplant)
            startActivity(new Intent("tw.edu.ncnu.csie.plant"));
        else if(v.getId()==R.id.buttonsearch)
            startActivity(new Intent("tw.edu.ncnu.csie.search"));
        else if(v.getId()==R.id.buttonmap) {
            //   startActivity(new Intent("tw.edu.ncnu.csie.map"));
            it.setData(Uri.parse("https://drive.google.com/open?id=13axOsKOYY4wtBU2tshoZSI8W_PI&usp=sharing"));
            startActivity(it);
        }
        else if(v.getId()==R.id.buttonfb) {
            it.setData(Uri.parse("https://www.facebook.com/ncnu.tw/?fref=ts&em=1"));
            startActivity(it);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnp=(ImageButton) findViewById(R.id.buttonplant);
        btnp.setOnClickListener(this);
        btns=(ImageButton) findViewById(R.id.buttonsearch);
        btns.setOnClickListener(this);
        btnm=(ImageButton) findViewById(R.id.buttonmap);
        btnm.setOnClickListener(this);
        btnf=(ImageButton) findViewById(R.id.buttonfb);
        btnf.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
