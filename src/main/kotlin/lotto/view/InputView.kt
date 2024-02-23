package lotto.view

import lotto.model.Lotto

const val REQUEST_PURCHASE_PRICE = "구입금액을 입력해 주세요."
const val PURCHASE_LOTTO_COUNT = "%d개를 구매했습니다."
const val REQUEST_WINNING_NUM = "당첨 번호를 입력해 주세요."
const val REQUEST_BONUS_NUM = "보너스 번호를 입력해 주세요."

fun insertCostMessage() {
    println(REQUEST_PURCHASE_PRICE)
}

fun purchaseCountMessage(count: Int) {
    println(PURCHASE_LOTTO_COUNT.format(count))
}

fun showLotto(lotto: Lotto) {
    println(
        lotto
            .numbers
            .sortedBy { it.lottoNumber }
            .map { it.lottoNumber },
    )
}

fun insertWinNumbers() {
    println()
    println(REQUEST_WINNING_NUM)
}

fun insertBonusNumbers() {
    println()
    println(REQUEST_BONUS_NUM)
}
