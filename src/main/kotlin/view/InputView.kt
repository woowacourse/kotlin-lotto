package view

class InputView {
    fun readPurchaseAmount(): Int? {
        println(GUIDE_INPUT_PURCHASE_AMOUNT)
        return readln().toIntOrNull()
    }

    fun readManualLotteryCount(): Int? {
        println(GUIDE_INPUT_MANUAL_PURCHASE_COUNT)
        return readln().toIntOrNull()
    }

    fun readManualLottery(): List<Int>? {
        return readln().split(",").map {
            it.trim().toIntOrNull()
                ?: return null
        }
    }

    fun readWinningNumbers(): List<Int>? {
        println(GUIDE_INPUT_WINNING_NUMBERS)
        return readln().split(",").map {
            it.trim().toIntOrNull()
                ?: return null
        }
    }

    fun readBonusNumber(): Int? {
        println(GUIDE_INPUT_BONUS_NUMBER)
        return readln().toIntOrNull()
    }

    companion object {
        private const val GUIDE_INPUT_PURCHASE_AMOUNT = "구입 금액을 입력해주세요."
        private const val GUIDE_INPUT_MANUAL_PURCHASE_COUNT = "\n수동으로 구매할 로또 수를 입력해 주세요."
        private const val GUIDE_INPUT_WINNING_NUMBERS = "지난 주 당첨 번호를 입력해 주세요."
        private const val GUIDE_INPUT_BONUS_NUMBER = "보너스 볼을 입력해 주세요."
    }
}
