package domain

import model.Lotto

class LottoMachine(private val lottoGenerator: LottoGenerator) {

    fun generateLotto(): Lotto {
        val numbers = lottoGenerator.generate()

        return Lotto(numbers)
    }
}
