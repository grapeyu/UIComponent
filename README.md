# UIComponent
实验内容：  
-----------
1、利用SimpleAdapter实现如下界面效果    
（1）注意列表项的布局  
（2）图片使用QQ群附件资源  
（3）使用Toast显示选中的列表项信息  

2、创建如图所示的自定义对话框，调用 AlertDialog.Builder 对象上的 setView() 将布局添加到AlertDialog  

3、使用XML定义菜单    
（1）字体大小（有小，中，大这3个选项；分别对应10号字，16号字和20号字）  
（2）点击之后设置测试文本的字体普通菜单项，点击之后弹出Toast提示    
（3）字体颜色（有红色和黑色这2个选项），点击之后设置测试文本的字体  

4、创建如图模式的上下文菜单  
（1）使用ListView或者ListActivity创建List  
（2）为List Item创建ActionMode形式的上下文菜单  

主要代码：
------------------
1.  
----------
SimpleadapterActivity.java:  
```public class SimpleadapterActivity extends Activity {
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
```   

2.
------------  
MyDialogActivity.java:  
```public class MyDialogActivity extends AppCompatActivity {
    private EditText name;
    private EditText password;
    private Button btnOK;
    private Button btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.mydialog);
        // 创建对话框构建器
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = View.inflate(MyDialogActivity.this, R.layout.mydialog, null);
        // 获取布局中的控件
        name=(EditText) view.findViewById(R.id.edname);
        password=(EditText) view.findViewById(R.id.edpassword);
        btnCancel=(Button) view.findViewById(R.id.cancel);
        btnOK=(Button) view.findViewById(R.id.ok);
        builder.setView(view);
        // 创建对话框
        final AlertDialog alertDialog = builder.create();
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname = name.getText().toString().trim();
                String psd = password.getText().toString().trim();
                Toast.makeText(MyDialogActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();// 对话框消失
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();// 对话框消失
            }
        });
        alertDialog.show();
    }
}
```  

3.
---------
MenuActivity.java:
```public class MenuActivity extends AppCompatActivity {
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
```  

4.
-------------
ActionModelActivity.java:  
```public class ActionModeActivity extends AppCompatActivity {
    private ListView listview;
    private String[] count=new String[]{"One","Two","Three","Four","Five"};
    private int[] icon=new int[]{R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_actionmode);
        listview = (ListView) findViewById(R.id.listview);
        List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < count.length; i++) {
            Map<String, Object> listItem = new HashMap<String, Object>();
            listItem.put("icon", icon[i]);
            listItem.put("count", count[i]);
            listItems.add(listItem);
        }
        SimpleAdapter actionAdapter=new SimpleAdapter(this,listItems,R.layout.item_actionmode,new String[]{"count","icon"},new int[]{R.id.count,R.id.icon});
        listview.setAdapter(actionAdapter);
        listview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listview.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position,
                                                  long id, boolean checked) {
                listview.getCheckedItemCount();
                findViewById(R.id.menu_delete);
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                // Respond to clicks on the actions in the CAB
                switch (item.getItemId()) {
                    case R.id.menu_delete:
                        //deleteSelectedItems();
                        mode.finish(); // Action picked, so close the CAB
                        return true;
                    default:
                        return false;
                }
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                // Inflate the menu for the CAB
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.menu_delete, menu);
                return true;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                // Here you can make any necessary updates to the activity when
                // the CAB is removed. By default, selected items are deselected/unchecked.
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                // Here you can perform updates to the CAB due to
                // an invalidate() request
                return false;
            }
        });
 
    }
}
```  

结果截图：
=============  
1、  
![image](https://github.com/grapeyu/UIComponent/blob/master/image/1.jpg)   
2、  
![image](https://github.com/grapeyu/UIComponent/blob/master/image/2.jpg)  
3、  
![image](https://github.com/grapeyu/UIComponent/blob/master/image/3.1.jpg)
![image](https://github.com/grapeyu/UIComponent/blob/master/image/3.2.jpg)  
4、  
![image](https://github.com/grapeyu/UIComponent/blob/master/image/4.1.jpg)
![image](https://github.com/grapeyu/UIComponent/blob/master/image/4.2.jpg)
   
