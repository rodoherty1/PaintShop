package paintshop


class PaintShop(n: Int) {
    val MATTE = "M"
    val GLOSS = "G"
    
    val PAINTTYPES = List(MATTE, GLOSS)
    
    val colours = 0 until n
    
    val batch = (colours map {i => i -> GLOSS}).toMap
    
    def apply(orders: List[Map[Int, String]]): List[String] = {
        def satisfiesOrders (c: Int, p: String): Boolean = ???
        
        val batch = for {
            c <- colours
            p <- PAINTTYPES
            if (satisfiesOrders(c, p))
        } yield c + p
        
        ???
    }

}