package domain.model.lotto

import domain.model.LottoResult
import domain.model.WinningNumbers

data class Lotto private constructor(val numbers: Set<LottoNumber>) {

    fun getLottoResult(winningNumbers: WinningNumbers): LottoResult =
        winningNumbers.findLottoResult(numbers)

    companion object {
        fun create(lottoNumbers: LottoTicket): Lotto =
            Lotto(
                lottoNumbers.map { number ->
                    LottoNumber.from(number)
                }.toSet()
            )
    }
}
