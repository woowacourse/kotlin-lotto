package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoTicketTest {
    @Test
    fun `로또 번호가 올바른 개수가 아닐 때 IsInvalidSize 에러를 리턴한다`() {
        val actual = makeTestTicketResult(1, 2, 3, 4, 5, 6, 7)
        val expected = LottoTicketResult.IsInvalidSizeOrDuplicated()
        assertThat(actual).hasSameClassAs(expected)
    }

    @Test
    fun `로또 번호가 중복될 때 IsInvalidSize 에러를 리턴한다`() {
        val actual = makeTestTicketResult(1, 2, 3, 4, 5, 5)
        val expected = LottoTicketResult.IsInvalidSizeOrDuplicated()
        assertThat(actual).hasSameClassAs(expected)
    }
}
