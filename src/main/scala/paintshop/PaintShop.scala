package paintshop

import paintshop.BatchFinder._
import scala.io.Source

object PaintShop extends App {
    
    def missingInputFile(): Unit = {
        println("Please specify an input test file as a command line argument")
    }
    
    def illegalInput(): Unit = {
        println("Please specify an input test file as a command line argument")
        println("Sample input file: ")
        println("\n5")
        println("1M 3G 5G")
        println("2G 3M 4G")
        println("5M")
    }
    
    
    
    override def main (args: Array[String]) {
        
        if (args.length < 2) missingInputFile()
        else {
            val orders = readFile(args(1))
            orders match {
                case None => illegalInput()
                case Some((batchSize, customerOrders)) => println(new BatchFinder(batchSize)(customerOrders))
            }
        }
    }
    
    def readFile(filename: String): Option[(Int, List[CustomerOrder])] = {
        val f = new java.io.File(filename)
        if (!f.exists()) None
        else {
            val lines = Source.fromFile(f).getLines()
            Some(5, List(Map(1 -> MATTE, 3 -> GLOSS, 5-> GLOSS), Map(2->GLOSS, 3->MATTE, 4-> GLOSS), Map(5->MATTE)))
        }
        
    } 
}