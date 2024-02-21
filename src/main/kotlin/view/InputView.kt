package view

object InputView {

    fun readPurchaseAmount(): Int {
        return try {
            println("구입금액을 입력해 주세요.")
            readln().toInt()
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException("입력값은 정수여야 합니다.")
        }
    }
}
