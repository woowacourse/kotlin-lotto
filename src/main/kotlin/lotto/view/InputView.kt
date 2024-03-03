package lotto.view

object InputView {
    private const val MINIMUM_PURCHASE_AMOUNT = 0
    private const val WINNING_NUMBER_DELIMITER = ","
    private const val MINIMUM_VALUE_EXCEPTION_MESSAGE = "수동 로또 개수는 ${MINIMUM_PURCHASE_AMOUNT}개 이상, 구입 수량 이하여야 합니다."
    private const val INVALID_UNIT_EXCEPTION_MESSAGE = "구입 금액은 %s원 단위여야 합니다."
    private const val INVALID_FORMAT_EXCEPTION_MESSAGE = "입력값은 정수여야 합니다."

    fun readPurchaseAmount(minPricePerUnit: Int): Long {
        return try {
            println("구입금액을 입력해 주세요.")
            val input = readln().toLong()
            require(input > 0L) { MINIMUM_VALUE_EXCEPTION_MESSAGE }
            require(input % minPricePerUnit == 0L) { INVALID_UNIT_EXCEPTION_MESSAGE.format(minPricePerUnit) }
            input
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException(INVALID_FORMAT_EXCEPTION_MESSAGE)
        }
    }

    fun readPurchaseQuantity(maxQuantity: Int): Int {
        return try {
            println("수동으로 구매할 로또 수를 입력해 주세요.")
            val input = readln().toInt()
            require(input in 0..maxQuantity) { MINIMUM_VALUE_EXCEPTION_MESSAGE }
            input
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException(INVALID_FORMAT_EXCEPTION_MESSAGE)
        }
    }

    fun readWinningNumbers(): List<Int> {
        return try {
            println("\n지난 주 당첨 번호를 입력해 주세요.")
            readln().split(WINNING_NUMBER_DELIMITER).map { it.trim().toInt() }
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException(INVALID_FORMAT_EXCEPTION_MESSAGE)
        }
    }

    fun readBonusNumber(): Int {
        return try {
            println("보너스 볼을 입력해 주세요.")
            readln().toInt()
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException(INVALID_FORMAT_EXCEPTION_MESSAGE)
        }
    }
}
