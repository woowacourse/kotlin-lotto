package lottogame.fixture

import lottogame.model.Lotto
import lottogame.model.LottoResult

fun createSuccessLottoResult(vararg numbers: Int = intArrayOf(1, 2, 3, 4, 5, 6)): LottoResult {
    return LottoResult.Success(Lotto(*numbers))
}

fun createLotto(vararg numbers: Int = intArrayOf(1, 2, 3, 4, 5, 6)): Lotto {
    return Lotto(*numbers)
}
