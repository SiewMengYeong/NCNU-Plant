package tw.edu.ncnu.csie.ncnuplant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioGroup;

public class search extends AppCompatActivity {
    RadioGroup flowertime;
    RadioGroup flowershape;
    RadioGroup flowernumber;
    RadioGroup leafs;
    RadioGroup leafshape;
    CheckBox color;
    CheckBox color2;
    CheckBox color3;
    CheckBox color4;
    CheckBox color5;
    CheckBox color6;
    CheckBox color7;
    CheckBox color8;
    CheckBox color9;
    private String SQL_order = "";
    int REQUEST_EXIT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        flowertime = (RadioGroup) findViewById(R.id.radioGroup);
        flowershape = (RadioGroup) findViewById(R.id.radioGroup2);
        flowernumber = (RadioGroup) findViewById(R.id.radioGroup3);
        leafs = (RadioGroup) findViewById(R.id.radioGroup4);
        leafshape = (RadioGroup) findViewById(R.id.radioGroup5);
        color = (CheckBox) findViewById(R.id.checkBox);
        color2 = (CheckBox) findViewById(R.id.checkBox2);
        color3 = (CheckBox) findViewById(R.id.checkBox3);
        color4 = (CheckBox) findViewById(R.id.checkBox4);
        color5 = (CheckBox) findViewById(R.id.checkBox5);
        color6 = (CheckBox) findViewById(R.id.checkBox6);
        color7 = (CheckBox) findViewById(R.id.checkBox7);
        color8 = (CheckBox) findViewById(R.id.checkBox8);
        color9 = (CheckBox) findViewById(R.id.checkBox9);
    }
    public void resetbutton(View v) {
        flowertime.clearCheck();
        flowershape.clearCheck();
        flowernumber.clearCheck();
        leafs.clearCheck();
        leafshape.clearCheck();
        color.setChecked(false);
        color2.setChecked(false);
        color3.setChecked(false);
        color4.setChecked(false);
        color5.setChecked(false);
        color6.setChecked(false);
        color7.setChecked(false);
        color8.setChecked(false);
        color9.setChecked(false);
    }
    public void show(View v) {
        String add="";
        switch(flowertime.getCheckedRadioButtonId()) {
            case R.id.radioButton:
                add="twotofour=1";
                break;
            case R.id.radioButton2:
                add="fivetoseven=1";
                break;
            case R.id.radioButton3:
                add="eighttoten=1";
                break;
            case R.id.radioButton4:
                add="eleventoone=1";
                break;
        }
        if(color.isChecked()) {
            if (add.isEmpty())
                add = "white=1";
            else
                add += " and white=1";
        }
        if(color2.isChecked()){
            if (add.isEmpty())
                add = "red=1";
            else
                add += " and red=1";
        }
        if(color3.isChecked()){
            if (add.isEmpty())
                add = "orange=1";
            else
                add += " and orange=1";
        }
        if(color4.isChecked()){
            if (add.isEmpty())
                add = "yellow=1";
            else
                add += " and yellow=1";
        }
        if(color5.isChecked()){
            if (add.isEmpty())
                add = "green=1";
            else
                add += " and green=1";
        }
        if(color6.isChecked()) {
            if (add.isEmpty())
                add = "blue=1";
            else
                add += " and blue=1";
        }
        if(color7.isChecked()){
            if (add.isEmpty())
                add = "purple=1";
            else
                add += " and purple=1";
        }
        if(color8.isChecked()){
            if (add.isEmpty())
                add = "brown=1";
            else
                add += " and brown=1";
        }
        if(color9.isChecked()){
            if (add.isEmpty())
                add = "other=1";
            else
                add += " and other=1";
        }
        switch(flowershape.getCheckedRadioButtonId()){
            case R.id.radioButton5: {
                if(add.isEmpty())
                    add = "regular=1";
                else
                    add+=" and regular=1";
                break;
            }
            case R.id.radioButton6: {
                if(add.isEmpty())
                    add = "irregular=1";
                else
                    add+=" and irregular=1";
                break;
            }
            case R.id.radioButton7: {
                if(add.isEmpty())
                    add = "composite=1";
                else
                    add+=" and composite=1";
                break;
            }
        }

        switch(flowernumber.getCheckedRadioButtonId()){
            case R.id.radioButton8: {
                if(add.isEmpty())
                    add = "three=1";
                else
                    add+=" and three=1";
                break;
            }
            case R.id.radioButton9: {
                if(add.isEmpty())
                    add = "four=1";
                else
                    add+=" and four=1";
                break;
            }
            case R.id.radioButton10: {
                if(add.isEmpty())
                    add = "five=1";
                else
                    add+=" and five=1";
                break;
            }
            case R.id.radioButton11: {
                if(add.isEmpty())
                    add = "six=1";
                else
                    add+=" and six=1";
                break;
            }
            case R.id.radioButton12: {
                if(add.isEmpty())
                    add = "seven=1";
                else
                    add+=" and seven=1";
                break;
            }
        }

        switch(leafs.getCheckedRadioButtonId()){
            case R.id.radioButton13: {
                if(add.isEmpty())
                    add = "alternate=1";
                else
                    add+=" and alternate=1";
                break;
            }
            case R.id.radioButton14: {
                if(add.isEmpty())
                    add = "opposite=1";
                else
                    add+=" and opposite=1";
                break;
            }
            case R.id.radioButton15: {
                if(add.isEmpty())
                    add = "whorled=1";
                else
                    add+=" and whorled=1";
                break;
            }
            case R.id.radioButton16: {
                if(add.isEmpty())
                    add = "fasciculate=1";
                else
                    add+=" and fasciculate=1";
                break;
            }
        }
        switch(leafshape.getCheckedRadioButtonId()){
            case R.id.radioButton17: {
                if(add.isEmpty())
                    add = "simpleleaf=1";
                else
                    add+=" and simpleleaf=1";
                break;
            }
            case R.id.radioButton18: {
                if(add.isEmpty())
                    add = "compoundleaf=1";
                else
                    add+=" and compoundleaf=1";
                break;
            }
            case R.id.radioButton19: {
                if(add.isEmpty())
                    add = "aclcularleaf=1";
                else
                    add+=" and aclcularleaf=1";
                break;
            }
        }

        if (add.isEmpty())
            SQL_order = "SELECT pid,cname,familia from plantdata";
        else
            SQL_order = "SELECT pid,cname,familia from plantdata where pid in (select pid from datatag where "+ add +")";
        Bundle password = new Bundle();
        password.putString("SQL_order", SQL_order);
        Intent it = new Intent("tw.edu.ncnu.csie.search2");
        it.putExtras(password);
        startActivityForResult(it, REQUEST_EXIT);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_EXIT) {
            if (resultCode == RESULT_OK) {
                this.finish();
            }
        }
    }
}
