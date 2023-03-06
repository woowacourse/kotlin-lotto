package domain

import model.Count
import model.Lotto

class LottoMachine(private val lottoGenerator: LottoGenerator) {

    private fun generateLotto(): Lotto {
        val numbers = lottoGenerator.generate()

        return Lotto(numbers)
    }

    fun makeLotto(lottoCount: Count) = List(lottoCount.number) {
        generateLotto()
    }
}
