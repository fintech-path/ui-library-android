# ObfuscatedTextView

![](https://img.shields.io/badge/version-1.0.0-green)
![](https://img.shields.io/badge/author-hcxc-green)

## 项目介绍

这是一个可以让你显示不同格式掩码的小工具。可以通过设置开始位置、结束位置以及掩码字符来自定义不同的掩码模板。效果可以下图。

[English](./obfuscated_text_view.md)

## 效果图

<img width="150"  src="screenshot/ScreenShot_ObfuscatedTextView.png"/>

## 下载 demo

#### [点我下载](./output/demo.apk)

#### [点我下载aar文件](./output/ObfuscatedEditText.aar)

## 版本说明

--

## 技术文档

### 1.基本用法

这个功能主要是在 TransformationMethod 接口上实现的，所以你只需要通过 builder 创建并设置到你的 textview 或者 editview 就可以实现这个功能了。

```kotlin
//example:
var method  = ObfuscatedTransformationMethodBuilder(start, end)
    .obfuscatedPatternCharacter(char).build()
textView.transformationMethod = method
```

设置需要混淆的开始索引和结束索引。并且还可以设置自定义字符作为混淆标记，例如“&”或“#”。 如果未设置，则默认使用“\*”。

<img width="150"  src="screenshot/ScreenShot_ObfuscatedTextView_sub.jpg"/>

同时我们还提供了一个带开关的 widget，可以在混淆后的文本和原文本之间切换。在布局文件中添加它。

```xml
//example:
<com.adastragrp.mobile.common.ui.view.ObfuscatedEditLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:obfuscateIcon="@drawable/xxxxxxx"
/>
```

### 2.各种疑难问题的描述，及其解决方案

注意返回的文本必须和源文本的长度完全相同。并且如果您对字符串进行了一些文本格式化操作（例如添加分隔符），请调整混淆开始位置和混淆结束位置。

## License

ObfuscatedTextView is available under the Apache 2.0 license. See the LICENSE file for more info.
