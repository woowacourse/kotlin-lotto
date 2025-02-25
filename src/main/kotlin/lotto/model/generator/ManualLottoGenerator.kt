package lotto.model.generator

import lotto.model.Lotto

class ManualLottoGenerator : LottoGenerator {
    private val lottoBundle = mutableListOf<Lotto>()

    fun addLotto(numbers: List<Int>) {
        lottoBundle.add(Lotto(numbers))
    }

    override fun generate(count: Int): List<Lotto> = lottoBundle
}
