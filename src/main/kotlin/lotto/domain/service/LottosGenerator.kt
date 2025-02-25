package lotto.domain.service

import lotto.domain.model.Lotto

class LottosGenerator(
    private val generator: LottoGenerator = RandomLottoGenerator(),
) {
    fun generate(count: Int): List<Lotto> {
        return List(count) { generator.generate(LOTTO_SIZE) }
    }

    private companion object {
        const val LOTTO_SIZE = 6
    }
}
