package lotto.model

import lotto.Constants

class LottoTicket(
    private val numbers: Set<LottoNumber> = generateLotto(),
    private val bonusNumber: LottoNumber? = null,
) {
    init {
        require(numbers.size == LOTTO_PICK_COUNT) {
            ERROR_LOTTO_SIZE
        }
        require(numbers.size == numbers.toSet().size) {
            ERROR_LOTTO_DUPLICATE
        }
        require(!numbers.contains(bonusNumber)) {
            ERROR_BONUS_DUPLICATE
        }
    }

    fun getSize() = numbers.size

    fun getNumbers() = numbers

    fun getBonusNumber() = bonusNumber

    companion object {
        private fun generateLotto(): Set<LottoNumber> =
            (Constants.MINIMUM_NUMBER..Constants.MAXIMUM_NUMBER)
                .shuffled()
                .take(LOTTO_PICK_COUNT)
                .sorted()
                .map { LottoNumber(it) }
                .toSet()

        private const val LOTTO_PICK_COUNT = 6
        private const val ERROR_LOTTO_SIZE = "로또 번호는 6개여야 합니다."
        private const val ERROR_LOTTO_DUPLICATE = "로또 번호는 서로 중복되면 안 됩니다."
        private const val ERROR_BONUS_DUPLICATE = "보너스 번호는 당첨 번호와 중복되면 안 됩니다."
    }
}
