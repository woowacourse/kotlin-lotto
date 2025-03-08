package lotto.model

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class LottoTicketTest {

    @Test
    fun `로또 티켓의 숫자가 6개가 아니면, 에러를 반환한다`() {
        org.junit.jupiter.api.assertThrows<IllegalArgumentException> {
            LottoTicket.create(LottoIssueType.MANUAL, 1, 2, 3, 4, 5)
        }
    }

    @Test
    fun `로또 티켓의 번호에 번호가 포함되어있으면 true를 반환한다`() {
        val lottoTicket = LottoTicket.create(LottoIssueType.MANUAL, 1, 2, 3, 4, 5, 6)

        val actual = lottoTicket.containsNumber(LottoNumber(1))

        val expected = true

        Assertions.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `로또 티켓의 번호에 번호가 포함되어있지 않으면 false를 반환한다`() {
        val lottoTicket = LottoTicket.create(LottoIssueType.MANUAL, 1, 2, 3, 4, 5, 6)

        val actual = lottoTicket.containsNumber(LottoNumber(7))

        val expected = false

        Assertions.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `로또 티켓의 번호를 반환한다`() {
        val lottoTicket = LottoTicket.create(LottoIssueType.MANUAL, 1, 2, 3, 4, 5, 6)

        val actual = lottoTicket.getNumbers()

        val expected =
            setOf(LottoNumber(1), LottoNumber(2), LottoNumber(3), LottoNumber(4), LottoNumber(5), LottoNumber(6))

        Assertions.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `로또 번호가 주어지면 일치하는 번호의 개수를 반환한다`() {
        val lottoTicket1 = LottoTicket.create(LottoIssueType.MANUAL, 1, 2, 3, 4, 5, 6)
        val lottoTicket2 = LottoTicket.create(LottoIssueType.MANUAL, 1, 2, 3, 4, 5, 6)
        val actual = lottoTicket1.matchNumbersSize(lottoTicket2)

        val expected = 6

        Assertions.assertThat(actual).isEqualTo(expected)
    }
}