package lotto.view

object InputView {
    private const val INPUT_PURCHASE_AMOUNT = "구입금액을 입력해 주세요."
    private const val INPUT_PURCHASE_SIZE_OF_MANUAL_LOTTO = "수동으로 구매할 로또 수를 입력해 주세요."
    private const val INPUT_GUIDE_MANUAL_LOTTO_NUMBER = "\n수동으로 구매할 번호를 입력해 주세요."
    private const val INPUT_WINNING_NUMBER = "\n지난 주 당첨 번호를 입력해 주세요."
    private const val INPUT_BONUS_NUMBER = "\n보너스 볼을 입력해 주세요."
    private const val INPUT_SEPARATOR = ','

    fun inputPurchaseAmount(): String {
        println(INPUT_PURCHASE_AMOUNT)
        return readlnOrNull().orEmpty()
    }

    fun inputPurchaseSizeOfManualLotto(): String {
        println(INPUT_PURCHASE_SIZE_OF_MANUAL_LOTTO)
        return readlnOrNull().orEmpty()
    }

    fun inputManualLottos(numberOfManaulLotto: Int): List<List<String>> {
        println(INPUT_GUIDE_MANUAL_LOTTO_NUMBER)
        return List(numberOfManaulLotto) { readlnOrNull().orEmpty().split(INPUT_SEPARATOR).map { it.trim() } }
    }

    fun inputWinningNumbers(): List<String> {
        println(INPUT_WINNING_NUMBER)
        return readlnOrNull().orEmpty().split(INPUT_SEPARATOR).map { it.trim() }
    }

    fun inputBonusNumber(): String {
        println(INPUT_BONUS_NUMBER)
        return readlnOrNull().orEmpty()
    }
}
