package lottotest.domain.model

import lotto.domain.model.LottoTicket
import lotto.domain.model.ManualLottoTicket
import lotto.domain.model.Rank
import lotto.domain.model.WinTicketInfo
import lotto.domain.valueobject.LottoNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class WinTicketInfoTest {
    @ParameterizedTest
    @MethodSource("duplicateWinBonusLottoNumbers")
    fun `당첨 티켓의 로또번호와 보너스 로또번호는 서로 중복되면 인스턴스가 생성되지 않는다`(
        rawWinNumbers: List<Int>,
        bonusNumber: LottoNumber,
    ) {
        // given
        val winLottoTicket: LottoTicket = ManualLottoTicket(rawWinNumbers.map { LottoNumber(it) })

        // when then
        assertThrows<IllegalArgumentException> {
            WinTicketInfo(winLottoTicket, bonusNumber)
        }
    }

    @ParameterizedTest
    @MethodSource("normalWinBonusLottoNumbers")
    fun `인스턴스가 생성되면 당첨 티켓의 로또번호와 보너스 로또번호를 보유한다`(
        rawWinNumbers: List<Int>,
        bonusNumber: LottoNumber,
    ) {
        // given
        val winLottoTicket: LottoTicket = ManualLottoTicket(rawWinNumbers.map { LottoNumber(it) })

        // when then
        val winTicketInfo = WinTicketInfo(winLottoTicket, bonusNumber)
        assertThat(winTicketInfo.winLottoTicket).isEqualTo(winLottoTicket)
        assertThat(winTicketInfo.bonusNumber).isEqualTo(bonusNumber)
    }

    @ParameterizedTest
    @MethodSource("normalWinBonusLottoNumbersWithRank")
    fun `생성된 당첨 인스턴스에게 외부의 구매 티켓을 하나 전달하면 등수 카테고리를 반환한다`(
        rawWinNumbers: List<Int>,
        bonusNumber: LottoNumber,
        rawBuyNumbers: List<Int>,
        expectedRank: Rank,
    ) {
        // given
        val winLottoTicket: LottoTicket = ManualLottoTicket(rawWinNumbers.map { LottoNumber(it) })
        val boughtLottoTicket: LottoTicket = ManualLottoTicket(rawBuyNumbers.map { LottoNumber(it) })

        // when then
        val winTicketInfo = WinTicketInfo(winLottoTicket, bonusNumber)
        assertThat(winTicketInfo.calculateLottoRank(boughtLottoTicket)).isEqualTo(expectedRank)
    }

    companion object {
        @JvmStatic
        fun duplicateWinBonusLottoNumbers() =
            Stream.of(
                Arguments.of(listOf(9, 8, 7, 6, 5, 4), LottoNumber(4)),
                Arguments.of(listOf(9, 22, 36, 34, 25, 18), LottoNumber(22)),
            )

        @JvmStatic
        fun normalWinBonusLottoNumbers() =
            Stream.of(
                Arguments.of(listOf(9, 8, 7, 6, 5, 4), LottoNumber(45)),
                Arguments.of(listOf(9, 22, 36, 34, 25, 18), LottoNumber(45)),
            )

        @JvmStatic
        fun normalWinBonusLottoNumbersWithRank() =
            Stream.of(
                Arguments.of(listOf(1, 2, 3, 4, 5, 6), LottoNumber(45), listOf(1, 2, 3, 4, 5, 6), Rank.FIRST),
                Arguments.of(listOf(9, 8, 7, 6, 5, 4), LottoNumber(45), listOf(22, 23, 24, 25, 26, 27), Rank.MISS),
            )
    }
}
