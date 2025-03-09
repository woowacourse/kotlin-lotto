package lottotest.domain.model.winning

import lotto.domain.model.lottoticket.LottoTicket
import lotto.domain.model.lottoticket.ManualLottoTicket
import lotto.domain.model.winning.Rank
import lotto.domain.model.winning.WinTicket
import lotto.domain.valueobject.LottoNumber
import lotto.domain.valueobject.WinningQuantity
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class WinTicketTest {
    @ParameterizedTest
    @MethodSource("duplicateWinBonusLottoNumbers")
    fun `당첨 티켓의 로또번호와 보너스 로또번호는 중복될 수 없다`(
        rawWinNumbers: List<Int>,
        rawBonusNumber: Int,
    ) {
        // given
        val winLottoTicket: LottoTicket = ManualLottoTicket(rawWinNumbers.map { LottoNumber(it) })

        // when then
        assertThrows<IllegalArgumentException> {
            WinTicket(winLottoTicket, LottoNumber(rawBonusNumber))
        }
    }

    @ParameterizedTest
    @MethodSource("normalWinBonusLottoNumbers")
    fun `당첨 티켓은 로또번호와 보너스 로또번호를 보유한다`(
        rawWinNumbers: List<Int>,
        rawBonusNumber: Int,
    ) {
        // given
        val winLottoTicket: LottoTicket = ManualLottoTicket(rawWinNumbers.map { LottoNumber(it) })

        // when then
        val winTicket = WinTicket(winLottoTicket, LottoNumber(rawBonusNumber))
        assertThat(winTicket.winLottoTicket).isEqualTo(winLottoTicket)
        assertThat(winTicket.bonusNumber).isEqualTo(LottoNumber(rawBonusNumber))
    }

    @ParameterizedTest
    @MethodSource("winNumber1to6")
    fun `당첨 티켓에게 구매한 로또들의 정보가 주어지면 당첨 통계를 알려준다`(
        rawWinNumbers: List<Int>,
        rawBonusNumber: Int,
    ) {
        // given
        val winLottoTicket: LottoTicket = ManualLottoTicket(rawWinNumbers.map { LottoNumber(it) })
        val winTicket = WinTicket(winLottoTicket, LottoNumber(rawBonusNumber))

        val boughtTicket1to6 = ManualLottoTicket((1..6).map { LottoNumber(it) })
        val boughtTicket2to7 = ManualLottoTicket((2..7).map { LottoNumber(it) })
        val boughtTickets: List<LottoTicket> = listOf(boughtTicket1to6, boughtTicket2to7)

        // when
        val winRankCounts: Map<Rank, WinningQuantity> = winTicket.calculateWinningStatistics(boughtTickets)
        val expectRankCounts: Map<Rank, WinningQuantity> =
            mapOf(Rank.FIRST to WinningQuantity(1), Rank.THIRD to WinningQuantity(1))

        // then
        assertThat(winRankCounts).isEqualTo(expectRankCounts)
    }

    companion object {
        @JvmStatic
        fun duplicateWinBonusLottoNumbers() =
            Stream.of(
                Arguments.of(listOf(9, 8, 7, 6, 5, 4), 4),
                Arguments.of(listOf(9, 22, 36, 34, 25, 18), 22),
            )

        @JvmStatic
        fun normalWinBonusLottoNumbers() =
            Stream.of(
                Arguments.of(listOf(9, 8, 7, 6, 5, 4), 45),
                Arguments.of(listOf(9, 22, 36, 34, 25, 18), 45),
            )

        @JvmStatic
        fun winNumber1to6() =
            Stream.of(
                Arguments.of((1..6).toList(), 45),
            )
    }
}
