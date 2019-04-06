package com.example.uitest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
import android.widget.TextView;
import android.graphics.Color;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.view.MenuInflater;

public class MenuActivity extends AppCompatActivity {
    /** Called when the activity is first created. */
    private TextView txt;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xmlmenu);
        txt = (TextView) this.findViewById(R.id.txt);
        //为文本框注册上下文菜单
        registerForContextMenu(txt);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        //MenuInflater inflater = new MenuInflater(this);
        //装填R.menu.my_menu对应的菜单,并添加到menu中
        //inflater.inflate(R.menu.menu_main, menu);
        //return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    //每次创建上下文菜单时都会触发该方法
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo)
    {
        MenuInflater inflater = new MenuInflater(this);
        //装填R.menu.context对应的菜单,并添加到menu中
        inflater.inflate(R.menu.context, menu);
        menu.setHeaderIcon(R.drawable.ic_launcher_background);
        menu.setHeaderTitle("请选择背景色");
    }

    //菜单项被单击后的回调方法
    @Override
    public boolean onOptionsItemSelected(MenuItem mi)
    {
        //判断单击的是哪个菜单项,并针对性地做出响应
        switch(mi.getItemId())
        {
            case R.id.font_10:
                txt.setTextSize(10*2);
                break;
            case R.id.font_16:
                txt.setTextSize(16*2);
                break;
            case R.id.font_20:
                txt.setTextSize(20*2);
                break;
            case R.id.red_font:
                txt.setTextColor(Color.RED);
                break;
            case R.id.black_font:
                txt.setTextColor(Color.BLACK);
                break;
            case R.id.plain_item:
                Toast toast = Toast.makeText(MenuActivity.this, "您单击了普通项菜单", Toast.LENGTH_SHORT);
                toast.show();
                break;
        }
        return true;
    }
}

