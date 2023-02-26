package lotto.domain

import lotto.controller.Controller

object ManualLottoCountValidator {

    private const val LOTTO_COUNT_NEGATIVE_ERROR = "구매할 수동 로또 금액이 투입 금액보다 큽니다."

    fun checkAvailable(manualLottoCount: Int, totalLottoCount: Int): Boolean {
        if (manualLottoCount <= totalLottoCount) return true
        println(Controller.ERROR_PREFIX + LOTTO_COUNT_NEGATIVE_ERROR)
        return false
    }
}
