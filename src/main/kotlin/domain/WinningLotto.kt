package domain

class WinningLotto(val winningLotto: Lotto, val bonusNumber: LottoNumber) {
    init {
        require(!winningLotto.numbers.contains(bonusNumber)) { BONUS_NUMBER_OVERLAY_ERROR_MESSAGE }
    }

    fun countSameLottoNumber(lotto: List<LottoNumber>): Int {
        return winningLotto.numbers.count { lotto.contains(it) }
    }

    fun hasBonusNumber(lotto: List<LottoNumber>): Boolean {
        return lotto.contains(bonusNumber)
    }

    companion object {
        const val BONUS_NUMBER_OVERLAY_ERROR_MESSAGE = "보너스 번호가 당첨 번호에 포함되어 있습니다."
    }
}
