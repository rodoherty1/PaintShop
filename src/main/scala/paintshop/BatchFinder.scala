package paintshop

object BatchFinder {
    type PaintType = Char
    type CustomerOrder = Map[Int, PaintType]
    
	val MATTE = 'M'
	val GLOSS = 'G'
    
    def generateBatches(batchSize: Int): List[List[PaintType]] = {
        if (batchSize == 0) List()
        else if (batchSize == 1) List[PaintType](GLOSS) :: List[PaintType](MATTE) :: Nil
        else {        
        	val rest = generateBatches(batchSize-1)
        	rest.map(b => GLOSS :: b) ::: rest.map(b => MATTE :: b)
        }
    }
}

class BatchFinder(batchSize: Int) {
    import BatchFinder._
    
    val allPossibleBatches = generateBatches(batchSize)
    
    def isBatchSuitable(batch: List[PaintType], orders: List[CustomerOrder]) : Boolean = {
        if (batch.length == 0 || orders.isEmpty) false
        else (orders.forall(order => order.exists({
                case (colour, paintType) => batch(colour-1) == paintType
            }
        )))
    }
    
    def findBestBatch(batches: List[List[PaintType]], orders: List[CustomerOrder]): List[PaintType] = {
        if (batches.isEmpty) Nil
        else {
            if (isBatchSuitable(batches.head, orders)) batches.head
            else findBestBatch(batches.tail, orders)
        }
    }

    def apply(orders: List[CustomerOrder]): List[Char] = {
        findBestBatch(allPossibleBatches, orders)
    }

}