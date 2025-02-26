package lotto.view

class InputView {
    fun readPurchaseAmount(): Int {
        println("구입금액을 입력해 주세요.")
        val purchaseAmount = readln().toIntOrNull()
        return purchaseAmount.validateIsNumber()
    }

    fun readWinningNumbers(): Set<Int> {
        println("\n지난 주 당첨 번호를 입력해 주세요.")
        val rawWinningNumbers = readln().split(", ")
        val winningNumbers = rawWinningNumbers.map { it.toIntOrNull() }
        return winningNumbers.validateWinningNumbers()
    }

    fun readPassivePurchaseQuantity(): Int {
        println("수동으로 구매할 로또 수를 입력해 주세요.")
        return readln().toIntOrNull().validateIsNumber()
    }

    fun readPassiveLottoNumbers(passivityQuantity: Int): List<Set<Int>> {
        val passiveLottoNumbers = mutableListOf<Set<Int>>()

        println("수동으로 구매할 번호를 입력해 주세요.")
        repeat(passivityQuantity) {
            val rawPassiveWinningNumbers = readln().split(", ")
            val mapToPassiveLottoNumbers = rawPassiveWinningNumbers.map { it.toIntOrNull() }
            passiveLottoNumbers.add(mapToPassiveLottoNumbers.validateWinningNumbers())
        }
        return passiveLottoNumbers
    }

    fun readBonusNumber(): Int {
        println("보너스 볼을 입력해 주세요.")
        return readln().toIntOrNull().validateIsNumber()
    }

    private fun List<Int?>.validateWinningNumbers(): Set<Int> = this.map { it.validateIsNumber() }.toSet()

    private fun Int?.validateIsNumber(): Int {
        if (this == null) throw IllegalArgumentException("[ERROR] 숫자를 입력해주세요.")
        return this
    }
}
