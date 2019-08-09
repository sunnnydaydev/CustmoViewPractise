### 自定义view练习
- 贝塞尔曲线 - 波浪线

- 仿小米手机的秒表

 效果预览：
 ![image](https://github.com/sunnnydaydev/CustmoViewPractise/raw/master/picture/wave.gif)
 
 ![image](https://github.com/sunnnydaydev/CustmoViewPractise/raw/master/picture/MiStopWatch.gif)

###### 1、波浪线实现图解：
 ![image](https://github.com/sunnnydaydev/CustmoViewPractise/raw/master/picture/WaveView.png)
 
 ###### 2、秒表分析
 （1）画数字（12-3-6-9）
 
 （2）画数字之间四段圆弧
 
 （3）画一定角度的暗亮渐变色（此处实现根据实现效果图不易看出）
 
 （4）画一圈刻度线（画一条线，画布旋转一圈即可）
 
 （5）画秒针（图中的三角形）
 
 （6）画分针、时针。
 
 （7）draw通知重新绘制（由于秒针、分钟、时针的刻度都是当前时间，所以不断重绘，他们就不断指向当前时间出现动态效果）




