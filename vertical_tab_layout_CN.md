![logo](images/vertical_tab_layout.png)

# Vertical Tab Layout

![version](https://img.shields.io/badge/%E7%89%88%E6%9C%AC-1.0.0-brightgreen.svg)
![author](https://img.shields.io/badge/%E4%BD%9C%E8%80%85-hcxc-orange.svg)

## 项目介绍

安卓垂直标签布局

## 下载

[Demo](screenshot/vertical-tab-layout.mp4)

[aar 文件](./output/VerticalTabLayout.aar)

### 如何使用

```xml

<com.hcxc.verticaltablayout.VerticalTabLayout 
    android:id="@+id/tab_layout_2"
    android:layout_width="150dp" 
    android:layout_height="match_parent"
    android:layout_marginLeft="142dp" 
    android:background="@android:color/holo_blue_dark"
    app:vDividerColor="@android:color/black" 
    app:vDividerHeight="1px" 
    app:vDividerPadding="15dp"
    app:vTabArrowColor="#cccccc" 
    app:vTabHeight="30dp" 
    app:vTabIconPadding="8dp"
    app:vTabIndicatorColor="@android:color/holo_green_dark" 
    app:vTabIndicatorGravity="fill"
    app:vTabSelectedTextColor="@android:color/white" 
    app:vTabTextColor="#555555"
    app:vTabTextSize="10dp" 
    app:vTabViewGravity="center" />
```

```java
VerticalTabLayout vTabLayout=(VerticalTabLayout)findViewById(R.id.tab_layout);
vTabLayout.addTab(vTabLayout.newTab().setText("TEST").setIcon(R.drawable.ic_selector));
vTabLayout.setOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedAdapter(){
        @Override
        public void onTabSelected(VerticalTabLayout.Tab tab,int position){
                Toast.makeText(getApplicationContext(),"onTabSelected: "+position,ToastLENGTH_SHORT).show();
        }
});
```
