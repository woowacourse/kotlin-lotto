package lotto.view

class InputView {
    fun readPurchaseAmount(): Int = readln().toInt()

    fun readManualLottoQuantity(): Int = readln().toInt()

    fun readLottoNumbers(): List<Int> {
        val rawLottoNumbers = readln().split(",").map { number -> number.trim() }
        return rawLottoNumbers.map { number -> number.toInt() }
    }

    fun readBonusNumber(): Int = readln().toInt()
}
