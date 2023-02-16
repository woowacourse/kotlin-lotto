package view

class InputView : InputViewInterface {
    override fun getMoney(): Int {
        while (true) {
            println("구입 금액을 입력해 주세요.")
            val money = readln()
            runCatching { money.toInt() / 1000 }
                .onSuccess { return it }
        }
    }

    override fun getNumbers(): Set<Int> {
        while (true) {
            println("지난 주 당첨 번호를 입력해 주세요.")
            val numbers = readln()
            runCatching { numbers.split(",").map { it.trim().toInt() }.toSet() }
                .onSuccess { return it }
        }
    }

    override fun getBonusNumber(): Int {
        while (true) {
            println("보너스 볼을 입력해 주세요.")
            val bonusNumber = readln()
            runCatching { bonusNumber.toInt() }
                .onSuccess { return it }
        }
    }
}
