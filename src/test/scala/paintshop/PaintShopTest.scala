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
        
        "fulfill an order from one customer with 5 colours" in {
            new PaintShop(5)(List(Map(1 -> MATTE, 3 -> GLOSS, 5-> GLOSS))) should be (List(GLOSS, GLOSS, GLOSS, GLOSS, GLOSS))
        } 
        
        "fulfill an order from two customers with 5 colours" in {
            new PaintShop(5)(List(Map(1 -> MATTE, 3 -> GLOSS, 5-> GLOSS), Map(5->MATTE))) should be (List(GLOSS, GLOSS, GLOSS, GLOSS, MATTE))
        } 
        
        "fulfill an order from three customers with 5 colours" in {
            new PaintShop(5)(List(Map(1 -> MATTE, 3 -> GLOSS, 5-> GLOSS), Map(2->GLOSS, 3->MATTE, 4-> GLOSS), Map(5->MATTE))) should be (List(GLOSS, GLOSS, GLOSS, GLOSS, MATTE))
        } 

        "determine if a particular batch fulfills a given order" in {
            val ps = new PaintShop(3)
            
            ps.isBatchSuitable(List(MATTE, MATTE, MATTE), List(Map(1 -> MATTE, 2 -> GLOSS, 3-> GLOSS))) should be (true)
            ps.isBatchSuitable(List(MATTE, MATTE, MATTE), List(Map(1 -> GLOSS, 2 -> GLOSS, 3-> GLOSS))) should be (false)
        }
        
        "generate all combinations of MATTE and GLOSS for batchsize = 1" in {
            generateBatches(1) should have size 2
            generateBatches(1) should contain (List(MATTE))
            generateBatches(1) should contain (List(GLOSS))
        }
        
        "generate all combinations of MATTE and GLOSS for batchsize = 2" in {
            val combos = generateBatches(2)
            combos should have size 4
            combos(3) should be (List(MATTE, MATTE))
            combos(2) should be (List(MATTE, GLOSS))
            combos(1) should be (List(GLOSS, MATTE))
            combos(0) should be (List(GLOSS, GLOSS))
        }
    }

}