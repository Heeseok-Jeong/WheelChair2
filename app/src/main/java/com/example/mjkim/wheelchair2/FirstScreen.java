package com.example.mjkim.wheelchair2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.mjkim.wheelchair2.Login.LoginScreen;
import com.example.mjkim.wheelchair2.Login.User;
import com.example.mjkim.wheelchair2.naver_map_fragment.FragmentMapActivity;

public class FirstScreen extends AppCompatActivity {

    ImageButton menu_button;
    ImageButton login_button;
    private BackPressCloseHandler backPressCloseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_screen);

        menu_button = (ImageButton)findViewById(R.id.menu_b);

        menu_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMenuTab();
            }
        });

        login_button = (ImageButton)findViewById(R.id.login_b);

        login_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                openLoginTab();

            }
        });

        backPressCloseHandler = new BackPressCloseHandler(this);





    }

    public void onClick(View view){
        if(view.getId()== R.id.surround_b){
            Intent intent=new Intent(this,NaverMapActivity.class);
            startActivity(intent);
        }
        if(view.getId()== R.id.name_b){
            Intent intent=new Intent(this,SearchActivity.class);
            startActivity(intent);
        }
        if(view.getId() == R.id.review_b){
            Intent intent=new Intent(this,ReviewSearch.class);
            startActivity(intent);

        }
    }
    public void openMenuTab(){
        Intent intent = new Intent(this, MenuScreen.class);
        startActivity(intent);
    }

    public void openLoginTab(){
        Intent intent = new Intent(this, LoginScreen.class);
        startActivity(intent);
    }

    public void onBackPressed(){
        backPressCloseHandler.onBackPressed();
    }


}
