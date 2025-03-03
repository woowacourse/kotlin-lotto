package lotto.model

data class Lotto(
    val numbers: List<LottoNumber>,
) {
    constructor(vararg numbers: Int) : this(numbers.map { number -> LottoNumber(number) })

    init {
        require(numbers.size == LOTTO_NUMBERS_COUNT) { ERROR_LOTTO_NUMBERS_SIZE.format(numbers.size) }
        require(numbers.size == numbers.toSet().size) { ERROR_DUPLICATED_LOTTO_NUMBERS.format(getDuplicatedNumber()) }
    }

    private fun getDuplicatedNumber(): String {
        val duplicates =
            numbers
                .groupingBy { it.number }
                .eachCount()
                .filter { it.value > RIGHT_NUMBER_COUNT }
                .keys
                .joinToString(DUPLICATED_NUMBER_DELIMITER)
        return duplicates
    }

    fun getMatchCount(lotto: Lotto): Int =
        numbers.count { number ->
            lotto.numbers.contains(number)
        }

    fun containsNumber(number: LottoNumber): Boolean = numbers.contains(number)

    companion object {
        private const val ERROR_DUPLICATED_LOTTO_NUMBERS = "입력한 로또 번호 %s이 중복됩니다. 로또 번호는 중복될 수 없습니다."
        private const val ERROR_LOTTO_NUMBERS_SIZE = "입력한 로또 번호 개수는 %d개 입니다. 로또 번호는 6개입니다."
        const val LOTTO_NUMBERS_COUNT = 6
        private const val RIGHT_NUMBER_COUNT = 1
        private const val DUPLICATED_NUMBER_DELIMITER = ", "
        const val LOTTO_PRICE = 1_000
    }
}
