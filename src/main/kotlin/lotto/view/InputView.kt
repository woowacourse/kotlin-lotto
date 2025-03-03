package lotto.view

class InputView {
    fun readPurchaseAmount(): Int {
        println(INPUT_PURCHASE_AMOUNT)
        val input = readln().trim()
        return runCatching { input.toInt() }.getOrElse {
            println(INVALID_NUMBER_FORMAT)
            readPurchaseAmount()
        }
    }

    fun readManualLottoCount(): Int {
        println(INPUT_MANUAL_LOTTO_COUNT)
        val input = readln().trim()
        return runCatching { input.toInt() }.getOrElse {
            println(INVALID_NUMBER_FORMAT)
            readManualLottoCount()
        }
    }

    fun readManualLottoNumbers(count: Int): List<List<Int>> {
        println(INPUT_MANUAL_LOTTO_NUMBERS)
        return List(count) {
            readNumbers()
        }
    }

    fun readWinningNumbers(): List<Int> {
        println(INPUT_WINNING_LOTTO)
        val input = readln().split(COMMA)
        return runCatching { input.map { it.trim().toInt() } }.getOrElse {
            println(INVALID_NUMBER_FORMAT)
            readWinningNumbers()
        }
    }

    fun readBonusNumber(): Int {
        println(INPUT_BONUS_NUMBER)
        val input = readln().trim()
        return runCatching { input.toInt() }.getOrElse {
            println(INVALID_NUMBER_FORMAT)
            readBonusNumber()
        }
    }

    private fun readNumbers(): List<Int> {
        val input = readln().split(COMMA)
        return runCatching { input.map { it.trim().toInt() } }.getOrElse {
            println(INVALID_NUMBER_FORMAT)
            readNumbers()
        }
    }

    companion object {
        private const val INPUT_PURCHASE_AMOUNT = "구입금액을 입력해 주세요."
        private const val INPUT_MANUAL_LOTTO_COUNT = "\n수동으로 구매할 로또 수를 입력해 주세요."
        private const val INPUT_MANUAL_LOTTO_NUMBERS = "\n수동으로 구매할 번호를 입력해 주세요."
        private const val INPUT_WINNING_LOTTO = "\n지난 주 당첨 번호를 입력해 주세요."
        private const val INPUT_BONUS_NUMBER = "보너스 볼을 입력해 주세요."
        private const val INVALID_NUMBER_FORMAT = "[ERROR] 숫자를 입력해야 합니다."
        private const val COMMA = ","
    }
}
