package domain

class LottoStatistics(val winningLotto: WinningLotto) {
    fun compareNumbers(numbers: Set<Int>): Int {
        val winningNumbers = winningLotto.getWinningNumbers()
        return numbers.filter { number ->
            winningNumbers.contains(number)
        }.size
    }
}
