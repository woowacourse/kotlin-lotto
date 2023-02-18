package util.common

fun IntRange.generateDistinctRandomNumbers(count: Int): List<Int> = shuffled().take(count)
