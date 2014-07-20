CircleView
==========

A Circle View containing Title and Subtitle

(Ignore the Activity title :P)

![CircleView](/Screenshots/device-2014-07-20-132345.png)


Attributes to choose from:

    * `titleText` - The text in the first row.
    * `subtitleText` - The text in the second row.
    * `titleSize` - The size of the first row text.
    * `subtitleSize` - The size of the second row text.
    * `strokeWidth` - The size of the outter line surrounding the view.
    * `fillRadius` - The radius of the inner circle.
    * `titleColor` - The color of the first row text.
    * `subtitleColor` - The color of the second row text.
    * `fillColor` - The color of the inner circle.
    * `strokeColor` - The color of the outter line circle.
    * `backgroundColor` - the color between the circle and the stroke.
    
Example
=======
```xml
 <com.github.circleView
        xmlns:app="http://schemas.android.com/apk/res-auto"
        android:layout_width="300dp"
        android:layout_height="300dp"
        app:titleText="title"
        app:subtitleText="subtitle"
        app:titleSize="12dp"
        app:subtitleSize="9dp"
        app:titleColor="@color/white"
        app:subtitleColor="@color/red"
        app:strokeColor="@color/blue"
        app:backgroundColor="@color/gray"
        app:fillColor="@color/green"
        app:fillRadius="0.9"
        app:strokeWidth="5"/>
```

Credits
=======
Author : Pavlos-Petros Tournaris (p.tournaris@gmail.com)

Google+ : [+Pavlos-Petros Tournaris](https://plus.google.com/u/0/+PavlosPetrosTournaris/)

Thanks to [+Filip Puđak](https://plus.google.com/u/0/117550349320705739707/) and his ProgressPieView that made me think of this View.

License
=======

    Copyright 2014 Pavlos-Petros Tournaris

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
       http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
