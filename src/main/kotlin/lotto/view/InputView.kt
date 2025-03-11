package lotto.view

class InputView {
    fun readPurchaseAmount(): Int {
        println("구입금액을 입력해 주세요.")
        return readln().toInt()
    }

    fun readManualLottoQuantity(): Int {
        println("수동으로 구매할 로또 수를 입력해 주세요.")
        return readln().toInt()
    }

    fun readManualLottoNumbers(): List<Int> {
        println("수동으로 구매할 번호를 입력해 주세요.")
        return readLottoNumbers()
    }

    private fun readLottoNumbers(): List<Int> {
        val rawLottoNumbers = readln().split(",").map { number -> number.trim() }
        return rawLottoNumbers.map { number -> number.toInt() }
    }

    fun readWinningLottoNumbers(): List<Int> {
        println("\n지난 주 당첨 번호를 입력해 주세요.")
        return readLottoNumbers()
    }

    fun readBonusNumber(): Int {
        println("보너스 볼을 입력해 주세요.")
        return readln().toInt()
    }
}
