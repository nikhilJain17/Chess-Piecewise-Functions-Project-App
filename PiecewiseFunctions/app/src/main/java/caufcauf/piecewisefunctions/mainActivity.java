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

    // calculate piecewise outputs for king
    public int king (int input) {

        int output = 0;

        if (input <= 4)
            output = 3;

        else if (input >= 5 && input <= 28)
            output = 5;

        else if (input >= 29 && input <= 64)
            output = 8;

        return output;
    }

    // calculate piecewise function for rook
    public int rook (int input) {

        return 14;
    }

    // calculate piecewise function for queen
    public int queen (int input) {

        int output = 0;

        if (input <= 28)
            output = 21;

        else if (input >= 28 && input <= 48)
            output = 23;

        else if (input >= 49 && input <= 60)
            output = 25;

        else if (input >= 61 && input <= 64)
            output = 27;

        return output;
    }

    // calculate piecewise for knight
    public int knight (int input) {

        int output = 0;

        if (input <= 4)
            output = 2;

        else if (input == 5 || input == 10 || input == 11|| input == 16|| input == 17|| input == 22|| input == 23|| input == 28|| input == 29|| input == 34|| input == 39|| input == 44)
            output = 3;

        else if ((input >= 6 && input <= 9) || (input >= 12 && input <= 15) || (input >= 18 && input <= 21) || (input >= 24 && input <= 27))
            output = 4;

        else if ((input >= 30 && input <= 33) || (input >= 35 && input <= 38) || (input >= 40 && input <= 43) || (input >= 45 && input <= 48))
            output = 6;

        else if (input >= 49 && input <= 64)
            output = 8;

        return output;

        /*


        4 if 6<=x<=9 OR 12<=x<=15 OR 18<=x<=21 OR 24<=x<=27

        6 if 30<=x<=33 OR 35<=x<=38 OR 40<=x<=43 OR 45<=x<=48

        8 if 49<=x<=64



         */

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

                int knightOutput;
                int kingOutput;
                int bishopOutput;
                int rookOutput;
                int queenOutput;

                // pass the userinput to the all the piecewise functions
                bishopOutput = bishop(userInput);
                knightOutput = knight(userInput);
                queenOutput = queen(userInput);
                rookOutput = rook(userInput);
                kingOutput = king(userInput);

                //Toast.makeText(mainActivity.this, Integer.toString(bishopOutput), Toast.LENGTH_SHORT).show();

                Bundle outputs = new Bundle();
                outputs.putString("Bishop", Integer.toString(bishopOutput));
                outputs.putString("Knight", Integer.toString(knightOutput));
                outputs.putString("King", Integer.toString(kingOutput));
                outputs.putString("Queen", Integer.toString(queenOutput));
                outputs.putString("Rook", Integer.toString(rookOutput));

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
