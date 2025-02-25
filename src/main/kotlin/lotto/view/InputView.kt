package lotto.view

class InputView {
    fun readPurchaseAmount(): String {
        println("구입금액을 입력해 주세요.")
        return readln()
    }

    fun readWinningNumbers(): String {
        println("\n지난 주 당첨 번호를 입력해 주세요.")
        return readln()
    }

    fun readPassivePurchaseQuantity(): String {
        println("수동으로 구매할 로또 수를 입력해 주세요.")
        return readln()
    }

    fun readPassiveLottoNumbers(passivityQuantity: Int): List<String> {
        val passiveLottoNumbers = mutableListOf<String>()

        println("수동으로 구매할 번호를 입력해 주세요.")
        repeat(passivityQuantity) {
            passiveLottoNumbers.add(readln())
        }
        return passiveLottoNumbers
    }

    fun readBonusNumber(): String {
        println("보너스 볼을 입력해 주세요.")
        return readln()
    }
}
