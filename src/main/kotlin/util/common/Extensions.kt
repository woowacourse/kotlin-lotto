package util.common

fun IntRange.generateDistinctRandomNumbers(count: Int): List<Int> = shuffled().take(count)

fun Int.divideByThousand(): Int {
    require(this >= 0) { "[ERROR] 입력값은 0이상이어야 합니다. " }

    return this.div(1000)
}
