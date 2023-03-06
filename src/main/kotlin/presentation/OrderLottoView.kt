package presentation

import model.Count

class OrderLottoView {

    fun getMoney(): Int? {
        printGuideEnterMoney()

        return readLine()?.toIntOrNull()
    }

    fun getManualLottoCount(): Int? {
        printGuideManualLottoCount()

        return readLine()?.toIntOrNull()
    }

    fun getManualLotto(): List<Int> {
        printGuideManualLotto()

        return readLine()?.split(SEPARATOR)?.mapNotNull { number ->
            number.toIntOrNull()
        } ?: emptyList()
    }

    fun printLottoCount(count: Count) = println(LOTTO_COUNT.format(count.manualLottoCount, count.autoLottoCount))
    fun printAllLotto(lotto: List<Int>) = println(lotto)

    private fun printGuideEnterMoney() = println(ENTER_MONEY)

    fun printInvalidValueError() = println(ERROR_VALUE)
    private fun printGuideManualLottoCount() = println(ENTER_MANUAL_LOTTO_COUNT)
    private fun printGuideManualLotto() = println(ENTER_MANUAL_LOTTO)

    companion object {
        private const val SEPARATOR = ","
        private const val LOTTO_COUNT = "수동으로 %d장, 자동으로 %d개를 구매했습니다."
        private const val ENTER_MONEY = "구입금액을 입력해 주세요."
        private const val ENTER_MANUAL_LOTTO_COUNT = "수동으로 구매할 로또 수를 입력해 주세요."
        private const val ERROR_VALUE = "[ERROR] 숫자를 필수로 입력해야합니다."
        private const val ENTER_MANUAL_LOTTO = "수동으로 구매할 번호를 입력해 주세요."
    }
}
