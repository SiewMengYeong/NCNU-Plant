package tw.edu.ncnu.csie.ncnuplant;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DBhelper extends SQLiteOpenHelper {

    private static String DB_NAME = "ncnu_plant.sqlite"; //DataBase name
    private SQLiteDatabase db; //手機DB
    private final Context context;
    private String DB_PATH; //DB路徑
    private DBhelper dbHelper; //建構子

    ///建構子
    public DBhelper(Context context) {
        super(context, DB_NAME, null, 1);//context,name,null,version
        this.context = context;
        DB_PATH = "/data/data/" + context.getPackageName() + "/" + "databases/"; //路徑存放

    }

    ///建立DB,判斷是否成功複製DB
    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();
        if (dbExist) {
            Log.v("recreate", "OK," + dbExist);
            this.getReadableDatabase();
            try {
                copyDataBase();
            } catch (IOException e) {
                Log.e("error","Error copying database");
            }


        }
        else {
            this.getReadableDatabase();
            try {
                copyDataBase();
            } catch (IOException e) {
                Log.e("error","Error copying database");
            }
        }
    }

    //判斷有無DB 傳回的參數為 布林值(True or False)
    private boolean checkDataBase() {
        File dbFile = new File(DB_PATH + DB_NAME);
        return dbFile.exists();
    }

    //到Assets拿DB 灌入手機
    private void copyDataBase() throws IOException {

        InputStream myInput = context.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        //Log.v("copy", "OK=");
        OutputStream mOutput = new FileOutputStream(outFileName);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = myInput.read(mBuffer))>0)
        {
            mOutput.write(mBuffer, 0, mLength);
        }
        //Log.v("copy", "OK");
        mOutput.flush();
        mOutput.close();
        myInput.close();
    }

    //撈DataBase裡的資料 傳入SLQ order String 傳回 Cursor data(指針資料)
    public Cursor getData(String sql_order) {
        dbHelper=new DBhelper(context);
        String myPath =DB_PATH + DB_NAME;
        Log.v("data","OKwait"+myPath);
        db=dbHelper.getWritableDatabase();
        return  db.rawQuery(sql_order, null);

    }
    //close DataBase
    public  void onDestroy(){
        if(db!=null ||dbHelper!=null)
        {db.close();
            dbHelper.close();}
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
    }
}
