package model.domain

import model.Lotto

class LottoMachine {
    private val _bundleOfLotto: MutableList<Lotto> = mutableListOf()
    val bundleOfLotto: List<Lotto> get() = _bundleOfLotto.toList()

    fun generateLotto(lottoGenerator: LottoGenerator) {
        val ticket = lottoGenerator.generate()
        _bundleOfLotto.add(Lotto(ticket))
    }
}
