import kotlin.test.Test
import kotlin.test.assertEquals

class TransportTycoonTest {
    @Test
    fun `deliver single cargo to A takes 5 hours`() {
        val result = calculateDeliveryTime("A")
        assertEquals(5, result)
    }

    @Test
    fun `deliver A and B in parallel takes 5 hours`() {
        val result = calculateDeliveryTime("AB")
        assertEquals(5, result)
    }

    @Test
    fun `deliver two cargos to B takes 5 hours`() {
        val result = calculateDeliveryTime("BB")
        assertEquals(5, result)
    }

    @Test
    fun `deliver ABB takes 7 hours`() {
        val result = calculateDeliveryTime("ABB")
        assertEquals(7, result)
    }

    @Test
    fun `deliver AABABBAB takes 29 hours`() {
        val result = calculateDeliveryTime("AABABBAB")
        assertEquals(29, result)
    }
}
