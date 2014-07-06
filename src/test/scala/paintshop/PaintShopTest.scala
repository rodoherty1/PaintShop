package paintshop

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.WordSpec

class PaintShopTest extends WordSpec with ShouldMatchers {
    
    "My Paintshop" should {
        "fulfill a simple order from one customer" in {
            PaintShop(List(Map(1 -> "G"))) should be (List("G"))
        } 
    }

}