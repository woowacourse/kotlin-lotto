package lotto.view

object InputView {
    private const val INPUT_NOT_INT_ERROR = "숫자를 입력해주세요."
    private const val INPUT_SPLIT_ERROR = "콤마로 구분된 숫자를 입력해주세요."

    fun readInputMoney(): Int {
        var input: String
        var pass = true
        do {
            input = readln()
            printErrorMessageIfNotPass(pass, INPUT_NOT_INT_ERROR)
            pass = input.toIntOrNull() != null
        } while (!pass)
        return input.toInt()
    }

    fun readInputLottoNumber(): List<Int> {
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

    fun readInputBonusNumber(): Int {
        var input: String
        var pass = true
        do {
            input = readln()
            printErrorMessageIfNotPass(pass, INPUT_NOT_INT_ERROR)
            pass = input.toIntOrNull() != null
        } while (!pass)
        return input.toInt()
    }

    fun readInputManualLottoCount(): Int {
        var input: String
        var pass = true
        do {
            input = readln()
            printErrorMessageIfNotPass(pass, INPUT_NOT_INT_ERROR)
            pass = input.toIntOrNull() != null
        } while (!pass)
        return input.toInt()
    }

    private fun printErrorMessageIfNotPass(pass: Boolean, message: String) {
        if (!pass) OutputView.printErrorMessage(message)
    }
}
