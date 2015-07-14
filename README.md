# AutoCursor

![](art/auto-cursor.png)

Convert Cursor to model by annotations.

## Usage

Before:

```java
List<Image> images = new ArrayList<>();

while (cursor.moveToNext()) {
    Image image = new Image();
    
    Long id = cursor.getLong(cursor.getColumnIndex(_ID));
    image.setId(id);
    
    // ...
    
    images.add(id);
}
```

```java
public class Image {
    Long id;

    public void setId(Long id) {
        this.id = id;
    }

    // ...
}
```

After:

```java
List<Image> images = new ArrayList<>();

while (cursor.moveToNext()) {
    images.add(Image.create(cursor));
}
```

```java
@AutoCursor
public abstract class Image implements Parcelable {
    @Nullable
    @AutoCursor.Column(name = _ID)
    public abstract Long id();

    @Nullable
    @AutoCursor.Column(name = DATA)
    public abstract String data();
 
    @Nullable
    @AutoCursor.Column(name = BUCKET_DISPLAY_NAME)
    public String bucketDisplayName();

    @Nullable
    @AutoCursor.Column(name = BUCKET_ID)
    public Long bucketId();

    // ...

    public static Image create(Cursor cursor) {
        return new AutoCursor_Image(cursor);
    }
}
```

## Installation

via jcenter:

```gradle
repositories {
    jcenter()
}

dependencies {
    compile 'com.infstory:auto-cursor:1.0.0'
}
```

via jitpack.io:

```gradle
repositories {
    maven {
        url "https://jitpack.io"
    }
}

dependencies {
    compile 'com.github.yongjhih.AutoCursor:auto-cursor:1.0.0'
}
```

## License

```
Copyright 2015 8tory, Inc.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
