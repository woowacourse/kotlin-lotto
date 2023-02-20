package domain

class Lotto(val numbers: List<LottoNumber>) {
    init {
        require(numbers.size == LOTTO_SIZE) { INPUT_LOTTO_SIZE_ERROR_MESSAGE }
        require(numbers.toSet().size == LOTTO_SIZE) { INPUT_LOTTO_DUPLICATE_ERROR_MESSAGE }
    }
    fun matchLotto(winningLotto: WinningLotto): Rank {
        return Rank.valueOf(winningLotto.countSameLottoNumber(numbers), winningLotto.hasBonusNumber(numbers))
    }

    companion object {
        const val LOTTO_SIZE = 6
        const val INPUT_LOTTO_SIZE_ERROR_MESSAGE = "당첨 번호가 ${LOTTO_SIZE}개가 아닙니다"
        const val INPUT_LOTTO_DUPLICATE_ERROR_MESSAGE = "당첨 번호가 중복되었습니다."
    }
}
