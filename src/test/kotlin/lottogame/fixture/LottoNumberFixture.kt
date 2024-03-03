package lottogame.fixture

import lottogame.model.GeneralLottoNumber
import lottogame.model.LottoNumber

fun createLottoNumbers(vararg numbers: Int = intArrayOf(1, 2, 3, 4, 5, 6)): List<LottoNumber> = numbers.map { GeneralLottoNumber(it) }
