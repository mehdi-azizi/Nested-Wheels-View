# Nested Wheels View for android
This component is  a customized view with 2 nested circle that you can spin independently and choose from items in each circle 


![ScreenShot](./title.jpg?raw=true "text sample")


## How to add

1- **add component to project :**   For adding this `View` to your project You just need to add `NestedWheelsView.java` to your project directory . 

2- **create an object :**

- Using  in xml layout :

```
    <com.ma.nestedwheels.NestedWheelsView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:id="@+id/my_nested_wheels"
         />
```

- Creating  in the source :

```
     NestedWheelsView myView=new NestedWheelsView(this);
     LinearLayout some_layout=(LinearLayout)findViewById(R.id.some_layout) ;
    LinearLayout.LayoutParams param=new
                LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT );
        some_layout.addView(myView,param);
```


