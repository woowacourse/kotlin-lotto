package lotto.domain

import lotto.model.UserLotto
import lotto.model.UserLottoCount
import lotto.view.OutputInterface

class FakeOutputView(
    val onPrintResult: (String) -> Unit
) : OutputInterface {
    override fun printPurchaseCounts(userLottoCount: UserLottoCount) {
        onPrintResult("수동 ${userLottoCount.manual}장 자동 ${userLottoCount.auto}장")
    }

    override fun printUserLottos(userLotto: UserLotto) {
        userLotto.lotto.forEach {
            onPrintResult(it.toString())
        }
    }

    override fun printResult(ranks: List<Int>, rates: String) {
        onPrintResult("수익률 $rates")
    }
}
