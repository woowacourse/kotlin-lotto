package lotto.view

class InputView : LottoInputView {

    override fun readMoney(): Int {
        var input: String
        var pass = true
        do {
            printErrorMessageIfNotPass(pass, INPUT_NOT_INT_ERROR)
            input = readln()
            pass = input.toIntOrNull() != null
        } while (!pass)
        return input.toInt()
    }

    override fun repeatReadLottoNumber(count: Int): List<List<Int>> {
        val lottoNumbers = mutableListOf<List<Int>>()
        repeat(count) {
            lottoNumbers.add(readLottoNumber())
        }
        return lottoNumbers
    }

    override fun readLottoNumber(): List<Int> {
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
        private const val INPUT_NOT_INT_ERROR = "숫자를 입력해주세요."
        private const val INPUT_SPLIT_ERROR = "콤마로 구분된 숫자를 입력해주세요."
    }
}
