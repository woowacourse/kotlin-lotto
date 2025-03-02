package lotto.model.generator

import lotto.model.Lotto

class ManualLottoGenerator : LottoGenerator {
    private val lottoBundle: MutableList<Lotto> = mutableListOf()

    override fun generate(count: Int): List<Lotto> = lottoBundle

    fun add(lottoNumbers: List<Int>) {
        val lotto = Lotto(lottoNumbers)
        lottoBundle.add(lotto)
    }
}
