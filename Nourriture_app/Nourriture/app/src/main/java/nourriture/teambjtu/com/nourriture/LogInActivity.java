package nourriture.teambjtu.com.nourriture;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class LogInActivity extends ActionBarActivity {

    private Spinner CountrySpinner;
    private Spinner IngredientsSpinner;

    public void IngredientList(View view) {
        Intent intent = new Intent(this, IngredientListActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        CountrySpinner = (Spinner) findViewById(R.id.CountrySpinner);
        IngredientsSpinner = (Spinner) findViewById(R.id.IngredientsSpinner);

// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> CountryAdapter = ArrayAdapter.createFromResource(this, R.array.country_array, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> IngredientsAdapter = ArrayAdapter.createFromResource(this, R.array.ingredients_array, android.R.layout.simple_spinner_item);

// Specify the layout to use when the list of choices appears
        CountryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        IngredientsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// Apply the adapter to the spinner
        CountrySpinner.setAdapter(CountryAdapter);
        IngredientsSpinner.setAdapter(IngredientsAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_log_in, menu);
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
