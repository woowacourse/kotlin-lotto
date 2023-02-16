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

    override fun getNumbers(): Set<Int> {
        println("지난 주 당첨 번호를 입력해 주세요.")
        val numbers = readln()
        var result = setOf<Int>()
        runCatching { result = numbers.split(",").map { it.toInt() }.toSet() }
            .onSuccess { return result }
            .onFailure {
                result = getNumbers()
            }
        return result
    }

    override fun getBonusNumber(): Int {
        TODO("Not yet implemented")
    }
}
