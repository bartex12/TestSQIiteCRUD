package com.example.testsqiitecrud;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "33333";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonCreateStudent = (Button) findViewById(R.id.buttonCreateStudent);
        buttonCreateStudent.setOnClickListener(new OnClickListenerCreateStudent());

        readRecords();
        countRecords();

    }

    public void countRecords() {
        int recordCount = new TableControllerStudent(this).count();
        TextView textViewRecordCount = (TextView) findViewById(R.id.textViewRecordCount);
        textViewRecordCount.setText(recordCount + " records found.");
    }

    public void readRecords() {
        // видимо, метод вместо адаптера списка (updateAdapter) используется

        LinearLayout linearLayoutRecords = (LinearLayout) findViewById(R.id.linearLayoutRecords);
        linearLayoutRecords.removeAllViews();

        List<ObjectStudent> students = new TableControllerStudent(this).read();

        if (students.size() > 0) {
            for (ObjectStudent obj : students) {
                int id = obj.id;
                String studentFirstname = obj.firstname;
                String studentEmail = obj.email;

                String textViewContents = studentFirstname + " - " + studentEmail;

                TextView textViewStudentItem= new BloknoteItemView(this);
                //TextView textViewStudentItem= new TextView(this);
                textViewStudentItem.setPadding(10, 10, 0, 10);
                textViewStudentItem.setTextSize(20);
                textViewStudentItem.setText(textViewContents);
                textViewStudentItem.setTag(Integer.toString(id));
                textViewStudentItem.setOnLongClickListener(new OnLongClickListenerStudentRecord());

                linearLayoutRecords.addView(textViewStudentItem);

/*
                TextView textViewStudentItem1= new BloknoteItemView(this);
                textViewStudentItem1.setPadding(10, 10, 0, 10);
                textViewStudentItem1.setTextSize(20);
                textViewStudentItem1.setText(studentFirstname);
                textViewStudentItem1.setTag(Integer.toString(id));
                textViewStudentItem1.setOnLongClickListener(new OnLongClickListenerStudentRecord());
                linearLayoutRecords.addView(textViewStudentItem1);

                TextView textViewStudentItem2= new BloknoteItemView(this);
                textViewStudentItem2.setPadding(10, 10, 0, 10);
                textViewStudentItem2.setTextSize(20);
                textViewStudentItem2.setText(studentEmail);
                textViewStudentItem2.setTag(Integer.toString(id));
                textViewStudentItem2.setOnLongClickListener(new OnLongClickListenerStudentRecord());
                linearLayoutRecords.addView(textViewStudentItem2);
                */
            }
        }
        else {
            TextView locationItem = new TextView(this);
            locationItem.setPadding(8, 8, 8, 8);
            locationItem.setText("No records yet.");

            linearLayoutRecords.addView(locationItem);
        }
    }
}
