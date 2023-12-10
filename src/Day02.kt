class Game(var ID: Int, map: MutableMap<String, Int>) {
    var id = ID
    var draws = arrayListOf(map)
    var real = true

}

fun main() {

    fun part1(input: List<String>): Int {
        var games: MutableList<Game> = mutableListOf<Game>()

        for (line in input) {
            val filterLeft = line.substringAfter("e")
            val gameID = filterLeft.substringBefore(":").trim()
            val game = Game(gameID.toInt(), mutableMapOf("red" to 12, "green" to 13, "blue" to 14))

            val drawnSets = line.substring(7).split(";")
            var counter = 0
            for (set in drawnSets) {
                if(counter > 0) {
                    game.draws.add(mutableMapOf("red" to 12, "green" to 13, "blue" to 14))
                }
                val draws = set.split(",")
                for (draw in draws){
                    val countDrawn = draw.filter { it.isDigit() }
                    val colorDrawn = draw.filter { it.isLetter() }
                    game.draws[counter].compute(colorDrawn) { _, value ->
                        value?.minus(countDrawn.toInt()) ?: 10
                    }
                }
                if (counter < drawnSets.size){
                    counter++
                }

            }

            for (set in game.draws) {
                for (value in set.values){
                    if (value < 0){
                        game.real = false
                        break
                    }
                }
            }
            games.add(game)
        }
        var sum = 0
        for (game in games){
            if (game.real){
                sum += game.id
            }
        }


        return sum
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    val result = part1(testInput)
    println(result)
    check(result == 8)

    val input = readInput("Day02")
    println("the answer for pt 1 is: " + part1(input))
    println("the answer for pt 2 is: " + part2(input))
}
