package lotto.model.generator

import lotto.model.Lotto

class RandomLottoGenerator : LottosGenerator {
    override fun generate(count: Int): List<Lotto> {
        return List(count) { Lotto.create(RANGE.shuffled().take(6)) }
    }

    companion object {
        private val RANGE: IntRange = 1..45
    }
}
