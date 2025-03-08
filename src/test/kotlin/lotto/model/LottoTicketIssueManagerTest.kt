package lotto.model

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTicketIssueManagerTest {
    @Test
    fun `수동 구매 개수와 수동 번호 입력 개수가 일치하지 않으면 에러를 발생한다`() {
        val manualLottoCount = 0
        val manualLottoNumbers = listOf(listOf(1, 2, 3, 4, 5, 6))
        val totalCount = 1

        assertThrows<IllegalArgumentException> {
            LottoTicketIssueManager(
                totalCount,
                manualLottoCount,
                manualLottoNumbers
            )
        }
    }

    @Test
    fun `수동 구매가 총 구매 개수를 초과하면 에러가 난다`() {
        val manualLottoCount = 2
        val manualLottoNumbers = listOf(listOf(1, 2, 3, 4, 5, 6))
        val totalCount = 1

        assertThrows<IllegalArgumentException> {
            LottoTicketIssueManager(
                totalCount,
                manualLottoCount,
                manualLottoNumbers
            )
        }
    }

    @Test
    fun `자동 로또 구매 개수를 반환한다`() {
        val manualLottoCount = 1
        val manualLottoNumbers = listOf(listOf(1, 2, 3, 4, 5, 6))
        val totalCount = 2

        val actual = LottoTicketIssueManager(totalCount, manualLottoCount, manualLottoNumbers).getAutoLottoTicketCount()

        val expected = 1

        Assertions.assertThat(actual).isEqualTo(expected)
    }
}
