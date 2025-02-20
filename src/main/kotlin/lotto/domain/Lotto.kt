package lotto.domain

class Lotto(val lottoNums: List<Int>) {
    init {
        require(lottoNums.size == DEFAULT_LOTTO_SIZE) { "로또는 6개의 숫자를 가져야 합니다" }
        require(lottoNums.all { it in MIN_RANGE..MAX_RANGE }) { "로또 번호는 1에서 45까지의 숫자이다" }
        require(lottoNums.size == lottoNums.toSet().size) { "로또 번호는 중복될 수 없습니다" }
    }

    fun compareWithWinningLotto(winningLotto: Lotto): Int {
        return lottoNums.count { it in winningLotto.lottoNums }
    }

    fun compareWithBonusNumber(bonusNumber: Int): Boolean {
        return lottoNums.contains(bonusNumber)
    }

    companion object {
        const val DEFAULT_LOTTO_SIZE = 6
        const val MIN_RANGE = 1
        const val MAX_RANGE = 45
    }
}
