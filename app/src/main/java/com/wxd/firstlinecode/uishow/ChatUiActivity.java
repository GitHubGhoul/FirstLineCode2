package com.wxd.firstlinecode.uishow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.wxd.firstlinecode.R;

import java.util.ArrayList;
import java.util.List;

public class ChatUiActivity extends AppCompatActivity {

    private List<Message> messages = new ArrayList<>();
    private RecyclerView rvChat;
    private EditText etContent;
    private Button btnSend;
    private ChatAdapter chatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_ui);
        initMsg();
        rvChat = findViewById(R.id.rv_chat);
        etContent = findViewById(R.id.et_content);
        btnSend = findViewById(R.id.btn_send);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rvChat.setLayoutManager(manager);
        chatAdapter = new ChatAdapter(messages);
        rvChat.setAdapter(chatAdapter);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = etContent.getText().toString().trim();
                if (!TextUtils.isEmpty(content)) {
                    Message message = new Message(content, Message.TYPE_SEND);
                    messages.add(message);
                    chatAdapter.notifyItemInserted(messages.size()-1);
                    rvChat.scrollToPosition(messages.size()-1);
                    etContent.setText("");
                }
            }
        });
    }

    private void initMsg() {
        Message message1 = new Message("Hello guy", Message.TYPE_RECEIVED);
        messages.add(message1);
        Message message2 = new Message("Hello,Who is that?", Message.TYPE_SEND);
        messages.add(message2);
        Message message3 = new Message("This is Tom,Nice talking to you.", Message.TYPE_RECEIVED);
        messages.add(message3);
    }
}