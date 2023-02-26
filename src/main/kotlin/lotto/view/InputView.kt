package lotto.view

class InputView : LottoInputView {

    override fun readMoney(): Int {
        println(INPUT_MONEY_PROMPT)
        var input: String
        var pass = true
        do {
            printErrorMessageIfNotPass(pass, INPUT_NOT_INT_ERROR)
            input = readln()
            pass = input.toIntOrNull() != null
        } while (!pass)
        return input.toInt()
    }

    override fun readManualLottoCount(): Int {
        println(INPUT_MANUAL_COUNT_PROMPT)
        var input: String
        var pass = true
        do {
            printErrorMessageIfNotPass(pass, INPUT_NOT_INT_ERROR)
            input = readln()
            pass = input.toIntOrNull() != null
        } while (!pass)
        return input.toInt()
    }

    override fun readManualLottoNumber(count: Int): List<List<Int>> {
        println(INPUT_MANUAL_LOTTO_NUMBERS_PROMPT)
        val lottoNumbers = mutableListOf<List<Int>>()
        repeat(count) {
            lottoNumbers.add(readLottoNumber())
        }
        return lottoNumbers
    }

    override fun readWinningLottoNumber(): List<Int> {
        println(INPUT_WINNING_NUMBERS_PROMPT)
        return readLottoNumber()
    }

    private fun readLottoNumber(): List<Int> {
        var input: List<String>
        var lottoNumbers: List<Int?>
        var pass = true
        do {
            printErrorMessageIfNotPass(pass, INPUT_SPLIT_ERROR)
            input = readln().split(",").map {
                it.trim()
            }
            lottoNumbers = input.map {
                it.toIntOrNull()
            }
            pass = !lottoNumbers.contains(null)
        } while (!pass)
        return input.map { it.toInt() }
    }

    override fun readBonusNumber(): Int {
        println(INPUT_BONUS_NUMBER_PROMPT)
        var input: String
        var pass = true
        do {
            printErrorMessageIfNotPass(pass, INPUT_NOT_INT_ERROR)
            input = readln()
            pass = input.toIntOrNull() != null
        } while (!pass)
        return input.toInt()
    }

    private fun printErrorMessageIfNotPass(pass: Boolean, message: String) {
        if (!pass) println(OutputView.ERROR_PREFIX + message)
    }

    companion object {
        private const val INPUT_BONUS_NUMBER_PROMPT = "보너스 볼을 입력해 주세요."
        private const val INPUT_MANUAL_COUNT_PROMPT = "수동으로 구매할 로또 수를 입력해 주세요."
        private const val INPUT_MANUAL_LOTTO_NUMBERS_PROMPT = "수동으로 구매할 번호를 입력해 주세요."
        private const val INPUT_MONEY_PROMPT = "구입금액을 입력해 주세요."
        private const val INPUT_WINNING_NUMBERS_PROMPT = "지난 주 당첨 번호를 입력해 주세요."
        private const val INPUT_NOT_INT_ERROR = "숫자를 입력해주세요."
        private const val INPUT_SPLIT_ERROR = "콤마로 구분된 숫자를 입력해주세요."
    }
}
