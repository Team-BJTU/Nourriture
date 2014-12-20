package nourriture.teambjtu.com.nourriture;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class CreateAccountActivity extends ActionBarActivity implements View.OnClickListener {

    public EditText FirstNameEditText;
    public EditText LastNameEditText;
    public EditText EmailEditText;
    public EditText UserNameEditText;
    public EditText PasswordEditText;
    public EditText RetypePasswordEditText;

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
    }


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
