package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView resultTc, solutionTv;
    MaterialButton buttonC, buttonBracOpen, buttonBracClose,buttonDivide,button7,button8,button9,buttonMul,button4,button5,button6,buttonplus,button3,button2,button1,buttonminus,buttonAC,button0,buttondot,buttonequal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTc=findViewById(R.id.result_tv);
        solutionTv=findViewById(R.id.solution_tv);
        assignId(buttonC, R.id.button_c);
        assignId(buttonBracOpen, R.id.button_openBracket);
        assignId(buttonBracClose, R.id.button_closeBracket);
        assignId(buttonDivide, R.id.button_divide);
        assignId(buttonMul, R.id.button_multiply);
        assignId(buttonplus, R.id.button_plus);
        assignId(buttonminus, R.id.button_minus);
        assignId(buttonAC, R.id.button_ac);
        assignId(buttonequal, R.id.button_equalto);
        assignId(buttondot, R.id.button_point);
        assignId(button7, R.id.button_7);
        assignId(button8, R.id.button_8);
        assignId(button9, R.id.button_9);
        assignId(button0, R.id.button_0);
        assignId(button4, R.id.button_4);
        assignId(button5, R.id.button_5);
        assignId(button6, R.id.button_6);
        assignId(button1, R.id.button_1);
        assignId(button2, R.id.button_2);
        assignId(button3, R.id.button_3);

    }

    void assignId(MaterialButton btn, int id){
        btn=findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
    MaterialButton button=(MaterialButton)view;
    String buttonText=button.getText().toString();
    String dataToCalculate=solutionTv.getText().toString();
        if(buttonText.equals("AC")){
            solutionTv.setText("");
            resultTc.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            solutionTv.setText(resultTc.getText());
            return;
        }
        if(buttonText.equals("C")){
            dataToCalculate=dataToCalculate.substring(0,dataToCalculate.length()-1);
        }
        else{
            dataToCalculate+=buttonText;
        }

    solutionTv.setText(dataToCalculate);
        String finalResult=getResult(dataToCalculate);
        if (finalResult.equals("Error")){
//         resultTc.setText(finalResult);
        }
        else{
        resultTc.setText(finalResult);
        }
    }
    String getResult(String data){
        try {
            Context context=Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable=context.initSafeStandardObjects();
          String finalResult=context.evaluateString(scriptable,data,"Javascript",1,null).toString();
          if(finalResult.endsWith(".0")){
              finalResult=finalResult.replace(".0","");
          }
          return finalResult;
        }
        catch (Exception e){
            return  "Error";
        }
    }
}