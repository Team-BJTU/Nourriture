package nourriture.teambjtu.com.nourriture;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    public EditText UsernameEditText;
    public EditText PasswordEditText;
    public Button LoginButton;

    public void LogIn(View view) {
        Intent intent = new Intent(this, LogInActivity.class);
        startActivity(intent);
    }

    public void CreateAnAccount(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
    }

    public void ForgotYourPasswordAction(View view) {
        Intent intent = new Intent(this, ForgotPasswordActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UsernameEditText = (EditText) findViewById(R.id.UsernameEditText);
        PasswordEditText = (EditText) findViewById(R.id.PasswordEditText);
        LoginButton = (Button) findViewById(R.id.LoginButton);

        UsernameEditText.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                UsernameEditText.setText("");
            }
        });

        PasswordEditText.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                PasswordEditText.setText("");
            }
        });

        /*LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login login = new Login();
                login.execute();
            }
        });*/
    }

   /* private class Login extends AsyncTask<Void, Integer, Void>
    {

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            Log.v("PreExecute", "On PreExcute");
        }


        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            Log.v("OnProgressUpdate", "On ProgressUpdate");

        }

        @Override
        protected Void doInBackground(Void... params) {
            Log.v("DoInBackGround", "On DoInBackGround");
            HttpPost httppost = new HttpPost("ec2-54-64-190-134.ap-northeast-1.compute.amazonaws.com:3000/SignIn");
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("Login", UsernameEditText.getText().toString()));
            nameValuePairs.add(new BasicNameValuePair("Password", PasswordEditText.getText().toString()));
            try {
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            HttpClient httpclient = new DefaultHttpClient();
            try {
                httpclient.execute(httppost);
            } catch (IOException e) {
                e.printStackTrace();
            }
            HttpResponse response= null;
            try {
                response = httpclient.execute(httppost);
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert response != null;
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String s = reader.readLine();
                Log.v("Response", s);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            Toast.makeText(getApplicationContext(), "Attempt to login is over !", Toast.LENGTH_LONG).show();
        }
    } */

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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
