/*
 * Main.scala
 */

import scala.Array
import scala.Boolean
import scala.Console
import scala.Int
import scala.None
import scala.Seq
import scala.Some
import scala.StringContext
import scala.Unit

import scala.Predef.augmentString
import scala.Predef.genericArrayOps
import scala.Predef.String

import java.io.File

/**
 * scala-xml-pp [-w|--width <N>] [-i|--indent <N>] [--minimizeEmpty] FILES...
 */
object Main {

  case class CommandArgs(
    width: Int = 80,
    indent: Int = 2,
    minimizeEmpty: Boolean = false,
    files: Seq[File] = Seq.empty
  )

  val defaultArgs = CommandArgs()

  val commandName = "scala-xml-pp"
  val commandVersion = "0.0"

  def main(args: Array[String]): Unit = {

    val parser = new scopt.OptionParser[CommandArgs]("scala-xml-pp") {
      head(commandName, commandVersion)

      opt[Int]('w', "width").valueName("<N>").action { (n, c) =>
        c.copy(width = n)
      }.text(s"Maximum width of N columns (default: ${defaultArgs.width})")

      opt[Int]('i', "indent").valueName("<N>").action { (n, c) =>
        c.copy(indent = n)
      }.text(s"Indent N spaces (default: ${defaultArgs.indent})")

      opt[Unit]("minimizeEmpty").action { (_, c) =>
        c.copy(minimizeEmpty = !c.minimizeEmpty)
      }.text(s"Minimize empty elements (default: ${defaultArgs.minimizeEmpty})")

      help("help").text("Output this help.")

      arg[File]("files...").unbounded().required().action { (f, c) =>
        c.copy(files = c.files :+ f)
      }.text("XML files to format.")
    }

    parser.parse(args.toSeq, defaultArgs) match {
      case Some(commandArgs) =>
        val prettyPrinter = prettyPrinterFrom(commandArgs)
        for (file <- commandArgs.files) yield {
          formatFile(prettyPrinter, file)
          Console.println(s"Formatted ${file.getPath}")
        }
        () // scala.sys.exit(0)
      case None =>
        () // scala.sys.exit(-1)
    }
  }

  def formatFile(prettyPrinter: scala.xml.PrettyPrinter, file: File): Unit = {
    val xml = scala.xml.XML.loadFile(file)
    val fileWriter = new java.io.PrintWriter(file)
    // scala.xml.XML.save(file.getPath, xml)
    prettyPrinter.format(xml).linesIterator.foreach(fileWriter.println)
    fileWriter.close()
  }

  def prettyPrinterFrom(commandArgs: CommandArgs) = {
    new scala.xml.PrettyPrinter(
      commandArgs.width,
      commandArgs.indent,
      commandArgs.minimizeEmpty
    )
  }

}
