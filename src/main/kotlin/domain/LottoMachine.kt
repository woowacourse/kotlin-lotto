package domain

class LottoMachine(private val numberGenerator: NumberGenerator) {

    fun generateLotto(): List<Int> {
      val lotto = numberGenerator.generate()

        return lotto
    }

    companion object {
        private const val LOTTO_NUMBER_COUNT_RULE = 6
        private const val MINIMUM_LOTTO_NUMBER = 1
        private const val MAXIMUM_LOTTO_NUMBER = 45

    }
}
