package caufcauf.piecewisefunctions;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;

import caufcauf.piecewisefunctions.mainActivity;

/**
 * This is the popup that shows the outputs for each piecewise function
 */
public class outputFragment extends DialogFragment {


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        String knight = getArguments().getString("Knight");
        String bishop = getArguments().getString("Bishop");
        String king = getArguments().getString("King");



        // object that allows us to build the dialog box
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // customize look and feel of the dialog box
        builder.setTitle("Outputs");
        builder.setMessage("King: " + king + "\nBishop: " + bishop + "\nKing: " + king);


        return builder.create();


        //return super.onCreateDialog(savedInstanceState);
    }
}
