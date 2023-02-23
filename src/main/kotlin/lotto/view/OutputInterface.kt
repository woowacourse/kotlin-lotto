package lotto.view

import lotto.model.UserLotto
import lotto.model.UserLottoCount

interface OutputInterface {
    fun printPurchaseCounts(userLottoCount: UserLottoCount)
    fun printUserLottos(userLotto: UserLotto)
    fun printResult(ranks: List<Int>, rates: String)
}
