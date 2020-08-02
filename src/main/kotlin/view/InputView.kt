package view

object InputView {
    fun receivePurchaseAmount(): Int {
        println("구매 금액을 입력해 주세요.")
        return readLine()!!.toInt()
    }

    fun receiveWinningLottoNumbers(): List<Int> {
        println("지난 주 당첨 번호를 입력해 주세요.")
        return readLine()!!.split(",")
            .map { it.trim().toInt() }
            .toList()
    }
}
