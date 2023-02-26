package lotto.controller

import lotto.domain.model.Lotto
import lotto.domain.model.Rank
import lotto.view.LottoOutputView

class TestOutputView(private val actual: MutableList<Any>) : LottoOutputView {
    override fun printLottoCountMessage(manualCount: Int, autoCount: Int) {
        actual.add("수동으로 ${manualCount}장, 자동으로 ${autoCount}장 구매")
    }

    override fun printLottoNumbers(lottoNumbers: List<Lotto>) {
        val lottos = mutableListOf<List<Int>>()
        lottoNumbers.forEach { lotto -> lottos.add(lotto.numbers.map { it.number }) }
        actual.add(lottos)
    }

    override fun printResult(ranks: List<Rank>, yield: Double) {
        val result = mutableListOf<String>()
        ranks.forEach { rank -> result.add(rank.name) }
        result.add("수익률은 %.2f".format(yield))
        this.actual.add(result)
    }
}
