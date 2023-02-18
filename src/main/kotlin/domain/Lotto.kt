package domain

class Lotto(val numbers: List<LottoNumber>) {
    fun matchLotto(winningLotto: WinningLotto): Rank {
        return Rank.valueOf(countSameLottoNumber(winningLotto.winningLotto), hasBonusNumber(winningLotto.bonusNumber))
    }

    private fun countSameLottoNumber(winningLotto: List<LottoNumber>): Int {
        return winningLotto.count { numbers.contains(it) }
    }

    private fun hasBonusNumber(bonusNumber: LottoNumber): Boolean {
        return numbers.contains(bonusNumber)
    }

    init {
        require(numbers.size == 6) { INPUT_LOTTO_SIZE_ERROR_MESSAGE }
        require(numbers.toSet().size == 6) { INPUT_LOTTO_DUPLICATE_ERROR_MESSAGE }
    }

    companion object {
        const val INPUT_LOTTO_SIZE_ERROR_MESSAGE = "당첨 번호가 6개가 아닙니다"
        const val INPUT_LOTTO_DUPLICATE_ERROR_MESSAGE = "당첨 번호가 중복되었습니다."
    }
}
