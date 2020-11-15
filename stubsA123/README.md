## Using makefile
```make all```

To compile your .java files

```make clean```

To remove the generated .class files

## Using run.sh
```run.sh {input_file} {output_file}```

Example:
```run.sh test.in res.out```

Both arguments are optional, inputfile is the file containing the test cases and output file is where you want the result to be written into.
In the case any argument is missing, console is used for input or output

## Format of test file

number of test cases

size

number of commands

command1

command2

...

size (next test case)

number of commands

command1

command2

...


One sample test file test.in is attached alongside

```Allocate Size```

```Free Address```

This is the format for commands required

