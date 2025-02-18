package lotto

class Lotto {
    val numbers: List<Int> = (MINIMUM_LOTTO_VALUE..MAXIMUM_LOTTO_VALUE).shuffled().take(LOTTO_NUMBER_AMOUNT).sorted()

    companion object {
        private const val MINIMUM_LOTTO_VALUE = 1
        private const val MAXIMUM_LOTTO_VALUE = 45
        private const val LOTTO_NUMBER_AMOUNT = 6
    }
}
