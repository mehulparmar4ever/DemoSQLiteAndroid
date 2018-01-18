package com.example.demosqlite;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    EditText myInput;
    TextView myText;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myInput = (EditText) findViewById(R.id.myInput);
        myText = (TextView) findViewById(R.id.myTextView);

        dbHandler = new DBHandler(this, null, null, 1);
        printDatabase();

    }

    public void printDatabase() {
        Log.i("Tag", "printDatabase");

        String dbString = dbHandler.databaseToString();
        myText.setText(dbString);
        myInput.setText("");
        Log.i("Tag", dbString);
    }

    public void addButtonClicked(View view) {
        Log.i("Tag", "addButtonClicked");
        Products products = new Products(myInput.getText().toString());
        dbHandler.addProducts(products);
        printDatabase();
    }

    public void deleteButtonClicked(View view) {
        Log.i("Tag", "deleteButtonClicked");

        String inputText = myInput.getText().toString();
        dbHandler.deleteProduct(inputText);
        printDatabase();
    }
}
