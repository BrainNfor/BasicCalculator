package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import android.widget.TextView;


import com.faendir.rhino_android.RhinoAndroidHelper;
import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView text_answer, text_type_request;
    MaterialButton  button_1, button_2, button_3, button_4, button_5, button_6, button_7, button_8, button_9, button_0, button_dot;
    MaterialButton button_divide, button_add, button_multiply, button_subtract, button_modulo, button_equals;
    MaterialButton button_arrow_left, button_cancel_answer, button_cancel_all;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text_answer = findViewById(R.id.text_answer);
        text_type_request = findViewById(R.id.text_type_request);

        assignId(button_0, R.id.button_0);
        assignId(button_1, R.id.button_1);
        assignId(button_2, R.id.button_2);
        assignId(button_3, R.id.button_3);
        assignId(button_4, R.id.button_4);
        assignId(button_5, R.id.button_5);
        assignId(button_6, R.id.button_6);
        assignId(button_7, R.id.button_7);
        assignId(button_8, R.id.button_8);
        assignId(button_9, R.id.button_9);
        assignId(button_dot, R.id.button_dot);
        assignId(button_divide, R.id.button_divide);
        assignId(button_add, R.id.button_add);
        assignId(button_multiply, R.id.button_multiply);
        assignId(button_modulo, R.id.button_modulo);
        assignId(button_subtract, R.id.button_subtract);
        assignId(button_arrow_left, R.id.button_arrow_left);
        assignId(button_cancel_all, R.id.button_cancel_typed);
        assignId(button_equals, R.id.button_equals);
        assignId(button_cancel_answer, R.id.button_cancel_all_answer);

    }

    @Override
    public void onClick(View view) {

        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = text_type_request.getText().toString();

        if (buttonText.equals("c")){
            text_type_request.setText("");
            return;
        }
        if (buttonText.equals("ca")){
            if (dataToCalculate != " "){
                text_type_request.setText("");
                text_answer.setText("");
            }

            return;
        }
        if (buttonText.equals("=")){
                if (!dataToCalculate.equals("")){
                    String finalResult = getResult(dataToCalculate);
                    if (!finalResult.equals("Err")){
                        text_answer.setText(finalResult);
                    }else {
                        text_answer.setText("Err");
                    }
                }else {
                    text_answer.setText("0");
                }

            return;
        }
        if (buttonText.equals("\u2190")){
            if (dataToCalculate.length() > 0){
                dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length()-1);

            }

        }else {
            dataToCalculate = dataToCalculate + buttonText;
        }
        text_type_request.setText(dataToCalculate);


    }


    void assignId(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);

    }

   String getResult(String data){

       try {
           @Deprecated
           Context context = new RhinoAndroidHelper().enterContext();
           context.setOptimizationLevel(-1);
           Scriptable scriptable = context.initStandardObjects();
           String finalResult = context.evaluateString(scriptable, data, "Javascript",1, null ).toString();
           return finalResult;
       }catch (Exception e){
          return  "Err";
       }

   }


}















