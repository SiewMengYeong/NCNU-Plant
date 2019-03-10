package tw.edu.ncnu.csie.ncnuplant;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class plant2 extends AppCompatActivity {
    private Bitmap bmp;
    private ImageView image;
    int REQUEST_EXIT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant2);
        final Bundle bundle = getIntent().getExtras();
        final String pid = bundle.getString("pid");
        final String familia = bundle.getString("familia");
        bmp = getImageFromAssetsFile("/assets/image/" + pid + "_" + familia + "/flower.jpg");
        if(bmp!=null) {
            image = (ImageView) findViewById(R.id.imageView13);
            image.setImageBitmap(bmp);
        }

        bmp = getImageFromAssetsFile("/assets/image/" + pid + "_" + familia + "/fruit.jpg");
        if(bmp!=null) {
        image = (ImageView) findViewById(R.id.imageView14);
        image.setImageBitmap(bmp);
        }
        else {
            TextView txv = (TextView) findViewById(R.id.textView11);
            txv.setText("");
        }
        bmp = getImageFromAssetsFile("/assets/image/" + pid + "_" + familia + "/leaf.jpg");
        if(bmp!=null) {
            image = (ImageView) findViewById(R.id.imageView15);
            image.setImageBitmap(bmp);
        }

        bmp = getImageFromAssetsFile("/assets/image/" + pid + "_" + familia + "/plant.jpg");
        if(bmp!=null) {
            image = (ImageView) findViewById(R.id.imageView16);
            image.setImageBitmap(bmp);
        }

        bmp = getImageFromAssetsFile("/assets/image/" + pid + "_" + familia + "/stem.jpg");
        if(bmp!=null) {
            image = (ImageView) findViewById(R.id.imageView17);
            image.setImageBitmap(bmp);
        }
        
    }
    public void description(View view) {
        Bundle bundle = getIntent().getExtras();
        String pid = bundle.getString("pid");
        Bundle password = new Bundle();
        password.putString("pid", pid);
        Intent it = new Intent("tw.edu.ncnu.csie.plant3");
        it.putExtras(password);
        startActivityForResult(it, REQUEST_EXIT);
    }
    private Bitmap getImageFromAssetsFile(String fileName)
    {
        Bitmap image = null;
        InputStream is;
        try {
            is = getClass().getResourceAsStream(fileName);
            if(is!=null)
                image= BitmapFactory.decodeStream(is, null, getBitmapOptions(2));
            else
                return null;
            is.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public BitmapFactory.Options getBitmapOptions(int scale){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inSampleSize = scale;
        return options;
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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_EXIT) {
            if (resultCode == RESULT_OK) {
                setResult(RESULT_OK, null);
                this.finish();
            }
        }
    }

}

