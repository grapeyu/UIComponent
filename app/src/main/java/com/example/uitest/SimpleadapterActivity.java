package com.example.uitest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.widget.AdapterView.OnItemClickListener;
import static android.widget.AdapterView.OnItemSelectedListener;

public class SimpleadapterActivity extends Activity {
    private String[] names=new String[]{"Lion","Tiger","Monkey","Dog","Cat","Elephent"};
    private int[] images=new int[]{R.drawable.lion,R.drawable.tiger,R.drawable.monkey,R.drawable.dog,R.drawable.cat,R.drawable.elephant};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simpleadapter);
        List<Map<String,Object>> listItems=new ArrayList<Map<String,Object>>();
        for(int i=0;i<names.length;i++){
            Map<String,Object> listItem=new HashMap<String,Object>();
            listItem.put("header",names[i]);
            listItem.put("images",images[i]);
            listItems.add(listItem);
        }
        SimpleAdapter simpleAdapter=new SimpleAdapter(this,listItems,R.layout.item,new String[]{"header","images"},new int[]{R.id.header,R.id.images});
        ListView list=(ListView) findViewById(R.id.mylist);
        list.setAdapter(simpleAdapter);

        /**
         * 为ListView的列表项的单击事件绑定事件监听器
         */
        list.setOnItemClickListener(new OnItemClickListener() {
            //第position项被选中时激发该方法
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                int flag=0;
                System.out.println(names[position]+position+"被单击");
                //点击则改变状态，改变颜色
                switch (flag){
                    case 0:
                        //view.setBackgroundColor(color1);
                        //此处对应上面布局文件的点击函数
                        view.setSelected(true);
                        CharSequence text=names[position];
                        //定义一个Toast表示哪一个图片所在item被点击
                        int duration= Toast.LENGTH_SHORT;
                        Toast toast=Toast .makeText(SimpleadapterActivity.this,text,duration);
                        toast.show();
                        flag=1;
                        break;
                    case 1:
                        //view.setBackgroundColor(color2);
                        view.setSelected(false);
                        CharSequence text1=names[position];
                        int duration1= Toast.LENGTH_SHORT;
                        Toast toast1=Toast .makeText(SimpleadapterActivity.this,text1,duration1);
                        toast1.show();
                        flag=0;
                        break;
                }
            }
        });
        //选中函数
        list.setOnItemSelectedListener(new OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                System.out.println(names[position]+"选中");
            }
            public void onNothingSelected(AdapterView<?> parent){

            }
        });
    }
}
