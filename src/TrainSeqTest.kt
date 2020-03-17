import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class TrainSeqTest {

    @Test
    fun `should calculate size`() {
        assertEquals(0, TrainSeq(listOf()).size)
        assertEquals(4, TrainSeq(listOf(0,1,2,3)).size)
    }

    @Test
    fun `should calculate start configuration`() {
        assertEquals(TrainSeq(listOf(0,1,2,3)), TrainSeq.start(4))
    }

    @Test
    fun `should calculate end configuration`() {
        assertEquals(TrainSeq(listOf(0,3,2,1)), TrainSeq.end(4))
    }

    @Test
    fun `should calculate empty position`() {
        assertEquals(0, TrainSeq.start(10).emptyPosition)
        assertEquals(0, TrainSeq.end(7).emptyPosition)

        assertEquals(2, TrainSeq(listOf(1,2,0,3)).emptyPosition)
    }

    @Test
    fun `should calculate train position`() {
        assertEquals(1, TrainSeq.start(10).trainPosition(1))
        assertEquals(6, TrainSeq.end(7).trainPosition(1))

        assertEquals(1, TrainSeq(listOf(1,2,0,3)).trainPosition(2))
    }

    @Test
    fun `should calculate if train is movable`() {
        val start = TrainSeq.start(4)

        assertTrue(start.canMove(1))
        assertTrue(start.canMove(2))
        assertFalse(start.canMove(3))
    }

    @Test
    fun `should calculate successors`() {
        val start = TrainSeq.start(4)
        val succs = start.successors()

        assertEquals(2, succs.size)
        assert(succs.containsKey(1))
        assert(succs.containsKey(2))
        assert(!succs.containsKey(3))

        val move1 = succs.getValue(1)
        assertEquals(4, move1.size)
        assertEquals(TrainSeq(listOf(1,0,2,3)), move1)

        val move2 = succs.getValue(2)
        assertEquals(4, move2.size)
        assertEquals(TrainSeq(listOf(2,1,0,3)), move2)
    }

    @Test
    fun `should calculate minimal length to end`() {
        assertEquals(5, TrainSeq.minLength(4))
    }
}