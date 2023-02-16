package View

class InputView : InputViewInterface {
    override fun getMoney(): Int {
        println("구입 금액을 입력해 주세요.")
        val money = readln()
        var result = 0
        runCatching { result = money.toInt() }
            .onSuccess { return result }
            .onFailure {
                result = getMoney()
            }
        return result
    }

    override fun getNumbers(): List<Int> {
        TODO("Not yet implemented")
    }

    override fun getBonusNumber(): Int {
        TODO("Not yet implemented")
    }
}
