# SlashToggleButton
[![Platform](https://img.shields.io/badge/platform-Android-yellow.svg)](https://www.android.com)
[![API](https://img.shields.io/badge/API-17%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=16)
<br/>
<img src="image.gif" width = 100/>

This library lets make a simple toggle button with an icon of your choice and an animated slash.<br/>

# Usage
Add this to your app build.gradle file.
```
dependencies {
    compile 'com.karthik.slashtogglebutton:slashtogglebutton:1.0.0'
}
```
## Example Usage 1
### XML
Create a custom xml namespace.
```
xmlns:custom="http://schemas.android.com/apk/res-auto"
```
```
<com.karthik.slashtogglebutton.SlashToggleButton
        android:id="@+id/slash"
        android:layout_width="100px"
        android:layout_height="100px"
        android:layout_gravity="center"
        android:layout_marginBottom="8dp"
        android:layout_marginEnd="8dp"
        android:layout_marginStart="8dp"
        android:layout_marginTop="8dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        custom:icon="@mipmap/baseline_screen_rotation_black_48" 
 />
 ```
 ### Java
 ```
 SlashToggleButton slashToggleButton = new SlashToggleButton(getApplicationContext());
 Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.baseline_screen_rotation_black_48);
 slashToggleButton.setIconBitmap(bitmap);
 LinearLayout linearLayout = findViewById(R.id.linearLayout);
 linearLayout.addView(slashToggleButton);
```

### Methods

|   Method       |       Parameter     |               Description            |
| -------------- | ------------------- | ------------------------------------ |
|    toggle()    |       -             | Change the toggle state of the button|
|setIconBitmap() | Bitmap bitmap       |      Set the bitmap to display       |
|setToggleState()| Boolean toggleState |  Set the toggle state of the button  |
|  setMargin()   |     int margin      |Set margin for starting point of slash|
