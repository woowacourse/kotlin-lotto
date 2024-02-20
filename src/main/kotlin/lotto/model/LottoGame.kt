package lotto.model

fun matchCount(winningNumbers: Set<Int>,lotto: Set<Int>): Int{
    return winningNumbers.intersect(lotto).size
}

fun matchBonusNumber(winningNumbers: Set<Int>, bonusNum: Int): Boolean{
    return winningNumbers.contains(bonusNum)
}