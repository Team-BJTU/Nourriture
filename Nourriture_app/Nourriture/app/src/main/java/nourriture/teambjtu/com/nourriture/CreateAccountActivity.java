package nourriture.teambjtu.com.nourriture;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
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


public class CreateAccountActivity extends ActionBarActivity implements View.OnClickListener {

    public EditText FirstNameEditText;
    public EditText LastNameEditText;
    public EditText EmailEditText;
    public EditText UserNameEditText;
    public EditText PasswordEditText;
    public EditText RetypePasswordEditText;
    public Button RegisterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        FirstNameEditText = (EditText) findViewById(R.id.FirstNameEditText);
        LastNameEditText = (EditText) findViewById(R.id.LastNameEditText);
        EmailEditText = (EditText) findViewById(R.id.EmailEditText);
        UserNameEditText = (EditText) findViewById(R.id.UserNameEditText);
        PasswordEditText = (EditText) findViewById(R.id.PasswordEditText);
        RetypePasswordEditText = (EditText) findViewById(R.id.RetypePasswordEditText);
        RegisterButton = (Button) findViewById(R.id.RegisterButton);

        FirstNameEditText.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                FirstNameEditText.setText("");
            }
        });

        LastNameEditText.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                LastNameEditText.setText("");
            }
        });

        EmailEditText.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                EmailEditText.setText("");
            }
        });

        UserNameEditText.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                UserNameEditText.setText("");
            }
        });

        PasswordEditText.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                PasswordEditText.setText("");
            }
        });

        RetypePasswordEditText.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                RetypePasswordEditText.setText("");
            }
        });

        /*RegisterButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Register register = new Register();
                register.execute();
            }
        });*/
    }

    /*private class Register extends AsyncTask<Void, Integer, Void>
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
            HttpPost httppost = new HttpPost("ec2-54-64-190-134.ap-northeast-1.compute.amazonaws.com:3000/signup");
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("First Name", FirstNameEditText.getText().toString()));
            nameValuePairs.add(new BasicNameValuePair("Last Name", LastNameEditText.getText().toString()));
            nameValuePairs.add(new BasicNameValuePair("Email", EmailEditText.getText().toString()));
            nameValuePairs.add(new BasicNameValuePair("Username", UserNameEditText.getText().toString()));
            nameValuePairs.add(new BasicNameValuePair("Password", PasswordEditText.getText().toString()));
            nameValuePairs.add(new BasicNameValuePair("Retype Password", RetypePasswordEditText.getText().toString()));

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
            Toast.makeText(getApplicationContext(), "Attempt to Register is over !", Toast.LENGTH_LONG).show();
        }
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_account, menu);
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

    @Override
    public void onClick(View v) {

    }
}
