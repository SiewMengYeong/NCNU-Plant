package tw.edu.ncnu.csie.ncnuplant;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;


public class plant3 extends AppCompatActivity{
    String pid;
    private DBhelper dbhelper;
    private String SQL_order = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant3);
        Bundle bundle = getIntent().getExtras();
        pid=bundle.getString("pid");

        dbhelper = new DBhelper(this);
        try {
            dbhelper.createDataBase();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        SQL_order = "SELECT * from plantdata where pid="+pid;
        Cursor c=dbhelper.getData(SQL_order);
        c.moveToFirst();
        String text="";
        text+="名字 : "+c.getString(1) + '\n'+ '\n';
        text+="科屬名 : "+c.getString(2) + '\n'+ '\n';
        text+="別名 : "+c.getString(3) + '\n'+ '\n';
        text+="學名 : "+c.getString(4) + '\n'+ '\n';
        text+="來源地 : "+c.getString(5) + '\n'+ '\n';
        text+="分布地 : "+c.getString(6) + '\n'+ '\n';
        text+="應用 : "+c.getString(7) + '\n'+ '\n';
        text+="葉 : "+c.getString(8) + '\n'+ '\n';
        text+="樹 : "+c.getString(9) + '\n'+ '\n';
        text+="花 : "+c.getString(10) + '\n'+ '\n';
        text+="花期 : "+c.getString(12) + "月~" + c.getString(13)+ "月" + '\n'+ '\n';
        text+="果實 : "+c.getString(11) + '\n';
        TextView txv=(TextView) findViewById(R.id.textView16);
        txv.setText(text);

    }
    public void image(View view) {
        finish();
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
            setResult(RESULT_OK, null);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }


}

