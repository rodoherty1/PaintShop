package paintshop

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.WordSpec

class PaintShopTest extends WordSpec with ShouldMatchers {
    
    "My Paintshop" should {
        "fulfill a simple order from one customer who wants 1->G" in {
            new PaintShop(1)(List(Map(1 -> 'G'))) should be (List('G'))
        } 
        "fulfill a simple order from one customer who wants 1->M" in {
            new PaintShop(1)(List(Map(1 -> 'M'))) should be (List('M'))
        } 
    }

}