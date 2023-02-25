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

    override fun getLottoNumbers(): Set<Int> {
        return runCatching {
            val numbers = readln().trim()
            numbers.split(",").map { it.trim().toInt() }.toSet()
        }.getOrElse { error ->
            println(error)
            getLottoNumbers()
        }
    }

    override fun getManualLottoCount(): Int {
        return runCatching {
            println("수동으로 구매할 로또 수를 입력해 주세요.")
            val count = readln()
            count.toInt()
        }.getOrElse { error ->
            println(error)
            getManualLottoCount()
        }
    }

    override fun getWinningLotto(): Set<Int> {
        return runCatching {
            println("지난 주 당첨 번호를 입력해 주세요.")
            getLottoNumbers()
        }.getOrElse {
            getWinningLotto()
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

    override fun getManualLottos(count: Int): List<Set<Int>> {
        val result = mutableListOf<Set<Int>>()
        repeat(count) {
            result.add(getManualLotto())
        }
        return result
    }

    private fun getManualLotto(): Set<Int> {
        return runCatching {
            println("수동으로 구매할 번호를 입력해 주세요.")
            getLottoNumbers()
        }.getOrElse { error ->
            println(error)
            getManualLotto()
        }
    }
}
