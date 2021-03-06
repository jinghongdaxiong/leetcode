想必很多刚接触Linux尤其是在redhat环境下,大家都见过rpm -qa | sort
之类的命令,但是并不知道|是一个什么符号.
管道符号,是unix功能强大的一个地方,符号是一个竖线:"|",   
用法:command1 | command2他的功能是把第一个命令command1
执行的结果作为command2的输入传给command2:例如 

    
    root@wl-MS-7673:/home/wl/桌面/shell# cat test.txt   
    123  
    456  
    789  
    567  
    875  
    421  
      
    root@wl-MS-7673:/home/wl/桌面/shell# cat test.txt | sort  
      
    123  
    421  
    456  
    567  
    789  

把test文件中的内容通过管道传递给sort进行排序~

## 在Linux中,管道连接着一个命令的标准输出和另一个命令的标准输入.

我们应该知道我们有的时候会用到类似于“>”,“<”之类的重定位
的SHELL语言完成特定的工作

而管道是进程中类似于重定位用法的一种进程之间的通信过程

让我们考虑一下非常常见的 ls 命令。 ls 有许多可用的选项，
但是如果目录的内容卷动速度快得你无法查看时该怎么办呢？ 
比如/etc目录的文件就不是一屏可以显示完的
当然ls可能有适当的参数来完成我们需要的功能-------分屏显示
但是,如果我们使用管道命令的话更方便也更直观
使用以下命令来查看 /etc 目录的内容： 

    ls -al /etc 

你怎么样才能在输出卷过屏幕之前仔细查看它们呢？ 

方法之一是把输出用管道导入到一个叫做 less 的程序工具。
 less 是一个分页显示文件的工具工具，它允许你一页一页
 （或一个屏幕一个屏幕）地查看信息。 
 
    使用竖线（ | ）来把输出用管道导入到命令中。 
    
    ls -al /etc | less
    
现在，你就可以一个屏幕一个屏幕地查看 /etc 目录的内容了。
要向前移动一个屏幕，按 [Space] 键；要向后移动一个屏幕，
按[b]键；要退出，按 [q] 键。使用 less 命令时，你还可以
使用箭头键来前后移动。要使用 less 来搜索文本文件的输出，
按 [/] ，然后键入你想在文件内搜索的内容。譬如：/Linux  
推举另一个应用
要更仔细地阅读启动消息，在 shell 提示下，键入 dmesg | less 。
你将能够一个屏幕一个屏幕地阅读该文件。使用箭头键来前后翻阅文件。
要搜索文件输出，按 [/] 并键入搜索内容。
管道还可以用来只打印一个文件中的某些行。键入： 
grep coffee sneakers.txt | lpr 
这将会打印 sneakers.txt 文件中提到“coffee”这个词的每一行.

下面，我们遇到一个新的问题，我们不仅仅想要看看/dev目录下面
有哪些文件，我们还希望用一个文件来纪录刚才ls的命令显示的所
有结果，难道需要用手工输入？不需要，
我们介绍两个管道符号：“>;”和“>;>;”，也就是大于符号啦。 

    　　ls /dev >; filenames.txt 
还有一个管道符号是由两个大于号组成的“>;>;”，
它和刚才“>;”的区别在于一个大于符号的管道是用来创建一个
新的文件filenames.txt，如果已经有了同名的文件就复盖
掉以前的内容；而两个大于符号的管道是用来在已经存在的文件
后面追加新的内容，如果没有这个文件就创建它。 

现在我们vi filenames.txt看看一共有多少行，是不是1000多行啊？
我们运行 

    　　ls /dev >;>; filenames.txt 

再来vi filenames.txt看看，是不是一共2000多行啦？ 


其实这两个管道符号很容易记忆，向左边的箭头意思就是把前
面命令的内容输入到后面的文件中去，刚才那个“|”管道符号
是用来把前面命令的输出结果当作后面一个命令的输入数据。
下面我们看一个和刚才“>;>;”刚刚相反的管道符号“<<”： 

    cat >;>; friends << "EOF" 
    　　>; a 
    　　>; b 
    　　>; c 
    　　>; EOF 
    
运行第一行以后，意思就是把我们接下来的输入内容保存到friends文件里面，
当我们输入“EOF”的时候结束。“<<”的意思就是向前面的命令传送一个数据。     

运行第一行以后，意思就是把我们接下来的输入内容保存到friends文件里面，
当我们输入“EOF”的时候结束。“<<”的意思就是向前面的命令传送一个数据。 


中间我象征性的输入了a b c三行，每一行前面的“>;”是系统自动产生的提示
符号。第四行输入“EOF”系统就保存刚才输入的a b c三行到一个叫做friends
的文件里面去。

现在我们cat friends看看是不是刚才的输入成功的保存了。怎么样，同样的命
令，使用不同的管道符号，即可以显示文件内容，也可以用来创建文件，这就是管
道符号的强大。












