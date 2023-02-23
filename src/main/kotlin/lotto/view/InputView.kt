package lotto.view

object InputView {
    fun readInputMoney(): Int {
        return readln().toIntOrNull() ?: readInputMoney()
    }

    fun readInputLottoNumber(): List<Int> {
        val input = readln().split(",").map {
            it.trim()
        }
        val lottoNumbers = input.map {
            it.toIntOrNull()
        }
        if (lottoNumbers.contains(null)) readInputLottoNumber()
        return input.map { it.toInt() }
    }

    fun readInputBonusNumber(): Int {
        return readln().toIntOrNull() ?: readInputBonusNumber()
    }

    fun readInputManualLottoCount(): Int {
        return readln().toIntOrNull() ?: readInputManualLottoCount()
    }
}
