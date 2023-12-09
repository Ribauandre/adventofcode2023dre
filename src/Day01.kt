fun main() {
    fun part1(input: List<String>): Int {
        var finalCalibrationNumber = 0
        for (line in input) {
            val stringBuilder = StringBuilder()
            val digitCalibrationCode = line.filter { it.isDigit() }
            if (digitCalibrationCode.length == 1) {
                var copy = digitCalibrationCode

                stringBuilder.append(copy).append(digitCalibrationCode)
                var recalibratedCode = stringBuilder.toString()

                finalCalibrationNumber += recalibratedCode.toInt()
            }
            else if (digitCalibrationCode.length == 2){
                finalCalibrationNumber += digitCalibrationCode.toInt()
            }
            else {
                var firstNum = digitCalibrationCode.first()
                var lastNum = digitCalibrationCode.last()

                stringBuilder.append(firstNum).append(lastNum)
                var recalibratedCode = stringBuilder.toString()

                finalCalibrationNumber += recalibratedCode.toInt()
            }

        }


        return finalCalibrationNumber
    }

    fun part2(input: List<String>): Int {
        val wordOfNumber = mapOf("one" to 1, "two" to 2, "three" to 3, "four" to 4, "five" to 5, "six" to 6, "seven" to 7, "eight" to 8, "nine" to 9)
        var finalCalibrationNumber: MutableList<Int> = mutableListOf<Int>()
        for (line in input) {
            var readLine = line
            val stringBuilder = StringBuilder()

            var wordBuilder = ""
            var index = 0
            for (char in line) {
                index++
                if(char.isLetter()) {
                    wordBuilder += char
                }
                else {
                    wordBuilder = char.toString()
                }
                for (key in wordOfNumber.keys) {
                    if (wordBuilder.contains(key)) {
                        readLine = readLine.replaceRange(index, index, wordOfNumber[key].toString())
                        readLine = readLine.replaceRange(index+1, index+1, key)
                        index += key.length+1
                        wordBuilder = char.toString()
                    }
                }
            }

            // re-used from part 1
            val digitCalibrationCode = readLine.filter { it.isDigit() }

            if (digitCalibrationCode.length == 1) {
                var copy = digitCalibrationCode
                stringBuilder.append(copy).append(digitCalibrationCode)
                var recalibratedCode = stringBuilder.toString()
                finalCalibrationNumber.add(recalibratedCode.toInt())
            }
            else if (digitCalibrationCode.length == 2){
                finalCalibrationNumber.add(digitCalibrationCode.toInt())
            }
            else {
                var firstNum = digitCalibrationCode.first()
                var lastNum = digitCalibrationCode.last()
                stringBuilder.append(firstNum).append(lastNum)
                var recalibratedCode = stringBuilder.toString()

                finalCalibrationNumber.add(recalibratedCode.toInt())
            }
        }
        return finalCalibrationNumber.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part2(testInput) == 281)

    val input = readInput("Day01")
    println("the answer for pt 1 is: " + part1(input))
    println("the answer for pt 2 is: " + part2(input))
}
