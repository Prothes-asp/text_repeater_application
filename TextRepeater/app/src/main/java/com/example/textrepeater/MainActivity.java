package com.example.textrepeater;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.appcompat.widget.AppCompatButton;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    private TextInputLayout inputLayout1,inputLayout2;
    private TextInputEditText numberInput,textInput;
    private CheckBox checkBox1,checkBox2;
    private AppCompatButton submitBtn;
    private TextView display,coptText;
    private ClipData clipData;
    private LinearLayout displayBox;
    private Typeface typeface;
    private AppCompatAutoCompleteTextView autoComplete;
    private ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setStatusBarColor(getColor(R.color.blue));
        this.getWindow().setNavigationBarColor(getColor(R.color.blue));
//        View view = getWindow().getDecorView();
//        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
//        view.setSystemUiVisibility(uiOptions);
        setContentView(R.layout.activity_main);

        inputLayout1 = findViewById(R.id.inputLayout1);
        inputLayout2 = findViewById(R.id.inputLayout2);
        numberInput = findViewById(R.id.numberInput);
        textInput = findViewById(R.id.textInput);
        checkBox1 = findViewById(R.id.checkBox1);
        checkBox2 = findViewById(R.id.checkBox2);
        submitBtn = findViewById(R.id.submitBtn);
        display = findViewById(R.id.display);
        coptText = findViewById(R.id.coptText);
        displayBox = findViewById(R.id.displayBox);
        autoComplete = findViewById(R.id.autoComplete);
        typeface = Typeface.createFromAsset(getAssets(),"fonts/monitor.ttf");
        String items[] = getResources().getStringArray(R.array.emoji_items);
        arrayAdapter = new ArrayAdapter<>(this,R.layout.emoji_item_list,R.id.emojiListItems,items);
        autoComplete.setAdapter(arrayAdapter);


        
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (numberInput.length()>0 && textInput.length()>0 && checkBox1.isChecked() && checkBox2.isChecked()){
                    String emoji = autoComplete.getText().toString();
                    String getNumber = numberInput.getText().toString();
                    String getText = textInput.getText().toString();
                    int num = Integer.parseInt(getNumber);
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i=1; i<=num; i++){
                        stringBuilder.append("\uD83D\uDE18 "+getText+" "+emoji+"\n");
                    }
                    display.setText(""+stringBuilder);
                } else if (numberInput.length()>0 && textInput.length()>0 && checkBox1.isChecked()) {
                    String emoji = autoComplete.getText().toString();
                    String getNumber = numberInput.getText().toString();
                    String getText = textInput.getText().toString();
                    int num = Integer.parseInt(getNumber);
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i=1; i<=num; i++){
                        stringBuilder.append(getText+" "+emoji+"\n");
                    }
                    display.setText(""+stringBuilder);
                }else if (numberInput.length()>0 && textInput.length()>0 && checkBox2.isChecked()) {
                    String emoji = autoComplete.getText().toString();
                    String getNumber = numberInput.getText().toString();
                    String getText = textInput.getText().toString();
                    int num = Integer.parseInt(getNumber);
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i=1; i<=num; i++){
                        stringBuilder.append("\uD83E\uDD70 "+getText+" "+emoji);
                    }
                    display.setText(""+stringBuilder);
                } else if (numberInput.length()>0 && textInput.length()>0) {
                    String emoji = autoComplete.getText().toString();
                    String getNumber = numberInput.getText().toString();
                    String getText = textInput.getText().toString();
                    int num = Integer.parseInt(getNumber);
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i=1; i<=num; i++){
                        stringBuilder.append(getText+" "+emoji);
                    }
                    display.setText(""+stringBuilder);
                } else if (numberInput.length()>0) {
                        numberInput.setError(null);
                    if (textInput.length()>0){
                        textInput.setError(null);
                    }else{
                        textInput.setError("Empty");
                        display.setText(null);
                    }
                }else if (textInput.length()>0) {
                    textInput.setError(null);
                    if (numberInput.length()>0){
                        numberInput.setError(null);
                    }else{
                        numberInput.setError("Empty");
                        display.setText(null);
                    }
                }

                else{
                    numberInput.setError("Empty");
                    textInput.setError("Empty");
                    display.setText("Developed By \nProthes");
                    display.setTextColor(getColor(R.color.prothes));
                    display.setTypeface(typeface);
                }

                display.setTextColor(getColor(R.color.black));
                display.setTypeface(null);
                displayBox.setBackground(getDrawable(R.drawable.strock));
                display.setPadding(0,0,0,0);
            }
        });




        coptText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getsms = display.getText().toString();
                if (getsms.length()>0){
                    ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                    clipData = ClipData.newPlainText("Text",getsms);
                    clipboardManager.setPrimaryClip(clipData);
                    Toast.makeText(MainActivity.this, "Copied Text", Toast.LENGTH_SHORT).show();
                    displayBox.setBackground(getDrawable(R.drawable.strock_bg));
                    display.setTextColor(getColor(R.color.black));
                    display.setTypeface(null);
                    if (getsms == "Developed By \nProthes"){
                        display.setPadding(0,400,0,0);
                    }else{
                        display.setPadding(0,0,0,0);
                    }
                }else{
                    displayBox.setBackground(getDrawable(R.drawable.strock_bg_red));
                    display.setText("Developed By \nProthes");
                    display.setTextColor(getColor(R.color.prothes));
                    display.setTypeface(typeface);
                    display.setPadding(0,400,0,0);
                }
            }
        });


    }

    // For Menu Option
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        MenuItem menuItem = menu.findItem(R.id.searchMenu);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    // Menu Item Clickable

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.shareMenu){
            Toast.makeText(this, "Share Menu", Toast.LENGTH_SHORT).show();
        }
        if (item.getItemId() == R.id.privacyMenu){
            Toast.makeText(this, "Privacy", Toast.LENGTH_SHORT).show();
        }
        if (item.getItemId() == R.id.settingMenu){
            Toast.makeText(this, "Setting", Toast.LENGTH_SHORT).show();
        }
        if (item.getItemId() == R.id.exitMenu){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }


    // For BackPressed Option Create
    /** @noinspection deprecation*/
    @Override
    public void onBackPressed() {
        if (isTaskRoot()){
            AlertDialog alertDialog = new AlertDialog.Builder(this)
                    .setTitle("Warning !!")
                    .setMessage("Are You Sure Want To Exit")
                    .setIcon(getDrawable(R.drawable.alert_orange))
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
                    .show();
            alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(getColor(R.color.appName));
            alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(getColor(R.color.appName));
        }else{
            super.onBackPressed();
        }
    }
}