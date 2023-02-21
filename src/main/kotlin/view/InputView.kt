package view

class InputView : InputViewInterface {
    override fun getMoney(): Int {
        return runCatching {
            println("구입 금액을 입력해 주세요.")
            val money = readln()
            money.toInt()
        }.getOrElse { error ->
            println(error)
            getMoney()
        }
    }

    override fun getNumbers(): Set<Int> {
        return runCatching {
            println("지난 주 당첨 번호를 입력해 주세요.")
            val numbers = readln().trim()
            numbers.split(",").map { it.trim().toInt() }.toSet()
        }.getOrElse { error ->
            println(error)
            getNumbers()
        }
    }

    override fun getBonusNumber(): Int {
        return runCatching {
            println("보너스 볼을 입력해 주세요.")
            val bonusNumber = readln()
            bonusNumber.toInt()
        }.getOrElse { error ->
            println(error)
            getBonusNumber()
        }
    }
}
