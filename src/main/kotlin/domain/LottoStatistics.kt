package domain

class LottoStatistics(val winningLotto: WinningLotto) {
    fun compareNumbers(numbers: Set<Int>): Int {
        return numbers.filter { number ->
            winningLotto.getWinningNumbers().contains(number)
        }.size
    }
}
