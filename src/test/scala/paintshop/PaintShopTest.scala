package paintshop

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.WordSpec
import paintshop.PaintShop._

class PaintShopTest extends WordSpec with ShouldMatchers {
    
    "My Paintshop" should {
        "fulfill a simple order from one customer who wants 1->G" in {
            new PaintShop(1)(List(Map(1 -> GLOSS))) should be (List(GLOSS))
        } 
        "fulfill a simple order from one customer who wants 1->M" in {
            new PaintShop(1)(List(Map(1 -> MATTE))) should be (List(MATTE))
        } 
        
        "fulfill an order from two customers who want 1->M and 2->G" in {
            new PaintShop(2)(List(Map(1 -> MATTE), Map(2 -> GLOSS))) should be (List(MATTE, GLOSS))
        } 
        
        "fulfill an order from three customers" in {
            new PaintShop(5)(List(Map(1 -> MATTE, 3 -> GLOSS, 5-> GLOSS))) should be (List(MATTE, MATTE, GLOSS, MATTE, GLOSS))
        } 

        "generate all combinations of MATTE and GLOSS for batchsize = 1" in {
            generateBatches(1) should have size 2
            generateBatches(1) should contain (List(MATTE))
            generateBatches(1) should contain (List(GLOSS))
        }
        "generate all combinations of MATTE and GLOSS for batchsize = 2" in {
            val combos = generateBatches(2)
            combos should have size 4
            combos should contain (List(MATTE, MATTE))
            combos should contain (List(MATTE, GLOSS))
            combos should contain (List(GLOSS, MATTE))
            combos should contain (List(GLOSS, GLOSS))
        }
    }

}