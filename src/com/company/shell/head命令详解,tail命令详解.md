当要查看上千行的大文件时,我们可不会用cat命令把整个文件内容给打印出来，
相反，我们可能只需要看文件的一小部分地内容（例如文件的前十行和后十行）
,我们也有可能需要打印出来前n行或后n行,也有可能打印除了前n行或后n行之
外的所有行，也有可能需要实时监控log日志的更新，那么怎么实现呢？下面一
起来看一下linux下使用率极高的head ,tail两个命令。


首先，输入head --help查看帮助信息:

    amosli@amosli-pc:~/learn/fd$ head --help
    Usage: head [OPTION]... [FILE]...
    Print the first 10 lines of each FILE to standard output.
    With more than one FILE, precede each with a header giving the file name.
    With no FILE, or when FILE is -, read standard input.
    
    Mandatory arguments to long options are mandatory for short options too.
      -c, --bytes=[-]K         print the first K bytes of each file;
                                 with the leading `-', print all but the last
                                 K bytes of each file
      -n, --lines=[-]K         print the first K lines instead of the first 10;
                                 with the leading `-', print all but the last
                                 K lines of each file
      -q, --quiet, --silent    never print headers giving file names
      -v, --verbose            always print headers giving file names
          --help     display this help and exit
          --version  output version information and exit
    
    K may have a multiplier suffix:
    b 512, kB 1000, K 1024, MB 1000*1000, M 1024*1024,
    GB 1000*1000*1000, G 1024*1024*1024, and so on for T, P, E, Z, Y.

head命令的语法格式为:

    head [OPTION]... [FILE]...

接下来将在实例中讲解各参数含义及用法：

实例：
1.使用head命令查看文件内容前十行
新建test.txt,共14行.

    amosli@amosli-pc:~/learn/fd$ cat -n test.txt 
         1    a
         2    b
         3    c
         4    d
         5    e
         6    f
         7    g
         8    h
         9    i
        10    j
        11    k
        12    l
        13    m
        14    n
        
2.-n参数，显示test.txt文件的前3行

    amosli@amosli-pc:~/learn/fd$ head -n 3 test.txt 
    a
    b
    c
    英文提示信息：
    
      -n, --lines=[-]K         print the first K lines instead of the first 10;                   
             
3.-n参数显示除了文件最后3行外的所有内容
             
     amosli@amosli-pc:~/learn/fd$ head -n -3 test.txt 
     a
     b
     c
     d
     e
     f
     g
     h
     i
     j
     k
     
     英文提示信息：
     
       -n, --lines=[-]K         print the first K lines instead of the first 10;
                                  with the leading `-', print all but the last
                                  K lines of each file      

加上'-',打印所有内容除了最后的K行。

4.-c参数，按文件内容大小来打印，打印前2个字节的内容

    amosli@amosli-pc:~/learn/fd$ head -c 2 test.txt 
    a
    2个字节就是一个“a”字母。
    
    英文提示信息：
    
      -c, --bytes=[-]K         print the first K bytes of each file;
      
5.-c参数，打印除了最后2个字节的文件内容
      
    amosli@amosli-pc:~/learn/fd$ head -c -2 test.txt 
    a
    b
    c
    d
    e
    f
    g
    h
    i
    j
    k
    l
    m
    
    英文提示信息：
    
      -c, --bytes=[-]K         print the first K bytes of each file;
                                 with the leading `-', print all but the last
                                 K bytes of each file

6.-q参数，打印时不显示文件名称

    amosli@amosli-pc:~/learn/fd$ head -q test.txt 
    a
    b
    c
    d
    e
    f
    g
    h
    i
    j
    
    英文提示信息：
    
      -q, --quiet, --silent    never print headers giving file names
    其实后面跟上--quiet,--silent都是一样的，都不会显示文件名称，和默认打印是一样的效果。
    
7.-v参数，打印是显示文件名称

    amosli@amosli-pc:~/learn/fd$ head -v test.txt 
    ==> test.txt <==
    a
    b
    c
    d
    e
    f
    g
    h
    i
    j
    英文提示信息：
    
      -v, --verbose            always print headers giving file names
    其中，用--verbose和-v显示的是一样的效果
    
    amosli@amosli-pc:~/learn/fd$ head --verbose test.txt 
    ==> test.txt <==
    a
    b
    c
    d
    e
    f
    g
    h
    i
    j

8.打印多个文件的内容
amosli@amosli-pc:~/learn/fd$ head -n 3 test.txt test2.txt 
==> test.txt <==
a
b
c

==> test2.txt <==
c
d
e

    
## 二、tail命令详解
   
tail命令和head 命令非常相似，只不过它是打印文件的尾部内容的，
当然也有一些特色之处,下面一起来看看吧。

首先，输入tail --help看一下提示信息
    
    amosli@amosli-pc:~/learn/fd$ tail --help 
    Usage: tail [OPTION]... [FILE]...
    Print the last 10 lines of each FILE to standard output.
    With more than one FILE, precede each with a header giving the file name.
    With no FILE, or when FILE is -, read standard input.
    
    
    Mandatory arguments to long options are mandatory for short options too.
      -c, --bytes=K            output the last K bytes; alternatively, use -c +K
                               to output bytes starting with the Kth of each file
      -f, --follow[={name|descriptor}]
                               output appended data as the file grows;
                               -f, --follow, and --follow=descriptor are
                               equivalent
      -F                       same as --follow=name --retry
      -n, --lines=K            output the last K lines, instead of the last 10;
                               or use -n +K to output lines starting with the Kth
          --max-unchanged-stats=N
                               with --follow=name, reopen a FILE which has not
                               changed size after N (default 5) iterations
                               to see if it has been unlinked or renamed
                               (this is the usual case of rotated log files).
                               With inotify, this option is rarely useful.
          --pid=PID            with -f, terminate after process ID, PID dies
      -q, --quiet, --silent    never output headers giving file names
          --retry              keep trying to open a file even when it is or
                                 becomes inaccessible; useful when following by
                                 name, i.e., with --follow=name
      -s, --sleep-interval=N   with -f, sleep for approximately N seconds
                                 (default 1.0) between iterations.
                                 With inotify and --pid=P, check process P at
                                 least once every N seconds.
      -v, --verbose            always output headers giving file names
          --help     display this help and exit
          --version  output version information and exit
    
    If the first character of K (the number of bytes or lines) is a `+',
    print beginning with the Kth item from the start of each file, otherwise,
    print the last K items in the file.  K may have a multiplier suffix:
    b 512, kB 1000, K 1024, MB 1000*1000, M 1024*1024,
    GB 1000*1000*1000, G 1024*1024*1024, and so on for T, P, E, Z, Y.
    
    With --follow (-f), tail defaults to following the file descriptor, which
    means that even if a tail'ed file is renamed, tail will continue to track
    its end.  This default behavior is not desirable when you really want to
    track the actual name of the file, not the file descriptor (e.g., log
    rotation).  Use --follow=name in that case.  That causes tail to track the
    named file in a way that accommodates renaming, removal and creation.