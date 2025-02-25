package lotto.model

import lotto.model.generator.LottoGenerator

class LottoStore {
    fun getTickets(
        input: Int,
        lottoGenerator: LottoGenerator,
    ): List<Lotto> = lottoGenerator.generate(input)
}
