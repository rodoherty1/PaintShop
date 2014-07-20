package paintshop

object PaintShop {
    type PaintType = Char
    type CustomerOrder = Map[Int, PaintType]
    
	val MATTE = 'M'
	val GLOSS = 'G'
    
    def generateBatches(batchSize: Int): Stream[List[PaintType]] = {
        //val batch = List.fill(batchSize)(GLOSS)
        Stream.cons(List[PaintType](GLOSS), Stream.cons(List[PaintType](MATTE), Stream()))
    }
    
    
}

class PaintShop(batchSize: Int) {
    import PaintShop._
    
    val allBatches = generateBatches(batchSize)
    
    def apply(orders: List[CustomerOrder]): List[Char] = {

        def isBatchSuitable(batch: List[PaintType], orders: List[CustomerOrder]) : Boolean = {
            if (batch.length == 0 || orders.isEmpty) false
            else (orders.forall(order => order.exists({ case (colour, paintType) => batch.contains(paintType)})))
        }

        def findBestBatch(batches: Stream[List[PaintType]]): List[PaintType] = {
            if (batches.isEmpty) Nil
            else {
                if (isBatchSuitable(batches.head, orders)) batches.head
                else findBestBatch(batches.tail)
            }
        }
        findBestBatch(allBatches)
    }

}