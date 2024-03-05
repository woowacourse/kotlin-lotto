package lotto.model

fun makeTestTicket(vararg numbers: Int): LottoTicket {
    val result = makeTestTicketResult(*numbers) as LottoTicketResult.Success
    return result.lottoTicket
}

fun makeTestTicketResult(vararg numbers: Int): LottoTicketResult =
    LottoTicket.fromListToResult(
        numbers.map { LottoNumber.getOrNull(it) },
    )

val winningTicket = makeTestTicket(1, 2, 3, 4, 5, 6)
val bonusNumber = LottoNumber.getOrNull(7)!!
val lottoWinning = LottoWinning(winningTicket, bonusNumber)
