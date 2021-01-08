
## Tenth Line
Given a text file file.txt,print just the
10th line of the file.

## Example

Assume that file.txt has the following content:

    Line 1
    Line 2 
    Line 3
    Line 4
    Line 5
    Line 6
    Line 7
    Line 8
    Line 9
    Line 10

Your script should output the tenth line,
which is :
    
    Line 10
    
Note:
1. If the file contains less than 10 lines, 
    what should you output?
2. There's at least three different solutions. 
    Try to explore all possibilities. 
    
    
## 结题思路

引用 : http://www.cnblogs.com/grandyang/p/5376902.html    

这道题让我们用Bash脚本来打印一个txt文件的第十行，可以用很多种方法
来实现，我们先来看一种是用awk来实现的方法，awk是强大的文本分析工具，
具有流控制、数学运算、进程控制、内置的变量和函数、循环和判断的功能，
具体可以参见[这个帖子](http://blog.51cto.com/tanxin/1222140)。其中NR表示行数，$0表示当前记录，所以我们可以
用if来判断行数为第十行时，将内容打印出来即可：
    
### 解法一:

    awk '{if(NR ==10) print $0}' file.txt 
    
我们也可以用更简洁的写法来打印出第十行如下:    

### 解法二:

    awk 'NR == 10' file.txt
    
我们也可以使用流编辑工具sed来做,关于sed的讲解可以参见[这个帖子](http://blog.51cto.com/tanxin/1208944)    
-n默认表示打印所有行,p限定了具体打印的行数:

### 解法三

    sed -n 10p file.txt
    
我们也可以使用tail和head关键字来打印,关于tail和head的用法详解请
参见[http://blog.51cto.com/tanxin/1208944]    

我们也可以使用tail和head关键字来打印,关于tail和head的用法详解请
参见[这个帖子](http://www.it610.com/article/1196291.htm)
其中head表示从头开始打印,tail表示从结尾开始打印,-n表示根据文件
行数进行打印,一些区别于联系请见下列例子:
    
    tail -n 3 file.txt: 打印file文件的最后三行内容
    
    tail -n +3 file.txt: 从file文件第三行开始打印所有内容
    
    head -n 3 file.txt: 打印file文件的前三行
    
    head -n -3 file.txt: 打印file文件除了最后三行的所有内容
    
至于竖杠|为管道命令,讲解参见[这个帖子](http://www.programgo.com/article/6462705832/;jsessionid=01A7179C41368EACBD963D21018878B2) ,   
用法:command1 | command2 他的功能是把第一个命令command1执行
的结果作为command2的输入传给command2.了解了这些知识,那么下面一行
命令就很好理解了,首先输入file文件从第十行开始的所有内容,然后将输出
内容的第一行打印出来即为第十行:

解法四:

    tail -n +10 file.txt | head -n 1
    
下面这种方法跟上面刚好相反,先输出file文件的前十行,然后从输出的
第十行开始打印,那么也能正好打印第十行的内容:

解法五:

    head -n 10 file.txt | tail -n +10    
    



    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
