package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class LottoTicketsTest {
    @Test
    fun `로또 티켓은 로또 번호들을 가지고 있다`() {
        val lottoTickets = LottoTickets(Lotto(1, 2, 3, 4, 5, 6), Lotto(2, 3, 4, 5, 6, 7))
        assertAll({
            assertThat(lottoTickets[0]).isEqualTo(Lotto(1, 2, 3, 4, 5, 6))
            assertThat(lottoTickets[1]).isEqualTo(Lotto(2, 3, 4, 5, 6, 7))
        })
    }

    @Test
    fun `로또 번호들을 순회하여 새로운 리스트를 만들 수 있다`() {
        val lottoTickets = LottoTickets(Lotto(1, 2, 3, 4, 5, 6), Lotto(2, 3, 4, 5, 6, 7))
        val lottos: List<Lotto> = lottoTickets.map { it }
        assertAll({
            assertThat(lottos[0]).isEqualTo(Lotto(1, 2, 3, 4, 5, 6))
            assertThat(lottos[1]).isEqualTo(Lotto(2, 3, 4, 5, 6, 7))
        })
    }

    @Test
    fun `로또 번호들을 순회할 수 있다`() {
        val lottoTickets = LottoTickets(Lotto(1, 2, 3, 4, 5, 6), Lotto(2, 3, 4, 5, 6, 7))
        val lottos: MutableList<Lotto> = mutableListOf()
        lottoTickets.forEach { lottos.add(it) }
        assertAll({
            assertThat(lottos[0]).isEqualTo(Lotto(1, 2, 3, 4, 5, 6))
            assertThat(lottos[1]).isEqualTo(Lotto(2, 3, 4, 5, 6, 7))
        })
    }

    @Test
    fun `로또 티켓의 개수를 반환할 수 있다`() {
        val lottoTickets = LottoTickets(Lotto(1, 2, 3, 4, 5, 6), Lotto(2, 3, 4, 5, 6, 7))
        assertThat(lottoTickets.size).isEqualTo(2)
    }

    @Test
    fun `로또 티켓을 시퀀스로 반환할 수 있다`() {
        val lottoTickets = LottoTickets(Lotto(1, 2, 3, 4, 5, 6), Lotto(2, 3, 4, 5, 6, 7))
        val lottos: List<Lotto> = lottoTickets.asSequence().toList()
        assertAll({
            assertThat(lottos[0]).isEqualTo(Lotto(1, 2, 3, 4, 5, 6))
            assertThat(lottos[1]).isEqualTo(Lotto(2, 3, 4, 5, 6, 7))
        })
    }
}
