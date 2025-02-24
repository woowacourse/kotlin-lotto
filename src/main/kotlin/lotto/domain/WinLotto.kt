package lotto.domain

class WinLotto(
    lotto: Lotto,
    val bonusNumber: LottoNumber,
) {
    val numbers: Set<Int> = lotto.numbers.map { number: LottoNumber -> number.value }.toSet()

    init {
        require(!lotto.numbers.contains(bonusNumber)) { ERROR_MESSAGE_BONUS_NUMBER_OVERLAPS_WITH_LOTTO_NUMBERS }
    }

    companion object {
        private const val ERROR_MESSAGE_BONUS_NUMBER_OVERLAPS_WITH_LOTTO_NUMBERS = "보너스 번호는 당첨 번호와 달라야 합니다."
    }
}
