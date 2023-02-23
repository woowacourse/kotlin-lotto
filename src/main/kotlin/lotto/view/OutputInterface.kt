package lotto.view

import lotto.model.UserLotto

interface OutputInterface {
    fun printPurchaseCounts(manual: Int, auto: Int)
    fun printUserLottos(userLotto: UserLotto)
    fun printResult(ranks: List<Int>, rates: String)
}
