package view

object InputView {
    private const val HEADER_READ_AMOUNT = "구입금액을 입력해 주세요."

    fun readAmount(): String {
        println(HEADER_READ_AMOUNT)
        return readln()
    }
}
