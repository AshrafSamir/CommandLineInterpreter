CLI should prompt the user to enter the input through the keyboard. After a sequence of characters is entered followed by a return, the string is parsed and the indicated command(s) executed. The user is then again prompted for another command.
The program implements some built-in commands; the list of required commands is listed below. This means that the program must implement these commands directly by using the system calls that implement them. Do not use exec to implement any of these commands. The exit command is also a special case: it should simply cause termination of your program.



cd

ls

cp

cat

more

Pipe Operator

< Redirect Operator

<< Redirect Operator

mkdir

rmdir

mv

rm

args

date

help

pwd

clear
