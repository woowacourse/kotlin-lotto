package lotto.view

object InputView {
    private const val WINNING_NUMBER_DELIMITER = ","

    fun readPurchaseAmount(minPricePerUnit: Int): Long {
        println("구입금액을 입력해 주세요.")
        return readln().toLongOrNull() ?: return readPurchaseAmount(minPricePerUnit)
    }

    fun readPurchaseQuantity(maxQuantity: Int): Int {
        println("수동으로 구매할 로또 수를 입력해 주세요.")
        val input = readln().toInt()
        return if (input !in 0..maxQuantity) maxQuantity else input
    }

    fun readLottoNumbers(): List<Int> {
        val lottoNumbers = readln().split(WINNING_NUMBER_DELIMITER).map { it.trim().toIntOrNull() }
        return if (lottoNumbers.contains(null)) {
            println("로또 번호는 숫자 형식 입니다.")
            readLottoNumbers()
        } else {
            lottoNumbers.filterNotNull()
        }
    }

    fun readBonusNumber(): Int {
        println("보너스 볼을 입력해 주세요.")
        return readln().toIntOrNull() ?: readBonusNumber()
    }
}
