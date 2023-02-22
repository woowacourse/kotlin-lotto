package view

class InputView : InputViewInterface {
    override fun inputPaymentMoney(): String? {
        println(INPUT_PAYMENT_MONEY)
        return readlnOrNull()
    }

    override fun inputManualLottoCount(): String? {
        println(INPUT_MANUAL_LOTTO_COUNT)
        return readlnOrNull()
    }

    override fun inputManualLotto(): String? {
        println(INPUT_MANUAL_LOTTO_NUMBERS)
        return readlnOrNull()
    }

    override fun inputWinningLotto(): String? {
        println(INPUT_WINNING_LOTTO_NUMBERS)
        return readlnOrNull()
    }

    override fun inputBonusNumber(): String? {
        println(INPUT_BONUS_NUMBER)
        return readlnOrNull()
    }

    companion object {
        private const val INPUT_PAYMENT_MONEY = "구입금액을 입력해 주세요."
        private const val INPUT_MANUAL_LOTTO_COUNT = "수동으로 구매할 로또 수를 입력해 주세요."
        private const val INPUT_MANUAL_LOTTO_NUMBERS = "수동으로 구매할 번호를 입력해 주세요."
        private const val INPUT_WINNING_LOTTO_NUMBERS = "지난 주 당첨 번호를 입력해 주세요."
        private const val INPUT_BONUS_NUMBER = "보너스 볼을 입력해 주세요."
    }
}