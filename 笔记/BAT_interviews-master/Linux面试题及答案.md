# Linux程序设计
   * [2 shell程序设计](#2-shell程序设计)
      * [1 什么使shell](1-什么是shell)
      * [2 管道和重定向]()
      * [3 shell的语法]()
         * [1 变量]()
         * [2 条件]()
         * [3 控制结构]()
         * [4 函数]()
         * [5 命令]()
         * [6 命令的执行]()
         * [7 here文档 ]()
      * [1 Linux文件结构]()
         * [1 目录]()
         * [2 文件和设备]()

      * [2 系统调用和设备驱动程序]()
      * [3 库函数]()

      * [4 底层文件访问]()
      * [5 标准呢I/O库]()
      * [6 格式化输入和输出]()
      * [7 文件和目录结构]()
      * [8 扫描目录]()
      * [9 错误处理]()
      * [10 /proc文件系统]()
      * [4 Linux环境]()
      * [1 程序参数]()
      * [2 环境变量]()
      * [3 时间和日期]()
      * []()
      * []()


      1.2.1 linux程序
      （1）Linux应用程序表现为2种特殊类型的文件：
      A）可执行文件：计算机可以直接运行的文件。
      B）脚本文件：一组指令的集合，这些指令将由另一个程序（解释器）来执行。
      （2）登陆Linux系统时，与一个shell程序进行交互，它在一组给定目录路径下按照给出的程序名搜索与之同名的文件，搜索的目录存储在shell变量PATH里，搜索路径由系统管理员配置，它通常包含一些存储系统程序的标准路径：
      A）/bin，采用与存放启动系统时用到的二进制文件。
      B）/usr/bin：用于存放用户使用的标准程序。
      C）/usr/local/bin：用于存放特定软件安装的程序。
      D）/opt：可选的操作系统组件和第三方应用。
      E）用 echo $PATH 查询PATH。
      1.2.2 C语言编译器
      ./hello，指示shell去执行当前目录下给定名称的程序。如果不包含./，且PATH变量不包含指向你的主目录的条目，shell就找不到hello程序。
      1.2.3 开发系统导引
      应用程序
      一般在usr/local目录结构下编译、运行自己的程序，因为它分离了厂商提供及后续添加的文件与系统本身提供的应用程序。
      头文件
      （1）用头文件来提供对常量的定义和对系统及库函数调用的声明，几乎都在usr/include目录及其子目录下。
      （2）用-I标志来包含保存在子目录或非标准位置中的include文件，它指示编译器不仅在标准位置，也在该目录下查找fred.c包含的头文件。
      gcc -I usr/openwin/include fred.c
      库文件
      （1）库是一组预先编译好的函数的集合，这些函数都是按照可重用的原则编写的，它们通常由一组相互关联的函数组成并执行某项常见的任务。
      （2）标准库文件一般存储在/lib或/usr/lib目录中，C语言编译器需要知道要搜索哪些库文件，仅把库文件放在标准目录中，然后希望编译器找到它是不够的，库文件必须遵循特定的命名规则并且要在命令行中明确指定。
      （3）库文件的名字以lib开头，.a代表传统的静态库，.so代表共享函数库。
      静态库
      （1）使用ar程序创建一个归档文件并把目标文件添加进去。
      ar crv libfoo.a bill.o fred.o
      要想成功地使用函数库，还需要为函数库生成一个内容表
      ranlib libfoo.a
      现在函数库就可以使用了
      gcc - c program.o program.c
      gcc -o program program.o libfoo.a
      （2）查看目标文件、函数库或可执行文件中包含的函数，我们可用nm命令，可以看到libfoo.a中包含fred和bill两个函数，而program里只包含函数bill。创建程序时，它只包含函数库中它实际需要的函数，虽然程序中的头文件包含函数库中所有函数的声明，但这并不将函数库包含在最终的程序中。
      共享库
      （1）静态库的一个缺点是，当我们运行许多应用程序并且它们都使用来自同一个函数库的函数时，就会在内存中有同一个函数的多份拷贝，在程序文件本身中也有多份同样的拷贝，这将消耗大量宝贵的内存和磁盘空间。
      （2）程序使用共享库时的链接方式：它本身不再包含函数代码，而是引用运行时可访问的共享代码。当编译好的程序被装载到内存中执行时，函数引用被解析并产生对共享库的调用，如果有必要共享库才被加载到内存中。
      （3）通过这种方法，系统可只保留共享库的一份拷贝并供许多应用程序同时使用，并且在磁盘上也仅保存一份，另一个好处是共享库的更新可以独立于依赖它的应用程序。

      第2章 shell程序设计
      2.3 什么是shell
      （1）Shell是一个作为用户与内核接口的程序，它允许用户向操作系统输入需要执行的命令。
      （2）Bash：是GUN系统上默认的shell。
      （3）实际上Shell是一个命令解释器，它允许由用户输入的命令并且把它们送到内核，它能够轻松的调用其他程序并对它们的输出进行处理，这种能力使得shell成为完成文本和文件处理任务的一个理想工具。
      2.4 管道和重定向
      2.4.1 重定向输入
      >，把ls命令的输出保存到lsoutput.txt中。
      >>，将其输出附加到指定文件的尾部。
      2.4.2 重定向输入
      2.4.3 管道
      （1）用管道操作符|来连接进程，在linux下通过管道连接的进程可以同时运行，并且随着数据流在它们之间的传递可以自动地进行协调。
      （2）如果你有一系列的命令需要执行，相应的输出文件是在这一组命令被创建的同时立刻被创建或写入的，所以不要在命令流中重复使用相同的文件名。
      2.6 shell的语法
      2.6.1 变量
      使用引号
      （1）$得到变量的内容
      （2）双引号不影响转换
      （3）单引号和反斜线不进行变量的替换
      （4）echo 输出到控制台
      （5）read 将用户的输入赋值给变量
      环境变量


      $*受到IFS的影响，而$@不受。
      2.6.2 条件
      if test -f fred.c 或者 if [ -f fred.c ]，注意要有空格
      2.6.3 控制结构
      if 语句

      elif 语句

      for 语句

      while语句

      （1）注意赋值时候不可以加空格。（2）括号的使用$(())。
      until 语句

      直到条件为真，否则一直执行
      case 语句

      （1）用|来合并多个条件。（2）每个条件使用双分号;;来结束。
      命令列表（AND，OR）



      2.6.4 函数
      2.6.5 命令
      break命令
      ：命令
      空命令，用于简化逻辑条件

      continue命令
      使for、while、until;循环跳到下一次循环继续执行时
      .命令
      用来执行当前shell中的命令。

      通常，当一个脚本执行一条外部命令或脚本程序时，会创建一个新的环境（一个子shell），命令将在这个新的环境中执行，在命令执行完毕后，这个环境被丢弃，只留下退出码返回给父shell，而外部的source命令和点命令在执行某个脚本程序中所列出的命令时，使用的是调用该脚本程序的同一个shell。
      通常被调用命令对环境变量做出的任何改变都会丢失，而点命令允许执行的命令改变当前环境。
      echo命令
      去掉换行符的echo命令：

      eval命令
      允许对参数进行求值，类似于一个额外的$，它给出一个变量的值的值。

      exec命令
      将当前shell替换为一个不同的程序，因此脚本程序exec命令后的代码都不会执行。

      exit n命令
      使脚本以退出码n结束运行，如果在任何一个交互式shell的命令提示符中使用这个命令，它就会让你退出系统。如果你允许自己的脚本程序在退出时不指定一个退出状态，那么该脚本中最后一条被执行的指令的状态将被最为返回值。
      export命令
      Export命令将作为它参数的变量导出到子shell中，并使之在子shell中有效。在默认情况下，在一个shell中被创建的变量在这个shell调用的下级（子）shell中是不可用的。

      expr命令
      expr命令将它的参数当作一个表达式来求解。

      printf

      return 命令
      使函数返回，如果没有指定参数，return命令默认返回最后一条指令的退出码。
      set命令
      Set命令的作用是为shell设置参数变量。许多命令的输出结果是以空格分隔的值，如果需要使用输出结果的某个域（例如$1,$2,..），这个命令就十分有用。

      shift命令
      shift把所有参数变量左移一个位置，使$2变为$1，$3变为$2，$1的值被丢弃。在扫描处理脚本程序的参数时经常用到shift命令。

      trap命令
      用于指定在接收到信号后将要采取的操作，trap命令的参数分为2部分，前一部分是接收到指定信号时将要采取的操作，后一部分是要处理的信号名。


      unset命令
      从环境变量中删除变量或函数，这个命令不能删除shell本身定义的只读变量（如IFS）

      find
      功能是查找文件，find [path] [options] [tests] [action]

      （1）Option

      （2）Tests

      （3）Action

      grep
      grep在文件中搜索字符串，事实上，一种非常常见的用法是在使用find命令时，将grep作为参数传递给-exec的一条命令。
      grep [options] PATTERN [FILES]

      （1）Options

      正则表达式

      2.6.6 命令的执行
      （1）$(commend)：执行一条命令并把该命令的输出放到一个变量中。
      （2）$((..))，算术扩展：

      （3）参数扩展：

      （4）多参数处理问题

      2.6.7 here文档
      在shell脚本程序中向一跳命令传递输入的一种特殊方法是使用here文档，它允许一条命令在执行时就好像是在读取一个文件或键盘一样，而实际上是从脚本程序中输入数据。

      第3章
      3.1 Linux文件结构
      （1）Linux环境中的文件为操作系统服务和设备提供了一个简单而同一的接口，在linux中，一切都是文件，这就意味着，程序完全可以像使用文件那样使用磁盘文件、串行口、打印机和其他设备。
      3.1.1 目录
      （1）文件，除了本身包含的内容外，还会有“管理信息”，例如文件的创建/修改日期和它的访问权限。这些属性被保存在文件的inode数据结构中，它是文件系统中的特殊的数据块，它同时还包含文件的长度和文件在磁盘上的位置。
      （2）目录是文件，它用来保存其他文件的节点号和名字，目录文件中的每个数据项都指向某个文件的节点，删除文件名就等于删除与之对应的连接。
      3.1.2 文件和设备
      （1）硬件设备在linux操作系统中通常也可被表示为文件。
      （2）Linux中比较重要的设备文件：
      A)/dev/console：系统控制台，错误消息和诊断消息通常会被发送到这个设备。
      B)/dev/tty：如果一个进程有控制的话，那么特殊文件/dev/tty就是这个控制终端（键盘或显示屏）的别名。
      C)/dev/null：空设备，所有写向这个设备的输出都将被丢弃，而读这个设备会立刻返回一个文件尾标志。
      （3）设备可分为字符设备和块设备，两者的区别在于访问设备时是否要一次读写一整块。块设备是那些支持随机文件系统存取的设备，例如硬盘。
      3.2 系统调用和设备驱动程序
      （1）用很少量的函数就可以对文件和设备进行访问和控制，这些函数被称为系统调用，它们也是通向操作系统本身的接口。
      （2）操作系统的核心部分，即内核，是一些对系统硬件进行控制的底层接口。为了向用户提供一个统一的接口，设备驱动程序封装了所有与硬件相关的特性，硬件的特有功能可通过ioctl调用来完成。
      （3）/dev目录中的设备文件的用法是一致的，它们都可以被打开、读、写和关闭，系统调用ioctl用来提供一些与特定硬件设备有关的必要控制（与正常输入输出相反），所有它的用法随设备的不同而不同，此外，每个驱动程序都定义了它自己的一组ioctl命令。

      3.3 库函数
      （1）在输入输出操作中，直接使用底层系统调用效率非常低。
      A）系统调用会影响系统的性能，在执行系统调用时，linux必须从用户代码切换到内核代码运行，然后再返回内核代码。
      B）硬件会对底层系统调用一次所能读写的数据块做出一定的限制。
      （2）为了给设备和磁盘文件提供更高层的接口，linux提供了一系列的标准函数库，它们是一些由函数构成的集合，你可以把它们包括在自己的程序中取处理那些与设备和文件有关的问题。
      3.4 底层文件访问
      （1）每个运行中的程序称为进程，它有一些与之关联的文件描述符，你可以通过它们访问打开的设备或文件。
      （2）当开始运行程序时，一般会有3个已经打开的文件描述符：标准输入（0）、输出（1）、错误（2）。
      （3）通过系统调用open把其他文件描述符与文件和设备关联起来。
      （4）包含头文件，unistd.h和stdlib.h
      3.4.1 write系统调用

      （1）返回实际写入的字节数，返回0表示未写入任何数据，如果-1则表示write调用出现了错误。
      （2）当程序退出时，所有已打开的文件描述符都会自动关闭。
      3.4.2 read系统调用

      （1）返回实际读入的字节数，返回0表示未读到任何数据且到达了文件尾，如果是-1则表示read调用出现了错误。
      （2）用管道和重定向为程序提供输入。

      3.4.3 open系统调用

      （1）open建立了一条到文件或设备的访问路径，如果操作成功，它将返回一个文件描述符，这个文件描述符是唯一的，它不会与任何其它运行中的进程共享。如果两个程序同时打开同一个文件，会得到两个不同的文件描述符。
      （2）Open调用失败时返回-1，并设置全局变量errno以指明失败的原因，新文件描述符总是使用未用描述符的最小值。
      （3）Oflags参数是通过主要文件访问模式与其他可选模式的结合来指定的，必须指定的访问模式。

      （4）oflags使用O_CREAT标志的open来创建文件时，必须使用有三个参数形式的open调用。
      （5）对文件的访问权限产生影响：
      A）只有在创建文件时才会指定访问权限。
      B）Open调用里给出的模式值将与当时的用户掩码的反值做AND操作，因此open调用中的标志实际上是设置文件访问权限的要求，所请求的权限是否被设置取决于当时umask的值。
      （6）umask：3个八进制数字，主要做并不能阻止一个程序或用户在今后使用chmod命令来添加其他用户的写权限，但它确实能帮助用户，使它们不必对每个新文件都去检查和设置其访问权限。

      close系统调用

      （1）close调用终止一个文件描述符fildes与其关联的文件之间的关联，文件描述符被释放并能够重新利用，close调用成功则返回0，出错则返回-1。
      ioctl系统调用
      （1）ioctl提供了一个用于控制设备及其描述行为和配置底层服务的接口，终端、文件描述符、套接字甚至磁带都可以有为它们定义的ioctl。Iostl的原型：

      （2）unistd.h必须最早出现，因为它根据POSIX规范定义的标志可能会影响到其他的头文件。
      3.4.5 其他与文件管理有关的系统调用
      lseek系统调用
      对文件描述符fildes的读写指针进行设置，可以用来设置文件的下一个读写位置。读写指针既可设置为文件中的某个绝对位置，也可以把它设置为相对于当前位置或文件尾的某个相对位置。
      所有打开的文件都有一个当前位置偏移量，读写通常会使CFO增大，文件打开时，CFO被初始化为0，除非使用了O_APPEND。

      fstat/stat/lstat系统调用
      （1）fstat：返回与打开的文件描述符相关的文件的状态信息。
      （2）stat与lstat：返回通过文件名查到的状态信息，但当文件是一个符号链接时，lstat返回的是该符号链接本身的信息，而stat返回的是该链接指向的文件的信息。
      dup和dup2系统调用
      （1）Dup系统调用提供了复制文件描述符的一般方法，使我们可以通过两个或更多个不同的描述符来访问同一文件。
      （2）这可以用与在文件的不同位置对数据进行读写，当我们通过管道在多个进程间通信时，这些调用也很有用。
      3.5 标准I/O库
      标准I.O库及其头文件stdio.h为底层系统调用提供了一个通用的接口，这个库已经成为ANSI标准C的一部分，而我们前面见到的系统调用却还不是。
      在标准I/O库中，与底层文件描述符对应的对等物叫做流，它实现为指向FILE的指针。
      在启动程序时，有三个文件流是自动打开的，它们是stdin、stdout和stderr。
      在接下来的内容中，将学习标准I/O库中的以下库函数：
      FILE* fopen(const char* filename, const char *mode)	成功时返回非空的FILE*指针，失败时返回NULL值，NULL值的定义在头文件stdlio.h中
      size_t fread(void * ptr, size_t size, size_t nitems, FILE *stream)	size指定每个数据记录的长度，nitems给出要传输的记录个数，返回值为成功读到数据缓冲区中的记录个数
      size_t fwrite(void* ptr, size_t size, size_t nitems, FILE *stream)	从指定的缓冲区中取出数据，并把它们写到输出流中
      int fclose(FILE* stream)	关闭指定的文件流，使所有尚未写出的数据都写出。因为stdio库会对数据进行缓冲，所以使用fclose可以确保数据已经全部写出。
      int fflush(FILE* stream)	把文件流里的所有未写出数据立刻写出
      int fseek(FILE* stream, long int offset, int whence)	与lseek等价的文件流函数
      int fgetc(FILE* stream)
      int getc(FILE* stream)
      int gerchar()	从文件流里取出下一个字节并把它作为一个字符返回，当到达文件尾或出现错误时，返回EOF，必须通过ferror或feof来区分这2种情况。
      gerchar()从标准输入中读取下一个字符。
      int fputc(int c, FILE* stream)
      int putc(int c, FILE* stream)
      int putchar(int c)	把一个字符写到一个输出文件流中，它返回写入的值；如果失败，则返回EOF。
      char* fgets(char* s, int n, FILE* stream)
      char* gets(char *s)
      （1）成功完成：返回一个指向字符串s的指针。
      （2）文件流到达文件尾，fgets会设置这个文件流的EOF标识并返回一个空指针。
      （3）出现错误：fgets返回一个空指针并设置errno给出错误的类型。
      （4）gets函数从标准输入读取数据并丢弃遇到的换行符，它在接收字符串的尾部加上一个NULL字节。	从输入文件流stream里读取一个字符串，它把读到的字符写到s指向的字符串里，知道出现下面几种情况：遇到换行符、已经传输了n-1个字符、或者指向文件尾。
      3.6 格式化输入和输出
      3.6.1
      int printf(const char* format,...)
      int sprintf(char *s, const char* format,..)
      int frpintf(FILE* stream, const char* format,..)
      int scanf(const char* format,..)
      Int sscanf(const char*s,cont char* format,..)
      Int fscanf(FILE* stream, const char* format,..)
      3.6.3 文件流错误
      （1）许多stdio库函数会返回一个超出范围的值，比如空指针或EOF常熟，此时，错误由外部变量errno指出。许多函数都可能会改变errno的值，因此必须在函数表明失败后立刻对其进行检查，在使用它之前，应该总是将它拷贝到另一个变量中。

      （2）通过检查文件流的状态

      ferror：测试一个文件流的错误标识，标识被设置返回非零值，否则返回0。
      feof：测试一个文件流的文件尾标识。
      clearerr：清除由stream指定的文件流的文件尾标识和错误标识。
      3.7 文件和目录维护
      int chmod(const char*path, mode_t mode)	改变文件或目录的访问权限，除非给予程序适当的特权，否则只有文件的属主或超级用户可以修改它的权限。
      int chown(const char* path, uid_t owner, gid_t group)	改变一个文件的属主，如果已经设置了适当的权限，文件的属主和所属组都会改变。
      int unlink(const char* path)
      int link(const char*path1, comst char* path2 )
      int symlink(const char* path1, const char* path2)	（1）unlink删除一个文件的目录项并减少它的链接数，如果文件的链接数减少到0，并且没有进程打开它，这个文件就会被删除。Rm使用的就是这个系统调用。
      （2）先open创建一个文件，然后对其调用unlink是某些程序员用来创建临时文件的技巧。
      （3）link创建一个已有文件path1的链接，新目录数据项由path2给出。
      Int mkdir(const char* path, modt_t mode)
      Int rmdir(const char* path)	只有在目录为空时才行
      Int chdir(const char*path)
      Int *getcwd(char* buf, size_t size)	切换目录；
      确定自己当前工作目录；

      3.8 扫描目录
      DIR *opendir(const char* name)	打开一个目录并建立目录流
      struct dirent *readdir(DIR* dirp)	（1）指针指向的结构里保存着目录流drip中下一个目录项的有关资料，后续的readdir调用将返回后续的目录项。
      （2）dirent:文件的inode节点号，文件的名字。
      long int telldir(DIR* dirp)	一个目录流的当前位置
      void seekdir(DIR* dirp, long int loc)	设置目录流dirp的目录项指针
      int closedir(DIR* dirp)	关闭一个目录流，并释放与之相关的资源
      3.9 错误处理
      （1）错误代码errno的取值在errno.h里。
      char* strerror(int errnum)	把错误编码映射为一个字符串，该字符串对发生的错误类型进行说明
      void perror(const char* s)	把error变量中报告的当前错误映射到一个字符串，并把它输出到标准错误输出流。
      3.10 /proc文件系统
      （1）linux将一切事物看作文件，硬件设备在文件系统中也有相应的条目。/dev目录中的文件使用底层系统调用这样一种特殊方式来访问硬件。
      （2）Linux提供了一种特殊的文件系统procfs，它通常表现为/proc目录，该目录包含了许多特殊文件以允许对驱动和内核信息进行高层访问。只要应用程序有正确的访问权限，它们就可以通过读写这些文件来获得信息或设置参数。
      （3）/proc目录中的文件会随系统的不同而不同。

      第4章 linux环境
      4.1 程序参数
      （1）int main(int args, char* argv[])
      argc是程序参数的个数，argv是代表参数自身的字符串数组。
      （2）main()，因为默认的返回值类型是int，函数中不用的形式参数不需要说明。argc和argv仍然存在，但如果不声明它们，就不能使用它们。
      （3）Shell接收用户输入的命令行，将命令分解成单词，然后把这些单词放入argv数组。
      （4）getopt：用来分析命令行参数。
      （5）getopt_long：接受以短横线--开始的所谓长参数。
      4.2 环境变量
      （1）Linux的变量分为环境变量和本地变量。
      （2）环境变量存在于所有的shell中，在登陆系统的时候就已经有了相应的系统定义的环境变量了，linux的环境变量具有继承性，即子shell会继承父shell的环境变量。
      （3）本地变量，当前shell中的变量，本地变量中的非环境变量不具有继承性。
      （4）环境变量
      A)显示：env
      B)设置：export variable-name = value
      C)清除：unset variable-name
      （5）本地变量
      A）显示：set
      B）设置：set variable-name = value
      C）清除：unset variable-name
      （6）当登入系统时，linux会读取环境变量
      A）/etc/profile：此文件为系统环境变量，它为每个用户设置环境信息，当用户第一次登陆时，该文件被执行，并从/etc/profile.d目录的配置文件中搜集shell的设置。
      B）/etc/bashrc：每个使用bash的用户在登陆以后执行完/etc/profiled中的内容后都会执行此文件，在新开一个bash的时候也会执行此文件
      C）~/.bash_profile：每个用户都可使用该文件输入专用于自己使用的shell信息，当用户登陆时，该文件仅仅执行一次。
      D）~/.bashrc：该文件包含专用于单人的bash shell的bash信息，当登录时以及每次打开一个新的shell时，该文件被读取。
      E）~/.bash_logout：当每次退出bash shell时，执行该文件。
      4.3 时间和日期
      4.4 临时文件
      4.5 用户信息
      4.6 主机信息
      4.7 日志
      4.8 资源和限制

      第7章 数据管理
      7.1 内存管理
      （1）除了特殊的嵌入式程序外，linux程序绝不允许直接访问物理内存。
      （2）Linux为应用程序提供了一个简洁的试图：
      A）它能反映一个巨大的可直接寻址的内存空间。
      B）Linux还提供了内存保护机制，它避免了不同的应用程序之间的互相干扰。
      C）如果机器被正确配置，并且有足够的交换空间，linux允许应用程序访问比实际物理内存更大的内存空间。
      7.1.1 简单的内存分配
      （1）void* malloc(size_t size)
      （2）Malloc函数返回的是一个void*指针，因此我们需要将它通过类型转换。Malloc函数可以保证其返回的内存是地址对齐的，所以它返回的指针可以转换为任何类型。
      （3）Malloc函数无法保证返回连续的内存空间。
      7.1.2 分配大量的内存
      （1）刚开始时，内核能简单地通过使用空闲的物理内存来满足应用程序对内存的要求，但是当物理内存耗尽时，它便会使用所谓的交换空间（在安装系统时分配的独立的硬盘空间），linux的交换空间中没有局部堆、全局或可丢弃代码段等需要在代码中操心的内容，linux内核会为你完成这些管理工作。
      （2）Linux实现了一个“请求分页的虚拟内存系统”，在程序使用的物理内存地址上并不真实地存在，linux将所有的内存都以页划分，通常每页的大小为4096字节，每当程序试图访问内存时，就会发生虚拟内存到物理内存的转换。当所访问的内存再物理上并不存在时，就会产生一个页面错误，并将控制权转给内核。
      （3）Linux内核会对访问的内存地址进行检查，如果这个地址对于程序来说是合法可用的，内核就会确定需要程序提供哪一个物理内存页面。
      如果该页面之前未被写入，就直接分配它。
      如果它已经被保存在硬盘的交换空间上，就读取包含数据的内存页面（在硬盘的交换空间上）到物理内存（内存），接着，在完成虚拟内存到物理地址的映射之后，内核允许用户程序继续运行。
      （5）当应用程序耗尽了所有的物理内存和交换空间，或者栈超出了其最大长度时，内核将拒绝此后的内核申请并可能提前终止程序。
      （6）动态内存一个常见的问题是试图在分配的内存块之后写数据，这可能造成覆盖了malloc函数库例程内部的一些数据。
      7.1.3 内存的滥用

      每个在linux系统中运行的程序都能看到属于自己的内存映像，不同的程序看到的内存映像不同，只有操作才知道物理内存是如何安排的，它不仅为用户程序管理内存，同时也为用户程序提供隔离保护。
      7.1.4 空指针

      对于空指针，允许读操作返回null，但不允许写操作。

      在零地址处，读写操作都不允许。
      7.1.5 内存的释放
      void free(void* ptr_to_free)
      动态使用内存的程序应该总是用free调用来把不同的内存释放给malloc内存管理器，这样做可以将分散的块重新合并到一起，并由malloc函数库来管理它。
      7.2 文件锁定
      （1）linux提供了多种特性来实现文件锁定
      A）以原子操作的方式创建锁文件，这就给程序提供了一种方式来确保它所创建的文件是唯一的，而且这个文件不可能被其它程序在同一时间创建。
      B）允许锁定文件的一部分，从而可以独享对这一部分内容的访问。
      7.2.1 创建锁文件
      （1）锁文件仅仅是充当一个指示器的角色，程序间需要通过相互协作来使用它们。锁文件只是一个建议性锁，与此独立的是强制性所。
      （2）需要调用带了O_CREAT和O_EXCL标志的open系统调用，这使我们能以一个原子操作完成两项工作：确定文件不存在，然后创建它。
      （3）进入临界区和退出临界区：
      File_desc = open(lock_file, O_RDWR | O_CREAT | O_EXCL, 0444)  //为-1则说明文件已存在
      (void)close(file_desc); //关闭文件描述符
      (void)unlink(lock_file); //删除锁文件
      7.2.2 锁定区域
      （1）用创建锁文件的方法来控制诸如串行口之类的资源独占式访问是不错的选择，但它不适用于访问大型的共享文件。
      （2）通过文件中的锁定区域来解决，文件的一部分被锁定，但其它程序可以访问这个文件的其他部分。
      （3）函数原型：int fcntl(int fildes, int command, struct flock *flock_structure)
      （4）共享锁：许多不同的进程可以拥有文件同一（或者重叠）区域上的共享锁，只要任一进程拥有一把共享锁，那么就没有进程可以再获得该区域上的独占锁，以“读”或“读/写”方式打开。
      （5）独占锁：只有一个进程可以在文件的任一部分拥有一把独占锁，一旦一个进程拥有了这样一把锁，任何其它进程在这个区域上都无法获得任何类型的锁，以“写”或“读/写”方式打开。
      Conmmand
      A）F_GETLK：获得fildes打开的文件的锁信息，它不会尝试去锁定文件。调用进程把自己想创建的锁类型信息和F_GETLK命令作为参数传递给fildes，它返回的信息告诉调用进程哪些因素会阻止锁。
      B）F_SETLK：加锁成功返回非-1值。
      C）F_SETLKW：在无法获取锁时，这个调用将等待直到可以为止。
      Struct flock
      A）short l_type：F_RDLCK（共享锁）/F_UNLOCK（解锁）/F_WRLOCK（独占锁）
      B）short l_whence：SEEK_SET/SEEK_CUR/SEEK_END
      C）off_t l_start：该区域的第一个字节
      D）off_t l_len：区域字节个数
      E）pid_t l_pid：持有锁的进程（一般在请求锁时将其设为-1，再根据返回值来判断在这个区域能否加锁）

      7.2.6 死锁
      （1）假设两个程序希望更新同一个文件，它们同时更新字节1和字节2，程序A首先更新字节2，而程序B首先更新字节1。两个程序同时启动，程序A锁定字节2而程序B锁定字节1，然后程序A尝试锁定字节1，但因为这个字节已被程序B锁定，所以程序A将在那里等待。同样程序B也在那里等待。
      （2）这种两个程序都无法继续执行下去的情况，称为死锁。
      第8章 mySQL
      （1）创建数据库
      creat database databse-name
      （2）删除数据库
      drop database database-name
      （3）创建新表
      creat table table-name( col1 type1 [null/not null] [auto_increment] [primary_key], col2 ... )
      （4）删除新表
      drop table table-name
      （5）增加一个列
      alter table table-name add column col type
      （6）创建索引
      creat [unique]
      （7）删除索引
      drop index idxname
      （8）常用操作
      选择：select * from table-name where 范围
      插入：insert into table-name values (value1,value2)
      删除：delete from table-name where 范围
      更新：update table-name set field1=value
      查找：select * from table-name where field1 like ‘%value%’
      排序：select * from table-name order by field1,field2 [des]
      总数：select count as totalcount from table-name
      求和：select sum(fileld1) as sumvalue from table-name
      最大：select max(field1) as maxvalue from table-name
      最小：select min(field1) as minvalue from table-name
      平均：select avg(field1) as avgvalue from table-name
      （9）分组：Group by
      第11章 进程和信号
      11.1 什么是进程？
      （1）一个其中运行着一个或多个线程的地址空间和这些线程所需要的资源。
      （2）Linux允许许多用户同时访问系统，每个用户可以同时运行许多个程序，甚至同时运行同一个程序的许多个实例，系统本身也运行着一些管理资源和控制用户访问的程序。
      （3）正在运行的程序或进程由程序代码、数据、变量（占用着系统的内存）、打开的文件（文件描述符）和环境组成。
      （4）Linux会在进程之间共享程序代码和系统函数库，所以在任何时刻内存中都只有代码的一份拷贝。
      11.2 进程的结构

      （1）PID：每个进程都会被分配一个唯一的数字编号，其范围是2到32768，当进程被启动时，系统将按顺序选择下一个未被使用的数组作为PID，而数字1一般是为init保留的。
      （2）Grep将要执行的程序保存在一个磁盘文件中。正常情况下，linux进程不能对用来存放程序代码的内存区域进行写操作，但它可以被多个进程安全地共享。
      （3）系统函数也可以共享，令包含可执行程序grep的磁盘文件容量比较小，因为它不含共享函数库代码。
      （4）并不是程序在运行时的所有东西都可被共享，例如：
      A）进程使用的变量，进程有自己的栈空间，用于保存函数中的局部变量和控制函数的调用与返回。
      B）环境空间，包含专门为这个进程建立的环境变量。
      C）程序计数器，用来记录它执行到的位置，即在执行线程中的位置，在使用线程时，进程有不止一个执行线程。
      11.2.1 进程表
      （1）Linux进程表就像一个数据结构，它把加载在内存中的所有进程的有关信息保存在一个表中，其中包括进程的PID，进程的状态，命令字符串和其他一些ps命令输出的各类信息。操作系统能通过进程的PID对它们进行管理。
      11.2.2 查看进程
      （1）TTY：显示了进程是从哪一个终端启动的；TIME：进程迄今为止所占用的CPU时间；CMD：显示启动进程所使用的命令。
      （2）Ps默认情况下只显示与终端、主控台，串行口或伪终端保持连接的进行的信息，-a选项查看所有的进程，-f选项显示进程完整的信息。
      11.2.3 系统进程
      （1）一般而言，每个进程都是由另一个我们称之为“父进程”的进程启动的，被父进程启动的进程叫做子进程。LINUX程序启动时，它将运行一个名为init的进程，该进程是系统运行的第一个进程，你可以把init进程看作为操作系统的进程管理器，它是其它所有进程的祖先进程。如果父进程不存在了，则显示的就是init的进程id。
      （2）启动新进程并等待它们结束的能力是整个系统的基础，我们将在本章的后面看到如何从自己的程序中用系统调用fork，exec和wait来完成同样的任务。
      11.2.4 进程调度
      （1）查看正在执行的进程：ps -ax
      （2）状态R表示这个程序不是在等待其他进程结束或等待输入输出操作来完成。
      （3）Linux内核用进程调度器来决定下一个时间片应该分给哪个进程，它的判断依据是进程的优先级，优先级高的进程运行得更为频繁，而优先级低的后台任务运行的就不是那么频繁。在linux中，进程运行时间不可能超过分配给它们的时间片，它们采用的是抢先式多任务处理，所以进程的挂起和继续运行无需彼此之间的协作。
      （4）在linux这样的多任务系统中，多个程序可能竞争使用同一资源，这种情况下，执行短期的突发性工作并暂停运行以等待输入的程序，要比持续占用处理器以进行计算或不断轮训系统以查看是否有新的输入到达的程序更好。
      （5）系统根据进程的nice值来决定它的优先级，一个进程nice值默认为0，长期不间断运行的程序的优先级一般比较低，而暂停以等待输入的程序会得到奖励。这可以保证与用户进行交互的程序保持及时的响应性。
      11.3 启动新进程

      （1）system函数的作用：运行以字符串参数的形式传递给它的命令并等待该命令的完成。
      （2）“ps -ax” 与 “ps -ax &”的区别：前一个必须等待由system函数启动的线程结束后才能继续，而后一个shell的命令是在一个后台运行程序的请求，所以ps程序一启动shell就返回了。
      （3）一般来说，启动system函数远非是启动其它进程的理想手段，因为它必须用一个shell来启动需要的程序，由于在启动程序之前需要先启动一个shell，而且对shell的安装情况及使用的环境的以来也很大。
      11.3.1 替换进程映像

      （1）一个exec函数可以把当前进程替换为一个新进程，新进程由path或file指定。
      （2）前三个的参数是可变的，参数以一个空指针结束，而execv和evecvp的第二个参数是一个字符串数组，不管哪种情况，新程序在启动时会把argv数组中给定的参数传递给main函数。
      （3）以字符p结尾的函数通过搜索PATH环境变量来查找新程序的可执行文件路径，如果可执行文件不在PATH定义的路径中，需要使用绝对路径传递参数。
      （4）全局变量environ可用来把一个值传递到新的程序环境中，此外，函数execle和execve可以通过额外的参数envp传递字符串数组作为新程序的环境变量。
      （5）运行中的程序开始执行exec调用中指定的代码，新进程的PID，PPID和nice值与原先的完全一样。
      （6）一般情况下，exec是不会返回的，除非发生了错误。
      （7）由exec启动的新进程继承了原进程的许多特征，特别是，在原进程中已打开的文件描述符在新进程中仍然打开，任何在原进程中打开的目录流都将被关闭。
      11.3.2 复制进程映像

      （1）要想让进程同时执行多个函数，我们可以使用线程或从原程序中创建一个完全分离的进程。
      （2）我们可以调用fork()创建一个新进程，这个系统调用复制当前进程（包括指令、变量值、程序调用栈，环境变量、缓冲区等），在进程表中创建一个新的表项，新表项中的许多属性与当前进程是相同的，新进程几乎与原进程一模一样，执行的代码也完全相全，但新进程有自己的数据空间、环境和文件描述符。
      （3）一次调用，两次返回。在父进程中返回一个新的PID，在子进程中返回0。
      11.3.3 等待一个进程

      （1）在父进程中调用wait函数让父进程等待子进程的结束，wait将暂停父进程的直到它的子进程结束为止，这个调用返回子进程的PID，如果stat_loc不是空指针，则状态信息就被写入它所指向的位置。
      11.3.4 僵尸进程
      （1）子进程终止时，它与父进程之间的关联还会保持，直到父进程也意外地终止或父进程调用wait才告结束。因此，进程表中代表子进程的表项不会被立即释放，虽然子进程已经不再运行，但它仍存在于系统当中，因为它的退出码还需要保存起来以备父进程今后的wait调用。这时它将成为一个僵尸进程。
      （2）在子进程结束之后父进程结束之前，如果父进程异常终止，子进程将自动把PID为1的进程设为自己的父进程。僵尸进程将一直保留在进程表中直到被init进程发现并释放，进程表越大，这一过程就越缓慢。应该尽量避免产生僵尸进程，因为在Init清理它们之前，它们将一直消耗着系统的资源。
      （3）让父进程周期性地检查某个特定的子进程是否已经终止。

      11.3.5 输入和输出重定向

      （1）freopen函数先关闭标准输入，然后将文件流stdin与参数给定的文件名关联起来，借来下，它调用execl来用upper程序替换掉正在运行的进程代码，因为已打开的文件描述符会在execl调用之后保留下来，所有upper程序的运行情况和它在shell提示符下的运行情况完全相同。
      11.3.6 线程
      （1）涉及线程的编程时比较困难的，但是它在某些应用软件中又有很大的用处（如多线程数据库服务器）。Linux中的线程都是非常轻量级的，而且编写多个互相协作的进程比编写线程要容易很多。
      11.4 信号
      （1）信号是linux和unix响应某些条件而常生一个事件，接收到该信号的进程会采取一些行动。
      （2）信号是由于某些错误条件而产生的，如内存段冲突、浮点处理器错误或非法指令等，它们由shell和终端管理器生成以引起中断，它们还可以作为在进程间传递消息或修改行为的一种方式，明确地由一个进程发送给另一个进程。
      （3）如果进程接收到这些信号中的一个，但事先没有安排捕获它，进程就会终止。通常，系统将生成核心转储文件core，并将其放在当前目录下。该文件是进程在内存中的映像，它对程序的调试很有用处。
      （4）常用的信号：
      A）SIGCHIL（子进程已经停止或终止）：对于管理子进程很有用，它是默认被忽略的。
      B）SIGCONT（继续执行暂停进程）：shell脚本通过它对作业进行控制，但用户程序很少使用它。
      C）SIGINT（终端中断）：在键盘上敲入终端字符，就会向前台进程（即当前正在运行的进程）发送SIGINT信号，这将引起该程序的终止，除非它事先安排了捕获这个信号。
      D）如果想发送一个信号给非前台进程，就需要使用kill命令。
      E）Killall命令允许我们发送信号给运行着同一命令的所有进程。
      signal

      Sig：准备捕获或忽略信号。
      Func：接收到指定的信号后将要调用的函数，或选择SIG_IGN，SIG_DEL。
      返回值：先前用来处理这个信号的函数。
      （5）在信号处理程序中，调用如printf这样的函数是不安全的，一个有用的技巧是，在信号处理程序中设置一个标识，然后在主程序中检查该标识。
      14.4.1 发送信号
      kill

      （1）要想发送一个信号，两个进程必须拥有相同的用户ID，超级用户可以发送信号给任何进程。
      Alarm

      在预定时间后，发送一个SIGALARM信号。将seconds重新置为0将取消所有已设置的闹钟请求，如果在接收到SIGALRM信号后再次调用alarm函数，则闹钟重新开始计时。
      pause()
      在安排好捕获信号后暂停运行，直到收到一个信号为止。这意味着程序不需要总是在执行着，程序不必在一个循环中无休止地检查某个时间是否已发生，相反，它可以等待时间的发生。
      14.4.2 健壮的信号接口
      sigaction()

      Sig：要捕获的信号。
      act：在该参数中设置对指定信号的动作。
      oact：把原先对该信号的动作写到它指向的位置。
      Sigaction结构体：

      14.4.3 信号集

      （1）Sigismember(sigset_t *set, int signo)：判断一个给定的信号是否是一个信号集的成员。
      （2）Sigprocmask(int how, const sigset_t *set, sigset_t *oset)：根据how指定的方法对进程的信号屏蔽字进行修改。
      （3）Sigpending(sigset_t *set)：查看它阻塞的信号中有哪些正停留在待处理状态。
      （4）Sigsuspend(const sigset_t *sigmask)：挂起自己的进程，直到信号集中的一个信号到达为止。

      第12章 POSIX线程
      12.1 什么是线程
      （1）一个程序中的多个执行线路叫做线程，更准确的定义是：线程是一个进程内部的一个控制序列。
      （2）fork系统调用和创建新线程之间的区别
      A）fork调用后的新进程拥有自己的变量和PID，它的时间调度也是独立的，它的执行通常完全独立于父进程。
      B）创建一个新线程时，新线程讲拥有自己的栈（因此有自己的局部变量），但与它的创建者共享全局变量，文件描述符，信号句柄和当前目录状态。
      （3）线程的优点和缺点：
      A）有时，让程序看起来好像是在同时做两件事是非常有用的。例如，在编辑的同时对文档中的单词个数进行实时的统计或者是多线程的数据库服务器，如果用多进程的方式来完成将很难做到高效率，因为各个不同的进程需要紧密合作才能满足加锁的和数据的一致性。
      B）如果一个进程在任一时刻最多只能做一件事的话，线程可以让它在等待数据连接之类的事情的同时做一些其他有用的事情。
      C）线程之间的切换需要操作系统做的工作要比进程之间的切换少很多，因此多个线程对资源的需求要远小于多个进程。
      （3）线程也有一些缺点：
      A）编写多线程程序需要非常仔细的设计，在多线程程序中，因时序上的细微偏差或无意造成变量共享而引发错误的潜在可能性是很大的。
      B）调试困难，因为线程之间的交互非常难于控制。
      C）将大量计算分成2个部分，并将这两个部分作为两个不同的线程来运行的程序在一台单处理器机器上并不一定运行得更快。
      12.2 第一个线程程序
      （1）在一个多线程程序里，只有一个errno变量供所有的线程共享。
      （2）类似的问题还存在于fputs之类的函数中，这些函数通常用一个单独的全局性区域来缓存输出数据。
      （3）可重入的例程：
      A）函数可中断。
      B）除了使用自己栈上的变量以外，不依赖于任何函数。
      C）如果需要访问全局变量，一定要注意互斥手段。
      （4）本章用到的函数在成功时返回0，在错误时返回相应的错误代码。

      （1）a_thread：线程被创建时，这个指针指向的变量中将被写入一个标识符，用该标识符来引用新的进程。
      （2）Thread_function：线程将要启动的函数。
      （3）Message：传递给函数的参数。

      终止调用它的线程并返回一个指向某个对象的指针。

      等待指定的进程终止后返回，返回值由pthread_exit()确定。
      12.4 同步
      （1）控制线程执行和访问临界代码区。
      （2）信号量：如同看守一段代码的看门人。
      （3）互斥量：保护代码段的一个互斥设备。
      （4）互斥量：控制任一时刻只能有一个线程可以访问一些共享内存。
      （5）计数信号量：控制对一组相同对象的访问，比如从五条可用的电话线中分配一条给某个线程。
      （6）互斥量和信号量的区别：
      A）互斥量用于线程的互斥，信号量用于线程的同步。
      这是互斥量和信号量的根本区别，也就是互斥和同步之间的区别。
      互斥：是指同一资源同时只允许一个访问者对其进行访问，具有唯一性和排它性，但互斥无法限制访问者对资源的访问顺序，即访问时无序的。
      同步：是指在互斥的基础上，通过其它机制实现访问者对资源的有序访问。在大多数情况下，同步已经实现了互斥，特别是所有写入资源的情况必定是互斥的，少数情况是指可以允许多个访问者同时访问资源。
      B）互斥量只能为0/1，信号量可以为非负整数。
      也就是说，一个互斥量只能用于一个资源的互斥访问，它不能实现多个资源的多线程互斥问题。信号量可以实现多个同类资源的多线程互斥和同步，当信号量为单值信号量时，也可以完成一个资源的互斥访问。
      12.5 线程的属性
      （1）线程属性标识符：pthread_attr_t。
      （2）线程属性结构：

      （3）属性及其相关的值：
      A）线程的作用域：进程域、系统域。
      B）线程的绑定状态：非绑定状态、绑定状态。
      C）线程的分离状态：非分离状态、分离状态。
      D）线程的优先级。
      E）线程的栈地址。
      F）线程的栈大小。
      G）线程的栈保护区大小。
      H）线程的调度策略：SCHED_FIFO/SCHED_RR/SCHED_OTHER。
      I）线程并行级别。
      12.6 取消一个线程
      （1）请求一个线程终止的函数：

      （2）线程设置自己的取消状态：
      两种选择：PTHREAD_CANCEL_ENABLE/PTHREAD_CANCEL_DISABLE

      （3）如果取消请求被接受了，则进入第2个控制层次：
      A）PTHREAD_CANCEL_ASYCHRONOUS：使得在接收到取消请求消息后立即采取行动。
      B）PTHREAD_CANCEL_DEFERRRD：的在接收到取消请求后，一直等待执行了下述函数之一才开始行动。
      12.7 多线程

      第13章 进程间通信：管道
      13.1 什么是管道
      （1）当一个进程连接数据流到另一个进程时，我们使用术语管道。我们通常是把一个进程的输出管道连接到另一个进程的输入。
      （2）Shell命令： cmd1 | cmd2
      A）cmd1的标准输入来自终端键盘。
      B）cmd1的标准输出传递到cmd2，作为它的标准输入。
      C）cmd2的标准输出连接到屏幕终端。
      13.2 进程管道
      popen() 和 pclose()

      （1）popen允许一个程序将另一个程序作为新进程来启动，并可以传递数据给它或通过它接收数据。Command字符串是要运行的程序名和相应的参数，open_mode必须是”w”或”r”。
      （2）”r”：被调用程序的输出就可以被调用程序使用，调用程序利用返回的文件流指针，通过fread来读取被调用程序的输出。
      ”w”：调用程序用fwrite向被调用命令发送数据，而被调用程序可以在自己的标准输入上读取这些数据。
      （3）pclose关闭与之关联的文件流，plcose调用只在popen启动的进程结束后才返回。
      如何实现popen()
      （1）请求popen调用运行一个程序时，它首先启动shell，即系统中的sh命令，然后将command字符串作为一个参数传递给它，这有两个效果，一个好，一个不好。
      A）所有的参数扩展都是由shell来完成的，它允许我们通过popen启动非常复杂的shell命令。
      B）针对每个popen调用，不仅要启动一个被请求的程序，还要启动一个shell，即每个popen将启动两个进程。
      13.4 pipe调用
      （1）底层的pipe函数，通过这个函数在两个程序之间传递数据不需要启动一个shell来解释请求的命令。

      pipe函数的参数是一个由两个整数类型的文件描述符组成的数组的指针，该函数在数组中填上两个新的文件描述符后返回0，如果失败返回-1。
      （2）写到file_descriptor[1]的所有数据都可以从file_descriptor[0]读回来，数据基于先进先出的原则。
      （3）当用fork调用创建新进程时，原先打开的文件描述符仍然保持打开状态，如果在原先的进程中创建一个管道，然后再调用fork创建新进程，我们即可通过管道在两个进程之间传递数据。
      13.5 父进程和子进程
      （1）如何在子进程中运行一个与其父进程完全不同的程序。我们用exec来完成这一工作，并将文件描述符作为一个参数传递给用exec启动的程序。

      13.5.1 管道关闭后的退出
      （1）当没有数据可读时，read调用通常会阻塞，即它暂停进程以等待直到有数据到达为止，如果管道的另一端已被关闭，则read调用将会返回0而不是阻塞。
      （2）如果我们跨越fork调用使用管道，就会有两个不同的文件描述符可以用于向管道写数据，分别在父进程和子进程中，只有把父子进程中的针对管道的写文件描述符都关闭，对管道的read调用才会失败。
      13.5.2 把管道用作标准输入和输出操作
      （1）dup调用创建新的文件描述符，这与open调用有些相似，不同的是dup调用创建的新文件描述符与作为它的参数的那个已有文件描述符指向同一文件（或管道）。

      13.6 命名管道FIFO
      （1）希望在不相关的进程之间交换数据。
      （2）命名管道是一种特殊类型的文件，它在文件系统中以文件名的形式存在。
      （3）可以像删除一个普通文件那样用命令rm，或者在程序中用unlink系统调用。

      13.6.1 访问FIFO文件
      使用open打开FIFO文件
      （1）打开FIFO的一个主要限制是，程序不能以O_RDWR模式打开FIFO文件进行操作。
      （2）打开FIFO文件的另一点区别，对open_flag的O_NONBLOCK选项的用法，使用这个选项不仅会改变open调用的处理方式，还会改变对这次open调用返回的文件描述符进行的读写请求的处理方式。
      A）O_RDONLY：open调用将阻塞，除非有一个进程以写方式打开同一个FIFO。
      B）O_RDONLY|O_NONBLOCK：即使没有其他进程以写方式打开FIFO，这个open调用也会立即返回。
      C）O_WRONLY：open调用将阻塞，除非有一个进程以读方式打开同一个FIFO。
      D）O_WRONLY|O_NONBLCOK：这个调用总是立刻返回，但如果没有进程以读方式打开FIFO文件，open调用将返回错误-1并且FIFO也不会打开。
      （3）使用O_NONBLCOK模式会影响到FIFO的read和write调用。
      A）对一个空的，阻塞的FIFO的read调用将等待，直到有数据可以读时才继续进行。
      B）对一个空的，非阻塞的FIFO的read调用将立刻返回0字节。
      C）对一个完全阻塞FIFO的write调用将等待，直到数据可以被写入时才继续进行，如果写入的数据长度小于PIPE_BUF，那么或者写入全部字节，或者一个字节都不写入。（如果有几个不同的程序尝试同时向FIFO写数据，要是能保证所有的写请求是发往一个阻塞的FIFO的，并且每个写请求的数据长度小于等于PIPE_BUF字节，系统就可以确保数据不会交错在一起）
      D）如果FIFO被设置为非阻塞模式，它将按下面的规则：
      如果请求写入的数据的长度小于PIPE_BUF字节，调用失败，数据不能写入。
      如果请求写入的数据的长度大于PIPE_BUF字节，将写入部分数据，返回实际写入的字节数，返回值也可能是0。

      第14章 信号量、共享内存和消息队列
      信号量：用于管理对资源的访问。
      共享内存：用于在程序之间高效地共享数据。
      消息队列：在程序之间传递数据的一种简单方法。
      14.1 信号量
      （1）当临界区可用时，信号量变量sv的值是true，然后P操作将它减1，使他变为false以表示临界区正在使用；当进程离开临界区时，使用V操作将它加1，使临界区域再次变为可用。注意，简单地用一个普通变量进行类似的加减法时不行的，因为在C/C++或几乎任何一个传统的编程语言中，都没有一个原子操作可以满足检测变量是否为true，如果是再将该变量设为false的操作，这也是信号量如此特殊的原因。
      （2）创建一个新信号量或取得一个已有信号量的键。

      （3）直接控制信号量的信息（用来初始化信号量和删除信号量）

      （4）改变信号量的值（实现P/V操作）

      sembuf结构：

      14.2 共享内存
      （1）共享内存为多个进程之间共享和传递数据提供了一种有效的方式。由于它并未提供同步机制，所以我们通常需要用其他的机制来同步对共享内存的访问。一种典型的应用时，我们用共享内存来提供对大块内存区域的有效访问，同时通过传递小消息来同步对该内存的访问。
      （2）共享内存是由IPC为进程创建的一个特殊的地址范围，它将出现在该进程的地址空间中。其他进程可以将同一段共享内存连接到它们自己的地址空间中。所有进程都可以访问共享内存中的地址，如果一个进程向共享内存写入了数据，所做的改动将立刻被可以访问同一段共享内存的任何进程看到。
      （3）创建共享内存

      （4）第一次创建共享内存段时，它不能被任何进程访问。要想启用对该共享内存的访问，必须将其连接到一个进程的地址空间中，这项工作由shmat函数来完成。

      （5）shmdt：将共享内存从当前进程中分离。
      （6）共享内存的控制函数（用来删除共享内存）

      14.3 消息队列
      （1）消息队列提供了一种在两个不相关进程之间传递数据的相当简单而有效的方法。
      （2）消息队列提供了一种从一个进程向另一个进程发送一个数据块的方法。而且，每个数据块都被认为含有一个类型，接收进程可以独立地接收含有不同类型的数据块。
      （3）创建和访问一个消息队列

      （4）把一条消息添加到消息队列中

      （5）消息的格式

      （6）从一个消息队列中取出消息

      （7）消息队列的控制函数



      Linux文件系统
      （1）	Linux文件系统是一个对复杂系统进行抽象化的有趣例子。通过使用一组通用的API函数，Linux可以在许多存储设备上支持许多文件系统。例如，read函数调用可以从指定的文件描述符读取一定数量的字节，但read函数不了解文件系统的类型。
      （2）	文件系统是对一个存储设备上的数据和元数据进行组织的机制，由于存在多种类型，因此Linux将文件系统接口实现为分层的体系结构，从而将用户接口层、文件系统实现和操作存储设备的驱动程序分隔开。
      （3）	在Linux中将一个文件系统与一个存储设备关联起来的过程称为挂装。使用mount命令将一个文件系统附着到当前系统中（根）。在执行挂装时，要提供文件系统类型、文件系统和一个挂装点。
      高层体系结构：用户空间和内核中与文件系统相关的主要组件

      A）	用户空间包含一些应用程序（例如，文件系统的使用者）和GUN C库，它们为文件系统调用（打开，读写和关闭）提供用户接口。
      B）	系统调用接口的作用就像是交换器，它将系统调用从用户空间发送到内核空间。
      C）	VFS是底层文件系统的主要接口。这个组件导出一组接口，然后将它们抽象到各个文件系统，各个文件系统的行为可能差异很大。有2个针对文件系统对象的缓存（inode和dentry）。它们缓存最近使用过的文件系统对象。
      D）	每个文件系统实现（ext2，JFS）导出一组通用接口，供VFS使用。
      E）	缓冲区缓存会缓存文件系统和相关块设备之间的请求。例如，对底层设备驱动程序的读写请求会通过缓冲区来传递。这就允许在其中缓存请求，减少访问物理设备的次数，加快访问速度，以最近使用列表（LRU）的形式管理缓冲区缓存。
      VFS
      （1）	虚拟文件系统为应用程序员提供一层抽象，屏蔽底层各种文件系统的差异。
      （2）	VFS支持的文件系统可以归结为3类：基于磁盘的文件系统（Ext2/Ext3），网络文件系统（NFS），特殊文件系统（proc/sysfs）。
      （3）	Linux以一组通用对象的角度看待所有文件系统，这些对象是超级块，inode，dentry和文件。
      A）	超级块在每个文件系统的根上，超级块描述和维护文件系统的状态，包含文件系统名称，文件系统的大小和状态，块设备的引用和元数据信息（比如空闲列表等）。超级块通常存储在存储媒体上。
      超级块中的一个重要元素是超级块操作的定义（super_operations），这个结构定义一组用来管理这个文件系统中的inode的函数。
      B）	文件系统中管理的每个对象（文件或目录）在Linux中表示为一个inode，它具有唯一资源标识符，inode包含管理文件系统中的对象所需的所有元数据（包括可以在对象上执行的操作）。
      C）	dentry用来实现名称和inode之间映射，有一个目录原来缓存最近使用的dentry。dentry还维护目录和文件之间的关系，从而支持在文件系统中移动。
      D）	VFS文件表示一个打开的文件（保存打开的文件的状态，比如写偏移量）。
      （4）Linux VFS的强大扩展能力，正是因为这种通用文件模型的设计。新支持的文件系统，只需要将自己的结构转换成这种通用的模型即可插入到Linux中。
