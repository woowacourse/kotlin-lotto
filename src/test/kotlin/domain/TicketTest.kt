package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TicketTest {
    @Test
    fun `각 당첨 등수 별 개수를 구한 Map을 반환하는 기능 검증`() {
        val lottos = listOf(
            Lotto(1, 2, 3, 4, 5, 6), // 1등
            Lotto(1, 2, 3, 4, 5, 7), // 2등
            Lotto(1, 2, 3, 11, 22, 33), // 5등
            Lotto(1, 2, 3, 11, 33, 44), // 5등
            Lotto(11, 12, 13, 14, 15, 16) // miss
        )
        val winningLotto = WinningLotto(Lotto(1, 2, 3, 4, 5, 6), LottoNumber.from(7))
        val ticket = Ticket(lottos)
        val result = ticket.matchTicketCount(winningLotto)
        val expected = mutableMapOf<Rank, Int>()
        expected[Rank.FIRST] = 1
        expected[Rank.SECOND] = 1
        expected[Rank.FIFTH] = 2
        expected[Rank.MISS] = 1
        assertThat(result).isEqualTo(expected)
    }
}
