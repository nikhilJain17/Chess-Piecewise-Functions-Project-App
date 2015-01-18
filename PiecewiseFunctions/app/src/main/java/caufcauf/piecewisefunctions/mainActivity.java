package caufcauf.piecewisefunctions;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//import caufcauf.piecewisefunctions.outputFragment;


public class mainActivity extends Activity {

    EditText input;
    Button go;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = (EditText) findViewById(R.id.input);    // where the user enters the
        go = (Button) findViewById(R.id.go);
        go.setOnClickListener(findOutput);

    }

    // checks whether or not the input is between 1 to 64 (not 0 - 100 real quick - that's something else)
    public int inputValidation () {

        String inputString = input.getText().toString();
        int inputInt; // initializing problems....... pay close attention por favor


        // if they (SOMEHOW) manage to enter something other than a number (y not let this fly)
        try {
            inputInt = Integer.parseInt(inputString);
        }

        catch (NumberFormatException n) {
            Toast.makeText(this, "You didn't enter a number!", Toast.LENGTH_SHORT);
            inputInt = -666; // automatically diverts this to make an error.
        }

        if (inputInt == -666) {
            Toast.makeText(this, "You didn't enter a number!", Toast.LENGTH_SHORT);
        }

        // less than one or greater than 64
        if (inputInt < 1 || inputInt > 64) {

            Toast.makeText(this, "Input has to be an int between 1 and 64 (inclusive)!", Toast.LENGTH_SHORT).show();
            return 0;
        }

        // wuaw they r not idiiots!?! da input is valid
        else {
            //Toast.makeText(this, "Valid input", Toast.LENGTH_SHORT).show();
            return inputInt;
        }

    }

    // calculate piecewise outputs for bishop
    public int bishop (int input) {

        int output = 0;

        if (input < 28)
            output = 7;

        else if (input >= 29 && input <= 48)
            output = 9;

        else if (input >= 49 && input <= 60)
            output = 11;

        else if (input > 60)
            output = 13;

        return output;
    }


    View.OnClickListener findOutput = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            // believe it or not, this validates the inputs (wuaw?)
            int userInput = inputValidation();

            // if the input is valid, continue. else, do nothing and wait for them to get their act together
            if (userInput > 0) {

                int knightOutput = 0;
                int kingOutput = 0;
                int bishopOutput = 0;

                // pass the userinput to the knight piecewise function
                bishopOutput = bishop(userInput);


                //Toast.makeText(mainActivity.this, Integer.toString(bishopOutput), Toast.LENGTH_SHORT).show();

                Bundle outputs = new Bundle();
                outputs.putString("Bishop", Integer.toString(bishopOutput));
                outputs.putString("Knight", Integer.toString(knightOutput));
                outputs.putString("King", Integer.toString(kingOutput));

                DialogFragment outputDialogFragment = new outputFragment();
                outputDialogFragment.setArguments(outputs);
                outputDialogFragment.show(getFragmentManager(), "yarfl?");

            }

        }

    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
