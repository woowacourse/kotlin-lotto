package lotto.view

class InputView {
    fun readPurchaseAmount(): Int {
        return runCatching {
            println(PURCHASE_AMOUNT_GUIDE)
            val amount = readLine().toIntOrNull()
            requireNotNull(amount) { PURCHASE_AMOUNT_TYPE_ERROR }
            amount
        }.getOrElse {
            printError(it.message ?: "")
            readPurchaseAmount()
        }
    }

    fun readManualLotteryQuantity(): Int {
        return runCatching {
            println(MANUAL_LOTTERY_QUANTITY_GUIDE)
            val quantity = readLine().toIntOrNull()
            requireNotNull(quantity) { MANUAL_LOTTERY_QUANTITY_ERROR }
            quantity
        }.getOrElse {
            printError(it.message ?: "")
            readManualLotteryQuantity()
        }
    }

    fun readManualLotteryNumbers(quantity: Int): List<List<Int>> {
        println(MANUAL_LOTTERY_TICKETS_GUIDE)
        return List(quantity) { readManualLotteryNumber() }
    }

    private fun readManualLotteryNumber(): List<Int> {
        return runCatching {
            val numbers = readLine().split(", ").mapNotNull { it.toIntOrNull() }
            require(numbers.size == 6) { MANUAL_LOTTERY_TICKET_ERROR }
            numbers
        }.getOrElse {
            printError(it.message ?: "")
            readManualLotteryNumber()
        }
    }

    fun readWinningNumbers(): List<Int> {
        return runCatching {
            println(WINNING_NUMBERS_GUIDE)
            val numbers = readLine().split(", ").mapNotNull { it.toIntOrNull() }
            require(numbers.size == 6) { WINNING_NUMBERS_TYPE_ERROR }
            numbers
        }.getOrElse {
            printError(it.message ?: "")
            readWinningNumbers()
        }
    }

    fun readBonusNumber(): Int {
        return runCatching {
            println(BONUS_NUMBERS_GUIDE)
            val number = readLine().toIntOrNull()
            requireNotNull(number) { BONUS_NUMBERS_TYPE_ERROR }
            number
        }.getOrElse {
            printError(it.message ?: "")
            readBonusNumber()
        }
    }

    private fun readLine(): String {
        return readln()
    }

    fun printError(message: String) {
        println("$ERROR_HEADER $message")
    }

    companion object {
        private const val BONUS_NUMBERS_GUIDE = "보너스 볼을 입력해 주세요."
        private const val BONUS_NUMBERS_TYPE_ERROR = "보너스 번호는 정수여야 합니다."
        private const val ERROR_HEADER = "[ERROR]"
        private const val MANUAL_LOTTERY_QUANTITY_GUIDE = "수동으로 구매할 로또 수를 입력해 주세요."
        private const val MANUAL_LOTTERY_QUANTITY_ERROR = "수동 로또 개수는 정수여야 합니다."
        private const val MANUAL_LOTTERY_TICKET_ERROR = "수동 로또 번호는 6개의 정수여야 합니다."
        private const val MANUAL_LOTTERY_TICKETS_GUIDE = "수동으로 구매할 번호를 입력해 주세요."
        private const val PURCHASE_AMOUNT_GUIDE = "구입금액을 입력해 주세요."
        private const val PURCHASE_AMOUNT_TYPE_ERROR = "구매금액은 정수여야 합니다."
        private const val WINNING_NUMBERS_GUIDE = "지난 주 당첨 번호를 입력해 주세요."
        private const val WINNING_NUMBERS_TYPE_ERROR = "당첨 번호는 6개의 정수여야 합니다."
    }
}
