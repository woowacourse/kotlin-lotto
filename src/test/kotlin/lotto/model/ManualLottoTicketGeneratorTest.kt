package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class ManualLottoTicketGeneratorTest {

    @Test
    fun `수동로또의 타입은 수동이다`() {
        val numbers = listOf(1, 2, 3, 4, 5, 6)
        val manualLotto = ManualLottoTicketGenerator(numbers)

        val actual = manualLotto.type

        val expected = LottoIssueType.MANUAL

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `수동로또 생성기는 수동 로또를 반환한다`() {
        val numbers = listOf(1, 2, 3, 4, 5, 6)

        val actual = ManualLottoTicketGenerator(numbers).generateLottoTicket()

        val expected = LottoTicket.create(LottoIssueType.MANUAL, 1, 2, 3, 4, 5, 6)

        assertThat(actual).isEqualTo(expected)
    }
}