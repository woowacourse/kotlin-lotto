package lottotest.domain.model.purchaseInfo.ticket

import lotto.domain.model.purchaseInfo.LottoNumber
import lotto.domain.model.purchaseInfo.ticket.LottoTicket
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class LottoTicketTest {
    @ParameterizedTest
    @MethodSource("notSortedLottoNumbers")
    fun `로또 티켓은 정렬된 로또 번호 컬렉션을 제공한다`(
        numbers: List<Int>,
        sortedNumbers: List<Int>,
    ) {
        // given
        val exampleLottoNumbers: Set<LottoNumber> = numbers.map { LottoNumber(it) }.toSet()
        val actualLottoTicket =
            object : LottoTicket {
                override val lottoNumbers: Set<LottoNumber> = exampleLottoNumbers
            }
        val expectedSortedLottoNumbers = sortedNumbers.map { LottoNumber(it) }

        // when then
        assertThat(actualLottoTicket.getSortedLottoNumbers()).isEqualTo(expectedSortedLottoNumbers)
    }

    companion object {
        @JvmStatic
        fun notSortedLottoNumbers() =
            Stream.of(
                Arguments.of(listOf(9, 8, 7, 6, 5, 4), listOf(4, 5, 6, 7, 8, 9)),
                Arguments.of(listOf(9, 22, 36, 34, 25, 18), listOf(9, 18, 22, 25, 34, 36)),
            )
    }
}
