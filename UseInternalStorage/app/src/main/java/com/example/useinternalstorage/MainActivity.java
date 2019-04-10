package com.example.useinternalstorage;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        readRaw();   //自己编写方法，读取 raw 文件夹下指定的文件
    }

    private void readRaw() {
        try{
            //得到资源中的Raw数据流 ,raw 文件夹下放置一个 test.txt 文件
            InputStream in = getResources().openRawResource(R.raw.myfile);

            //得到数据的大小
            int length = in.available();
            byte [] buffer = new byte[length];

            //读取数据
            in.read(buffer);
//依test.txt的编码类型选择合适的编码，如果不调整会乱码
            String res = "raw:"+ Xml.Encoding.UTF_8;

            Toast.makeText(MainActivity.this, res, Toast.LENGTH_LONG).show();
            Log.i("info",res );
            //关闭
            in.close();
            TextView A1=(TextView)findViewById(R.id.textview1);
            A1.setText(new String(buffer));
        }catch(Exception e){
            e.printStackTrace();
        }
        String FLIENAME = "myfile";
        String data ="在内部文件中的数据";
        try {
            FileOutputStream fos = openFileOutput(FLIENAME, Context.MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            osw.write(data);
            osw.flush();
            fos.flush();
            osw.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
