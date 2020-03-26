package chapter.android.aweme.ss.com.homework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ChatRoom extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatroom);
        TextView tv = findViewById(R.id.tv_content_info);
        Bundle extras = getIntent().getExtras();
        if(extras!=null) {
            tv.setText(String.format("这是第%d项",  extras.getInt("position")));
        }else{
            tv.setText("");
        }
    }
}
