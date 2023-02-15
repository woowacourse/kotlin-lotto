package lotto.view

class OutputView {
    fun printInputMoneyPrompt() {
        println(INPUT_MONEY_PROMPT)
    }

    companion object {
        const val INPUT_MONEY_PROMPT = "구입금액을 입력해 주세요."
    }
}