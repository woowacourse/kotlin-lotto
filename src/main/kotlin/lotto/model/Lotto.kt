package lotto.model

class Lotto(
    val numbers: List<LottoNumber>,
) {
    init {
        require(numbers.size == numbers.toSet().size) {
            val duplicatedNumber = findDuplicatedNumber()
            ERROR_DUPLICATED_LOTTO_NUMBERS.format(
                duplicatedNumber,
            )
        }
        require(numbers.size == LOTTO_NUMBERS_COUNT) { ERROR_LOTTO_NUMBERS_SIZE.format(numbers.size) }
    }

    private fun findDuplicatedNumber(): String {
        val duplicates =
            numbers
                .groupingBy { it.number }
                .eachCount()
                .filter { it.value > RIGHT_NUMBER_COUNT }
                .keys
                .joinToString(DUPLICATED_NUMBER_DELIMITER)
        return duplicates
    }

    fun isMatchedBonusNumber(bonusNumber: LottoNumber) = numbers.contains(bonusNumber)

    companion object {
        private const val ERROR_DUPLICATED_LOTTO_NUMBERS = "입력한 로또 번호 %s이 중복됩니다. 로또 번호는 중복될 수 없습니다."
        private const val ERROR_LOTTO_NUMBERS_SIZE = "입력한 로또 번호 개수는 %d개 입니다. 로또 번호는 6개입니다."
        const val LOTTO_NUMBERS_COUNT = 6
        private const val RIGHT_NUMBER_COUNT = 1
        private const val DUPLICATED_NUMBER_DELIMITER = ", "
    }
}
