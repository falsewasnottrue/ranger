object Ranger {

    fun nth(res: Double, n: Int): Int {
        return (res * Math.pow(10.0, n.toDouble())).toInt() % 10
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val a = 5.0 // TrainSeq.minLength(4)
        println("a: $a")

        val b = 10.0 // TrainSeq.minLength(5)
        println("b: $b")

        val c = 16.0 // TrainSeq.minLength(6)
        println("c: $c")

        val d = 21.0 // TrainSeq.minLength(7)
        println("d: $d")

        val e = 31.0 // TrainSeq.minLength(8)
        println("e: $e")

        // U = [(C/(D+E))].<2>
        val u = nth(c / (d+e), 2)
        // V = [(A/E)].<5>
        val v = nth(a/e, 5)
        // W = [(B/D)].<1>
        val w = nth(b/d, 1)

        // X = [(A/(C+D+E))].<5>
        val x = nth(a/(c+d+e), 5)
        // Y = [((A+B)/E)].<3>
        val y = nth((a+b)/e, 3)
        // Z = [(A/D)].<6>
        val z = nth(a/d, 6)

        val pos = "N 52 28.$u$v$w E 013 33.$x$y$z"

        println(pos)

    }

}