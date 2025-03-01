package lotto.domain.model

import lotto.domain.generator.LottoNumbersGenerator

class LottoMachine(
    private val generator: LottoNumbersGenerator,
) {
    fun generateLottoBundle(count: Int): LottoBundle? {
        if (count == 0) return null

        val lottos = generateLottos(count)
        return LottoBundle(lottos)
    }

    private fun generateLottos(count: Int): List<Lotto> {
        return List(count) { generateLotto() }
    }

    private fun generateLotto(): Lotto {
        val numbers = generator.generate()
        val sortedNumbers = numbers.toSortedSet(compareBy { it.number })
        return Lotto(sortedNumbers)
    }

    companion object {
        const val LOTTO_PRICE = 1000
    }
}
