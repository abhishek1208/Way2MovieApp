package com.example.pagerank.themovieapp.model;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.pagerank.themovieapp.R;
import com.example.pagerank.themovieapp.databinding.ActivityBookTicketBinding;
//import com.example.pagerank.themovieapp.databinding.ActivityBookTicketBinding;

import br.com.ilhasoft.support.validation.Validator;

public class BookTicketActivity extends AppCompatActivity {
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityBookTicketBinding binding= DataBindingUtil.setContentView(this, R.layout.activity_book_ticket);
        User user = new User();
        final Validator validator = new Validator(binding);
        binding.setUser(user);
        submit= (Button) findViewById(R.id.btn_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validator.validate()){
                    Toast.makeText(BookTicketActivity.this, "Submitted!", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        });
    }
}
