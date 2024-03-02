package lotto.view

class InputView {
    fun readPurchaseAmount(): Int {
        return try {
            println("구입금액을 입력해 주세요.")
            readln().toInt()
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException(INVALID_FORMAT_EXCEPTION_MESSAGE)
        }
    }

    fun readManualLottoAmount(): Int {
        return try {
            println("\n수동으로 구매할 로또 수를 입력해 주세요.")
            readln().toInt()
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException(INVALID_FORMAT_EXCEPTION_MESSAGE)
        }
    }

    fun readManualLottos(): List<Int> {
        return try {
            readln().split(",").map { it.trim().toInt() }
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

    companion object {
        private const val INVALID_FORMAT_EXCEPTION_MESSAGE = "입력값은 정수여야 합니다."
        private const val WINNING_NUMBER_DELIMITER = ","
    }
}
