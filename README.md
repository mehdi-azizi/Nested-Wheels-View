# Nested Wheels View for android
This component is  a customized view with 2 nested circle that you can spin independently and choose from items in each circle 


![ScreenShot](./title.jpg?raw=true "text sample")


## How to add
### There are three simple steps need to use NestedWheel view :


1- **add component to project :**   For adding this `View` to your project ,you just need to add `NestedWheelsView.java` file to your project directory . 

2- **create an object from NestetWheel view:**

- By using  in xml layout :


       <com.ma.nestedwheels.NestedWheelsView
          android:layout_width="match_parent"
          android:layout_height="match_parent"
          android:id="@+id/my_nested_wheels"
         />


- Or Creating  in the source :

```java
     NestedWheelsView myView=new NestedWheelsView(this);
     LinearLayout some_layout=(LinearLayout)findViewById(R.id.some_layout) ;
     LinearLayout.LayoutParams param=new
                LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT );
        some_layout.addView(myView,param);
```


3- **Initialize the NestedWheel view**

```java
      myView.init( <List of String for Outer wheel>, <List of String for Inner wheel>,
      <List of Paint for Outer wheel>, <List of Paint for Inner wheel>);
```
* NOTE: For every wheel the size of **Paint list** and **string list** has to be equal otherwise you receive an `IllegalArgumentException` exception. 


#### for example :
   
   ```java
       ArrayList<String> texts = new ArrayList<>();
        texts.add("text 1");
        texts.add("text 2");
        texts.add("text 3");
        texts.add("text 4");
   
        Paint paint1 = new Paint();
        paint1.setColor(Color.RED);
        
        Paint paint2 = new Paint();
        paint1.setColor(Color.GREEN);
        
         ArrayList<Paint> paints = new ArrayList<>();
          paints.add(paint1);
          paints.add(paint2);
          paints.add(paint1);
          paints.add(paint2);
          
           myView.init(texts, texts, paints, paints);
        
   ```
