package lotto.domain

class WinLotto(
    private val lotto: Lotto,
    private val bonusNumber: LottoNumber,
) {
    init {
        require(!lotto.contains(bonusNumber)) { ERROR_MESSAGE_BONUS_NUMBER_OVERLAPS_WITH_LOTTO_NUMBERS }
    }

    fun hasNumber(number: LottoNumber): Boolean = lotto.contains(number)

    fun bonusNumberMatched(lotto: Lotto): Boolean = lotto.contains(bonusNumber)

    companion object {
        private const val ERROR_MESSAGE_BONUS_NUMBER_OVERLAPS_WITH_LOTTO_NUMBERS = "보너스 번호는 당첨 번호와 달라야 합니다."
    }
}
