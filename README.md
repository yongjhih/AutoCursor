# AutoCursor

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-AutoCursor-green.svg?style=flat)](https://android-arsenal.com/details/1/2138)
[![Download](https://api.bintray.com/packages/yongjhih/maven/AutoCursor/images/download.svg) ](https://bintray.com/yongjhih/maven/AutoCursor/_latestVersion)
[![JitPack](https://img.shields.io/github/tag/yongjhih/AutoCursor.svg?label=JitPack)](https://jitpack.io/#yongjhih/AutoCursor)
[![Build Status](https://travis-ci.org/yongjhih/AutoCursor.svg)](https://travis-ci.org/yongjhih/AutoCursor)
[![Join the chat at https://gitter.im/yongjhih/AutoCursor](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/yongjhih/AutoCursor?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

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
    String data = cursor.getLong(cursor.getColumnIndex(DATA));
    image.setData(data);
    String bucketDisplayName = cursor.getLong(cursor.getColumnIndex(BUCKET_DISPLAY_NAME));
    image.setBucketDisplayName(bucketDisplayName);
    // ...

    images.add(image);
}
```

```java
public class Image {
    Long id;
    String data;
    String bucketDisplayName;

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
buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath 'com.neenbedankt.gradle.plugins:android-apt:1.4'
    }
}

apply plugin: 'com.neenbedankt.android-apt'

repositories {
    jcenter()
}

dependencies {
    compile 'com.infstory:auto-cursor:1.0.0'
    apt 'com.infstory:auto-cursor-processor:1.0.0'
}
```

via jitpack.io:

```gradle
buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath 'com.neenbedankt.gradle.plugins:android-apt:1.4'
    }
}

apply plugin: 'com.neenbedankt.android-apt'

repositories {
    maven {
        url "https://jitpack.io"
    }
}

dependencies {
    compile 'com.github.yongjhih.AutoCursor:auto-cursor:-SNAPSHOT'
    apt 'com.github.yongjhih.AutoCursor:auto-cursor-processor:-SNAPSHOT'
}
```

## Sample

[yongjhih/RxMediaStore](https://github.com/yongjhih/RxMediaStore):

* https://github.com/yongjhih/RxMediaStore/blob/master/rx-media-store/src/main/java/android/provider/Image.java
* https://github.com/yongjhih/RxMediaStore/blob/master/rx-media-store/src/main/java/rx/observables/MediaStoreObservable.java

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
