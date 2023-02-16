package domain

import model.Lotto

class LottoMachine(private val numberGenerator: NumberGenerator) {

    fun generateLotto(): Lotto {
        val numbers = numberGenerator.generate()

        return Lotto(numbers)
    }
}
