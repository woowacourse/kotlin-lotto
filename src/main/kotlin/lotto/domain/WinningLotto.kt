package lotto.domain

class WinningLotto(
    lotto: Lotto,
    val bonusNumber: Int,
) {
    val numbers: Set<Int> = lotto.numbers

    init {
        require(bonusNumber in Lotto.LOTTO_NUMBER_MIN..Lotto.LOTTO_NUMBER_MAX) { ERROR_MESSAGE_BONUS_NUMBER_SHOULD_BE_IN_1_TO_45 }
        require(!lotto.numbers.contains(bonusNumber)) { ERROR_MESSAGE_BONUS_NUMBER_OVERLAPS_WITH_LOTTO_NUMBERS }
    }

    companion object {
        private const val ERROR_MESSAGE_BONUS_NUMBER_SHOULD_BE_IN_1_TO_45 = "보너스 번호는 1부터 45 사이의 숫자만 가질 수 있습니다."
        private const val ERROR_MESSAGE_BONUS_NUMBER_OVERLAPS_WITH_LOTTO_NUMBERS = "보너스 번호는 당첨 번호와 달라야 합니다."
    }
}
