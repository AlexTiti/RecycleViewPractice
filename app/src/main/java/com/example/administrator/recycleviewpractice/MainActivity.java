package com.example.administrator.recycleviewpractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ItemTouchHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//        LinearLayoutManager manager = new LinearLayoutManager(this);
//        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,3));
        RecyclerViewAdapter adapter = new RecyclerViewAdapter();
        recyclerView.setAdapter(adapter);
       // recyclerView.addItemDecoration(new MyItemDecoration(MainActivity.this,MyItemDecoration.VERTICAL));
        recyclerView.addOnItemTouchListener(new ClickHelpListenering(recyclerView,MainActivity.this){
            @Override
            public void onItemClick(RecyclerViewAdapter.ViewHolder viewHolder) {
                Toast.makeText(MainActivity.this,viewHolder.textView.getText(),Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onItemLongClick(RecyclerViewAdapter.ViewHolder viewHolder) {
                Toast.makeText(MainActivity.this,viewHolder.textView.getText()+"长按",Toast.LENGTH_SHORT).show();
                if (viewHolder.getAdapterPosition() != 0)
                    helper.startDrag(viewHolder);
            }
        });

        helper = new ItemTouchHelper(new Callback(adapter));
        helper.attachToRecyclerView(recyclerView);



    }
}
