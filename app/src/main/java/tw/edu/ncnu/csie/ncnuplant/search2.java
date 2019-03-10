package tw.edu.ncnu.csie.ncnuplant;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class search2 extends AppCompatActivity{
    private SimpleAdapter adapter;
    private ListView lv;
    private List<Map<String, Object>> items;
    int REQUEST_EXIT;
    private DBhelper dbhelper;
    private String SQL_order = "";
    private Bitmap bmp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant);
        dbhelper = new DBhelper(this);
        try {
            dbhelper.createDataBase();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Bundle bundle = getIntent().getExtras();
        SQL_order=bundle.getString("SQL_order");//上一頁傳送過來的sql order
        Cursor c=dbhelper.getData(SQL_order);

        lv = (ListView) findViewById(R.id.lv);
        items = new ArrayList<Map<String,Object>>();

        if(c.moveToFirst())
        {
        do {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("pid", c.getString(0));
            item.put("cname", c.getString(1));
            item.put("familia", c.getString(2));
            bmp = getImageFromAssetsFile("/assets/image/" +c.getString(0) + "_" + c.getString(2) + "/flower.jpg");
            if(bmp!=null)
                item.put("image", bmp);
            items.add(item);
        }while(c.moveToNext());}
        else {
           Map<String, Object> item = new HashMap<String, Object>();
           item.put("cname", "no result");
           items.add(item);
       }
        adapter = new SimpleAdapter(this,
                items, R.layout.list_item, new String[]{"pid", "cname", "familia", "image"},
                new int[]{R.id.textView9, R.id.textView, R.id.textView2, R.id.imageView12});
        // 处理simpleAdapter中包括bitmap类型
        adapter.setViewBinder(new SimpleAdapter.ViewBinder() {
            public boolean setViewValue(View view, Object data,
                                        String textRepresentation) {
                if (view instanceof ImageView && data instanceof Bitmap) {
                    ImageView image = (ImageView) view;
                    image.setImageBitmap((Bitmap) data);
                    return true;
                }
                return false;
            }
        });
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView txv = (TextView) view.findViewById(R.id.textView);
                if(txv.getText().toString()!="no result") {
                    txv = (TextView) view.findViewById(R.id.textView9);
                    Bundle password = new Bundle();
                    password.putString("pid", txv.getText().toString());
                    txv = (TextView) view.findViewById(R.id.textView2);
                    password.putString("familia", txv.getText().toString());
                    Intent it = new Intent("tw.edu.ncnu.csie.plant2");
                    it.putExtras(password);
                    startActivityForResult(it, REQUEST_EXIT);
                }
            }
        });
        dbhelper.onDestroy();
    }
    private Bitmap getImageFromAssetsFile(String fileName)
    {
        Bitmap image = null;
        InputStream is;
        try {
            is = getClass().getResourceAsStream(fileName);
            if(is!=null)
                image= BitmapFactory.decodeStream(is, null, getBitmapOptions(15));
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

        if (id == R.id.action_home){
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
