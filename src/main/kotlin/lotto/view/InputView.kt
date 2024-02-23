package lotto.view

const val REQUEST_PURCHASE_PRICE = "구입금액을 입력해 주세요."
const val REQUEST_WINNING_NUM = "당첨 번호를 입력해 주세요."
const val REQUEST_BONUS_NUM = "보너스 번호를 입력해 주세요."

fun inputCostMessage() {
    println(REQUEST_PURCHASE_PRICE)
    readlnOrNull()
}

fun inputWinNumbers() {
    println()
    println(REQUEST_WINNING_NUM)
}

fun inputBonusNumbers() {
    println()
    println(REQUEST_BONUS_NUM)
}
