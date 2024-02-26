package lotto.model

import lotto.constants.GameConstant
import model.Lotto
import model.LottoNumber

class WinningNumbersGenerator() {
    fun generateWinningNumbers(input: String): Lotto =
        Lotto(
            input.split(
                GameConstant.SPLIT_DELIMITER,
            ).map {
                LottoNumber(it.toInt())
            }
                .toSet(),
        )
}
