
fun <E> Iterable<E>.updated(index: Int, elem: E) = mapIndexed { i, existing ->  if (i == index) elem else existing }
fun <E> List<E>.last(): E = this[this.size-1]

data class TrainSeq(val trains: List<Int>) {
    val size = trains.size

    fun trainPosition(train: Int): Int = trains.indexOf(train)

    val emptyPosition: Int = trains.indexOf(0)

    fun canMove(train: Int): Boolean {
        val distTo0 = Math.abs(emptyPosition - trainPosition(train))
        return distTo0 <= 2
    }

    private fun move(train: Int): TrainSeq {
        val pos = trainPosition(train)
        val newTrains = trains.updated(emptyPosition, train).updated(pos, 0)

        return TrainSeq(newTrains)
    }

    fun successors(): Map<Int, TrainSeq> {
        val movable = (1 until size).toList().filter { canMove(it) }
        return movable.map { it to move(it) }.toMap()
    }


    companion object {
        fun start(n: Int): TrainSeq = TrainSeq((0 until n).toList())
        fun end(n: Int): TrainSeq = TrainSeq(listOf(0).plus(n-1 downTo 1))

        fun minLength(n: Int): Int {
            val start = start(n)
            val end = end(n)

            val generations: MutableList<List<TrainSeq>> = mutableListOf()
            val generation0: List<TrainSeq> = listOf(start)

            generations.add(generation0)

            while (!generations.last().contains(end)) {
                val allSuccessors: List<TrainSeq> = generations.last().flatMap { it.successors().values }.distinct()
                val nextGeneration: List<TrainSeq> = allSuccessors.filter { successor -> generations.all {
                    generation -> !generation.contains(successor)
                } }

                // println("GENERATION ${generations.size} -> ${nextGeneration.size}")
                // println(" --- $nextGeneration")

                generations.add(nextGeneration)
            }

            return generations.size - 1
        }
    }
}

