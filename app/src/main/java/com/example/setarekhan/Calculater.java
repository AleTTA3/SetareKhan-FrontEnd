package com.example.setarekhan;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class Calculater extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calculater);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button btn_num0 = findViewById(R.id.btn_zero);
        Button btn_num1 = findViewById(R.id.btn_one);
        Button btn_num2 = findViewById(R.id.btn_two);
        Button btn_num3 = findViewById(R.id.btn_tree);
        Button btn_num4 = findViewById(R.id.btn_for);
        Button btn_num5 = findViewById(R.id.btn_five);
        Button btn_num6 = findViewById(R.id.btn_six);
        Button btn_num7 = findViewById(R.id.btn_seven);
        Button btn_num8 = findViewById(R.id.btn_eight);
        Button btn_num9 = findViewById(R.id.btn_nine);
        Button btn_minus = findViewById(R.id.btn_menha);
        Button btn_plus = findViewById(R.id.btn_jam);
        Button btn_multiplication = findViewById(R.id.btn_zarb);
        Button btn_division = findViewById(R.id.btn_taghsim);
        Button btn_mosavi = findViewById(R.id.btn_sum);
        Button btn_ac = findViewById(R.id.btn_ac);
        TextView txt_sum = findViewById(R.id.txt_sum);
        TextView txt_result = findViewById(R.id.txt_result);

        btn_num0.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                txt_sum.setText(txt_sum.getText().toString()+"0");
            }
        });
        btn_num1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                txt_sum.setText(txt_sum.getText().toString()+"1");
            }
        });
        btn_num2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                txt_sum.setText(txt_sum.getText().toString()+"2");
            }
        });
        btn_num3.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                txt_sum.setText(txt_sum.getText().toString()+"3");
            }
        });
        btn_num4.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                txt_sum.setText(txt_sum.getText().toString()+"4");
            }
        });
        btn_num5.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                txt_sum.setText(txt_sum.getText().toString()+"5");
            }
        });
        btn_num6.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                txt_sum.setText(txt_sum.getText().toString()+"6");
            }
        });
        btn_num7.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                txt_sum.setText(txt_sum.getText().toString()+"7");
            }
        });
        btn_num8.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                txt_sum.setText(txt_sum.getText().toString()+"8");
            }
        });
        btn_num9.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                txt_sum.setText(txt_sum.getText().toString()+"9");
            }
        });
        btn_ac.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                txt_sum.setText("");
                txt_result.setText("");
            }
        });
        btn_plus.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                txt_sum.setText(txt_sum.getText().toString()+"+");
            }
        });
        btn_minus.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                txt_sum.setText(txt_sum.getText().toString()+"-");
            }
        });
        btn_multiplication.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                txt_sum.setText(txt_sum.getText().toString()+"*");
            }
        });
        btn_division.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                txt_sum.setText(txt_sum.getText().toString()+"/");
            }
        });
        btn_mosavi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String expression = txt_sum.getText().toString();
                double firstInput = 0;
                double secondInput = 0;
                char operator = ' ';
                double result = 0;

                if (expression.contains("+")) {
                    operator = '+';
                } else if (expression.contains("-")) {
                    operator = '-';
                } else if (expression.contains("*")) {
                    operator = '*';
                } else if (expression.contains("/")) {
                    operator = '/';
                }
                int operatorIndex = expression.indexOf(operator);
                firstInput = Double.parseDouble(expression.substring(0, operatorIndex));
                secondInput = Double.parseDouble(expression.substring(operatorIndex + 1));

                switch (operator) {
                    case '+':
                        result = firstInput + secondInput;
                        break;
                    case '-':
                        result = firstInput - secondInput;
                        break;
                    case '*':
                        result = firstInput * secondInput;
                        break;
                    case '/':
                        if (secondInput != 0) {
                            result = firstInput / secondInput;
                        } else {
                            txt_result.setText("تقسیم بر صفر ممکن نیست");
                            return;
                        }
                        break;
                }

                txt_result.setText(String.valueOf(result));
            }
        });

    }
}