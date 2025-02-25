package domain.fixture

import domain.model.LottoNumber
import domain.model.WinningLotto

fun createWinningLotto(
    vararg numbers: Int = intArrayOf(1, 2, 3, 4, 5, 6),
    bonus: Int,
): WinningLotto {
    return WinningLotto(createLotto(*numbers), LottoNumber(bonus))
}