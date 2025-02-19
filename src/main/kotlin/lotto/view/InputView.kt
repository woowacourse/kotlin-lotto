package lotto.view

object InputView {
    fun inputPurchasePrice(): String {
        println("구입금액을 입력해 주세요.")
        return readlnOrNull() ?: ""
    }

    fun inputWinningNumber(): List<String> {
        println("지난 주 당첨 번호를 입력해 주세요.")
        return readlnOrNull()?.split(",")?.map { it.trim() } ?: emptyList()
    }

    fun inputBonusNumber(): String {
        println("보너스 볼을 입력해 주세요.")
        return readlnOrNull() ?: ""
    }
}
