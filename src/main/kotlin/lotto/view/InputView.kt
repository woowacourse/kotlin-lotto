package lotto.view

class InputView : InputInterface {
    override fun getPurchaseMoney(): Int {
        println(INSERT_MONEY)
        return getNumber() ?: getPurchaseMoney()
    }

    override fun getManualLottoCount(): Int {
        println(INSERT_MANUAL_LOTTO_COUNT)
        return getNumber() ?: getManualLottoCount()
    }

    override fun getManualLottoNumbers(): List<Int> {
        println(INSERT_MANUAL_LOTTO)
        return getNumberList() ?: getManualLottoNumbers()
    }

    override fun getWinningLottoNumbers(): List<Int> {
        println(INSERT_WINNING_NUMBER)
        return getNumberList() ?: getWinningLottoNumbers()
    }

    override fun getWinningBonusNumber(): Int {
        println(INSERT_BONUS_BALL)
        return getNumber() ?: getWinningBonusNumber()
    }

    private fun getNumber(): Int? {
        val input = readln().trim().toIntOrNull()
        return if (input != null && input.isPositive()) input else null
    }

    private fun getNumberList(): List<Int>? {
        val input = readln().trim().split(",")
        val value = input.map { it.trim().toIntOrNull() }
        return if (null !in value) value.filterNotNull() else null
    }

    private fun Int.isPositive(): Boolean = this >= 0

    companion object {
        private const val INSERT_MONEY = "구입금액을 입력해 주세요."
        private const val INSERT_MANUAL_LOTTO_COUNT = "\n수동으로 구매할 로또 수를 입력해 주세요."
        private const val INSERT_MANUAL_LOTTO = "\n수동으로 구매할 번호를 입력해 주세요."
        private const val INSERT_WINNING_NUMBER = "\n지난 주 당첨 번호를 입력해 주세요."
        private const val INSERT_BONUS_BALL = "보너스 볼을 입력해 주세요."
    }
}
