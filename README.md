Scala XML pretty-printer command
================================

This is a command-line tool that uses `scala.xml.PrettyPrinter` to
format (_pretty-print_) and overwrite in-place XML files.

```
$ java -jar ~/Downloads/scala-xml-pp.jar
Error: Missing argument files...
Try --help for more information.

$ function scala-xml-pp() {
    java -jar ~/Downloads/scala-xml-pp.jar "$@";
}

$ scala-xml-pp --help
scala-xml-pp 0.0
Usage: scala-xml-pp [options] files...

  -w, --width <N>   Maximum width of N columns (default: 80)
  -i, --indent <N>  Indent N spaces (default: 2)
  --minimizeEmpty   Minimize empty elements (default: false)
  --help            Output this help.
  files...          XML files to format.

$ scala-xml-pp -w 80 -i 2 books.xml 
Formatted books.xml
