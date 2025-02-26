package domain.fixture

import domain.model.WinningLotto
import domain.model.number.LottoNumber

fun createWinningLotto(
    vararg numbers: Int = intArrayOf(1, 2, 3, 4, 5, 6),
    bonus: Int,
): WinningLotto {
    return WinningLotto(createLotto(*numbers), LottoNumber(bonus))
}
