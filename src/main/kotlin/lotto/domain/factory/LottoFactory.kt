package lotto.domain.factory

import lotto.domain.Lotto

interface LottoFactory {
    fun createLotto(): Lotto = throw IllegalStateException(WRONG_FUNCTION_ACCESS_ERROR)

    fun createLotto(getManualPurchaseNumbers: () -> List<Int>): Lotto = throw IllegalStateException(WRONG_FUNCTION_ACCESS_ERROR)

    companion object {
        private const val WRONG_FUNCTION_ACCESS_ERROR = "허용되지 않은 함수에 접근하였습니다(LottoFactory)"
    }
}
