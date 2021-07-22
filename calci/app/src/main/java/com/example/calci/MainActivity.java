package com.example.calci;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button button0,button1,button2,button3,button4,button5,button6,button7,button8,button9,buttondot,buttonadd,buttonsub,buttonmul,buttondiv,buttonrem,buttoneq,buttonc;
    TextView input;
    String operand1="";
    String operand2="";
    String operator="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button0=(Button)findViewById(R.id.button0);
        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
        button3=(Button)findViewById(R.id.button3);
        button4=(Button)findViewById(R.id.button4);
        button5=(Button)findViewById(R.id.button5);
        button6=(Button)findViewById(R.id.button6);
        button7=(Button)findViewById(R.id.button7);
        button8=(Button)findViewById(R.id.button8);
        button9=(Button)findViewById(R.id.button9);
        buttondot=(Button)findViewById(R.id.buttondot);
        buttonadd=(Button)findViewById(R.id.buttonadd);
        buttonsub=(Button)findViewById(R.id.buttonsub);
        buttonmul=(Button)findViewById(R.id.buttonmul);
        buttondiv=(Button)findViewById(R.id.buttondiv);
        buttonrem=(Button)findViewById(R.id.buttonrem);
        buttonc=(Button)findViewById(R.id.buttonc);
        buttoneq=(Button)findViewById(R.id.buttoneq);

        input =(TextView)findViewById(R.id.input);

        button0.setOnClickListener(this::setvalue);
        button1.setOnClickListener(this::setvalue);
        button2.setOnClickListener(this::setvalue);
        button3.setOnClickListener(this::setvalue);
        button4.setOnClickListener(this::setvalue);
        button5.setOnClickListener(this::setvalue);
        button6.setOnClickListener(this::setvalue);
        button7.setOnClickListener(this::setvalue);
        button8.setOnClickListener(this::setvalue);
        button9.setOnClickListener(this::setvalue);
        buttondot.setOnClickListener(this::setvalue);

        buttonadd.setOnClickListener(this::setoperator);
        buttonsub.setOnClickListener(this::setoperator);
        buttonmul.setOnClickListener(this::setoperator);
        buttondiv.setOnClickListener(this::setoperator);
        buttonrem.setOnClickListener(this::setoperator);

        buttonc.setOnClickListener(this::clear);

        buttoneq.setOnClickListener(this::evaluate);

    }

    private void setvalue(View v)
    {
        CharSequence text=((Button)v).getText();
        if(operator.equals(""))
            operand1+=text;
        else
            operand2+=text;
        input.setText(input.getText()+""+text);
    }
    private void setoperator(View v)
    {
        if(input.getText().length()!=0)
        {
            CharSequence text=((Button)v).getText();
            if(!operator.equals(""))
            {
                evaluate(null);
            }
            operator=v.getTag().toString();
            input.setText(input.getText()+""+text);

        }
    }
    private void clear(View v)
    {
        operand1="";
        operand2="";
        operator="";
        input.setText("");
    }
    private void evaluate(View v)
    {
        double result=0;
        boolean flag=false;
        switch (operator)
        {
            case "add":result=(Double.parseDouble(operand1)) + (Double.parseDouble(operand2));
                break;
            case "sub":result=(Double.parseDouble(operand1)) - (Double.parseDouble(operand2));
                break;
            case "mul":result=(Double.parseDouble(operand1)) * (Double.parseDouble(operand2));
                break;
            case "div":

                result=(Double.parseDouble(operand1)) / (Double.parseDouble(operand2));
                break;
            case "rem":

                result=(Double.parseDouble(operand1)) % (Double.parseDouble(operand2));
                break;
        }
        operand1=((result % 1 !=0 ))?String.valueOf(result):String.valueOf((int)result);
        input.setText(operand1);
        operand2="";
        operator="";
    }
}
