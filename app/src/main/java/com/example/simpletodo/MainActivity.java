package com.example.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etToDo;
    Button btnAdd, btnDel, btnClear;
    ListView lvToDo;
    ArrayList<String> alToDo;
    ArrayAdapter<String> aaToDo;
    Spinner spnTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etToDo=findViewById(R.id.editTextToDo);
        btnAdd=findViewById(R.id.buttonAdd);
        btnDel=findViewById(R.id.buttonDel);
        btnClear=findViewById(R.id.buttonClear);
        lvToDo=findViewById(R.id.listViewToDo);
        spnTasks=findViewById(R.id.spinnerTasks);

        alToDo=new ArrayList<String>();

        aaToDo=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alToDo);
        lvToDo.setAdapter(aaToDo);

        spnTasks.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        etToDo.setHint("@string/hintA");
                        btnDel.setEnabled(false);
                        btnAdd.setEnabled(true);
                        break;
                    case 1:
                        etToDo.setHint("@string/hintB");
                        btnAdd.setEnabled(false);
                        btnDel.setEnabled(true);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etToDo.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this,"You don't have any task to be added",Toast.LENGTH_LONG).show();
                }else{
                    String todo=etToDo.getText().toString();
                    alToDo.add(todo);
                    aaToDo.notifyDataSetChanged();
                    etToDo.setText("");
                }
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(alToDo.isEmpty()){
                    Toast.makeText(MainActivity.this,"You don't have any task to remove",Toast.LENGTH_LONG).show();
                }else {
                    String index = etToDo.getText().toString();
                    alToDo.remove(index);
                    aaToDo.notifyDataSetChanged();
                    etToDo.setText("");
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(alToDo.isEmpty()){
                    Toast.makeText(MainActivity.this,"You don't have any task to remove",Toast.LENGTH_LONG).show();
                }else{
                    alToDo.clear();
                    aaToDo.notifyDataSetChanged();
                }
            }
        });
    }
}