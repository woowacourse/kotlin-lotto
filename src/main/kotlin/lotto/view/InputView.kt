package lotto.view

const val REQUEST_PURCHASE_PRICE = "구입금액을 입력해 주세요."
const val PURCHASE_LOTTO_COUNT = "수동으로 %d장, 자동으로 %d개를 구매했습니다."
const val REQUEST_WINNING_NUM = "지난 주 당첨 번호를 입력해 주세요."
const val REQUEST_BONUS_NUM = "보너스 볼을 입력해 주세요."
const val REQUEST_MANUALLY_LOTTO_COUNT = "수동으로 구매할 로또 수를 입력해 주세요."
const val REQUEST_MANUALLY_LOTTO = "수동으로 구매할 번호를 입력해 주세요."

fun insertCostMessage() {
    println(REQUEST_PURCHASE_PRICE)
}

fun purchaseCountMessage(
    maunalCount: Int,
    count: Int,
) {
    println(PURCHASE_LOTTO_COUNT.format(maunalCount, count))
}

fun insertWinNumbers() {
    println()
    println(REQUEST_WINNING_NUM)
}

fun insertBonusNumbers() {
    println()
    println(REQUEST_BONUS_NUM)
}

fun insertManuallyLottoCount() {
    println(REQUEST_MANUALLY_LOTTO_COUNT)
}

fun insertManuallyLotto() {
    println(REQUEST_MANUALLY_LOTTO)
}
