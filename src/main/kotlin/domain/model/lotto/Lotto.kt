package domain.model.lotto

import domain.model.LottoResult
import domain.model.WinningLotto

class Lotto(val numbers: Set<LottoNumber>) {

    init {
        require(numbers.size == NUMBER_COUNT) {
            NUMBER_COUNT_ERROR
        }
    }

    fun getLottoResult(winningLotto: WinningLotto): LottoResult {
        val matchCount = numbers.getMatchCount(winningLotto)
        val hasBonusNumber = numbers.contains(winningLotto.bonusNumber)

        return LottoResult.valueOf(matchCount, hasBonusNumber)
    }

    private fun Set<LottoNumber>.getMatchCount(winningLotto: WinningLotto): Int = this.count { lottoNumber ->
        winningLotto.catchLotto.numbers.contains(lottoNumber)
    }

    companion object {
        private const val NUMBER_COUNT = 6

        private const val NUMBER_COUNT_ERROR = "[ERROR] 로또 번호는 6개의 숫자로 이루어져야 합니다."
    }
}
