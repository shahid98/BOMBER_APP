package com.BAKCHOD.CODER;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
public class MainActivity extends AppCompatActivity {
    public int responseCode,flag=1;
public String number,times;
   public  String html;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText num = (EditText) findViewById(R.id.Number);
        final EditText  count = (EditText) findViewById(R.id.Count);
        final TextView status = (TextView) findViewById(R.id.Status);
        EditText STATIC_TEST_NUM=(EditText)findViewById(R.id.MOBILE);
        EditText STATIC_TEST_COUNT=(EditText)findViewById(R.id.TIMES) ;
        STATIC_TEST_NUM.setEnabled(false);
        STATIC_TEST_COUNT.setEnabled(false);
        Button submit = (Button) findViewById(R.id.submit);
        Button about =(Button)findViewById(R.id.About);
        /*Notice.setText("MESSAGE LIMIT ARE UPTO 150 AND 2 SECOND FOR EACH MEAGGE");
        Notice.setEnabled(false);*/

            Thread thread = new Thread(new Runnable() {
                public void run() {
                    try {
                        URL obj = new URL("https://bomb-test.000webhostapp.com/bomb.php");
                        while(responseCode!=200) {
                            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                            con.setRequestMethod("POST");
                            con.setRequestProperty("User-Agent", "Mozilla/5.0");
                            responseCode = con.getResponseCode();
                            if (con.getResponseCode() == 200) {
                                status.setText("UP");
                                flag = 0;
                            }
                            else
                            {

                                Toast.makeText(getApplicationContext(), "CONNECTING TO SERVER", Toast.LENGTH_SHORT).show();
                            }


                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            });
            thread.start();

            if(flag==1)
            {
                status.setText("DOWN");

            }
            else
            {
                Toast.makeText(getApplicationContext(), "Server Up", Toast.LENGTH_LONG).show();
            }
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialog = new MyDialogFragment();
                dialog.show(getSupportFragmentManager(), "MyDialogFragmentTag");
    }
                         });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number=num.getText().toString();
                times=count.getText().toString();
                if(isNetworkConnected() && flag==0)
                {

                    if (num.getText().toString().isEmpty() || count.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "FILL the Data", Toast.LENGTH_SHORT).show();
                    } else {
                        //Toast.makeText(getApplicationContext(), "Number -  " + num.getText().toString() + " \n" + "Count -  " + count.getText().toString(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(),"BOMBING IN PROGRESS",Toast.LENGTH_LONG).show();


                        try {
                            //GetText();
                            OkHttpHandler okHttpHandler = new OkHttpHandler();
                            okHttpHandler.execute();
                            okHttpHandler.getStatus();
                            //Toast.makeText(getApplicationContext(), (int) responseCode, Toast.LENGTH_LONG).show();
                        }
                        catch(Exception e) {
                            e.printStackTrace();

                        }
                       num.setText(null);
                       count.setText(null);
                       count.clearFocus();
                       num.clearFocus();

                    }



                }
                else if (isNetworkConnected() && flag==1)
                {
                    Toast.makeText(getApplicationContext(),"SERVER DOWN",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"NO INTERNET",Toast.LENGTH_LONG).show();
                    //Toast.makeText(getApplicationContext(), "Number -  " + number + " \n" + "Count -  " + times, Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    public static class MyDialogFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            // Use the Builder class for convenient dialog construction
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("About Dev");
            builder.setMessage("Prank Your Friends , Don't Misuse IT :)\nBAKCHODI ZINDABAD(MAKAUTIAN)");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                }
            });


            return builder.create();
        }
    }


    public class OkHttpHandler extends AsyncTask<String,Void,String>{

      // OkHttpClient.Builder client = new OkHttpClient().newBuilder().protocols(Arrays.asList(Protocol.HTTP_1_1));//ok
       OkHttpClient client1 = new OkHttpClient();


        @Override
        protected String doInBackground(String...params) {

            RequestBody formBody = new FormBody.Builder()
                    .add("ph",number)
                    .add("count",times)
                    .build();
            Request request = new Request.Builder()
                    .url("https://bomb-test.000webhostapp.com/bomb.php")
                    .post(formBody)
                    .build();
            try {
                Response response = client1.newCall(request).execute();


             /*   Toast.makeText(getApplicationContext(), response.body().string(), Toast.LENGTH_LONG).show();
                if (response.code() == 200) {
                    Log.d("Server response 200",response.body().string());
                    return response.body().string();
                } else {
                    Log.d("Server response Error","ERROR");
                    return "Server response Error";
                }
*/
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {

            // Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
            super.onPostExecute(s);

        }
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }


}
