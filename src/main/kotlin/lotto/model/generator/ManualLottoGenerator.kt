package lotto.model.generator

import lotto.model.Lotto

class ManualLottoGenerator(
    private val lottoBundle: List<List<Int>>,
) : LottoGenerator {
    override fun generate(count: Int): List<Lotto> =
        lottoBundle.map { numbers ->
            Lotto(numbers)
        }
}
