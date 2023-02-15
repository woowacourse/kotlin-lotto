package domain

import model.Lotto

class LottoMachine(private val numberGenerator: NumberGenerator) {

    fun generateLotto(): Lotto {
        val numbers = numberGenerator.generate()

        return Lotto(numbers)
    }

    companion object {
        private const val LOTTO_NUMBER_COUNT_RULE = 6
        private const val MINIMUM_LOTTO_NUMBER = 1
        private const val MAXIMUM_LOTTO_NUMBER = 45

    }
}
