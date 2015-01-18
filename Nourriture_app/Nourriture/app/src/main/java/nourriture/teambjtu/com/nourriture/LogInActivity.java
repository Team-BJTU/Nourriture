package nourriture.teambjtu.com.nourriture;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;


public class LogInActivity extends ActionBarActivity {

    private Spinner CountrySpinner;
    private Spinner IngredientsSpinner;

    //LIST OF GLOBAL ARRAY STRINGS WHICH WILL SERVE AS LIST ITEMS
    public static ArrayList<String> listItems = new ArrayList<String>();


    public void AddIngredients(View v) {
        String IngredientsSpinnerValue = IngredientsSpinner.getSelectedItem().toString();
        Log.i("IngredientsValue", IngredientsSpinnerValue);
        listItems.add(IngredientsSpinnerValue);

        //Display items on the list.
        /*for (String Items : listItems )
        {
            Log.i("IngredientList", Items);
        }*/
    }

    public void CreateReceipe(View view) {

        int duration = Toast.LENGTH_SHORT;

        ArrayList<String> NewReceipe = new ArrayList<String>();
        NewReceipe = listItems;

        /*CreateReceipeClass createreceipe = new CreateReceipeClass();
         createreceipe.execute();*/

        listItems.clear();

        Context context = getApplicationContext();
        CharSequence text = "A new receipe has been created !";

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void IngredientList(View view) {
        Bundle extra = new Bundle();
        extra.putSerializable("listItems", listItems);
        Intent intent = new Intent(this, IngredientListActivity.class);
        intent.putExtra("extra", extra);
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


  /*private class CreateReceipeClass  extends AsyncTask<Void, Integer, Void>
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

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            Toast.makeText(getApplicationContext(), "Attempt to login is over !", Toast.LENGTH_LONG).show();
        }
    }*/

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
