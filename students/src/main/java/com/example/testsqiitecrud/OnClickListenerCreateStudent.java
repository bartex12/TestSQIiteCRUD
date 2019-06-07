package com.example.testsqiitecrud;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

class OnClickListenerCreateStudent  extends MainActivity implements View.OnClickListener {

    public static final String TAG = "33333";

    @Override
    public void onClick(View view) {

        //так надо context = com.example.testsqiitecrud.MainActivity@d8958bc
        final Context context = view.getContext();
        //так нельзя context = com.android.internal.policy.DecorContext@721696a
        //final Context context = view.getRootView().getContext();
        Log.d (TAG, " OnClickListenerCreateStudent onClick context = " + context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.student_input_form, null, false);

        final EditText editTextStudentFirstname = (EditText) formElementsView.findViewById(R.id.editTextStudentFirstname);
        final EditText editTextStudentEmail = (EditText) formElementsView.findViewById(R.id.editTextStudentEmail);

        new AlertDialog.Builder(context).
                setView(formElementsView).
                setTitle("Create Student").
                setPositiveButton("Add",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String studentFirstname = editTextStudentFirstname.getText().toString();
                                String studentEmail = editTextStudentEmail.getText().toString();

                                ObjectStudent objectStudent = new ObjectStudent();
                                objectStudent.firstname = studentFirstname;
                                objectStudent.email = studentEmail;

                                boolean createSuccessful = new TableControllerStudent(context).create(objectStudent);

                                if (createSuccessful) {
                                    Toast.makeText(context, "Student information was saved.", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(context, "Unable to save student information.", Toast.LENGTH_SHORT).show();
                                }

                                //офигенный способ достуапа к MainActivity и её методам
                                // https://www.androidcode.ninja/android-sqlite-tutorial/
                                //только в статье view.getRootView().getContext(); а надо context = view.getContext();
                                ((MainActivity) context).countRecords();
                                ((MainActivity) context).readRecords();

                                dialogInterface.cancel();
                            }
                        }).show();



    }
}
